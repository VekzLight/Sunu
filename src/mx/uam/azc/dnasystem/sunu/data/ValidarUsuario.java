package mx.uam.azc.dnasystem.sunu.data;

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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class ValidarUsuario {

	public String validarLoginUsuario(UsuarioDTO datosUsuario) throws ClassNotFoundException, NoSuchAlgorithmException {
        boolean encontrado = false;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = (Connection) DriverManager
            .getConnection("jdbc:mysql://localhost:3306/sunudb?useSSL=false", "vekz", "Inf13rn0311530");

            PreparedStatement preparedStatement = (PreparedStatement) connection
            .prepareStatement("select * from usuario where nombre_usuario = ?")) {
            preparedStatement.setString(1, datosUsuario.getNombre_usuario());
          
            ResultSet rs = preparedStatement.executeQuery();
            encontrado = rs.next();
            
            if (encontrado==true) {
                String passwordEncriptadaMysql =  rs.getString("password");
                String passwordEncriptadaFormulario = "";
                passwordEncriptadaFormulario = getMD5Hash(datosUsuario.getPassword());
                if((passwordEncriptadaFormulario).equals(passwordEncriptadaMysql)) {
                	System.out.println("Login Exitoso");
                    String rol =  rs.getString("rol");
                    if(rol.equals("rolAdmin")) {
                    	return "rolAdmin";
                    } else if(rol.equals("rolEditor")) {
                    	return "rolEditor";
                    } else { return "rolUsuario"; }
                } else {
                	return "Contraseña invalida";
                }
            } else { return "Usuario No Registrado"; }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }
	
	public static String getMD5Hash(String entradaH) throws NoSuchAlgorithmException {
		String resultado = entradaH;
		if (entradaH != null) {
		    MessageDigest md = MessageDigest.getInstance("SHA-1");
		    md.update(entradaH.getBytes());
		    BigInteger hash = new BigInteger(1, md.digest());
		    resultado = hash.toString(16);
		    while (resultado.length() < 40) {
		        resultado = "0" + resultado;
		    }
		}
		return resultado; 
	}
	
}
