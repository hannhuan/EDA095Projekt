package update3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatThreadP extends Thread {
	private Socket socketclient;
	private PrintWriter out;
	private BufferedReader in;
	private ClientManagerP manager;

	public ChatThreadP(Socket socketclient, ClientManagerP manager) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.out = new PrintWriter(socketclient.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));

	}

	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			System.out.println(clientID + "\t" + getName() + "\t" + "connected");

			String line = null;
			while (((line = in.readLine()) != null)) {
					manager.writeToAll(line);
				
			}
		} catch (IOException e) {
			try {
				System.err.println("Closing connection with a clinet");
				manager.removeClient(this);
				socketclient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void write(String line) {
		out.println(line);
		out.flush();
	}
}
