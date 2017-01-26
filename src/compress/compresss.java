package compress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public class compresss {
	public static void main(String[] args) throws Exception {
		
		String file = "C:\\Users\\laks3339\\Desktop\\READ\\TestReport_27_12_2016_071338_PM_2.html";
		String fileWrite = "C:\\Users\\laks3339\\Desktop\\READ\\TestReport_27_12_2016_071338_PM_2_COM.html";
		String html = "", sCurrentLine;
		  Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        String dateStart =sdf.format(cal.getTime());
	        System.out.println( dateStart );
		
	    try {
	    	System.out.println("Started compressing");
	        BufferedReader input = new BufferedReader(new FileReader(file));
	        while ((sCurrentLine = input.readLine()) != null) {
	        	System.out.println("Line: "+sCurrentLine);
	            html += sCurrentLine;
	        }
	        HtmlCompressor compressor = new HtmlCompressor();
	        String compressedHtml = compressor.compress(html);
	        System.out.println(("Compiling Resource... " + file));
	        
	        FileWriter output = new FileWriter(fileWrite);
	        BufferedWriter bw = new BufferedWriter(output);
	        bw.write(compressedHtml);
	        bw.close();
	        
	        String dateend =sdf.format(cal.getTime());
	        System.out.println( "Started : "+dateStart );
	        System.out.println( "Ended "+dateend );
	        input.close();
	    } catch (Exception e) {
	        System.out.println("Asset Pipeline Error: " + e.getMessage());
	    }
	}
	
}
