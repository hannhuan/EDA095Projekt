package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class LobbyList {
	private List<Lobby> lobbyList;
	
	
	public LobbyList(){
		lobbyList = new ArrayList<Lobby>();
	}
	
	public void addLobby (Lobby lobby){
		lobbyList.add(lobby);
	}
	
	public void deleteLobby (Lobby lobby){
		lobbyList.indexOf(lobby);
	}

}
