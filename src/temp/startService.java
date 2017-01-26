package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class startService {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String STATE_PREFIX = "STATE              : ";
		//sc \\10.8.0.1
		
		//String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "query", "Autotop_Automation", "|", "find", "/C", "\"RUNNING\""};//to check whether service is running or not

		//String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "query", "Autotop_Automation"};//to check whether service is running or not
		
		//String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "query", "Autotop_Automation","STATE"};
		
		String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "query", "Autotop_Automation","STATE=STOPPED"};
		
		//String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "start", "Autotop_Automation"};//to start service
		
		
		
		//String[] script = {"cmd.exe", "/c", "sc \\\\192.168.110.157", "stop", "Autotop_Automation"};//to stop service
		
		//Process process = new ProcessBuilder(script).start();
		

		
		  try {
	            Process process = new ProcessBuilder(script).start();
	            InputStream inputStream = process.getInputStream(); 
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                System.out.println(line);
	            }
	        } catch(Exception ex) {
	            System.out.println("Exception : "+ex);
	        }
		
		
		/*Runtime runtime = Runtime.getRuntime();
	    Process process = runtime.exec(script);//or scriptStop
	    process.waitFor();*/
		
		System.out.println("RUn");
		
		
	}
	
}
