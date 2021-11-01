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

import mx.uam.azc.dnasystem.sunu.data.ProductoDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class ProductoUpdateServlet
 */

/**
 * @author vekz
 *
 * Equipo DNA System
 */
@WebServlet(name = "ProductoCompra", urlPatterns = { "/ProductoCompra" })
public class ProductoCompraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductoCompraServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log("Actualizando Informacion del Producto");
        try {
            updateProducto(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
                  
        String base = request.getContextPath();
        response.sendRedirect(base + "/cliente_compra_exitosa.jsp");

    }

    private void updateProducto(HttpServletRequest request, HttpServletResponse response)
            throws NamingException, SQLException {
        ProductoDTO producto = getProducto( request );
        
        // Obtener conexion
        Context context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        Connection connection = source.getConnection();

        try {
            // Ejecutar peticion
          updateProducto(connection, producto, request);
        } finally {
            connection.close();
        }
    }

    /**
     * @param request
     * @return
     */
    private ProductoDTO getProducto( HttpServletRequest request )
    {
      // leer parametros de forma
      String idProducto = request.getParameter("idProducto");
      String nombre = request.getParameter("nombre");
      String precio = request.getParameter("precio");
      String cantidad = request.getParameter("cantidad");
      
      // Crear DTO
      ProductoDTO producto = new ProductoDTO();
      producto.setId( idProducto );
      producto.setNombre( nombre );
      producto.setPrecio( Integer.valueOf( precio ));
      producto.setCantidad( Integer.valueOf( cantidad ) );
      return producto;
    }

    private void updateProducto(Connection connection, ProductoDTO producto, HttpServletRequest request)
          throws SQLException {
      
        PreparedStatement statement = connection.prepareStatement("UPDATE "
            + "producto SET cantidad_producto=? WHERE id_producto=?;");
        
  
        try {
          int nuevaCantidad = producto.getCantidad() - 1;
          statement.setInt( 1, nuevaCantidad );
          statement.setInt( 2, Integer.parseInt( producto.getId() ) );
          statement.executeUpdate();

          
        } finally {
          statement.close();
        }
    }

}