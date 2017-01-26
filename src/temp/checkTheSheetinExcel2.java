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


public class checkTheSheetinExcel2 {

	static protected Logger log = Logger.getLogger(checkTheSheetinExcel2.class.getName());

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

		//String componentName="Perform_GBR_ONLINE_TOPUP_NOGAF";
		//log.info("Component is : "+componentName);

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
			int sheetCount=wb.getNumberOfSheets();
			System.out.println("No of sheets in "+fileNameNoExtension+" is : "+sheetCount);
			
			String suffix=fileNameNoExtension.split("_")[4];

			for(int sheetIndex=1;sheetIndex<sheetCount;sheetIndex++){
				ws=wb.getSheetAt(sheetIndex);
				
				int rowCount=ws.getLastRowNum();
				
				for(int i=1;i<=rowCount;i++){
					row=ws.getRow(i);
					int colCount=row.getLastCellNum();
					
					for(int colNum=0; colNum<colCount; colNum++){
						String cellValue=null;
						try{
						cellValue=row.getCell(colNum).getStringCellValue();
						if(cellValue.trim().equals("Transaction_Number")){
							System.out.println("SheetName is : "+ws.getSheetName());
							row.getCell(colNum).setCellValue("Transaction_Number_AIO_"+suffix);
							System.out.println("Updated...!");
						}
						}catch (IllegalStateException e) {
							System.out.println("Caught Illeage state exception");
						}catch (Exception e) {
							System.out.println("Normal exception");
						}
						

					}

				}
			}
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
