package ClientSide;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;

import Util.Paket;

public class ClientStreamW extends Thread{
	
	private Socket client;
	
	public ClientStreamW(Socket s){
		this.client = s;
		
	}
	
	public void run(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			while (!client.isClosed()) {
				System.out.println("WAITING, What is the type?");
				String type = in.readLine();
				System.out.println("FIXED TO MSG, so what is message");
				String content = in.readLine();
				
				System.out.println("START SEND");
				Paket pak1 = generatePaket(type, content);
				oos.writeObject(pak1);
				System.out.println("END SEND");
				
			}
			client.close();
		} catch (Exception e) {
			System.out.println("Kan inte skriva");
		}
	}
	private Paket generatePaket(String type, String content){
		Paket pac;
		switch (type){
		case "file":
			try {
				System.out.println("HÄR123");
				File file = new File("src/pic1.jpg");
				pac = new Paket(type, Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				pac = new Paket("ERROR", "ERROR".getBytes());
			}
			return pac;
		default:
			pac = new Paket(type, content.getBytes());
			return pac;
		}
	}

}
