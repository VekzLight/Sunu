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
public class ProductoDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductoDeleteServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log("Borrando Producto");
        int idProducto = Integer.parseInt( request.getSession(false).getAttribute("idProducto").toString() );
        
        try {
            deleteProducto(idProducto);
        } catch (Exception e) {
            throw new ServletException(e);
        }
                  
        String base = request.getContextPath();
        response.sendRedirect(base + "/ProductoUpdateForm");
        
    }

    private void deleteProducto(int idProducto )
            throws NamingException, SQLException {
        // Obtener conexion
        Context context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        Connection connection = source.getConnection();

        try {
            // Ejecutar peticion
          deleteProducto(connection, idProducto);
        } finally {
            connection.close();
        }
    }

    /**
     * @param request
     * @return
     */


    private void deleteProducto(Connection connection, int idProducto)
          throws SQLException {
      
        PreparedStatement statement = connection.prepareStatement("DELETE FROM producto WHERE id_producto=?");
        try {
          statement.setInt( 1, idProducto );
          statement.executeUpdate();
        } finally {
          statement.close();
        }
    }

}