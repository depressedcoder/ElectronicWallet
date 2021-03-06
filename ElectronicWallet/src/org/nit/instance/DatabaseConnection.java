package org.nit.instance;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection {

	private Connection con;
	private static DatabaseConnection dbc;
	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/ElectronicWallet","root", "mobinur1996");
		}
		catch(Exception ex) {
			System.out.println("Couldn't Connect the database for "+ex);
		}
	}
	public static DatabaseConnection getDatabaseConnection() {
		if(dbc == null) {
			dbc = new DatabaseConnection();
		}
		return dbc;
	}
	public Connection getConnection() {
		return con;
	}

}
