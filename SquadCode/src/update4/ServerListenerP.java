package update4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import Util.Paket;


public class ServerListenerP extends Thread {
	private ObjectInputStream ois;
	private ProjectGUI start;

	public ServerListenerP(ObjectInputStream ois, ProjectGUI start) {
		this.ois = ois;
		this.start = start;
	}


	public void run() {
		System.out.println("You are now connected to the server!");
		for(;;) {	
			try {	
				String type;
				while(true) {
					Paket pac = (Paket) ois.readObject();
					type = pac.getType();
//						if(line.startsWith("?")){
//							start.fillList(line.substring(1));
//						}else if(line.startsWith("$1")){
//							String classname = line.substring(2, line.indexOf("%"));
//							String codetext = line.substring(line.indexOf("%")+1);
//							start.loadProject(classname, codetext);
//						}else if(line.startsWith("$*")){
//							start.setProject();
//						}
						if(type.equals("chat")){
							start.chat(pac.getDoc());
						}else if(type.startsWith("§+")){
							start.addNewClass(type.substring(2));
						}else if(type.startsWith("§-")){
							start.removeClass(type.substring(2));
						}
				}
			} catch (Exception e) {
				return;
			}
		}
	}
}
