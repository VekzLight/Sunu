package mx.uam.azc.dnasystem.sunu.controller;

/**
*************************************************
* DNA System                                    *
* Por imposible que parezca ¡Tiene Solución!    *
*                                               *
* José Enrique García Ramírez        2163033941 *
* Tania Guadalupe Zárate Chávez      2173075371 *
* Christopher Yael Meneses Martínez  2152001568 *
* Hurtado Avilés Gabriel             2172000781 *
*                                               *
* Taller de desarrollo de aplicaciónes web      *
* Hugo Pablo Leyva                              *
* 13/Agosto/2021                                *
*************************************************
*/

import mx.uam.azc.dnasystem.sunu.data.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

	/**
	* Servlet implementation class ClienteFormServlet
	*/

@WebServlet(name = "ClienteUpdateForm", urlPatterns = { "/ClienteUpdateForm" })

public class ClienteUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	
    public ClienteUpdateFormServlet() {
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
          ArrayList<ClienteDTO> clientes = getClientes();
            request.setAttribute("clientes", clientes);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(""
            + "/cliente_search.jsp");
        
        dispatcher.forward(request, response);
    }

    private ArrayList<ClienteDTO> getClientes() throws NamingException, SQLException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getClientes(connection);
        } finally {
            connection.close();
        }
    }

    private ArrayList<ClienteDTO> getClientes(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        
        String query = ""
            + "SELECT "
            + " c.id_cliente, "
            + " c.nombre_cliente,"
            + " c.apellido_paterno_cliente, "
            + " c.apellido_materno_cliente, "
            + " c.edad_cliente, "
            + " s.sexo_cliente, "
            + " u.id_usuario, "
            + " u.nombre_usuario, "
            + " u.email "
            + "FROM cliente c, sexo s, usuario u "
            + "WHERE c.sexo_id_sexo=s.id_sexo "
            + "  AND u.id_usuario=c.usuario_id_usuario;";
        
        ResultSet cursor =  statement.executeQuery(query);
        
        try {
            while(cursor.next()) {
              ClienteDTO cliente = new ClienteDTO();
              UsuarioDTO usuario = new UsuarioDTO();
              
              cliente.setId(cursor.getInt(1));
              cliente.setNombre(cursor.getString(2));
              cliente.setPaterno(cursor.getString(3));
              cliente.setMaterno(cursor.getString(4));
              cliente.setEdad(cursor.getInt(5));
              cliente.setSexo(cursor.getString(6));
              
              usuario.setId_usuario( cursor.getInt( 7 ) );
              usuario.setNombre_usuario( cursor.getString( 8 ) );
              usuario.setEmail( cursor.getString( 9 ));
                  
              cliente.setUsuario( usuario );
              clientes.add( cliente );
            }
            
            return clientes;
        } finally {
            cursor.close();
        }
    }
}
