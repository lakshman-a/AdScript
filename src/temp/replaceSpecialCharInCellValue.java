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
 * <h1>replaceSpecialCharInCellValue</h1>
 * To find a Cell Value in Excel, replace the char with new character using the WorkSheet Name, Column Name and a Data Row Number
 * Input as Read Batch Folder and Output Excel to New Write Path
 * <p>
 * <b>Note: Supports Batch Type Input, New File will be generated</b> 
 * 
 * @author Lakshman A
 * @see 
 * @since Jul 26, 2016
 *
 */

public class replaceSpecialCharInCellValue {

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
		String sheetName="Start_Script_XMLVal_SP_NodeEnv2";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv3";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv4";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv6";
		//String sheetName="Start_Script_XMLVal_SP_NodeEnv5";
		//String sheetName="Post_OnlineTopup";
		
		
		//Column name in which the New value to be updated shall be found
		String colName="XLDB_Input_Query";
		//Row number
		int rowNumberToEdit=1;
		/*String tofindsplChar1="042016";
		String toReplacesplChar1="042025";*/
		String tofindsplChar1="[";
		String toReplacesplChar1="";
		String tofindsplChar2="]";
		String toReplacesplChar2="";
		String tofindsplChar3="$";
		String toReplacesplChar3="";
		

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

				HSSFRow row = ws.getRow(rowNumberToEdit);
				HSSFCell cell = row.getCell(colNumber);
				if(cell==null){
					//throw new RuntimeException("Cell is NULL");
					System.err.println("Cell is NULL...!");
				}

				String cellValue = cellToString(cell);
				System.out.println("Cell value is : "+cellValue);

				cellValue = cellValue.replace(tofindsplChar1, toReplacesplChar1);
				cellValue = cellValue.replace(tofindsplChar2, toReplacesplChar2);
				cellValue = cellValue.replace(tofindsplChar3, toReplacesplChar3);
				System.out.println("Updated cell value is : "+cellValue);

				HSSFCell cell1 = ws.getRow(rowNumberToEdit).getCell(colNumber);
				cell1.setCellValue(cellValue);

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

		System.out.println("Successfully processed. Write folder path is : " +writeFolderPath);
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
