

import java.util.ArrayList;
import java.util.List;

public class ClientManager8 {
	private List<ChatThreadP> clients;

	public ClientManager8() {
		clients = new ArrayList<ChatThreadP>();
	}

	public void addClient(ChatThreadP thread) {
		clients.add(thread);
	}
	
	public void removeClient(ChatThreadP thread) {
		clients.remove(thread);
	}
	
	
	public void writeToAll(String line, String from) {
		System.out.println(from);
		for (ChatThreadP thread : clients) {
			if (!(thread.getThreadName().equals(from))) {
				thread.write(from + ": " + line);
			}
		}
	}
}
