package testchatsystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerP {

	public static void main(String[] args) throws IOException {
		int port = 30000;
		ServerSocket serversocket = new ServerSocket(port);
		System.out.println("Socket Up: " + port);
		ClientManager8 manager = new ClientManager8();
		
		
		while (true) {
			Socket socketclient = serversocket.accept();
			ChatThreadP thread = new ChatThreadP(socketclient, manager);
			thread.start();
			manager.addClient(thread);
		}
	}
}
