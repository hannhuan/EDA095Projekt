package update4;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Util.Paket;

public class ChatThreadP extends Thread {
	private Socket socketclient;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private ClientManagerP manager;
	private LobbyList lobbylist;
	private ObjectInputStream ois;

	public ChatThreadP(Socket socketclient, ClientManagerP manager, LobbyList lobbylist) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.oos = new ObjectOutputStream(socketclient.getOutputStream());
		//this.in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
		this.ois = new ObjectInputStream(socketclient.getInputStream());
		this.lobbylist = lobbylist;
	}

	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			System.out.println(clientID + "\t" + getName() + "\t" + "connected");

			String type = null;
			
			while (!socketclient.isClosed()) {
				System.out.println("VI KLARADE SSS HIT");
				Paket pac = (Paket) ois.readObject();
				type = pac.getType();
				System.out.println("OK förbi read");
				
				if(type.equals("chat")){
//					String usrName = pac.getDoc().getTitle();
//					String msg = new String(pac.getDoc().getContent(), "UTF-8");
					
					manager.sendToAll(pac);
				}
//				else if(line.startsWith("?")){
//					for(String lobbyname : manager.getLobbys(socketclient).keySet()){
//						out.println("?"+lobbyname);
//						out.flush();
//					}
//				}else if(line.startsWith("$*")){
//					String lobbyName = line.substring(2);
//					HashMap<String, String> currentLobby = manager.getLobby(lobbyName);
//					for(String classname : currentLobby.keySet()){
//						out.println("$1"+classname + "%" + currentLobby.get(classname));
//						out.flush();
//					}
//					out.println("$*");
//				}else if(line.startsWith("§+")){
//					manager.sendToAll(line);
//				}else if(line.startsWith("§-")){
//					manager.sendToAll(line);
//				}
			}
		} catch (Exception e2) {
			try {
				System.err.println("Closing connection with a clinet");
				manager.removeClient(this);
				socketclient.close();
				e2.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void send(Paket pac) {
//		out.println(line);
//		out.flush();
		
		try {
			oos.writeObject(pac);
		} catch (IOException e) {}
	}
}
