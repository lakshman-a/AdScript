package library;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class versionManagement {

	private static Properties config;
	static FileInputStream fis = null;
	static FileOutputStream fout=null;
	static XSSFWorkbook wb = null;

	public static void main(String[] args) throws Exception {

		try{

			versionManagement versionObj = new versionManagement();

			config = new Properties();
			FileInputStream input = new FileInputStream("./src/property/config.properties");
			config.load(input);

			String referenceExcel = config.getProperty("ReferenceExcelPath");
			System.out.println("\nProcessing file : "+referenceExcel);

			File excel = new File("//\\"+referenceExcel);
			fis = new FileInputStream(excel);
			wb = new XSSFWorkbook(fis);

			//Get the Version
			String ALL_Country=config.getProperty("ALL_Countries");

			/************************** ALL EXE's ********************************************/
			
			
			
			
			
			/*********** EXIBS ***********/
			
			/*-----------------GBR exe ---------------------*/
			String exibsVersion = versionObj.connectExecuteUnixCommand("EXIBS",
					config.getProperty("EXIBS_ipaddress"),
					config.getProperty("EXIBS_username"),
					config.getProperty("EXIBS_password"), 
					config.getProperty("EXIBS_command_GBR"));
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("EXIBS_Application"), exibsVersion);
			System.out.println("'"+config.getProperty("EXIBS_Application")+"' version Updated");
			/*-----------------GBR exe END---------------------*/
			
			/*-----------------USA exe ---------------------*/
			String exibsVersion_USA = versionObj.connectExecuteUnixCommand("EXIBS",
					config.getProperty("EXIBS_ipaddress"),
					config.getProperty("EXIBS_username"),
					config.getProperty("EXIBS_password"), 
					config.getProperty("EXIBS_command_GBR"));
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("EXIBS_Application"), exibsVersion_USA);
			System.out.println("'"+config.getProperty("EXIBS_Application")+"' version Updated");
			/*-----------------USA exe END---------------------*/
			
			/*-----------------NOR exe ---------------------*/
			String exibsVersion_NOR = versionObj.connectExecuteUnixCommand("EXIBS",
					config.getProperty("EXIBS_ipaddress"),
					config.getProperty("EXIBS_username"),
					config.getProperty("EXIBS_password"), 
					config.getProperty("EXIBS_command_NOR"));
			updateVersionInExcel(config.getProperty("NOR_Country"), config.getProperty("EXIBS_Application"), exibsVersion_NOR);
			System.out.println("'"+config.getProperty("EXIBS_Application")+"' version Updated");
			/*-----------------NOR exe END---------------------*/
			
			/*-----------------FRA exe ---------------------*/
			String exibsVersion_FRA = versionObj.connectExecuteUnixCommand("EXIBS",
					config.getProperty("EXIBS_ipaddress"),
					config.getProperty("EXIBS_username"),
					config.getProperty("EXIBS_password"), 
					config.getProperty("EXIBS_command_FRA"));
			updateVersionInExcel(config.getProperty("FRA_Country"), config.getProperty("EXIBS_Application"), exibsVersion_FRA);
			System.out.println("'"+config.getProperty("EXIBS_Application")+"' version Updated");
			/*-----------------FRA exe END---------------------*/

			/*********** EXIBS END ***********/
			
			
			
			
			
			
			/*-----------------ESME exe ---------------------*/
			String esmeVersion = versionObj.connectExecuteUnixCommand("ESME",
					config.getProperty("ESME_ipaddress"),
					config.getProperty("ESME_username"),
					config.getProperty("ESME_password"), 
					config.getProperty("ESME_command_GBR"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("ESME_Application"), esmeVersion);
			System.out.println("'"+config.getProperty("ESME_Application")+"' version Updated");
			/*-----------------ESME exe END---------------------*/

			/*-----------------HLR exe ---------------------*/
			String hlrVersion = versionObj.connectExecuteUnixCommand("HLR",
					config.getProperty("HLR_ipaddress"),
					config.getProperty("HLR_username"),
					config.getProperty("HLR_password"), 
					config.getProperty("HLR_command_GBR"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("HLR_Application"), hlrVersion);
			System.out.println("'"+config.getProperty("HLR_Application")+"' version Updated");
			/*-----------------HLR exe END---------------------*/

			/*-----------------ITG exe ---------------------*/
			String itgVersion = versionObj.connectExecuteUnixCommand("ITG",
					config.getProperty("ITG_ipaddress"),
					config.getProperty("ITG_username"),
					config.getProperty("ITG_password"), 
					config.getProperty("ITG_command_GBR"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("ITG_Application"), itgVersion);
			System.out.println("'"+config.getProperty("ITG_Application")+"' version Updated");
			/*-----------------ITG exe END---------------------*/

			/*-----------------IMG exe ---------------------*/
			String imgVersion = versionObj.connectExecuteUnixCommand("IMG",
					config.getProperty("IMG_ipaddress"),
					config.getProperty("IMG_username"),
					config.getProperty("IMG_password"), 
					config.getProperty("IMG_command_GBR"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("IMG_Application"), imgVersion);
			System.out.println("'"+config.getProperty("IMG_Application")+"' version Updated");
			/*-----------------IMG exe END---------------------*/

			/*-----------------IMG SIM exe ---------------------*/
			String imgSIMVersion = versionObj.connectExecuteUnixCommand("IMG_SIM",
					config.getProperty("IMGSIM_ipaddress"),
					config.getProperty("IMGSIM_username"),
					config.getProperty("IMGSIM_password"), 
					config.getProperty("IMGSIM_command_GBR"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("IMGSIM_Application"), imgSIMVersion);
			System.out.println("'"+config.getProperty("IMGSIM_Application")+"' version Updated");
			/*-----------------IMG SIM exe END---------------------*/

			
			
			/************************** EXE END ********************************************/
			
			
			
			
			
			
			
			
			
			
			/************************** ORACLE DATABASES ********************************************/

			/*-----------------RRBS DB ---------------------*/
			String rrbsDBVersion = versionObj.getORACLEDBVersion("RRBS DB",
					config.getProperty("RRBS_DBServer"),
					config.getProperty("RRBS_DBPort"),
					config.getProperty("RRBS_DBName"), 
					config.getProperty("RRBS_DBPUsername"),
					config.getProperty("RRBS_DBPassword"),
					config.getProperty("RRBS_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("RRBS_DBApplication"), rrbsDBVersion);
			System.out.println("'"+config.getProperty("RRBS_DBApplication")+"' version Updated");
			/*-----------------RRBS DB END---------------------*/

			/*-----------------EXIBS COM DB ---------------------*/
			String exibsCOMDBVersion = versionObj.getORACLEDBVersion("EXIBS COM DB",
					config.getProperty("EXIBS_DBServer"),
					config.getProperty("EXIBS_DBPort"),
					config.getProperty("EXIBS_DBName"), 
					config.getProperty("EXIBS_DBPUsername"),
					config.getProperty("EXIBS_DBPassword"),
					config.getProperty("EXIBS_COM_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("EXIBS_COM_DBApplication"), exibsCOMDBVersion);
			System.out.println("'"+config.getProperty("EXIBS_COM_DBApplication")+"' version Updated");
			/*-----------------EXIBS COM DB END---------------------*/

			/*-----------------EXIBS DM DB ---------------------*/
			String exibsDMDBVersion = versionObj.getORACLEDBVersion("EXIBS DM DB",
					config.getProperty("EXIBS_DBServer"),
					config.getProperty("EXIBS_DBPort"),
					config.getProperty("EXIBS_DBName"), 
					config.getProperty("EXIBS_DBPUsername"),
					config.getProperty("EXIBS_DBPassword"),
					config.getProperty("EXIBS_DM_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("EXIBS_DM_DBApplication"), exibsDMDBVersion);
			System.out.println("'"+config.getProperty("EXIBS_DM_DBApplication")+"' version Updated");
			/*-----------------EXIBS DM DB END---------------------*/

			/*-----------------HLR DB ---------------------*/
			String hlrDBVersion = versionObj.getORACLEDBVersion("HLR DB",
					config.getProperty("HLR_DBServer"),
					config.getProperty("HLR_DBPort"),
					config.getProperty("HLR_DBName"), 
					config.getProperty("HLR_DBPUsername"),
					config.getProperty("HLR_DBPassword"),
					config.getProperty("HLR_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("HLR_DBApplication"), hlrDBVersion);
			System.out.println("'"+config.getProperty("HLR_DBApplication")+"' version Updated");
			/*-----------------HLR DB END---------------------*/

			/************************** ORACLE DATABASE END ********************************************/
			
			
			
			
			
			
			
			
			
			
			
			/************************** SQL DATABASES ********************************************/
			
			

			
			/******** MVNO GBR DETAILS **********/
			
			
			/*-----------------CBOS SQL DB ---------------------*/
			String cbosDBVersion = versionObj.getSQLDBVersion("CBOS DB",
					config.getProperty("MVNO_GBR_DBSQLServer"),
					config.getProperty("MVNO_GBR_DBName"),
					config.getProperty("MVNO_GBR_DBUsername"), 
					config.getProperty("MVNO_GBR_DBPassword"),
					config.getProperty("CBOS_DBQuery"));
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("CBOS_DBApplication"), cbosDBVersion);
			System.out.println("'"+config.getProperty("CBOS_DBApplication")+"' version Updated");
			/*-----------------CBOS SQL DB END---------------------*/

			/*-----------------CPOS SQL DB ---------------------*/
			String cposDBVersion = versionObj.getSQLDBVersion("CPOS DB",
					config.getProperty("MVNO_GBR_DBSQLServer"),
					config.getProperty("MVNO_GBR_DBName"),
					config.getProperty("MVNO_GBR_DBUsername"), 
					config.getProperty("MVNO_GBR_DBPassword"),
					config.getProperty("CPOS_DBQuery"));
			//String product,String sqlserver, String sqldbname, String sqlusername, String sqlpassword, String QueryToExecute
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("CPOS_DBApplication"), cposDBVersion);
			System.out.println("'"+config.getProperty("CPOS_DBApplication")+"' version Updated");
			/*-----------------CPOS SQL DB END---------------------*/

			/*-----------------CRM SQL DB ---------------------*/
			String crmDBVersion = versionObj.getSQLDBVersion("CRM DB",
					config.getProperty("MVNO_GBR_DBSQLServer"),
					config.getProperty("MVNO_GBR_DBName"),
					config.getProperty("MVNO_GBR_DBUsername"), 
					config.getProperty("MVNO_GBR_DBPassword"),
					config.getProperty("CRM_DBQuery"));
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("CRM_DBApplication"), crmDBVersion);
			System.out.println("'"+config.getProperty("CRM_DBApplication")+"' version Updated");
			/*-----------------CRM SQL DB END---------------------*/

			/*-----------------DSM SQL DB ---------------------*/
			String dsmDBVersion = versionObj.getSQLDBVersion("DSM DB",
					config.getProperty("MVNO_GBR_DBSQLServer"),
					config.getProperty("MVNO_GBR_DBName"),
					config.getProperty("MVNO_GBR_DBUsername"), 
					config.getProperty("MVNO_GBR_DBPassword"),
					config.getProperty("DSM_DBQuery"));
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("DSM_DBApplication"), dsmDBVersion);
			System.out.println("'"+config.getProperty("DSM_DBApplication")+"' version Updated");
			/*-----------------DSM SQL DB END---------------------*/

			/*-----------------ESHOP SQL DB ---------------------*/
			String eshopDBVersion = versionObj.getSQLDBVersion("ESHOP DB",
					config.getProperty("ESHOP_DBSQLServer"),
					config.getProperty("ESHOP_DBName"),
					config.getProperty("ESHOP_DBUsername"), 
					config.getProperty("ESHOP_DBPassword"),
					config.getProperty("ESHOP_DBQuery"));
			updateVersionInExcel(config.getProperty("GBR_Country"), config.getProperty("ESHOP_DBApplication"), eshopDBVersion);
			System.out.println("'"+config.getProperty("ESHOP_DBApplication")+"' version Updated");
			/*-----------------ESHOP SQL DB END---------------------*/

			
			/******** MVNO GBR DETAILS END **********/

			
			
			
			/******** MVNO USA DETAILS **********/
			
			
			/*-----------------CBOS SQL DB ---------------------*/
			String cbosDBVersion_USA = versionObj.getSQLDBVersion("CBOS DB",
					config.getProperty("MVNO_USA_DBSQLServer"),
					config.getProperty("MVNO_USA_DBName"),
					config.getProperty("MVNO_USA_DBUsername"), 
					config.getProperty("MVNO_USA_DBPassword"),
					config.getProperty("CBOS_DBQuery"));
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("CBOS_DBApplication"), cbosDBVersion_USA);
			System.out.println("'"+config.getProperty("CBOS_DBApplication")+"' version Updated");
			/*-----------------CBOS SQL DB END---------------------*/

			/*-----------------CPOS SQL DB ---------------------*/
			String cposDBVersion_USA = versionObj.getSQLDBVersion("CPOS DB",
					config.getProperty("MVNO_USA_DBSQLServer"),
					config.getProperty("MVNO_USA_DBName"),
					config.getProperty("MVNO_USA_DBUsername"), 
					config.getProperty("MVNO_USA_DBPassword"),
					config.getProperty("CPOS_DBQuery"));
			//String product,String sqlserver, String sqldbname, String sqlusername, String sqlpassword, String QueryToExecute
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("CPOS_DBApplication"), cposDBVersion_USA);
			System.out.println("'"+config.getProperty("CPOS_DBApplication")+"' version Updated");
			/*-----------------CPOS SQL DB END---------------------*/

			/*-----------------CRM SQL DB ---------------------*/
			String crmDBVersion_USA = versionObj.getSQLDBVersion("CRM DB",
					config.getProperty("MVNO_USA_DBSQLServer"),
					config.getProperty("MVNO_USA_DBName"),
					config.getProperty("MVNO_USA_DBUsername"), 
					config.getProperty("MVNO_USA_DBPassword"),
					config.getProperty("CRM_DBQuery"));
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("CRM_DBApplication"), crmDBVersion_USA);
			System.out.println("'"+config.getProperty("CRM_DBApplication")+"' version Updated");
			/*-----------------CRM SQL DB END---------------------*/

			/*-----------------DSM SQL DB ---------------------*/
			String dsmDBVersion_USA = versionObj.getSQLDBVersion("DSM DB",
					config.getProperty("MVNO_USA_DBSQLServer"),
					config.getProperty("MVNO_USA_DBName"),
					config.getProperty("MVNO_USA_DBUsername"), 
					config.getProperty("MVNO_USA_DBPassword"),
					config.getProperty("DSM_DBQuery"));
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("DSM_DBApplication"), dsmDBVersion_USA);
			System.out.println("'"+config.getProperty("DSM_DBApplication")+"' version Updated");
			/*-----------------DSM SQL DB END---------------------*/

			/*-----------------ESHOP SQL DB ---------------------*/
			String eshopDBVersion_USA = versionObj.getSQLDBVersion("ESHOP DB",
					config.getProperty("ESHOP_USA_DBSQLServer"),
					config.getProperty("ESHOP_USA_DBName"),
					config.getProperty("ESHOP_USA_DBUsername"), 
					config.getProperty("ESHOP_USA_DBPassword"),
					config.getProperty("ESHOP_DBQuery"));
			updateVersionInExcel(config.getProperty("USA_Country"), config.getProperty("ESHOP_DBApplication"), eshopDBVersion_USA);
			System.out.println("'"+config.getProperty("ESHOP_DBApplication")+"' version Updated");
			/*-----------------ESHOP SQL DB END---------------------*/
			
			
			/******** MVNO USA DETAILS END **********/
			
			
			
			
			/*-----------------REPORT SQL DB ---------------------*/
			String reportDBVersion = versionObj.getSQLDBVersion("REPORT DB",
					config.getProperty("REPORT_DBSQLServer"),
					config.getProperty("REPORT_DBName"),
					config.getProperty("REPORT_DBUsername"), 
					config.getProperty("REPORT_DBPassword"),
					config.getProperty("REPORT_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("REPORT_DBApplication"), reportDBVersion);
			System.out.println("'"+config.getProperty("REPORT_DBApplication")+"' version Updated");
			/*-----------------REPORT SQL DB END---------------------*/
			
			/*-----------------TT SQL DB ---------------------*/
			String ttDBVersion = versionObj.getSQLDBVersion("TT DB",
					config.getProperty("TT_DBSQLServer"),
					config.getProperty("TT_DBName"),
					config.getProperty("TT_DBUsername"), 
					config.getProperty("TT_DBPassword"),
					config.getProperty("TT_DBQuery"));
			updateVersionInExcelAllCountry(ALL_Country, config.getProperty("TT_DBApplication"), ttDBVersion);
			System.out.println("'"+config.getProperty("TT_DBApplication")+"' version Updated");
			/*-----------------TT SQL DB END---------------------*/


			
			
			
			/************************** SQL DATABASE END ********************************************/
			
			
			
			/*********************************** ALL DETAILS END ********************************************/
			
			
			
			String writePath=config.getProperty("WriteExcelPath");
			String writeFileName=config.getProperty("WriteFileName");

			//Create the given write path
			File theDir = new File("//\\"+writePath);
			if (!theDir.exists()) {
				try{
					theDir.mkdir();
					System.out.println("New folder created");
				} 
				catch(Exception se){
					System.out.println("Exception occured while creating folder -> "+se);
				}        
			}

			//Writing the Output Excels
			fout=new FileOutputStream(new File("//\\"+writePath+"\\"+writeFileName));
			wb.write(fout);
			System.out.println("\nNew File Path : " +writePath+"\\"+writeFileName);
			System.out.println("************Completed************");

		}catch(Exception e){
			System.out.println("Exception occured -> "+e);
			e.printStackTrace();
		}finally{
			fis.close();
			fout.close();
			wb.close();
		}

	}

	public String connectExecuteUnixCommand(String product,String ipaddress, String username, String password, String command){

		String strInputStream="";

		try {
			System.out.println("\nGetting the Version of Product -> "+product);
			System.out.println("Connecting UNIX server with IP -> "+ipaddress);

			JSch jsch=new JSch();
			Session JSHsession=jsch.getSession(username, ipaddress);
			JSHsession.setPassword(password);
			Properties JSHProperties=new Properties();
			JSHProperties.put("StrictHostKeyChecking", "no");
			//JSHsession.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			JSHsession.setConfig(JSHProperties);
			JSHsession.connect();
			if(JSHsession.isConnected()){
				System.out.println("Server Connected!");
			}else{
				System.out.println("Not Connected");
			}

			Channel channel=JSHsession.openChannel("exec");
			ChannelExec exec=(ChannelExec)channel;
			//System.out.println("Executing the Command : "+command);
			exec.setCommand(command);
			exec.connect();

			BufferedInputStream in = new BufferedInputStream(exec.getInputStream());
			byte[] contents = new byte[1024];
			int bytesRead=0;

			while( (bytesRead = in.read(contents)) != -1){
				strInputStream += new String(contents, 0, bytesRead);
				if(channel.isClosed()){
					System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
			}
			System.out.println("Command Output is : "+strInputStream);
			channel.disconnect();
			JSHsession.disconnect();
			System.out.println("DONE");

		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}
		return strInputStream;

	}
	
	public String getSQLDBVersion(String product,String sqlserver, String sqldbname, String sqlusername, String sqlpassword, String QueryToExecute){

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String version=null;

		try {
			System.out.println("\nGetting the Version of Product -> "+product);

			String dbUrl = "jdbc:sqlserver://"+ sqlserver +";DatabaseName=" + sqldbname +";";                  
			String username = sqlusername;   
			String password = sqlpassword; 

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con = DriverManager.getConnection(dbUrl,username,password);
			stmt = con.createStatement(); 
			System.out.println("SQL Connection is established from '"+sqldbname+"' DB in '"+sqlserver+"' Successfully.");

			String query = QueryToExecute;
			rs = stmt.executeQuery(query);
			rs.next();
			version = rs.getString(1).trim();

			stmt.close();
			con.close();
			System.out.println("DONE");

		}catch(NullPointerException e){
			System.out.println("Null pointer occurred in SQL DB Fetch :"+e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}
		return version;
	}

	public String getORACLEDBVersion(String product,String dbserver, String portnumber, String dbname, String dbusername, String dbpassword, String QueryToExecute){

		Connection rrbsconnection = null;
		Statement rrbsstatement = null;
		ResultSet rrbsresultset = null;
		String version=null;

		try {
			System.out.println("\nGetting the Version of Product -> "+product);
			String dbUrl = "jdbc:oracle:thin:@" + dbserver + ":" + portnumber + ":" + dbname; 
			String username = dbusername;   
			String password = dbpassword; 
			String driverName = "oracle.jdbc.OracleDriver";
			Class.forName(driverName);         
			rrbsconnection = DriverManager.getConnection(dbUrl,username,password);
			rrbsstatement = rrbsconnection.createStatement(); 
			System.out.println("RRBS DB Connection is established from '"+dbname+"' DB in '"+dbserver+"' Successfully.");

			String query = QueryToExecute;
			rrbsresultset = rrbsstatement.executeQuery(query);
			rrbsresultset.next();
			version = rrbsresultset.getString(1).trim();
			System.out.println("DONE");

		}catch(NullPointerException e){
			System.out.println("Null pointer occurred in SQL DB Fetch :"+e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}
		return version;
	}

	public static void updateVersionInExcel(String country,String application, String version) throws Exception{

		try{
			String sheetName=config.getProperty("SheetName");
			String countryName = country;
			String newVersion=version;
			String applicationName=application;

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
				//System.out.println("Total Number of Columns -> "+totalColumn);

				XSSFRow headerRow = ws.getRow(4);	
				for(int i=0; i<totalColumn; i++){
					if(headerRow.getCell(i).getStringCellValue().equals(applicationName.trim())){
						colNumberToUpdate=i;
						//System.out.println("ColNumber to Update -> "+colNumberToUpdate);
						break;
					}
				}

				int rowNum = ws.getLastRowNum() + 1;
				int colNum = ws.getRow(4).getLastCellNum();
				//System.out.println("Total RowNum -> "+rowNum);
				//System.out.println("Total COlNum -> "+colNum);
				String[][] data = new String[rowNum][colNum];
				//Get the Row Number using the Country
				for (int i = 0 ; i < rowNum ; i++) {
					//System.out.println("Row i -> "+i);
					XSSFRow row = ws.getRow(i);
					//for (int j = 0 ; j < colNum ; j++) {
					//System.out.println("Col j -> "+j);
					XSSFCell cell = row.getCell(0);
					cellValue = cellToString(cell);
					data[i][0] = cellValue ;
					//System.out.println("The Cell Value is " + cellValue);
					if(cellValue.trim().equalsIgnoreCase(countryName)){
						rowNumberToUpdate=i;
						//System.out.println("RowNumber to Update -> "+rowNumberToUpdate);
						break;
					}
					//}
				}

				if(rowNumberToUpdate != -1){	
					XSSFCell cellToUpdate = ws.getRow(rowNumberToUpdate).getCell(colNumberToUpdate);
					String oldValue=cellToString(cellToUpdate);
					System.out.println("Old Verison is -> "+oldValue);
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

		}catch(Exception e){
			System.out.println("Exception occured!");
			e.printStackTrace();

		}
	}

	public static void updateVersionInExcelAllCountry(String countries,String application, String version) throws Exception{

		try{
			String sheetName=config.getProperty("SheetName");
			//String countryNames = countries;

			//String countryName = country;
			String newVersion=version;
			String applicationName=application;

			String[] countryNames=new String[(countries.split(",").length)];
			countryNames=countries.split(",");

			for (String countryName : countryNames) {
				//System.out.println(country);
				
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
					//System.out.println("Total Number of Columns -> "+totalColumn);

					XSSFRow headerRow = ws.getRow(4);	
					for(int i=0; i<totalColumn; i++){
						if(headerRow.getCell(i).getStringCellValue().equals(applicationName.trim())){
							colNumberToUpdate=i;
							//System.out.println("ColNumber to Update -> "+colNumberToUpdate);
							break;
						}
					}

					int rowNum = ws.getLastRowNum() + 1;
					int colNum = ws.getRow(4).getLastCellNum();
					//System.out.println("Total RowNum -> "+rowNum);
					//System.out.println("Total COlNum -> "+colNum);
					String[][] data = new String[rowNum][colNum];
					//Get the Row Number using the Country
					for (int i = 0 ; i < rowNum ; i++) {
						//System.out.println("Row i -> "+i);
						XSSFRow row = ws.getRow(i);
						//for (int j = 0 ; j < colNum ; j++) {
						//System.out.println("Col j -> "+j);
						XSSFCell cell = row.getCell(0);
						cellValue = cellToString(cell);
						data[i][0] = cellValue ;
						//System.out.println("The Cell Value is " + cellValue);
						if(cellValue.trim().equalsIgnoreCase(countryName)){
							rowNumberToUpdate=i;
							//System.out.println("RowNumber to Update -> "+rowNumberToUpdate);
							break;
						}
						//}
					}

					if(rowNumberToUpdate != -1){	
						XSSFCell cellToUpdate = ws.getRow(rowNumberToUpdate).getCell(colNumberToUpdate);
						String oldValue=cellToString(cellToUpdate);
						System.out.println("Old Verison is -> "+oldValue);
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
			}
		}catch(Exception e){
			System.out.println("Exception occured!");
			e.printStackTrace();

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
