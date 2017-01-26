package adobd;
import java.io.File;

import com.codoid.products.fillo.*;

public class AbdMain {

	static File nfile=null;
	public static String StringerrorReturnFlag="No";
	public static Fillo fillo=null;
	static Connection filloConnection=null;
	static Recordset recordset=null;
	

	public static void main(String[] args) {

		//String FileLocation="";
		//String FileName="";
		//String Select_Column_Name="App_Component_Description";
		String Select_Column_Name="Msisdn";
		String sqlQuery="select Msisdn from sample where transactionId='TID0000000650'";
		//String sqlQuery="select App_Component_Description from ADD_USER_WITH_PAYMENT_010 where Execution_Order='Open_Command_Prompt'";
		//SELECT Input_XML FROM XML_Template Where Test_Case_Name ='ADD_USER_WITH_PAYMENT_010'
		
		//Param.getProperty("SupportFiles_Location"),Param.getProperty("XML_Template_File_Name"),"Input_XML",XLDB_Input_Query)

		
		String FilePath="C:\\Users\\laks3339\\Desktop\\sample.xls";
		//String FilePath="C:\\Users\\laks3339\\Desktop\\ADD_USER_WITH_PAYMENT_010.xls";

		String record=null;
		try {
			fillo=new Fillo();
			filloConnection=fillo.getConnection(FilePath);
			recordset=filloConnection.executeQuery(sqlQuery);
			while(recordset.next()){
				record=recordset.getField(Select_Column_Name);

			}

		} catch (Exception e) {
			System.out.println("FilloException::"+e.getMessage());
			//Report_Functions.ReportEventFailure(doc,  "RetrieveValueFromTestDataBasedOnQuery","Error in Fetching data from Excel sheet:"+e.getMessage(), false);
			e.printStackTrace();;

		}

		filloConnection.close();
		System.out.println(record);


	}

}
