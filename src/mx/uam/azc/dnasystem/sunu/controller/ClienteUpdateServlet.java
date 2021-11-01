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

import java.io.*;
import java.sql.*;

	/**
	* Servlet implementation class ClienteFormServlet
	*/

@WebServlet(name = "ClienteUpdate", urlPatterns = { "/ClienteUpdate" })

public class ClienteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	
    public ClienteUpdateServlet() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
	    
        PrintWriter out = response.getWriter();

        String idUsuario = request.getParameter( "idUsuario" );
        String nombreUsuario = request.getParameter("nombre_usuario");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        
        String idCliente = request.getParameter( "idCliente" );
        String nombreCliente = request.getParameter("nombre_cliente");
        String apellidoPaternoCliente = request.getParameter("apellido_paterno_cliente");
        String apellidoMaternoCliente = request.getParameter("apellido_materno_cliente");
        String edadCliente = request.getParameter("edad_cliente");
        String sexoCliente = request.getParameter("sexo_id_sexo");

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/sunudb","vekz","Inf13rn0311530");

            PreparedStatement ps = con.prepareStatement("UPDATE "
                + "usuario SET nombre_usuario=?, email=? WHERE id_usuario=?;");
            ps.setString(1, nombreUsuario);
            ps.setString(2, email);
            ps.setString(3, idUsuario);
            
            int i = ps.executeUpdate();
            
            if(i > 0) {
                out.println("Actualizando Usuario... ");
            }
            
            PreparedStatement ps2 = con.prepareStatement("UPDATE "
                + "cliente SET nombre_cliente=?, "
                + "apellido_paterno_cliente=?, "
                + "apellido_materno_cliente=?, "
                + "edad_cliente=?, "
                + "sexo_id_sexo=? "
                + "WHERE id_cliente=?;");
            ps2.setString(1, nombreCliente);
            ps2.setString(2, apellidoPaternoCliente);
            ps2.setString(3, apellidoMaternoCliente);
            ps2.setString(4, edadCliente);
            
            String verificarGenero = sexoCliente;
            if (verificarGenero.equals("Masculino")) {
                ps2.setString(5, "2");
            } else if (verificarGenero.equals("Femenino")) {
                ps2.setString(5, "1");
            } else {
                ps2.setString(5, "1");
            }
            ps2.setString(6, idCliente);
            
	        int j = ps2.executeUpdate();
	        
	        if(j > 0) {
	            out.println("Cliente Actualizado ");
	            
	            HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("Usuario", nombreUsuario);
                request.setAttribute("nombreUsuario", nombreUsuario);
	            
	            String base = request.getContextPath();
	            response.sendRedirect(base + "/cliente_update_done.jsp");
	        }
        }
        catch(Exception se) {
            se.printStackTrace();
            String base = request.getContextPath();
            response.sendRedirect(base + "/cliente_update_fail.jsp");
        }
        
        
    }

}
