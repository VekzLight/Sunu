package mx.uam.azc.dnasystem.sunu.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import net.sf.jxls.transformer.XLSTransformer;

import mx.uam.azc.dnasystem.sunu.data.ClienteDTO;

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

    private List<ClienteDTO> getCliente(int key, HttpServletResponse response) throws NamingException, SQLException, IOException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getCliente(connection, key, response);
        } finally {
            connection.close();
        }
    }

    private List<ClienteDTO> getCliente(Connection connection, int key, HttpServletResponse response) throws SQLException, IOException{
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
            Map<String, ClienteDTO> beans = new HashMap<String, ClienteDTO>();
            
            while(cursor.next()) {
                ClienteDTO cliente = new ClienteDTO();
                
                cliente.setId(cursor.getInt(1));
                cliente.setNombre(cursor.getString(2));
                cliente.setPaterno(cursor.getString(3));
                cliente.setMaterno(cursor.getString(4));
                cliente.setEdad(cursor.getInt(5));
                cliente.setSexo(cursor.getString(6));
                
                clientes.add(cliente);
                beans.put( "cliente", cliente );
            }
            
            xlsShow(beans, response, key);
            System.out.println("Documento creado");
            
            return clientes;
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
