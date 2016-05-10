package ServerSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import Util.Paket;

public class ServStreamL extends Thread{
	
		private Socket client;
		private Server server;
	
		public ServStreamL(Server server, Socket s){
			this.client = s;
			this.server = server;
			setName("TEST SOCKET");
		}
		
		public void run(){
			try {
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				while (!client.isClosed()) {
					System.out.println("START RECEIVE");
					Paket pac1r = (Paket) ois.readObject();
					System.out.println("END RECEIVE");
					System.out.println("TYPE: " + pac1r.getType());
					System.out.println("CONTENT: " + new String(pac1r.getContent(), "UTF-8"));
					
					checkPaket(pac1r);
				}
				client.close();
			} catch (Exception e) {
				System.out.println("Kan inte läsa");
			}
		}
		
		public void checkPaket(Paket pac){
			System.out.println("ANALYSING PAKET");
			switch (pac.getType()){
			case "users" : 
				server.getConnected(client);
				break;
			case "file":
				server.receiveFile(pac.getContent());
				break;
			case "quit":
				server.disconnUsr(client);
				break;
			default :
				;
				break;
			}
			
		}
}
