package ClientSide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;

import Util.Paket;

public class Client {
	
	public static void main(String[] args){
		try {
			Socket s = new Socket(InetAddress.getLocalHost(), 30000);
			
			System.out.println("Connected");
			
			Thread listener = new ClientStreamL(s);
			listener.start();
			Thread Writer = new ClientStreamW(s);
			Writer.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
