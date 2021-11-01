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

import mx.uam.azc.dnasystem.sunu.data.ClienteDTO;
import mx.uam.azc.dnasystem.sunu.data.UsuarioDTO;

import com.lowagie.text.*;
import com.lowagie.text.html.HtmlWriter;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vekz
 * DNA System
 *
 */
@WebServlet(name = "ClienteFormAllHtml", urlPatterns = { "/ClienteFormAllHtml" })
public class ClienteFormAllHtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            List<ClienteDTO> clientes = getCliente(response);
            request.setAttribute("clientes", clientes);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente_search.jsp");
        //dispatcher.forward(request, response);
    }

    private List<ClienteDTO> getCliente(HttpServletResponse response) throws NamingException, SQLException, IOException, DocumentException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getCliente(connection, response);
        } finally {
            connection.close();
        }
    }

    private List<ClienteDTO> getCliente(Connection connection, HttpServletResponse response) throws SQLException, IOException, DocumentException{
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
            + "WHERE c.sexo_id_sexo=s.id_sexo "
            + "  AND u.id_usuario=c.usuario_id_usuario;";
        
        ResultSet cursor =  statement.executeQuery(query);
        
        try {
            List<ClienteDTO> clientes =  new ArrayList<ClienteDTO>();
            
            while(cursor.next()) {
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
              
              clientes.add( cliente );
            }
            
            documentShow(clientes, response);
            System.out.println("Documento creado");
            
            return clientes;
        } finally {
            cursor.close();
        }
    }

    private void documentShow( List<ClienteDTO> clientes,
        HttpServletResponse response ) 
    {
      try {
        response.setContentType("application/html");
        response.addHeader( "Content-Disposition", "attachment;filename=ReporteClientes.html" );
        OutputStream fos = response.getOutputStream();
        Document document = new Document( PageSize.LETTER.rotate() );
        HtmlWriter writer = HtmlWriter.getInstance( document, fos );
        document.addTitle( "Detalles del cliente" );
        document.addAuthor( "DNA System" );
        document.addCreationDate();
        document.addSubject( "Clientes en HTML" );
        document.addCreator( "iText" );
        document.open();
        
        Font font = FontFactory.getFont( FontFactory.COURIER, 18, Font.BOLD, new Color(0,0,128));
        Phrase phrase = new Phrase("\n\nEmpesa\n", font);
        Table tabla = new Table(8);
    
        tabla.setBorderWidth( 3 );
        tabla.setBorderColor( new Color(0,0,255) );
        tabla.setBackgroundColor( new Color(226, 222, 222) );
        tabla.setPadding( 5 );
        tabla.setSpacing( 5 );
        
        Cell celda = new Cell("Clientes");
        celda.setHeader( true );
        celda.setColspan( 8 );
        celda.setBorderColor( new Color(0, 198, 0) );
        tabla.addCell( celda );
        document.add( tabla );
        
        document.add( new Phrase("-") );
        
        for(ClienteDTO cliente: clientes) {
          tabla = new Table(8);
          tabla.setBorderWidth( 3 );
          tabla.setBorderColor( new Color(0, 0, 255) );
          tabla.setBackgroundColor( new Color(226, 222, 222) );
          tabla.setPadding( 5 );
          tabla.setSpacing( 5 );
          
          font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD, new Color(64, 64, 255));
          phrase = new Phrase("Id_Cliente", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Nombre", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Paterno", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Materno", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Edad", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Sexo", font);
          tabla.addCell( phrase );
          document.add( tabla );
          
          tabla = new Table(8);
          font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD, new Color(0, 128, 0));
          phrase = new Phrase(cliente.getId()+"", font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getNombre(), font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getPaterno(), font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getMaterno(), font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getEdad()+"", font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getSexo(), font);
          tabla.addCell( phrase );
          document.add( tabla );
          
          
          
          tabla = new Table(8);
          tabla.setBorderWidth( 3 );
          tabla.setBorderColor( new Color(0, 0, 255) );
          tabla.setBackgroundColor( new Color(226, 222, 222) );
          tabla.setPadding( 5 );
          tabla.setSpacing( 5 );
          
          font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD, new Color(64, 64, 255));
          phrase = new Phrase("Id_Usuarui", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Usuario", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Email", font);
          tabla.addCell( phrase );
          phrase = new Phrase("Materno", font);
          tabla.addCell( phrase );
          document.add( tabla );
          
          tabla = new Table(8);
          font = FontFactory.getFont( FontFactory.COURIER, 8, Font.BOLD, new Color(0, 128, 0));
        
          phrase = new Phrase(cliente.getUsuario().getId_usuario() + "", font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getUsuario().getNombre_usuario(), font);
          tabla.addCell( phrase );
          phrase = new Phrase(cliente.getUsuario().getEmail(), font);
          tabla.addCell( phrase );
          document.add( tabla );
          document.add( new Phrase("-") );
        }
        
        
        fos.flush();
        document.close();
      } catch ( BadElementException e ) {
        e.printStackTrace();
      } catch ( DocumentException e ) {
        e.printStackTrace();
      } catch(IOException ex) {
        ex.printStackTrace();
      }
    }


    

}
