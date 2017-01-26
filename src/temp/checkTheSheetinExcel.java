package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class checkTheSheetinExcel {
	
	static protected Logger log = Logger.getLogger(checkTheSheetinExcel.class.getName());

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

	public static void main(String[] args) {
		
		PropertyConfigurator.configure("src\\log4j.properties");
		File excel = null;
		FileInputStream fis = null;
		HSSFWorkbook wb = null;
		HSSFSheet ws= null;
		HSSFRow row= null;

		/* EDIT THE FOLLOWING DETAILS RESPECTIVELY*/

		//String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String writeFolderPath="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\";
		
		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String sheetName="Post_Do_BundleTopup4";

			File excelBatchFolder = new File(readfolderPath);
			File[] excelFiles = excelBatchFolder.listFiles();
			System.out.println("No. of SpreadSheets : "+excelFiles.length);
			
			String componentName="Open_RRBS_Connection";
			log.info("Component is : "+componentName);

			for (int filePointer = 0; filePointer < excelFiles.length; filePointer++) {   

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
				    
				excel = new File(excelFile);
				try {
					fis = new FileInputStream(excel);
					wb = new HSSFWorkbook(fis);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				//ws=wb.getSheetAt(0);
				ws=wb.getSheet(fileNameNoExtension);
				row=ws.getRow(0);
				
				//log.info("SNo : "+(filePointer+1));
				//log.info(ws.getSheetName());
				//log.info("------------------------");
				
				int rowCount=ws.getLastRowNum();
				//int colCount=row.getLastCellNum();
				//log.info("No of Rows: "+rowCount);
				//log.info("No of Cols: "+colCount);
				//log.info("\n");
				
				
				for(int i=1;i<=rowCount;i++){
					row=ws.getRow(i);
					String cellValue=null;
					try {
						cellValue = row.getCell(3).getStringCellValue();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//log.info(cellValue);
					if(cellValue.equalsIgnoreCase(componentName)){
						log.info(ws.getSheetName());
					}

				}
				
				//log.info("------------------------");
				//log.info("\n");
			}
			log.info("\n");
			//System.out.println("Output Write folder path is : " +writeFolderPath);
			try {
				wb.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
