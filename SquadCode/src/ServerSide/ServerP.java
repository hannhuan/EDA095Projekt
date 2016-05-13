package ServerSide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerP {

	public static void main(String[] args) throws IOException {
		//int port = 30000;
		ServerSocket serversocket = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("Server is Up: " + args[0]);
		System.out.println("IP is: " + InetAddress.getLocalHost());
		ClientManager manager = new ClientManager();
		

		while (true) {
			Socket socketclient = serversocket.accept();
			ClientListener thread = new ClientListener(socketclient, manager);
			manager.addClient(thread);
			thread.start();
		}
	}
}
