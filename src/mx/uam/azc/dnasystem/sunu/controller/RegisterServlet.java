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

import mx.uam.azc.dnasystem.sunu.data.UsuarioDTO;
import mx.uam.azc.dnasystem.sunu.data.ValidarUsuario;

	/**
	* Servlet implementation class ClienteFormServlet
	*/

@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	
    public RegisterServlet() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
	    
        PrintWriter out = response.getWriter();
        
	
        String nombreUsuario = request.getParameter("nombre_usuario");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        
        String nombreCliente = request.getParameter("nombre_cliente");
        String apellidoPaternoCliente = request.getParameter("apellido_paterno_cliente");
        String apellidoMaternoCliente = request.getParameter("apellido_materno_cliente");
        String edadCliente = request.getParameter("edad_cliente");
        String sexoCliente = request.getParameter("sexo_id_sexo");

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/sunudb","vekz","Inf13rn0311530");

            PreparedStatement ps = con.prepareStatement
            ("INSERT INTO `usuario` (`nombre_usuario`, `email`, `password`, `rol`) VALUES (?, ?, ?,'rolUsuario')");
            ps.setString(1, nombreUsuario);
            ps.setString(2, email);
            
    	    ValidarUsuario validarLogin = new ValidarUsuario();
    	    password = validarLogin.getMD5Hash(password);
    	    
            ps.setString(3, password);

    	    
    	    UsuarioDTO datosLoginUsuario = new UsuarioDTO();
    	    datosLoginUsuario.setNombre_usuario(nombreUsuario);
    	    datosLoginUsuario.setPassword(password);
            
            int i = ps.executeUpdate();
            
            
            if(i > 0) {
                out.println("Registrando Usuario... ");
                
            }
            
            Statement ps3 = con.createStatement();
            ResultSet res = ps3.executeQuery("SELECT id_usuario FROM usuario order by id_usuario desc limit 1;");
            res.next();
            i = res.getInt( 1 );
            
            PreparedStatement ps2 = con.prepareStatement
            		("INSERT INTO `cliente` (`nombre_cliente`, `apellido_paterno_cliente`, `apellido_materno_cliente`, `edad_cliente`, `sexo_id_sexo`, `usuario_id_usuario`) VALUES (?,?,?,?,?,?)");
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
	        ps2.setInt(6, i);
	        
	        int j = ps2.executeUpdate();
	        
	        if(j > 0) {
	            out.println("Cliente registrado ");
	            String base = request.getContextPath();
	            response.sendRedirect(base + "/cliente_register_done.jsp");
	        }
        }
        catch(Exception se) {
            se.printStackTrace();
            String base = request.getContextPath();
            response.sendRedirect(base + "/cliente_register_fail.jsp");
        }
        
        
    }

}
