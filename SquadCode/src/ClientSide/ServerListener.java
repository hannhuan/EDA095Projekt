package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import Util.Paket;
import gui.ProjectGUI;


public class ServerListener extends Thread {
	private ObjectInputStream ois;
	private ProjectGUI start;
	private ServerManager sm;

	public ServerListener(ObjectInputStream ois, ServerManager sm, ProjectGUI start) {
		this.ois = ois;
		this.start = start;
		this.sm = sm;
	}


	public void run() {
		System.out.println("You are now connected to the server!");
		for(;;) {	
			try {	
				String type;
				while(true) {
					Paket pac = (Paket) ois.readObject();
					type = pac.getType();
						if(type.equals("chat")){
							start.chat(pac.getDoc());
						}else if(type.equals("newclass")){
							if(sm.addNewClass(pac.getDoc())){
								start.addNewClass(pac.getDoc().getTitle());
							}
						}else if(type.equals("removeclass")){
							sm.removeClass(pac.getDoc());
							start.removeClass(pac.getDoc().getTitle());
						}else if (type.equals("submit")){
							if(!(sm.getKeySet().contains(pac.getDoc().getTitle()))){
								start.addNewClass(pac.getDoc().getTitle());
								sm.addNewClass(pac.getDoc());
								sm.receiveDoc(pac.getDoc());
								sm.refreshDoc(pac.getDoc().getTitle());
							} else {
								sm.receiveDoc(pac.getDoc());
							}
						}
				}
			} catch (Exception e) {
				return;
			}
		}
	}
}
