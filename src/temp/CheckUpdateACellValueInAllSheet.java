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


public class CheckUpdateACellValueInAllSheet {
	
	static protected Logger log = Logger.getLogger(CheckUpdateACellValueInAllSheet.class.getName());

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
		String writeFolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		//String sheetName="Post_Do_BundleTopup4";

		File excelBatchFolder = new File(readfolderPath);
		File[] excelFiles = excelBatchFolder.listFiles();
		System.out.println("No. of SpreadSheets : "+excelFiles.length);
		
		String componentName="Perform_GBR_ONLINE_TOPUP_NOGAF";
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
			fis = new FileInputStream(excel);
			wb = new HSSFWorkbook(fis);
			//ws=wb.getSheetAt(0);
			ws=wb.getSheet(fileNameNoExtension);
			row=ws.getRow(0);
			int rowCount=ws.getLastRowNum();
			
			for(int i=1;i<=rowCount;i++){
				row=ws.getRow(i);
				String cellValue=row.getCell(3).getStringCellValue();
				if(cellValue.equalsIgnoreCase(componentName)){
					//log.info( ws.getSheetName());
					String caseName = ws.getSheetName();
					String suffix=caseName.split("_")[3];
					
					HSSFSheet neededSheet = wb.getSheet("POST_GBR_ONLINE_TOPUP");
					HSSFRow headerRowInSheet = neededSheet.getRow(0);
					int colCount = headerRowInSheet.getLastCellNum();
					int neededColNumber =-1;
					
					for(int col=0; col<colCount ; col++){
						if(headerRowInSheet.getCell(col).getStringCellValue().trim().equalsIgnoreCase("ESHOP_MSISDN_Condition")){
							neededColNumber=col;
							break;
						}
					}
					
					HSSFRow dataRow = neededSheet.getRow(1);
					dataRow.getCell(neededColNumber).setCellValue("SubscriberId ='09944020030' order by 1 desc");
					
					
				}

			}
			
			/*fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			wb.write(fout);*/
			wb.close();
			fis.close();
			System.out.println("End*****");
		
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
