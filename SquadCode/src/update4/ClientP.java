package update4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

import Util.Doc;

public class ClientP {
	private static ProjectGUI gui;

	/**Starts the ClientSocket! Creates an output and input that will be connected to the server
	 * also start the first StartGUI where the user login and creates a ServerListener */
	public static void main(String[] args) throws IOException, UnknownHostException {
		Socket clientsocket = new Socket(args[0], Integer.parseInt(args[1]));
		
		PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
		ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
		
		ObjectInputStream ois = new ObjectInputStream(clientsocket.getInputStream());
		
		//StartGUI start = new StartGUI(clientsocket, oos);
		HashMap<String, Doc> hej = new HashMap<String, Doc>();
		hej.put("turtle", new Doc("turtle", "turtle kod som per gillar".getBytes()));
		ProjectGUI proj = new ProjectGUI(oos,"kalle anka", hej);
		ServerListenerP listener = new ServerListenerP(ois, proj);
		listener.start();
	}
}