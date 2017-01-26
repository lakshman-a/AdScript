package temp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class datime {
public static void main(String[] args) {
	
	String expectedDate=null;
	
	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	Date date = new Date();
	
	dateFormat = new SimpleDateFormat("dd");
	String checkDate=dateFormat.format(date);
	int checkDateInt=Integer.parseInt(checkDate);
	expectedDate=String.valueOf(checkDateInt);
	
	dateFormat = new SimpleDateFormat("MM");
	String checkMonth=dateFormat.format(date);
	int checkDMonthInt=Integer.parseInt(checkMonth);
	expectedDate=expectedDate+String.valueOf(checkDMonthInt);
	
	dateFormat = new SimpleDateFormat("yyyy");
	String checkYear=dateFormat.format(date);
	expectedDate=expectedDate+checkYear;
	
	System.out.println("Needed Date is : "+expectedDate);
}
}
