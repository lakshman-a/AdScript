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
 * <h1>UpdateCellWithNewValue</h1>
 * To Update a Old Cell Value in Excel with given New Value, WorkSheet Name, Column Name and a Row Value
 * Input as Read Batch Folder and Output Excel to New Write Path
 * <p>
 * <b>Note: Supports Batch Type Input, New File will be generated</b> 
 * 
 * @author Lakshman A
 * @see 
 * @since Jul 26, 2016
 *
 */

public class UpdateCellWithNewValueWithRow1 {

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

	public static void main(String[] args) throws IOException {

		/* EDIT THE FOLLOWING DETAILS RESPECTIVELY*/

		String readfolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
		String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//Search the following content in the sheet in order to get corresponding Row Number
		//Check whether cell's Old Value should be verified and then Updated or direct without verifying cell's old value
		boolean oldValueCheck=true;
		//String oldCellValue="3";
		//String oldCellValue="TOTAL_MINUTES=0;TOTAL_SMS=0;NET_MINUTES_RECEIVED=0;FREE_DATA=0;ONNET_FREE_MINUTES=0;ONNET_FREE_SMS=0;OFFNET_FREE_MINUTES=0;ONNET_SMS=0";
		
		//TOTAL_MINUTES=0;TOTAL_SMS=0;NET_MINUTES_RECEIVED=0;FREE_DATA=0;ONNET_FREE_MINUTES=0;ONNET_FREE_SMS=0;OFFNET_FREE_MINUTES=0;ONNET_SMS=0
		//String oldCellValue="SQLDB_select";
		//New Value to be Updated
		//String newValue="0";
		//String newValue="SQLDB_Select";
		//If sheet is Main sheet update the 'sheetName' variable as "MAIN_SHEET" else give the corresponding sheet name.
		//String sheetName="MAIN_SHEET";
		//String sheetName="Start_Script_XMLVal_SinglePair";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv2";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv3";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv4";
		String sheetName="Start_Script_XMLVal_SP_NodeEnv6";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv5";
		//Column name in which the New value to be updated shall be found
		//String colName="Disable_Events";
		String colName="Output_XML_Data";
		//String colName="Input_XML_Data";

		//String tofindsplChar1="TOTAL_MINUTES=0;TOTAL_SMS=0;NET_MINUTES_RECEIVED=0;FREE_DATA=0;ONNET_FREE_MINUTES=0;ONNET_FREE_SMS=0;OFFNET_FREE_MINUTES=0;ONNET_SMS=0";
		//String toReplacesplChar1="TOTAL_MINUTES=30;TOTAL_SMS=30;NET_MINUTES_RECEIVED=10;FREE_DATA=1073741824;ONNET_FREE_MINUTES=10;ONNET_FREE_SMS=10;OFFNET_FREE_MINUTES=10;ONNET_SMS=10";
		
	
		String tofindsplChar1="TOTAL_MINUTES=0;TOTAL_SMS=0;NET_MINUTES_RECEIVED=0;FREE_DATA=0;BUNDLE_EXPIRY_DATE=FUTUREDATE;ONNET_FREE_MINUTES=0;ONNET_FREE_SMS=0;OFFNET_FREE_MINUTES=0;ONNET_SMS=0";
		String toReplacesplChar1="TOTAL_MINUTES=30;TOTAL_SMS=30;NET_MINUTES_RECEIVED=10;FREE_DATA=1073741824;BUNDLE_EXPIRY_DATE=FUTUREDATE;ONNET_FREE_MINUTES=10;ONNET_FREE_SMS=10;OFFNET_FREE_MINUTES=10;ONNET_SMS=10";
		
		//String tofindsplChar1 = "COUNTRY_CODE=USA;";
		//String toReplacesplChar1 = "";
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
			int colNumber=-1;	
			int rowNumberToUpdate=-1;
			int index=-1;

			if(sheetName.equalsIgnoreCase("MAIN_SHEET")){
				index = 0;	
			}else{
				index = wb.getSheetIndex(sheetName);
			}

			//If Sheet Exist
			if(index >=0 ){	

				ws = wb.getSheetAt(index);
				//To Find the Column Number using the given column Name
				int colnum2=ws.getRow(0).getLastCellNum();
				colNumber=-1;	
				HSSFRow Suiterow = ws.getRow(0);				
				for(int i=0; i<colnum2; i++){
					if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
						colNumber=i;					
					}					
				}

				rowNumberToUpdate=1;

				if(rowNumberToUpdate != -1){	
					HSSFCell cell1 = ws.getRow(rowNumberToUpdate).getCell(colNumber);
					String cellValue=cellToString(cell1);
					System.out.println("Old Cell Value is : "+cellValue);

					if(oldValueCheck){

						if(cellValue.contains(tofindsplChar1)){
							cellValue=cellValue.replace(tofindsplChar1, toReplacesplChar1);
							cell1.setCellValue(cellValue);
							System.out.println("New Value updated in the Cell after checking condition");
						}

					}else{

						cellValue=cellValue.replace(tofindsplChar1, toReplacesplChar1);
						cell1.setCellValue(cellValue);

						System.out.println("New Value updated in the Cell");
					}
				}

			}else{
				System.out.println("Corresponding Sheet '"+sheetName+"' does not Exist in the Workbook...");
			}

			//Writing the Output Excels
			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			wb.write(fout);
			fout.close();
			wb.close();
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
		case 2: // String Value in Excel 
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
