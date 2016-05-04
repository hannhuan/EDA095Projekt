
import java.util.ArrayList;
import java.util.List;

public class ClientManagerP {
	private List<ChatThreadP> clients;

	public ClientManagerP() {
		clients = new ArrayList<ChatThreadP>();
	}

	public void addClient(ChatThreadP thread) {
		clients.add(thread);
	}

	public void removeClient(ChatThreadP thread) {
		clients.remove(thread);
	}

	public void writeToAll(String line) {
		for (ChatThreadP thread : clients) {
			thread.write(line);
		}
	}
}
