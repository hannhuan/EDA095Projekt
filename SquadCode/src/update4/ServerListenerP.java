
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ServerListenerP extends Thread {
	private BufferedReader br;
	private StartGUI start;

	public ServerListenerP(InputStream in, StartGUI start) {
		br = new BufferedReader(new InputStreamReader(in));
		this.start = start;
	}


	public void run() {
		System.out.println("You are now connected to the server!");
		for(;;) {	
			try {	
				String line;
				while(true) {
					line= br.readLine();
					if(line!=null) {
						if(line.startsWith("?")){
							start.fillList(line.substring(1));
						}else if(line.startsWith("$1")){
							String classname = line.substring(2, line.indexOf("%"));
							String codetext = line.substring(line.indexOf("%")+1);
							start.loadProject(classname, codetext);
						}else if(line.startsWith("$*")){
							start.setProject();
						}else if(line.startsWith("@")){
							start.chat(line.substring(1));
						}else if(line.startsWith("§+")){
							start.addNewClass(line.substring(2));
						}else if(line.startsWith("§-")){
							start.removeClass(line.substring(2));
						}
					}
				}
			} catch (IOException e) {
				return;
			}
		}
	}
}
