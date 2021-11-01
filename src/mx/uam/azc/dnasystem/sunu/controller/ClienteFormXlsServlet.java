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

import java.io.*;
import java.sql.*;
import java.util.*;

import net.sf.jxls.transformer.XLSTransformer;

import mx.uam.azc.dnasystem.sunu.data.ClienteDTO;
import mx.uam.azc.dnasystem.sunu.data.UsuarioDTO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.naming.*;
import javax.servlet.*;
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
@WebServlet(name = "ClienteFormXls", urlPatterns = { "/ClienteFormXls" })
public class ClienteFormXlsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int key = Integer.valueOf(request.getParameter("llave")).intValue();
        try {
          ClienteDTO cliente = getCliente(key, response);
            request.setAttribute("clientes", cliente);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(""
            + "/cliente_search.jsp");
        
        //dispatcher.forward(request, response);
    }

    private ClienteDTO getCliente(int key, HttpServletResponse response) throws NamingException, SQLException, IOException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getCliente(connection, key, response);
        } finally {
            connection.close();
        }
    }

    private ClienteDTO getCliente(Connection connection, int key, HttpServletResponse response) throws SQLException, IOException{
      Statement statement = connection.createStatement();
      
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
          + "WHERE c.id_cliente=" + key + ""
          + "  AND c.sexo_id_sexo=s.id_sexo "
          + "  AND u.id_usuario=c.usuario_id_usuario;";
      
      ResultSet cursor =  statement.executeQuery(query);
      
      try {
          cursor.next();
          ClienteDTO cliente = new ClienteDTO();
          UsuarioDTO usuario = new UsuarioDTO();
          
          cliente.setId(cursor.getInt(1));
          cliente.setNombre(cursor.getString(2));
          cliente.setPaterno(cursor.getString(3));
          cliente.setMaterno(cursor.getString(4));
          cliente.setEdad(cursor.getInt(5));
          
          String sexo = cursor.getString(6).equalsIgnoreCase("F") ? "Femenino" : "Masculino";
          
          cliente.setSexo(sexo);
          
          usuario.setId_usuario( cursor.getInt( 7 ) );
          usuario.setNombre_usuario( cursor.getString( 8 ) );
          usuario.setEmail( cursor.getString( 9 ));
              
          cliente.setUsuario( usuario );
            
          Map<String, ClienteDTO> beans = new HashMap<>();
          beans.put( "cliente", cliente );
          xlsShow(beans, response, key);
          
          System.out.println("Documento creado");
          
          return cliente;
        } finally {
            cursor.close();
        }
    }

    private void xlsShow( Map<String, ClienteDTO> beans,
        HttpServletResponse response, int key ) throws IOException
    {
        ServletContext context = getServletContext();
        InputStream istream = context.getResourceAsStream(""
            + "/WEB-INF/templates/PlantillaCliente.xls");
        XLSTransformer transformer = new XLSTransformer();
        HSSFWorkbook workbook = transformer.transformXLS( istream, beans );
        
        response.setContentType( "application/msexcel" );
        response.addHeader( "Content-Disposition", 
            "attachment;filename=ReporteCliente"+key+".xls" );
        OutputStream os = response.getOutputStream();
        workbook.write( os );
        os.flush();
    }
    
    

}
