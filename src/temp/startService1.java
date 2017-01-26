package temp;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class startService1 
 { 
    public static void main(String args[]) 
      { 
        try 
         { 
           //Process p=Runtime.getRuntime().exec("sc \\\\192.168.110.157 query Autotop_Automation"); //sc query is commond and browser is the name of service
           Process p=Runtime.getRuntime().exec("sc \\\\192.168.110.157 start Autotop_Automation");
           
           
BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 

           String line=reader.readLine();
           while(line!=null) 
            { 
              if(line.trim().startsWith("STATE"))

               {

                if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1"))
    System.out.println("Stopped");
else
    if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2"))
        System.out.println("Startting....");
    else
        if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3"))
            System.out.println("Stopping....");
        else
            if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4"))
                System.out.println("Running");

  }
   line=reader.readLine(); 
   } 

 } 

 catch(IOException e1) { } 



   } 
 } 