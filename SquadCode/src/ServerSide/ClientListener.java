package ServerSide;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Util.Doc;
import Util.Paket;

public class ClientListener extends Thread {
	private Socket socketclient;
	private ObjectOutputStream oos;
	private ClientManager manager;
	private ObjectInputStream ois;

	public ClientListener(Socket socketclient, ClientManager manager) throws IOException {
		this.socketclient = socketclient;
		this.manager = manager;
		this.oos = new ObjectOutputStream(socketclient.getOutputStream());
		this.ois = new ObjectInputStream(socketclient.getInputStream());
	}

	public void run() {
		try {
			String clientID = socketclient.getRemoteSocketAddress().toString();
			System.out.println(clientID + "\t" + getName() + "\t" + "connected");

			String type = null;
			
			while (!socketclient.isClosed()) {
				Paket pac = (Paket) ois.readObject();
				type = pac.getType();
				System.out.println("A paket was sent, type is: " + pac.getType());
				
				if(type.equals("chat")){
					manager.sendToAll(pac);
				}
				else if(type.equals("submit")){
					manager.sendToAll(pac);
					manager.sendToAll(new Paket("chat", new Doc("/SERVER", (": New submit in " + 
							pac.getDoc().getTitle()).getBytes())));
					
				}else if(type.equals("newclass")){
					manager.sendToAll(pac);
					manager.sendToAll(new Paket("chat", new Doc("/SERVER", (": Added " + 
							pac.getDoc().getTitle()).getBytes())));
				}else if(type.equals("removeclass")){
					manager.sendToAll(pac);
					manager.sendToAll(new Paket("chat", new Doc("/SERVER", (": Removed " + 
							pac.getDoc().getTitle()).getBytes())));
				}
			}
		} catch (Exception e2) {
			try {
				System.err.println("Closing connection with " + socketclient.getRemoteSocketAddress().toString());
				manager.removeClient(this);
				socketclient.close();
			} catch (IOException e1) {
			}
		}
	}
	
	public void send(Paket pac) {
		try {
			oos.writeObject(pac);
		} catch (IOException e) {}
	}
}
