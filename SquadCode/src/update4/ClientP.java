package update4;

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

	/**Starts the ClientSocket! Creates an output and input that will be connected to the server
	 * also start the first StartGUI where the user login and creates a ServerListener */
	public static void main(String[] args) throws IOException, UnknownHostException {
		Socket clientsocket = new Socket(args[0], Integer.parseInt(args[1]));
		
		PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
		InputStream in = clientsocket.getInputStream();
		
		StartGUI start = new StartGUI(clientsocket, out);
		
		ServerListenerP listener = new ServerListenerP(in, start);
		listener.start();
	}
}