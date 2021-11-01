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

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import mx.uam.azc.dnasystem.sunu.data.ValidarUsuario;

	/**
	* Servlet implementation class ClienteFormServlet
	*/

@WebServlet(name = "ClienteUpdatePwd", urlPatterns = { "/ClienteUpdatePwd" })

public class ClienteUpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	
    public ClienteUpdatePwdServlet() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
	    
        PrintWriter out = response.getWriter();

        String usuario = request.getSession().getAttribute( "Usuario" ).toString();
        String password = request.getParameter("password");
    
        ValidarUsuario validar = new ValidarUsuario();
        String passwordSHA;

        try {
            passwordSHA = validar.getMD5Hash( password );
          
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/sunudb","vekz","Inf13rn0311530");

            PreparedStatement ps = con.prepareStatement("UPDATE "
                + "usuario SET password=? WHERE nombre_usuario=?;");
            ps.setString(1, passwordSHA);
            ps.setString(2, usuario);
            
            int i = ps.executeUpdate();
            
            if(i > 0) {
                out.println("Contrasena Actualizada ");
                String base = request.getContextPath();
                response.sendRedirect(base + "/cliente_update_done.jsp");
            } else {
                String base = request.getContextPath();
                response.sendRedirect(base + "/cliente_update_fail.jsp");
            }
        }
        catch(Exception se) {
            se.printStackTrace();
            String base = request.getContextPath();
            response.sendRedirect(base + "/cliente_update_fail.jsp");
        }
        
        
    }

}
