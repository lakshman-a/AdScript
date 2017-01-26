package temp;

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

public class checkSheetnamecontainsexistt {

	public static int retrieveNoOfColsMain(HSSFWorkbook wb){
		HSSFSheet ws = null;	
		try{
			ws = wb.getSheetAt(0);
			int colCount=ws.getRow(0).getLastCellNum();			
			return colCount;
		}catch(Exception e){
			throw e;
		}finally{
			ws = null;	
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {

		/* EDIT THE FOLLOWING DETAILS RESPECTIVELY*/

		//String readfolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
		String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//If sheet is Main sheet update the 'sheetName' variable as "MAIN_SHEET" else give the corresponding sheet name.
		//String sheetName="Post_OnlineTopup";
		
		String sheetName="Post_Do_BundleTopup4";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv3";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv2"; //113,115,116
		//String sheetName="Post_Do_BundleTopup_Sucess";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv6";
		//String sheetName="Start_Script_XMLVal_SinglePair";

		/* eND */	

		File excelBatchFolder = new File(readfolderPath);
		File[] excelFiles = excelBatchFolder.listFiles();
		System.out.println("No. of SpreadSheets : "+excelFiles.length);

		for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

			String excelFile = readfolderPath+excelFiles[filePointer].getName();
			System.out.println("\nProcessing file : "+excelFiles[filePointer].getName());

			File excel = new File(excelFile);
			FileInputStream fis = new FileInputStream(excel);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet ws=null;
			int index=-1;

			if(sheetName.equalsIgnoreCase("MAIN_SHEET")){
				index = 0;	
			}else{
				index = wb.getSheetIndex(sheetName);
			}

			//If Sheet Exist
			if(index >=0 ){	

				System.out.println("Sheet '"+sheetName+"' available");


			}else{
				//System.out.println("***Corresponding Sheet '"+sheetName+"' does not Exist in the Workbook...");
			}

			//Writing the Output Excels
		}

		System.out.println("Output Write folder path is : " +writeFolderPath);
	}

	public static String cellToString(HSSFCell cell) {  
		int type;
		Object result;
		type = cell.getCellType();

		switch (type) {

		case 0: // numeric value in Excel
			result = cell.getNumericCellValue();
			break;
		case 1: // String Value in Excel 
			result = cell.getStringCellValue();
			break;
		case 3: // String Value in Excel 
			result = cell.getStringCellValue();
			break;
		default:  
			throw new RuntimeException("There is no support for this type of cell");                        
		}

		return result.toString();
	}
}
