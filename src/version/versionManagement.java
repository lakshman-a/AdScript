package version;

import java.io.BufferedInputStream;
import java.util.Properties;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class versionManagement {

	public static void main(String[] args) {
		
		versionManagement versionObj = new versionManagement();
		versionObj.connectExecuteUnixCommand("EXIBS","192.168.151.66", "testteam", "testteam", "cat /opt/product/testteam/Products/LAKSHMAN/EXIBS/USA/1.1.19.0/EXIBSVers.txt");

	}
	
	public void connectExecuteUnixCommand(String product,String ipaddress, String username, String password, String command){

		try {
			System.out.println("Getting the Version of Product -> "+product);
			System.out.println("Connecting UNIX server with IP -> "+ipaddress);
			
			JSch jsch=new JSch();
			Session JSHsession=jsch.getSession(username, ipaddress);
			JSHsession.setPassword(password);
			Properties JSHProperties=new Properties();
			JSHProperties.put("StrictHostKeyChecking", "no");
			//JSHsession.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			JSHsession.setConfig(JSHProperties);
			JSHsession.connect();
			if(JSHsession.isConnected()){
				System.out.println("Server Connected!");
			}else{
				System.out.println("Not Connected");
			}

			Channel channel=JSHsession.openChannel("exec");
			ChannelExec exec=(ChannelExec)channel;
			System.out.println("Executing the Command : "+command);
			exec.setCommand(command);
			exec.connect();

			BufferedInputStream in = new BufferedInputStream(exec.getInputStream());
			byte[] contents = new byte[1024];
			int bytesRead=0;
			String strInputStream = "";

			while( (bytesRead = in.read(contents)) != -1){
				strInputStream += new String(contents, 0, bytesRead);
				if(channel.isClosed()){
					System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
			}
			System.out.println("Command Output is : "+strInputStream);
			channel.disconnect();
			JSHsession.disconnect();
			System.out.println("DONE");

		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}
		
	}
	
	
}
