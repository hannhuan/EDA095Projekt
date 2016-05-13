package ServerSide;

import java.util.ArrayList;
import java.util.List;
import Util.Paket;

public class ClientManager {
	private List<ClientListener> clients;

	public ClientManager() {
		clients = new ArrayList<ClientListener>();
	}

	public void addClient(ClientListener thread) {
		clients.add(thread);
	}

	public void removeClient(ClientListener thread) {
		clients.remove(thread);
	}

	public void sendToAll(Paket pac) {
		for (ClientListener thread : clients) {
			thread.send(pac);
		}
	}
}
