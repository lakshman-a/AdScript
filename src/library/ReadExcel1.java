package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel1 {

	public static String filename;
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;

	public ReadExcel1(String path){
		this.path=path;

		try{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
			sheet = workbook.getSheetAt(index);
			int number=sheet.getLastRowNum();
			return number;

		}
	}

	public int getColumnCount(String sheetName){

		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
		}

		if(row==null)
			return -1;

		return row.getLastCellNum();
	}

	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return "";

			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);

			int col_Num=-1;
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1)
				return "";

			row = sheet.getRow(rowNum);
			if(row==null)
				return "";
			cell = row.getCell(col_Num);

			if(cell==null)
				return "";

			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue().trim();

			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC )
				return String.valueOf(cell.getNumericCellValue()).trim();

			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue()).trim();

		}
		catch(Exception e){
			e.printStackTrace();
			// return "row "+rowNum+" or column "+colName +" does not exist in xls";
			return "";
		}

	}
	
	public String getCellData(String sheetName,String colName,String rowName){
		try{

			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return "";

			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);

			int rowNum = getRowCount(sheetName);
			System.out.println("rowNum is : "+rowNum);
			
			int col_Num=-1;
			int row_Num=-1;
			
			System.out.println("getLastCellNum is : "+row.getLastCellNum());
			
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i)==null){
					System.out.println("Cell is NULL");
				}else{
				System.out.println("row cell value is : "+row.getCell(i).getStringCellValue());
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
				}
				}
			if(col_Num==-1)
				return "";
			

			for(int j=0; j<rowNum; j++){
				XSSFRow Suitecol = sheet.getRow(j);				
				if(Suitecol.getCell(1).getStringCellValue().equals(rowName.trim())){
					row_Num=j;	
				}					
			}

			if(row_Num==-1){
				return "";				
			}

			row = sheet.getRow(row_Num);
			if(row==null)
				return "";
			cell = row.getCell(col_Num);

			if(cell==null)
				return "";

			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue().trim();

			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC )
				return String.valueOf(cell.getNumericCellValue()).trim();

			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue()).trim();

		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}

	}

	public int getNumericCellData(String sheetName,String colName,int rowNum){
		int value = -1;
		try{
			if(rowNum <=0)
				return -1;

			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return -1;

			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);

			int col_Num=-1;
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1)
				return -1;

			row = sheet.getRow(rowNum);
			if(row==null)
				return -1;
			cell = row.getCell(col_Num);

			if(cell==null)
				return -1;

			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC )
				value = (int) (cell.getNumericCellValue());

			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				return -1;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return value;

	}

	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return "";

			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);

			row = sheet.getRow(rowNum);
			if(row==null)
				return "";
			cell = row.getCell(colNum);

			if(cell==null)
				return "";

			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();

			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC )
				return String.valueOf(cell.getNumericCellValue());

			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		}
		catch(Exception e){
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}

	}
	
	public boolean CheckTestCaseNamedSheetExist(String filelocation, String TESTCASE_NAME) throws Exception{
		FileInputStream ipstr = null;
		HSSFWorkbook wb = null;
		boolean sheetExist=false;
		try{
			ipstr = new FileInputStream(filelocation);
			wb = new HSSFWorkbook(ipstr);
			ipstr.close();
			for(int k=0; k<wb.getNumberOfSheets(); k++){
				if(wb.getSheetName(k).equalsIgnoreCase(TESTCASE_NAME)){
					sheetExist=true;
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			ipstr = null;
			if(wb!=null){
				wb.close();
				wb=null;
			}
		}
		return sheetExist;
	}

}



