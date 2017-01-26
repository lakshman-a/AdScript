package temp2;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
public class OpenWorkBook
{
   public static void main(String args[])throws Exception
   { 
      File file = new File("C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\sample.xls");
      FileInputStream fIP = new FileInputStream(file);
      //Get the workbook instance for XLSX file 
      HSSFWorkbook workbook = new HSSFWorkbook(fIP);
      if(file.isFile() && file.exists())
      {
         System.out.println(
         "openworkbook.xlsx file open successfully.");
      }
      else
      {
         System.out.println(
         "Error to open openworkbook.xlsx file.");
      }
   }
}