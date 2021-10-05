package mx.uam.azc.dnasystem.sunu.controller;

import java.awt.Color;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import net.sf.jxls.transformer.XLSTransformer;

import mx.uam.azc.dnasystem.sunu.data.ClienteDTO;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

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
@WebServlet(name = "ClienteFormPdf", urlPatterns = { "/ClienteFormPdf" })
public class ClienteFormPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int key = Integer.valueOf(request.getParameter("LLave")).intValue();
        try {
            List<ClienteDTO> clientes = getCliente(key, response);
            request.setAttribute("clientes", clientes);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(""
            + "/cliente_search.jsp");
        
        //dispatcher.forward(request, response);
    }

    private List<ClienteDTO> getCliente(int key, HttpServletResponse response) throws NamingException, SQLException, IOException, DocumentException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getCliente(connection, key, response);
        } finally {
            connection.close();
        }
    }

    private List<ClienteDTO> getCliente(Connection connection, int key, HttpServletResponse response) throws SQLException, IOException, DocumentException{
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
            
            documentShow(clientes, response, key);
            System.out.println("Documento creado");
            
            return clientes;
        } finally {
            cursor.close();
        }
    }

    private void documentShow( List<ClienteDTO> clientes,
        HttpServletResponse response, int key ) 
    {
      try {
        response.setContentType("application/pdf");
        response.addHeader( "Content-Disposition", 
            "attachment;filename=ReporteCliente"+key+".pdf" );
        OutputStream fos = response.getOutputStream();
        Document document = new Document( PageSize.LETTER.rotate() );
        PdfWriter writer = PdfWriter.getInstance( document, fos );
        document.addTitle( "Detalles del cliente" );
        document.addAuthor( "DNA System" );
        document.addCreationDate();
        document.addSubject( "Clientes en PDF" );
        document.addCreator( "iText" );
        document.open();
        
        Font font = FontFactory.getFont( FontFactory.COURIER, 
            18, Font.BOLD, new Color(0,0,128));
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
        
        tabla = new Table(8);
        tabla.setBorderWidth( 3 );
        tabla.setBorderColor( new Color(0, 0, 255) );
        tabla.setBackgroundColor( new Color(226, 222, 222) );
        tabla.setPadding( 5 );
        tabla.setSpacing( 5 );
        
        font = FontFactory.getFont( FontFactory.COURIER, 8, 
            Font.BOLD, new Color(64, 64, 255));
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
        font = FontFactory.getFont( FontFactory.COURIER, 8, 
            Font.BOLD, new Color(0, 128, 0));
        
        for(ClienteDTO cliente: clientes) {
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
        }
        document.add( tabla );
        
        fos.flush();
        document.close();
      } catch ( BadElementException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch ( DocumentException e ) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch(IOException ex) {
        ex.printStackTrace();
      }
    }


    

}
