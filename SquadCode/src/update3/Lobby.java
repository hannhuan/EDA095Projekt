package update3;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lobby {
	
	private String name;
	private List<Socket> people;
	private HashMap <String, LobbyClass> klasser;
	
	public Lobby(String name){
		this.name = name;
		people = new ArrayList<Socket>();
		klasser = new HashMap<String, LobbyClass>();
	}
	
	public void newClass(String name, String content){
		LobbyClass lclass = new LobbyClass(name, content);
		klasser.put(lclass.getName(), lclass);
	}
	
	public List<Socket> getPeople(){
		return people;
	}
	
	public HashMap <String, LobbyClass> getClasses(){
		return klasser;
	}
	

}
