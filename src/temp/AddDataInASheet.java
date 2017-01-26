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


public class AddDataInASheet {
	
	static protected Logger log = Logger.getLogger(AddDataInASheet.class.getName());

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
		
		/*String componentName="Pending_Approval_Credit";
		log.info("Component is : "+componentName);*/

		for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

			int colCount=-1;
			String excelFile = readfolderPath+excelFiles[filePointer].getName();
			//System.out.println("\nProcessing file : "+excelFiles[filePointer].getName());

			String fileName=excelFiles[filePointer].getName();
			String fileNameNoExtension=null;

			 int index = fileName.lastIndexOf('.');
			    if (index == -1) {
			    	//log.info("DO nothing");
			    } else {
			    	fileNameNoExtension= fileName.substring(0, index);
			    }
			    
			String suffix=fileNameNoExtension.split("_")[3];
			    
			excel = new File(excelFile);
			fis = new FileInputStream(excel);
			wb = new HSSFWorkbook(fis);
			//ws=wb.getSheetAt(0);
			
			ws=wb.getSheet("Pending_Approval_Credit");
			row=ws.getRow(0);
			colCount=row.getLastCellNum();
			
			HSSFCell cell = ws.getRow(0).createCell(colCount);
			cell.setCellValue("EnvVariableName");
			HSSFCell cellData = ws.getRow(1).createCell(colCount);
			cellData.setCellValue(fileNameNoExtension);
			
		
			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			wb.write(fout);
			wb.close();
			fis.close();
		
		}
		log.info("\n");
		
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
