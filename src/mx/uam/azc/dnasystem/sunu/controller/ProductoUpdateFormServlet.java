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

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mx.uam.azc.dnasystem.sunu.data.ProductoDTO;

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
@WebServlet(name = "ProductoUpdateForm", urlPatterns = { "/ProductoUpdateForm" })
public class ProductoUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            List<ProductoDTO> productos = getProducto();
            request.setAttribute("productos", productos);
        } catch( Exception e ) { 
            throw new ServletException(e); 
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(""
            + "/productos_update_form.jsp");
        
        dispatcher.forward(request, response);
    }

    private List<ProductoDTO> getProducto() throws NamingException, SQLException{
        Context  context =  new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        
        Connection connection =  source.getConnection();
        try {
            return getProducto(connection);
        } finally {
            connection.close();
        }
    }

    private List<ProductoDTO> getProducto(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        
        ResultSet cursor =  statement.executeQuery("SELECT * FROM producto;");
        
        try {
            List<ProductoDTO> productos =  new ArrayList<ProductoDTO>();
            while(cursor.next()) {
                ProductoDTO producto = new ProductoDTO();
                
                producto.setId(cursor.getString(1));
                producto.setNombre(cursor.getString(2));
                producto.setPrecio( cursor.getInt( 4 ) );
                producto.setCantidad( cursor.getInt( 5 ) );
                
                productos.add(producto);
            }
            return productos;
        } finally {
            cursor.close();
        }
    }
    
    

}
