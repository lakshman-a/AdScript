package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ChangeGBRSheetNames {

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

		String readfolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\";
		String writeFolderPath="C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\WRITE\\";
		int mainSheetNameIndex=0;
		String colName="Execution_Order";

		String sheetName_1="Eshop_GBR_SQL_CloseConnection";
		String newSheetName_1="Eshop_Close_SQL_Connection";
		String oldCellValue_1="Eshop_GBR_SQL_CloseConnection";
		String newValue_1="Eshop_Close_SQL_Connection";

		String sheetName_2="Close_SQL_Connection_GBR";
		String newSheetName_2="Close_SQL_Connection";
		String oldCellValue_2="Close_SQL_Connection_GBR";
		String newValue_2="Close_SQL_Connection";

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

			if(wb.getSheetIndex(sheetName_1)!=-1){
				wb.setSheetName(wb.getSheetIndex(sheetName_1), newSheetName_1);
				updateMainSheetColumn(ws, wb, mainSheetNameIndex, colName, oldCellValue_1, newValue_1);
				System.out.println(sheetName_1 +"'s SheetName/ Main sheets Column updated");
			}else{
				System.out.println(sheetName_1 +" sheet does not exist");
			}

			if(wb.getSheetIndex(sheetName_2)!=-1){
				wb.setSheetName(wb.getSheetIndex(sheetName_2), newSheetName_2);
				updateMainSheetColumn(ws, wb, mainSheetNameIndex, colName, oldCellValue_2, newValue_2);
				System.out.println(sheetName_1 +"'s SheetName/ Main sheets Column updated");
			}else{
				System.out.println(sheetName_1 +" sheet does not exist");
			}

			//Writing the Output Excels
			fis.close();
			FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
			wb.write(fout);
			fout.close();
			wb.close();
		}
		//Writing the Output Excels

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

	public static void updateMainSheetColumn(HSSFSheet ws,HSSFWorkbook wb,int mainSheetNameIndex,String colName, String oldCellValue, String newValue){

		String cellValue=null;
		int colNumber=-1;	
		int rowNumberToUpdate=-1;

		ws = wb.getSheetAt(mainSheetNameIndex);
		//To Find the Column Number using the given column Name
		int colnum2=ws.getRow(0).getLastCellNum();
		colNumber=-1;	
		HSSFRow Suiterow = ws.getRow(0);				
		for(int i=0; i<colnum2; i++){
			if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
				colNumber=i;					
			}					
		}
	//	System.out.println("colNumber is : "+colNumber);
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
				if(cellValue.trim().equalsIgnoreCase(oldCellValue)){
					rowNumberToUpdate=i;
				//	System.out.println("Cell Found! Row Number : "+i+" | Column Number is : "+j);
					break;

				}
			}
		}

		if(rowNumberToUpdate != -1){	
			HSSFCell cell1 = ws.getRow(rowNumberToUpdate).getCell(colNumber);
			String oldValue=cellToString(cell1);
			System.out.println("Cell's Old Value is : "+oldValue);

			if(oldValue.trim().equals(oldCellValue)){
				cell1.setCellValue(newValue);
				System.out.println("New Value ["+newValue+"] updated in the Cell");
			}
		}

	}

}
