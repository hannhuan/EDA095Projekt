package update4;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManagerP {
	private List<ChatThreadP> clients;
	private LobbyList lobbylist;

	public ClientManagerP(LobbyList lobbylist) {
		clients = new ArrayList<ChatThreadP>();
		this.lobbylist = lobbylist;
	}

	public void addClient(ChatThreadP thread) {
		clients.add(thread);
	}

	public void removeClient(ChatThreadP thread) {
		clients.remove(thread);
	}
	
	//return all lobbys that this persons is a part of
	public HashMap<String, HashMap<String, String>> getLobbys(Socket socket){
		HashMap<String, HashMap<String, String>> lobbys = new HashMap<String, HashMap<String, String>>();
		for(Lobby l : lobbylist.getLobbys()){
			if(l.getPeople().contains(socket)){
				lobbys.put(l.getLobbyName(), l.getClasses());
			}
		}
		return lobbys;
	}

	public HashMap<String, String> getLobby(String lobbyName) {
		return lobbylist.getLobby(lobbyName);
	}

	public void sendToAll(String line) {
		for (ChatThreadP thread : clients) {
			thread.send(line);
		}
	}
	
}
