package update3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ServerP {

	public static void main(String[] args) throws IOException {
		int port = 30000;
		ServerSocket serversocket = new ServerSocket(port);
		System.out.println("Socket Up: " + port);
		ClientManagerP manager = new ClientManagerP();
		
		
		while (true) {
			Socket socketclient = serversocket.accept();
			ChatThreadP thread = new ChatThreadP(socketclient, manager);
			thread.start();
			manager.addClient(thread);
		}
	}
}
