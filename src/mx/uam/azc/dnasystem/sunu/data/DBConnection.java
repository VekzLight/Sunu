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

import java.sql.*;

public class DBConnection {
	 
    public static Connection createConnection() {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/sunudb";
    String username = "root";
    String password = "";
    try {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Se logro establecer conexion a la BD exitosamente - " + con);
    }
    catch (SQLException e)  {
         System.out.println("No se pudo establecer conexion con la base de datos...");
         e.printStackTrace();
       }
    return con;
    }
}