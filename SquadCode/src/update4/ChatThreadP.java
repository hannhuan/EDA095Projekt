package update4;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatThreadP extends Thread {
	private Socket socketclient;
	private PrintWriter out;
	private BufferedReader in;
	private ClientManagerP manager;
	private LobbyList lobbylist;

	public ChatThreadP(Socket socketclient, ClientManagerP manager, LobbyList lobbylist) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.out = new PrintWriter(socketclient.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
		this.lobbylist = lobbylist;
	}

	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			System.out.println(clientID + "\t" + getName() + "\t" + "connected");

			String line = null;
			while (((line = in.readLine()) != null)) {
				
				if(line.startsWith("@")){
					manager.sendToAll(line);
				}
				else if(line.startsWith("?")){
					for(String lobbyname : manager.getLobbys(socketclient).keySet()){
						out.println("?"+lobbyname);
						out.flush();
					}
				}else if(line.startsWith("$*")){
					String lobbyName = line.substring(2);
					HashMap<String, String> currentLobby = manager.getLobby(lobbyName);
					for(String classname : currentLobby.keySet()){
						System.out.println("$1"+classname + "%" + currentLobby.get(classname));
						out.println("$1"+classname + "%" + currentLobby.get(classname));
						out.flush();
					}
					out.println("$*");
				}else if(line.startsWith("§+")){
					manager.sendToAll(line);
				}else if(line.startsWith("§-")){
					manager.sendToAll(line);
				}
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
	
	public void send(String line) {
		out.println(line);
		out.flush();
	}
}
