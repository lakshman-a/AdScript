package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <h1>UpdateCellWithNewValue</h1>
 * To add a new column with header and data value in Excel with given New Header and cell Value, WorkSheet Name.
 * Column with header and cell data by default are added at the end
 * Input as Read Batch Folder and Output Excel to New Write Path
 * <p>
 * <b>Note: Supports Batch Type Input, New File will be generated</b> 
 * 
 * @author Lakshman A
 * @see 
 * @since Jul 26, 2016
 *
 */

public class addNewColumnsTestData {

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
		//If sheet is Main sheet update the 'sheetName' variable as "MAIN_SHEET" else give the corresponding sheet name.
		//String sheetName="Start_Script_XMLVal_SinglePair";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv2";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv3";
		//String sheetName="Stop_Script";
		String sheetName="Pre_Do_BundleTopup";
		
		int rowNumberToWriteHeader1=0;
		String headerValueToWrite1="TABLE_RRBS_SUBSCRIBER_PROFILE";
		int rowNumberToWriteData1=1;
		String cellValueToWrite1="RRBS_SUBSCRIBER_PROFILE";
		
		int rowNumberToWriteHeader2=0;
		String headerValueToWrite2="SERVICE_CONTROL_COLUMN_NAME";
		int rowNumberToWriteData2=1;
		String cellValueToWrite2="SERVICE_CONTROL";
		
		int rowNumberToWriteHeader3=0;
		String headerValueToWrite3="SERVICE_CONTROL_COLUMN_VALUE";
		int rowNumberToWriteData3=1;
		String cellValueToWrite3="000000000000000000000000000000";
		
		int rowNumberToWriteHeader4=0;
		String headerValueToWrite4="RRBS_UPDATE_CONDITION_3";
		int rowNumberToWriteData4=1;
		String cellValueToWrite4="MSISDN='9911054000'";

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

				ws = wb.getSheetAt(index);
				//To Find the Column Number using the given column Name
				int colNum1 = ws.getRow(0).getLastCellNum();
				int colNum2 = ws.getRow(0).getLastCellNum()+1;
				int colNum3 = ws.getRow(0).getLastCellNum()+2;
				int colNum4 = ws.getRow(0).getLastCellNum()+3;

				HSSFCell cell1 = ws.getRow(rowNumberToWriteHeader1).createCell(colNum1);
				cell1.setCellValue(headerValueToWrite1);
				HSSFCell cellData1 = ws.getRow(rowNumberToWriteData1).createCell(colNum1);
				cellData1.setCellValue(cellValueToWrite1);
				
				HSSFCell cell2 = ws.getRow(rowNumberToWriteHeader2).createCell(colNum2);
				cell2.setCellValue(headerValueToWrite2);
				HSSFCell cellData2 = ws.getRow(rowNumberToWriteData2).createCell(colNum2);
				cellData2.setCellValue(cellValueToWrite2);
				
				HSSFCell cell3 = ws.getRow(rowNumberToWriteHeader3).createCell(colNum3);
				cell3.setCellValue(headerValueToWrite3);
				HSSFCell cellData3 = ws.getRow(rowNumberToWriteData3).createCell(colNum3);
				cellData3.setCellValue(cellValueToWrite3);
				
				HSSFCell cell4 = ws.getRow(rowNumberToWriteHeader4).createCell(colNum4);
				cell4.setCellValue(headerValueToWrite4);
				HSSFCell cellData4 = ws.getRow(rowNumberToWriteData4).createCell(colNum4);
				cellData4.setCellValue(cellValueToWrite4);
				
				colNum1 = 0;
				colNum2 = 0;
				colNum3 = 0;
				colNum4 = 0;
				
				System.out.println("New Column and data is created successfully.");

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
		case 3: // String Value in Excel 
			result = cell.getStringCellValue();
			break;
		default:  
			throw new RuntimeException("There is no support for this type of cell");                        
		}

		return result.toString();
	}
}
