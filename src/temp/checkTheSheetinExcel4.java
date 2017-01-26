package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class checkTheSheetinExcel4 {

	static protected Logger log = Logger.getLogger(checkTheSheetinExcel4.class.getName());

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
		HSSFWorkbook wb = null;
		HSSFSheet ws= null;
		HSSFRow row= null;

		/* EDIT THE FOLLOWING DETAILS RESPECTIVELY*/

		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		String writeFolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
		//String sheetName="Post_Do_BundleTopup4";

		File excelBatchFolder = new File(readfolderPath);
		File[] excelFiles = excelBatchFolder.listFiles();
		System.out.println("No. of SpreadSheets : "+excelFiles.length);

		for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

			String excelFile = readfolderPath+excelFiles[filePointer].getName();
			System.out.println("\nProcessing file : "+excelFiles[filePointer].getName());

			String fileName=excelFiles[filePointer].getName();
			String fileNameNoExtension=null;

			int index = fileName.lastIndexOf('.');
			if (index == -1) {
				//log.info("DO nothing");
			} else {
				fileNameNoExtension= fileName.substring(0, index);
			}

			excel = new File(excelFile);
			fis = new FileInputStream(excel);
			wb = new HSSFWorkbook(fis);

			String suffix=fileNameNoExtension.split("_")[3];

			ws=wb.getSheet("Pending_Approval_Credit");
			row=ws.getRow(0);
			int colCount=row.getLastCellNum();

			int neededCol=-1;
			for(int colNum=0; colNum<colCount; colNum++){
				for(int currentCol=0;currentCol<colNum;currentCol++){
					if(row.getCell(colNum).getStringCellValue().trim().equalsIgnoreCase("EnvVaraibleName_ID")){
						neededCol=colNum;
						break;
					}
				}
			}
			
			HSSFRow rowToUpdate= ws.getRow(1);
			rowToUpdate.getCell(neededCol).setCellValue(fileNameNoExtension);
			System.out.println("Updated the cell value");


			System.out.println("\n");

			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			wb.write(fout);
			wb.close();
			fis.close();
			System.out.println("End*****");

		}
		System.out.println("\n");

		/*wb.close();
		fis.close();*/
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
