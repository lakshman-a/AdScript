package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class updateVersionInExcel {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = null;
		FileOutputStream fout=null;
		XSSFWorkbook wb = null;
		
		try{
			String writePath="C:\\Users\\laks3339\\Desktop\\";
			String writeFileName="CRM_Automation_ Development Environment_Details_1 0 1 0_Rev10_29thSep_V1_1_Check.xlsx";
			String countryName="GBR";
			String newVersion="1.0.20.0";
			String sheetName="CRM_Auto_Reg_Environment";
			String applicationName="CRM Web Service";

			String excelFile = "C:\\Users\\laks3339\\Desktop\\CRM_Automation_ Development Environment_Details_1 0 1 0_Rev10_29thSep_V1_1.xlsx";
			System.out.println("\nProcessing file : "+excelFile);

			File excel = new File(excelFile);
			fis = new FileInputStream(excel);
			wb = new XSSFWorkbook(fis);
			XSSFSheet ws=null;
			String cellValue=null;
			int colNumberToUpdate=-1;	
			int rowNumberToUpdate=-1;
			int index=-1;
			index = wb.getSheetIndex(sheetName);
			
			if(index >=0 ){
				ws = wb.getSheetAt(index);
				//Get the Total Column from Header Row - 4
				int totalColumn=ws.getRow(4).getLastCellNum();
				System.out.println("Total Number of Columns -> "+totalColumn);
				
				XSSFRow headerRow = ws.getRow(4);	
				for(int i=0; i<totalColumn; i++){
					if(headerRow.getCell(i).getStringCellValue().equals(applicationName.trim())){
						colNumberToUpdate=i;
						System.out.println("ColNumber to Update -> "+colNumberToUpdate);
						break;
					}
				}
				
				int rowNum = ws.getLastRowNum() + 1;
				int colNum = ws.getRow(4).getLastCellNum();
				String[][] data = new String[rowNum][colNum];
				//Get the Row Number using the Country
				for (int i = 0 ; i < rowNum ; i++) {
					XSSFRow row = ws.getRow(i);
					for (int j = 0 ; j < colNum ; j++) {
						XSSFCell cell = row.getCell(j);
						cellValue = cellToString(cell);
						data[i][j] = cellValue ;
						//System.out.println("The Cell Value is " + cellValue);
						if(cellValue.trim().equalsIgnoreCase(countryName)){
							rowNumberToUpdate=i;
							System.out.println("RowNumber to Update -> "+rowNumberToUpdate);
							break;
						}
					}
				}

				if(rowNumberToUpdate != -1){	
					XSSFCell cellToUpdate = ws.getRow(rowNumberToUpdate).getCell(colNumberToUpdate);
					String oldValue=cellToString(cellToUpdate);
					System.out.println("Old Value is -> "+oldValue);
					//Update the New Value
					cellToUpdate.setCellType(Cell.CELL_TYPE_STRING);
					cellToUpdate.setCellValue(newVersion);
					System.out.println("New version '"+newVersion+"' updated successfully.");
				}else{
					System.out.println("Cell Content does not exist...");
				}
			}else{
				System.out.println("Corresponding Sheet '"+sheetName+"' does not Exist in the Workbook...");
			}

			//Writing the Output Excels
			fout=new FileOutputStream(new File(writePath+"\\"+writeFileName));
			wb.write(fout);
			System.out.println("New File Path : " +writePath+"\\"+writeFileName);

		}catch(Exception e){
			System.out.println("Exception occured!");
			e.printStackTrace();
		
		}finally{
			fis.close();
			fout.close();
			wb.close();
		}
	}

	public static String cellToString(XSSFCell cell) {  
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
		case 2: // String Value in Excel 
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
