package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Modelo - Conex�o com o banco
 * @author fernando.mssantos2
 *
 */

public class DAO {
	// Par�metros de conex�o
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://10.26.49.137:3306/FsMotors";
	private String user="root";
	private String password = "123@senac";
	
	/**
	 * M�todo respons�vel pela conex�o
	 * @return con / null
	 */
	
	public Connection conectar() {
		// Objeto usado para conex�o
		Connection con = null;
		// Tratamento de exce��es
		try {
			// uso do driver
			Class.forName(driver);
			// estabelecer a conex�o
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
			
		}		
	}			
}
