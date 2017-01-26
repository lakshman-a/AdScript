
package temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.io.*;
    public class Classname
    {
        public static void main(String args[])
        {
        File file =new File("C:\\Users\\LAKSHMAN\\Desktop\\check.txt");
        Scanner in = null;
        boolean found=false;
        try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                String line=in.nextLine();
                if(line.contains("TID000")){
                	System.out.println(line);
                	
                	Pattern p = Pattern.compile("TID(//d*)");
                	Matcher m = p.matcher(line);

                	while (m.find()){
                	    System.out.println(m.group(1));
                	}
                	
                	found=true;
                	
                }
            }
            
            if(found){
            	System.out.println("Line is found, extract the word");
            }
            
            System.out.println("Program end");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }}

