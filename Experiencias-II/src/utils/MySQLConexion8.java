package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion8 {
	//Metodo encargado de realizar la coneccion
	public static Connection getConexion() {
		//declarando un objeto de tipo "connection"
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//determinar los datos de coneccion
			       //driver:protocoloDriver/localhost
			String url = "jdbc:mysql://localhost:3306/experiencias2?serverTimezone=UTC";//"jdbc:mysql://localhost/ciberfarma:3306?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root"; //root
			String psw = "admin"; //contraseña
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado!!" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexión con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		} 
		return con;
	}

}
