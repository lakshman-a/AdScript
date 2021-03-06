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

public class UpdateEntireRowCellType {

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
		//If sheet is Main sheet update the 'sheetName' variable as "MAIN_SHEET" else give the corresponding sheet name.
		//String sheetName="Post_Do_BundleTopup";
		//String sheetName="Post_Do_BundleTopup2";
		String sheetName="RRBSDB_Select";
		//String sheetName="RRBSDB_Select";
		int rowNumberToCovert=1;

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
			int index=-1;

			if(sheetName.equalsIgnoreCase("MAIN_SHEET")){
				index = 0;	
			}else{
				index = wb.getSheetIndex(sheetName);
			}
			try{
				//If Sheet Exist
				if(index >=0 ){	

					ws = wb.getSheetAt(index);

					int rowNum = rowNumberToCovert;
					int colNum = ws.getRow(0).getLastCellNum();

					HSSFRow row = ws.getRow(rowNum);
					for (int j = 0 ; j < colNum ; j++) {
						HSSFCell cell = row.getCell(j);
						//cellValue = cellToString(cell);

						//System.out.println("The Cell Value is : " + cellValue);

						//CellType = 0 implies cell type is Numeric i.e., .0 is added 
						//So to Update only the Numeric cells use the below code
						/*if(cell.getCellType()==0){
							System.out.println("The Cell Value is " + cellValue);
							cell.setCellType(cell.CELL_TYPE_STRING);
							//System.out.println("*Updated the Numeric cell type to Text");
						}*/

						//Convert the all the Cells type irrespective of their cell type
						cell.setCellType(cell.CELL_TYPE_STRING);
						System.out.println("*Updated the Cell Type to Text");

					}

				}else{
					System.out.println("Corresponding Sheet '"+sheetName+"' does not Exist in the Workbook...");
				}
			}catch(Exception e){
				e.printStackTrace();
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
