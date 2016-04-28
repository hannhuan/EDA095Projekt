package testchatsystem;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThreadP extends Thread {
	private Socket socketclient;
	private PrintWriter out;
	private BufferedReader in;
	private ClientManager8 manager;
	private String threadName;

	public ChatThreadP(Socket socketclient, ClientManager8 manager) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.out = new PrintWriter(socketclient.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
		this.threadName = this.getName();
	}

	@Override
	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			String clientname = this.getName();
			System.out.println(clientID + "\t" + clientname + "\t" + "connected");

			String line = null;
			loop: while (((line = in.readLine()) != null && line.length()>1)) {
				String command = line.substring(0, 2);
				String message = line.substring(2).trim();
				switch (command) {
				case "E:":
					System.out.println("Echo from " + threadName + ": " + message);
					out.println(line);
					out.flush();
					break;
				case "M:":
					System.out.println("Message from " + threadName + " to everyone: " + message);
					manager.writeToAll(line.substring(2).trim(), threadName);
					break;
				case "C:":
					System.out.println("Message from " + threadName + " to everyone: " + message.toUpperCase());
					manager.writeToAll(message.toUpperCase(), threadName);
					break;
				case "U:":
					System.out.println(threadName + " changed Username to: " + line.substring(2).trim());
					this.setThreadName(message);
					break;
				case "Q:":
					System.err.println("Closing connection with client: " + threadName);
					manager.removeClient(this);
					socketclient.close();
					break loop;
				default:
					System.out.println("Unvalid Command from client");
					out.println("Use Commands:");
					out.println("E - for echo");
					out.println("M - for message");
					out.println("U - to change username");
					out.println("Q - to quit");
					out.flush();
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
