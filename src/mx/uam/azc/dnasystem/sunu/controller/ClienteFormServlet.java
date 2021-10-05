package mx.uam.azc.dnasystem.sunu.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.uam.azc.dnasystem.sunu.data.ClienteDTO;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author vekz
 * DNA System
 *
 */
@WebServlet(name = "ClienteForm", urlPatterns = { "/ClienteForm" })
public class ClienteFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int key = Integer.valueOf(request.getParameter("LLave")).intValue();
        try {
            List<ClienteDTO> clientes = getCliente(key);
            request.setAttribute("clientes", clientes);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(""
            + "/cliente_view.jsp");
        
        dispatcher.forward(request, response);
    }

    private List<ClienteDTO> getCliente(int key) throws NamingException, SQLException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getCliente(connection, key);
        } finally {
            connection.close();
        }
    }

    private List<ClienteDTO> getCliente(Connection connection, int key) throws SQLException{
        Statement statement = connection.createStatement();
        
        String query = ""
            + "SELECT "
            + " c.id_Cliente, "
            + " c.nombre_Cliente,"
            + " c.apellido_paterno_cliente, "
            + " c.apellido_materno_cliente, "
            + " c.edad_cliente, "
            + " s.sexo_cliente "
            + "FROM cliente c, sexo s "
            + "WHERE id_cliente=" + key
                + " AND c.sexo_id_sexo=s.id_sexo;";
        
        ResultSet cursor =  statement.executeQuery(query);
        
        try {
            List<ClienteDTO> clientes =  new ArrayList<ClienteDTO>();
            while(cursor.next()) {
                ClienteDTO cliente = new ClienteDTO();
                
                cliente.setId(cursor.getInt(1));
                cliente.setNombre(cursor.getString(2));
                cliente.setPaterno(cursor.getString(3));
                cliente.setMaterno(cursor.getString(4));
                cliente.setEdad(cursor.getInt(5));
                cliente.setSexo(cursor.getString(6));
                
                clientes.add(cliente);
            }
            return clientes;
        } finally {
            cursor.close();
        }
    }
    
    

}
