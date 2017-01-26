package temp;

import java.io.File;

public class CheckFileExist {

	public static void main(String[] args) {


		String filePathString="C:\\Users\\laks3339.PLINTRON\\Desktop\\TEMP_WORKSPACE\\READ\\DO_ONLINE_TOPUP_100.xls";

		File f = new File(filePathString);
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("File Exist");
		}else{
			System.out.println("FIle not avail...!");
		}
	}

}
