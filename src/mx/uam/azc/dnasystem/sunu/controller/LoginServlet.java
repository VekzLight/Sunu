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

import mx.uam.azc.dnasystem.sunu.data.*;
	/**
	* Servlet implementation class ClienteFormServlet
	*/

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	
    public LoginServlet() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String nombreUsuario = request.getParameter("nombre_usuario");
	    String password = request.getParameter("password");
	 
	    UsuarioDTO datosLoginUsuario = new UsuarioDTO();
	    datosLoginUsuario.setNombre_usuario(nombreUsuario);
	    datosLoginUsuario.setPassword(password);
	 
	    ValidarUsuario validarLogin = new ValidarUsuario();
	 
	    try {
	    	String usuarioValidado = validarLogin.validarLoginUsuario(datosLoginUsuario);
	        
	        if(usuarioValidado.equals("rolAdmin")) {
	            System.out.println("Pagina de Admin");
	 
	            HttpSession session = request.getSession();
	            session.setAttribute("Admin", nombreUsuario);
	            request.setAttribute("nombreUsuario", nombreUsuario);
	 
	            String base = request.getContextPath();
                response.sendRedirect(base + "/ProductoUpdateForm");
	        }
	        else if(usuarioValidado.equals("rolEditor")) {
	            System.out.println("Pagina de Cliente");
	 
	            HttpSession session = request.getSession();
	            session.setAttribute("Editor", nombreUsuario);
	            request.setAttribute("nombreUsuario", nombreUsuario);
	 
	            request.getRequestDispatcher("cliente_view.jsp").forward(request, response);
	            //request.getRequestDispatcher("/JSP/Editor.jsp").forward(request, response);
	        }
	        else if(usuarioValidado.equals("rolUsuario")) {
	            //System.out.println("Pagina de Usuario no registrado");
	 
	            HttpSession session = request.getSession();
	            session.setMaxInactiveInterval(10*60);
	            session.setAttribute("Usuario", nombreUsuario);
	            request.setAttribute("nombreUsuario", nombreUsuario);
	            
	            
	            String base = request.getContextPath();
	            response.sendRedirect(base + "/ProductoSelectClienteForm");
	            //request.getRequestDispatcher("/ProductoSelectClienteForm").forward(request, response);
	        }
	        else {
	            System.out.println("Error message = " + usuarioValidado);
	            request.setAttribute("errMessage", usuarioValidado);
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	    }
	    catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    catch (Exception e2) {
	        e2.printStackTrace();
	    }
    }	

}
