package temp2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <h1>UpdateEntireSheetType</h1>
 * To Update Cell Type of the complete given row in a sheet where input will be the WorkSheet Name
 * Input as Read Batch Folder and Output Excel to New Write Path
 * <p>
 * <b>Note: Supports Batch Type Input, New File will be generated</b> 
 * 
 * @author Lakshman A
 * @see 
 * @since Jul 26, 2016
 *
 */

public class check1 {


	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {

		/* EDIT THE FOLLOWING DETAILS RESPECTIVELY*/

		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
	

		File excelBatchFolder = new File(readfolderPath);
		File[] excelFiles = excelBatchFolder.listFiles();
		System.out.println("No. of SpreadSheets : "+excelFiles.length);

		for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

			String excelFile = readfolderPath+excelFiles[filePointer].getName();
			System.out.println("\nProcessing file : "+excelFiles[filePointer].getName());

			File excel = new File(excelFile);
			FileInputStream fis = new FileInputStream(excel);
			
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			
			/*HSSFSheet ws=null;
			int index=-1;

			if(sheetName.equalsIgnoreCase("MAIN_SHEET")){
				index = 0;	
			}else{
				index = wb.getSheetIndex(sheetName);
			}

			if(index >=0 ){
				
				if(sheetName.equalsIgnoreCase("Eshop_GBR_SQL_CloseConnection"))
				wb.setSheetName(wb.getSheetIndex(sheetName), "Eshop_Close_SQL_Connection");
				
				if(sheetName1.equalsIgnoreCase("Close_SQL_Connection_GBR"))
				wb.setSheetName(wb.getSheetIndex(sheetName1), "Close_SQL_Connection");

				System.out.println("Sheet Name Changed");
				
			}else{
				//System.out.println("***Corresponding Sheet '"+sheetName+"' does not Exist in the Workbook...");
			}*/

			//Writing the Output Excels
			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(readfolderPath+"Sample.xlsx"));
			wb.write(fout);
			fout.close();
			wb.close();
		}
		//Writing the Output Excels

		System.out.println("finished");
	}

	
}

