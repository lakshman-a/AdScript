package temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Name;

public class ModifyingDataInExcel {


public static void main(String[] args) {
	
		// HSSFSheet sheet = null;
		HSSFWorkbook workbook=null;
		HSSFSheet sheet = null;

		int sheetCount = 0;
		
		String ProcessingFilePath="C:\\Users\\mura2261\\Desktop\\READ_FILE\\";
		String sheetNameTochange="Open_Germany_SQL_Connection";
		String NewSheetName="Open_HKG_SQL_Connection";

		File file = new File(ProcessingFilePath);
		File []files = file.listFiles();
		FileInputStream fis = null;
		String sheetName = null;
		int filesCounter = files.length;
		System.out.println("Number of Excel Files present inside the folder '"+ProcessingFilePath+"' is '"+filesCounter+"'");

		try{
			for (int i = 0;i<filesCounter;i++){

				System.out.println("Processing the File - "+files[i]);
				fis = new FileInputStream(files[i]);
				workbook = new HSSFWorkbook(fis);
				fis.close();
				sheetCount = workbook.getNumberOfSheets();
				System.out.println("Number of sheets present in this file - "+sheetCount);

				for (int j=0;j<sheetCount;j++){

					sheetName = workbook.getSheetAt(j).getSheetName();
					System.out.println("sheet name traverse:::"+sheetName);

					sheet = workbook.getSheetAt(j);
					System.out.println(sheetNameTochange);
					int sheetIdx = workbook.getSheetIndex(sheetNameTochange);

					System.out.println(sheetIdx);
					//if (sheetNameTochange.equalsIgnoreCase(sheet.getSheetName())){
					//	System.out.println("Found the old sheet '"+sheetNameTochange+"' which need to be renamed to "+NewSheetName+" in the file '"+files[i]+"'");
					//System.out.println("wb sheet name based in index :"+workbook.getSheetAt(sheetIdx).getSheetName());
					workbook.setSheetName(sheetIdx, NewSheetName);

					workbook.close();
					System.out.println("workBook closed ."+files[i]);

					
					System.out.println("Sheet Name has been set for '"+sheetNameTochange+"' with new name '"+NewSheetName+"' present in index--"+j);
					break;
					//}
				}
				
				FileOutputStream fileout = new FileOutputStream(new File("C:\\Users\\mura2261\\Desktop\\READ_FILE\\WRITE\\NewSheet.xls"));
				//FileOutputStream fout=new FileOutputStream(new File(writeFolderPath+excelFiles[filePointer].getName()));
				workbook.write(fileout);
				workbook.close();
				fis.close();
				

			}

		}catch(Exception e ){
			System.out.println("Error occured while changing the sheet name ----"+e.getMessage());
			e.printStackTrace();
		}


	}


}
