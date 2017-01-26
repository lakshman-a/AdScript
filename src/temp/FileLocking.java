package temp;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public class FileLocking {
  public static void main(String[] args) throws Exception {
   // FileOutputStream fos = new FileOutputStream("file.txt");
    
    
    //File ff = new File("//\\" +path +fileName);
    
    File ff = new File("//\\" +"192.168.110.157\\d$\\temp\\CRM\\CRMService\\CRMServiceGBR_LYCA.log");
    
    FileOutputStream fos = new FileOutputStream(ff);
    
    FileLock fl = fos.getChannel().tryLock();
    if (fl != null) {
      System.out.println("Locked File");
      Thread.sleep(100);
      fl.release();
      System.out.println("Released Lock");
    }
    fos.close();
  }
} ///:~