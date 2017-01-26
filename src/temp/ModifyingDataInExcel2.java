package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ModifyingDataInExcel2 {

	static protected Logger log = Logger.getLogger(checkTheSheetinExcel3.class.getName());

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

		PropertyConfigurator.configure("src\\log4j.properties");
		File excel = null;
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;

		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		String writeFolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\WRITE\\";

		File excelBatchFolder = new File(readfolderPath);
		File[] excelFiles = excelBatchFolder.listFiles();
		System.out.println("No. of SpreadSheets : "+excelFiles.length);

		String sheetNameTochange="Open_Germany_SQL_Connection";
		String NewSheetName="Open_HKG_SQL_Connection";

		for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

			String excelFile = readfolderPath+excelFiles[filePointer].getName();
			//System.out.println("\nProcessing file : "+excelFiles[filePointer].getName());

			//String fileName=excelFiles[filePointer].getName();
			/*String fileNameNoExtension=null;

			int index = fileName.lastIndexOf('.');
			if (index == -1) {
				//log.info("DO nothing");
			} else {
				fileNameNoExtension= fileName.substring(0, index);
			}*/

			excel = new File(excelFile);
			fis = new FileInputStream(excel);

			workbook = new HSSFWorkbook(fis);
			int sheetCount = workbook.getNumberOfSheets();
			System.out.println("Number of sheets present in this file - "+sheetCount);

			System.out.println(sheetNameTochange);
			int sheetIdx = workbook.getSheetIndex(sheetNameTochange);

			System.out.println(sheetIdx);
			workbook.setSheetName(sheetIdx, NewSheetName);

			System.out.println("Sheet Name has been set for '"+sheetNameTochange+"' with new name '"+NewSheetName+"' present in index--");

			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			workbook.write(fout);
			workbook.close();
			fis.close();

		}
		log.info("Done! ");

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
