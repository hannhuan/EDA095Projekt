package ServerSide;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;

public class Server {
	
	private static int port = 30000;
	private ArrayList<Socket> clients;
	private ArrayList<Thread> clientConns;
	
	public Server(){
		clients = new ArrayList<Socket>();
		clientConns = new ArrayList<Thread>();
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server up");
			
			while(true){
				Socket client = server.accept();
				System.out.println("Made connection with: " + client.getInetAddress().toString() + "on: " + client.getPort());
				clients.add(client);
				System.out.println("ADDED client");
				
				Thread listener = new ServStreamL(this, client);
				clientConns.add(listener);
				listener.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getStatus(){
		for(Socket s : clients){
			try {
				PrintWriter out = new PrintWriter(new BufferedOutputStream(s.getOutputStream()), true);
				out.println("Welcome user to the shittiest server ever.");
			} catch (IOException e) { }
		}
	}
	
	public void getConnected(Socket theOneWhoAsked){
		try {
			PrintWriter out = new PrintWriter(new BufferedOutputStream(theOneWhoAsked.getOutputStream()), true);
			for(Socket s : clients){
				out.println("User on: " + s.getInetAddress().toString());
			}
		} catch (IOException e) { }
	}
	
	public void receiveFile(byte[] content){
		try {
			File file = new File("src/pic1R.jpg");
			Files.write(file.toPath(), content);
			System.out.println("File saved");
		} catch (IOException e) {
			System.out.println("Did could not save file");
		}
	}
	
	public void disconnUsr(Socket client){
		clients.remove(client);
	}
	
	public static void main(String[] args) {
		Server server = new Server();
	}

}
