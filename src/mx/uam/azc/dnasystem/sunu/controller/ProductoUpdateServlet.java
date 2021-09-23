package mx.uam.azc.dnasystem.sunu.controller;

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
@WebServlet(name = "ProductoUpdate", urlPatterns = { "/ProductoUpdate" })
public class ProductoUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductoUpdateServlet() {
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
        response.sendRedirect(base + "/ProductoUpdateForm");

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
          updateProducto(connection, producto);
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
      String idProducto = request.getParameter("id");
      String nombreProducto = request.getParameter("nombre");

      // Crear DTO
      ProductoDTO producto = new ProductoDTO();
      producto.setId( idProducto );
      producto.setNombre( nombreProducto );
      return producto;
    }

    private void updateProducto(Connection connection, ProductoDTO producto)
          throws SQLException {
      
        PreparedStatement statement = connection.prepareStatement("UPDATE "
            + "producto SET nombre_producto=? WHERE id_producto=?;");
        try {
          statement.setString( 1, producto.getNombre() );
          statement.setString( 2, producto.getId() );
          statement.executeUpdate();
        } finally {
          statement.close();
        }
    }

}