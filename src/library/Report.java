package library;

import java.util.Calendar;

public class Report {

	public static void main(String[] args) {
		
		String Table_name="CdrMaster";
		String Column_name="Cdr_Year";
		String SQL_condition="Cdr_Database = 'BLU_MONTH05'";
		
		int Monthdifference=0;
	    Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + Monthdifference;
	    int year = cal.get(Calendar.YEAR);
	    
	    String expmonth = Integer.toString(month);
	
	    if (month % 10 != 0) {
	    	expmonth = "0" + expmonth;
        }
	    
	    String seperator="-";
	    
	    String ExpectedDate = year + seperator +"01" + seperator + expmonth +" "+ "00:00:00.000";
	    
	    String envvardate = "01" + "/" + expmonth + "/" + year;
	    
	    System.out.println("envvardate is : "+envvardate);
	    
		String query = "update "+Table_name+" set "+ Column_name + " = '"+ ExpectedDate +"' where "+SQL_condition;

		System.out.println(query);
		
	}
	
}
