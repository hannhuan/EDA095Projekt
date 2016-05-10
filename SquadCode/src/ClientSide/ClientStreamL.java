package ClientSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientStreamL extends Thread{
	
		private Socket server;
	
		public ClientStreamL(Socket s){
			this.server = s;
		}
		
		public void run(){
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
				while (!server.isClosed()) {
					String line = in.readLine();
					System.out.println(server.getInetAddress() + ":" + server.getPort() + ": " + line);
				}
				server.close();
			} catch (Exception e) {
				System.out.println("Kan inte läsa");
			}
		}
}
