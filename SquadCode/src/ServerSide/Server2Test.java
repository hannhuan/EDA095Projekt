package ServerSide;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;

import Util.Paket;

public class Server2Test {

	private static int port = 30000;
	private ArrayList<Socket> clients;

	public Server2Test() {
		clients = new ArrayList<Socket>();
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server up");


				Socket client = server.accept();
				System.out.println(
						"Made connection with: " + client.getInetAddress().toString() + "on: " + client.getPort());
				System.out.println("OK");
				
//				System.out.println("-SKA TA EMOT-");
//				File file = new File("src/pic1R.jpg");
//				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
//				byte[] content = (byte[]) ois.readObject();
//				Files.write(file.toPath(), content);
//				System.out.println("-END-");
				
				System.out.println("START RECEIVE");
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				Paket pac1r = (Paket) ois.readObject();
				System.out.println("END RECEIVE");
				System.out.println("TYPE: " + pac1r.getType());
				System.out.println("CONTENT: " + new String(pac1r.getContent(), "UTF-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server2Test server2Test = new Server2Test();
	}

}
