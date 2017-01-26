package compress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class CheckLogi {

	public static void main(String[] args) throws Exception {
		
		String strAbsResultFileName;
		File htmlReportPath = new File("D:\\D Drive\\AUTOMATION\\WORKSPACE\\CRM\\CRM_Parallel_Framework_v3\\Reports\\ExtentReports\\");

		ArrayList<String> arrayReportHtmlNames=new ArrayList<String>();
		File[] resultFolderArray=htmlReportPath.listFiles();

		for(int fileNumber=0; fileNumber < htmlReportPath.listFiles().length; fileNumber++){
			strAbsResultFileName=resultFolderArray[fileNumber].getAbsoluteFile().getName().toString();
			if(strAbsResultFileName.contains(".html")){
				arrayReportHtmlNames.add(strAbsResultFileName);
			}
		}
		
		arrayReportHtmlNames.sort(null);

		System.out.println("No of files : "+arrayReportHtmlNames.size());

		for (String reportFile : arrayReportHtmlNames) {

			System.out.println("reportFile is "+reportFile);
			String filePath ="D:\\D Drive\\AUTOMATION\\WORKSPACE\\CRM\\CRM_Parallel_Framework_v3\\Reports\\ExtentReports\\"+reportFile;
			
			String contents = FileUtils.readFileToString(new File(filePath), "UTF-8");

			System.out.println("contents");
			//contents.replaceAll("[\n\r]", "");
			//contents.replace("\n", "");
			contents = contents.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\t", "");

			FileWriter output = new FileWriter(filePath);
			BufferedWriter bw = new BufferedWriter(output);
			bw.write(contents);
			bw.close();
			System.out.println("file compressed!!");
		}
		
		System.out.println("All Done");

		/*String filePath = "C:\\Users\\laks3339\\Desktop\\TEMOP\\TestReport_CHROME__02_01_2017_101304_1.html";
		String filePathW = "C:\\Users\\laks3339\\Desktop\\TEMOP\\TestReport_CHROME__02_01_2017_101304_1.html";
		String contents = FileUtils.readFileToString(new File(filePath), "UTF-8");

		System.out.println("contents");
		//contents.replaceAll("[\n\r]", "");
		//contents.replace("\n", "");
		contents = contents.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\t", "");

		FileWriter output = new FileWriter(filePathW);
		BufferedWriter bw = new BufferedWriter(output);
		bw.write(contents);
		bw.close();
		
		System.out.println("Done");*/
	}

}
