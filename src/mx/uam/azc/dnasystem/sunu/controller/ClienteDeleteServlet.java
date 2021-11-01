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
 * Servlet implementation class ClienteUpdateServlet
 */

/**
 * @author vekz
 *
 * Equipo DNA System
 */
@WebServlet(name = "ClienteDelete", urlPatterns = { "/ClienteDelete" })
public class ClienteDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	log("Actualizando Informacion de los Clientes");
    	try {
			deleteCliente(request, response);
			log("Borrando Informaci?n del Cliente");
		}catch (Exception e) {
			log("No se puede borrar el Cliente.");
		}

        response.sendRedirect("cliente_search.jsp");

    }

    
    
private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException{
	    
	    
	    int key = Integer.valueOf(request.getParameter("LLave")).intValue();

		
		Connection connection = getConnection();
		try {
			deleteCliente(connection, key);
		}finally {
			connection.close();
		}
		
	}

	private void deleteCliente(Connection connection, int key) throws SQLException {
		
		PreparedStatement statementVenta = connection.prepareStatement("DELETE FROM venta WHERE id_cliente = ?;");
		PreparedStatement statement = connection.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?;");
		try {
			statementVenta.setInt(1,key);
			statement.setInt(1, key);
			statementVenta.executeUpdate();
			statement.executeUpdate();
		}finally {
			statement.close();
		}
	}

	private Connection getConnection() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/TestDS");
		Connection connection = source.getConnection();
		return connection;
	}


}