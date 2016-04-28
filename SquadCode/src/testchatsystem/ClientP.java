package testchatsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientP {

	public static void main(String[] args) throws IOException, UnknownHostException {
		Socket clientsocket = new Socket(args[0], Integer.parseInt(args[1]));

		PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
		InputStream in = clientsocket.getInputStream();
		BufferedReader streamIn = new BufferedReader(new InputStreamReader(System.in));
		
		ServerListenerP listener = new ServerListenerP(in);
		listener.start();
		String line;
		
		while ((line = streamIn.readLine()) != null) {
			out.println(line);
			if(line.substring(0, 2).equals("Q:")) {
				System.out.println("You're logged out");
				System.exit(0);
			} 
		}
	}
}