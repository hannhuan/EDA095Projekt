package gui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyList{
	private ArrayList<Lobby> lobbyList;
	
	
	public LobbyList(){
		lobbyList = new ArrayList<Lobby>();
	}
	
	public void addLobby (Lobby lobby){
		lobbyList.add(lobby);
	}
	
	public void deleteLobby (Lobby lobby){
		lobbyList.indexOf(lobby);
	}
	
	public List<Lobby> getLobbys(){
		return lobbyList;
	}

	public HashMap<String, String> getLobby(String lobbyName) {
		for(Lobby l : lobbyList){
			if (l.getLobbyName().equals(lobbyName)){
				return l.getClasses();
			}
		}
		return null;		
	}
	
}