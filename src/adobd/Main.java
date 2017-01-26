package adobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {

	
  public static Connection getConnection() throws Exception {
	  
	  String filename="C:\\Users\\laks3339\\Desktop\\sample.xls";
    String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
   // String url = "jdbc:odbc:excelDB";
    String url = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + filename;
   // con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + filename);
    //String username = "yourName";
    //String password = "yourPass";
    Class.forName(driver);
    //return DriverManager.getConnection(url, username, password);
    return DriverManager.getConnection(url);
  }

  public static void main(String args[]) throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    conn = getConnection();
    stmt = conn.createStatement();
    String excelQuery = "select * from [sample$]";
    rs = stmt.executeQuery(excelQuery);

    while (rs.next()) {
      System.out.println(rs.getString("Msisdn") + " " + rs.getString("topupAmount"));
    }
    
    rs.close();
    stmt.close();
    conn.close();
  }
}