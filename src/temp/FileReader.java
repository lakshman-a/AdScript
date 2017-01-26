package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileReader {

    public static void main(String args[]) throws Exception {

            //File file = new File("//\\" +"192.168.110.157\\d$\\temp\\CRM\\CRMService\\CRMServiceGBR_LYCA.log");
           // File file = new File("//\\" +"192.168.110.157\\d$\\temp\\CRM\\CRMService\\CRMService.log");
            File file = new File("C:\\Users\\laks3339.Plintron\\Desktop\\check.txt");
            File fout = new File("C:\\Users\\laks3339.Plintron\\Desktop\\out.txt");
            
            FileOutputStream fos = new FileOutputStream(fout);
            
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
           
            
            System.out.println(file.getAbsolutePath());
            if(file.exists() && file.canRead()){
            	
                long fileLength = file.length();
                
               /* System.out.println("Before first readline");
                readFile(file,0L);*/
                System.out.println("Before Second readline");
                while(true){

                    if(fileLength<file.length()){
                        readFile(file,fileLength,bw);
                        fileLength=file.length();
                    }
                }
            }
            fos.close();
            bw.close();
    }

    public static void readFile(File file,Long fileLength,BufferedWriter bw) throws IOException {
        String line = null;

        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        in.skip(fileLength);
        while((line = in.readLine()) != null)
        {
            System.out.println(line);
            bw.write(line);
    		bw.newLine();
        }
        in.close();
        
    }
}