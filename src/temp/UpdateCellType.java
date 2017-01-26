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
 * <h1>UpdateCellType</h1>
 * To Update Cell Type in a sheet to a text/other formats with given Cell Type, WorkSheet Name, Column name and Row Data 
 * Input as Read Batch Folder and Output Excel to New Write Path
 * <p>
 * <b>Note: Supports Batch Type Input, New File will be generated</b> 
 * 
 * @author Lakshman A
 * @see 
 * @since Jul 26, 2016
 *
 */

public class UpdateCellType {

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

		String readfolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
		String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//Search the following content in the sheet in order to get corresponding Row Number
		String cellContentToSearch="landlineno";
		//If sheet is Main sheet update the 'sheetName' variable as "MAIN_SHEET" else give the corresponding sheet name.
		String sheetName="SQLDB_Select";
		//Column name in which the New value to be updated shall be found
		String colName="Column_Value";

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
			String cellValue=null;
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

				//Get the Last Row Number to Loop through all Cell
				int rowNum = ws.getLastRowNum() + 1;
				int colNum = ws.getRow(0).getLastCellNum();
				String[][] data = new String[rowNum][colNum];

				for (int i = 0 ; i < rowNum ; i++) {
					HSSFRow row = ws.getRow(i);
					for (int j = 0 ; j < colNum ; j++) {
						HSSFCell cell = row.getCell(j);
						cellValue = cellToString(cell);
						data[i][j] = cellValue ;
						//System.out.println("The Cell Value is " + cellValue);
						if(cellValue.trim().equalsIgnoreCase(cellContentToSearch)){
							rowNumberToUpdate=i;
							System.out.println("Cell Found! Row Number : "+i+" | Column Number is : "+j);
							break;

						}
					}
				}

				HSSFCell cell1 = ws.getRow(rowNumberToUpdate).getCell(colNumber);
				String oldCellValue=cellToString(cell1);
				System.out.println("Cells value is : "+oldCellValue);
				//Update the found cell Type to Text
				cell1.setCellType(cell1.CELL_TYPE_STRING);
				System.out.println("Cell Format Successfully changed to Text.");

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
