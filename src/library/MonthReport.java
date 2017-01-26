package library;

import java.util.Calendar;

public class MonthReport {

	public static void main(String[] args) {

		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String expectedType = null;
		String Cdr_Year_Day = null;
		String Call_date_Day = null;
		String ExpectedDate = null;

			Table_name="EDR";
			Column_name="CDR_Time_Stamp";
			SQL_condition="MSISDN_No = '449944020074'";
			expectedType  ="MONTHDB";
			Cdr_Year_Day  ="01";
			Call_date_Day = "01";
			String seperator="";
			int Monthdifference=0;


			String query=null;

			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + Monthdifference;
			int year = cal.get(Calendar.YEAR);

			String expmonth = Integer.toString(month);

			if (month % 10 != 0) {
				expmonth = "0" + expmonth;
			}

			if(expectedType.equalsIgnoreCase("REPORTDB")){
				ExpectedDate = year + seperator +"01" + seperator + expmonth +" "+ "00:00:00.000";
			}

			if(expectedType.equalsIgnoreCase("MONTHDB")){
				ExpectedDate = year + seperator + expmonth + seperator + "01" +"000000";
			}

			System.out.println("ExpectedDate is : "+ExpectedDate);

			query = "update "+Table_name+" set "+ Column_name + " = '"+ ExpectedDate +"' where "+SQL_condition;

			System.out.println(query);
		}

	}
