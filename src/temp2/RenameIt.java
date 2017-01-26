package temp2;

import java.io.File;
import java.io.FileInputStream;

public class RenameIt {

	public static void main(String[] args) throws Exception {
		
		//FileInputStream fis = new FileInputStream(new File("C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\"));
		
		//File newFile = new File("C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\");
		
		 File folder =new File("C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\");
	        
		 File[] listOfFiles = folder.listFiles();
		 
		 for (File file : listOfFiles) {
			    if (file.isFile()) {
			    	
			    	System.out.println(file.getName());
			    	
			    	File oldfile = file;
			    	
			    	File newfile=new File("C:\\Users\\laks3339\\Desktop\\TEMP_WORKSPACE\\READ\\sample.xlsx");
			    	
			    	
			    	if(oldfile.renameTo(newfile)){
			            System.out.println("File renamed");
			        }else{
			            System.out.println("Sorry! the file can't be renamed");
			        }
			        
			        
			        
			    }
			}
		 
		 
		 
		 
		 /*
		 File newfile =new File("sample.xlsx");

	        if(oldfile.renameTo(newfile)){
	            System.out.println("File renamed");
	        }else{
	            System.out.println("Sorry! the file can't be renamed");
	        }
		
	}*/
		 
	}
}
