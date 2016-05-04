import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClientP {
	private static ProjectGUI gui;
	
	public  ClientP(){
	}

	public static void main(String[] args) throws IOException, UnknownHostException {
		Socket clientsocket = new Socket(args[0], Integer.parseInt(args[1]));
		String username = args[2];
		WorkSpace workspace = new WorkSpace();
		HashMap<String, String> classes = workspace.getWorkSpace();

		PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
		InputStream in = clientsocket.getInputStream();
		BufferedReader streamIn = new BufferedReader(new InputStreamReader(System.in));
		
		gui = new ProjectGUI(out, username, classes);
		ServerListenerP listener = new ServerListenerP(in, gui);
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