package defaultPackage;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Lobby {
	
	private List<Socket> people;
	private List<HashMap <String, LobbyClass>> klasser;
	
	public Lobby(){
		
	}
	
	public List<Socket> getPeople(){
		return people;
	}
	
	public List<HashMap <String, LobbyClass>> getClasses(){
		return klasser;
	}
	

}
