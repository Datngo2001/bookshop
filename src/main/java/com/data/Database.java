package com.data;

import java.sql.*;
//import com.mysql.jdbc.Driver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			InitialContext ic = new InitialContext();
			Context xmlContext = (Context) ic.lookup("java:comp/env");
			DataSource datasource = (DataSource) xmlContext.lookup("jdbc/shop");
			Connection con = datasource.getConnection();
			return con;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeConnection(Connection myCon, Statement mySta, ResultSet myRes) {
		try {
			if(myCon != null) 
				myCon.close();
			if(mySta != null)
				mySta.close();
			if(myRes != null)
				myRes.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
