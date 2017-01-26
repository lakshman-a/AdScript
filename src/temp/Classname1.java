package temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.io.*;
public class Classname1
{
	public static void main(String args[]) throws InterruptedException
	{
		File file =new File("C:\\Users\\laks3339\\Desktop\\LOG_AT\\success.txt");
		Scanner in = null;
		boolean found=false;
		int i=0;
		while(i<60){
			Thread.sleep(1000);

			try {
				in = new Scanner(file);
				while(in.hasNext())
				{
					String line=in.nextLine();
					//if(line.contains("TID000")){
					//if(line.contains("Insert Topup")){
						if(line.contains("Topup Response from RRBS")){
						System.out.println(line);

						//String pattercheck="<TOPUP><REQUESTTYPE>specialtopup</REQUESTTYPE><MSISDN>9944020166</MSISDN>";
						String pattercheck="<TOPUPRESPONSE xmlns=\"\"><RETURNCODE>0</RETURNCODE><ERRDESCRITION>success</ERRDESCRITION>";
						//String pattercheck="()<responsemessage>Payment Authorized</responsemessage><parenttranid></parenttranid></paymentresponse></paymentresponse>";
						//Pattern p = Pattern.compile("TID[0-9]+");
						//Pattern p = Pattern.compile("[0-9]{10,13}");
						Pattern p = Pattern.compile(pattercheck);
						Matcher m = p.matcher(line);

						while (m.find()){
							found=true;
							System.out.println("Matcher found");
							System.out.println(m.group(0));
						}

						if(found){
							System.out.println("Paten is found, break the file read line Loop");
							break;
						}
						//found=true;

					}
				}

				if(found){
					System.out.println("TID  is found, break the Time Loop");
					break;
				}

				System.out.println("Program end");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

