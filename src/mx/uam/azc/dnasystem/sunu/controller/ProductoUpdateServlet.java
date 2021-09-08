package mx.uam.azc.dnasystem.sunu.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        response.sendRedirect(base + "/productos_update_form.jsp");

    }

    private void updateProducto(HttpServletRequest request, HttpServletResponse response)
            throws NamingException, SQLException {
        // leer parametros de forma
        String idProducto = request.getParameter("id");
        String nombreProducto = request.getParameter("nombre");

        // Obtener conexion
        Context context = new InitialContext();
        DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
        Connection connection = source.getConnection();

        try {
            // Ejecutar peticion
          updateProducto(connection, idProducto, nombreProducto);
        } finally {
            connection.close();
        }
    }

    private void updateProducto(Connection connection, String idProducto, String nombreProducto)
          throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("UPDATE producto SET nombre_producto=\"" 
                + nombreProducto + "\"WHERE id_producto=\"" + idProducto + "\";");
        } finally {
            statement.close();
        }
    }

}