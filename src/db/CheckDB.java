package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CheckDB {


	public static int reverse(int n) {
		String input = String.valueOf(n);
		String result = "";
		for (int i = input.length() - 1; i >= 0; i--) {
			result = result + input.charAt(i);
		}
		int reversedInt = Integer.parseInt(result);
		return reversedInt;
	}

	public static void main(String[] args) {


		//String input = "CZ12.34C45";
		
		float input = 13345.5456f;
		
		String check = String.valueOf(input);
		System.out.println(check.split("\\.")[1]);
		
		
		
		
//		String result = "";

		/*System.out.println(input.indexOf(input)+1);
		for (int i =input.hashCode(); i <= input.length(); i++) {
			System.out.println(input);
			//result = result + input.charAt(i);
		}
		int reversedInt = Integer.parseInt(result);
		System.out.println(reversedInt);*/

		/*		Connection con;
		Statement stmt;

		String sqlserver="192.168.110.82\\INCHDLFSQLFUNC";
		String username="sa";
		String password="SAlycatel@123";

		String sqldbname="CBOS_GBR_AUTO";

		String dbUrl = "jdbc:sqlserver://"+ sqlserver +";DatabaseName=" + sqldbname +";";     
		System.out.println(dbUrl);

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

			con = DriverManager.getConnection(dbUrl,username,password);

			stmt = con.createStatement(); 


			String query = "Select batchcode from MSTMVNOACCOUNT where MSISDN = '449944058998'";


			ResultSet resultSet = stmt.executeQuery(query);

			resultSet.next();

			String Actual_Value = resultSet.getString(1).trim();

			System.out.println("Actual_Value "+Actual_Value);



			if(Actual_Value=="1574"){
				System.out.println("Pass");
			}else{
				System.out.println("Fail");
			}

			String str = "1234";



			stmt.close();
			con.close();





		} catch (Exception e) { 
			e.printStackTrace();
		}
		 */

	}

}
