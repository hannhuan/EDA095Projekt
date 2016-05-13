package update2;

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
	private ClientManager manager;
	private String threadName;

	public ChatThreadP(Socket socketclient, ClientManager manager) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.out = new PrintWriter(socketclient.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
		this.threadName = this.getName();
	}

	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			String clientname = this.getName();
			System.out.println(clientID + "\t" + clientname + "\t" + "connected");

			String line = null;
			loop: while (((line = in.readLine()) != null && line.length()>1)) {
				switch (line.substring(0, 2)) {
				case "U:":
					System.out.println(threadName + " changed Username to: " + line.substring(2).trim());
					this.setThreadName(line.substring(2).trim());
					break;
				case "Q:":
					System.err.println("Closing connection with client: " + threadName);
					manager.removeClient(this);
					socketclient.close();
					break loop;
				default:
					System.out.println("Message from " + threadName + " to everyone: " + line);
					manager.writeToAll(line, threadName);
					break;
				}
			}
		} catch (IOException e) {
			try {
				System.err.println("Closing connection with client: " + threadName);
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

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String newName) {
		threadName = newName;
	}
}
