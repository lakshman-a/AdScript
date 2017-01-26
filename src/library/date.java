package library;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class date {

	public static void main(String[] args) {

		/*SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String simpleDate = dateformat.format(date);
		System.out.println(simpleDate);


		Calendar cal = Calendar.getInstance();

		// print current date
		System.out.println("The current date is : " + cal.getTime());
		String simpleDate1 = dateformat.format(cal.getTime());
		System.out.println("The current formatted date is : " + simpleDate1);
		

		// add 20 days to the calendar
		cal.add(Calendar.DATE, 20);
		System.out.println("20 days later: " + cal.getTime());

		// subtract 2 months from the calendar
		cal.add(Calendar.MONTH, +6);
		String simpleDate2 = dateformat.format(cal.getTime());
		System.out.println("The current formatted date is : " + simpleDate2);

		// subtract 5 year from the calendar
		cal.add(Calendar.YEAR, -5);
		System.out.println("5 years ago: " + cal.getTime());
		*/
		
		
		/*int i=13;
		int j =0;
		
		j=i%12;
		System.out.println(j);
		*/
		
		
		// print current date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.YEAR,+((Integer.parseInt("2"))));
		
		String simpleDate1 = dateformat.format(cal1.getTime());
		System.out.println("The current formatted date is : " + simpleDate1);
		
		
		SimpleDateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		cal.set(Integer.parseInt(simpleDate1), 12, 31);
		cal.add(Calendar.MONTH, -1);
		
		String funtureYearDate = dateformat1.format(cal.getTime());
		
		System.out.println("Date: "+funtureYearDate);
		
		
	}

}
