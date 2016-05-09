
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
		int port = 30000;
		ServerSocket serversocket = new ServerSocket(port);
		System.out.println("Socket Up: " + port);
		LobbyList lobbylist = new LobbyList();
		ClientManagerP manager = new ClientManagerP(lobbylist);

		Lobby lobby1 = new Lobby("defaultLobby");
		lobby1.newClass("TurtleRace", "turtleCode");
		lobby1.newClass("Mandelbrot", "getMadelbrot()");
		lobby1.newClass("Square", "getSquare()");
		lobbylist.addLobby(lobby1);
		
		Lobby lobby2 = new Lobby("myFirstLobby");
		lobby2.newClass("annieClass", "this is annie");
		lobby2.newClass("hannaClass1", "this is hanna");
		lobby2.newClass("joakimClass", "this is joakim");
		lobby2.newClass("hannaClass2", "this is hanna");
		lobbylist.addLobby(lobby2);

		while (true) {
			Socket socketclient = serversocket.accept();
			ChatThreadP thread = new ChatThreadP(socketclient, manager, lobbylist);
			lobby1.addPerson(socketclient);
			lobby2.addPerson(socketclient);
			manager.addClient(thread);
			thread.start();
		}
	}
	
	
	
	
	/**----------------------------------------------------------------------------------------------------------*/

	private static String getTurtleRace() {
		return "\n import se.lth.cs.ptdc.window.SimpleWindow;" + "\n" + "\n public class TurtleRace {"
				+ "\n 	public static void main(String[] args) {" + "\n 		Scanner scan = new Scanner(System.in);" + "\n"
				+ "\n 	System.out.println(\"Skriv in storleken på ditt fönster\");" + "\n 	int size = scan.nextInt();" + "\n"
				+ "\n 	SimpleWindow w = new SimpleWindow(size, size, \"Turtle Race\");"
				+ "\n	 RaceTrack track = new RaceTrack((int) Math.round(size * 0.9)," + "\n		 (int) Math.round(size * 0.1));" + "\n	 track.draw(w);"
				+ "\n" + "\n 	Turtle t1 = new Turtle(w, (int) Math.round(w.getWidth() / 3)," + "\n 		track.getYStart());"
				+ "\n 	Turtle t2 = new Turtle(w, (int) Math.round(w.getWidth() / 3 * 2)," + "\n 		track.getYStart());"
				+ "\n	 RacingEvent event = new RacingEvent(track, t1, t2);" + "\n" + "\n 	while (true) {" + "\n		 w.waitForEvent();"
				+ "\n		 if (w.getEventType() == SimpleWindow.MOUSE_EVENT)" + "\n 			event.start();" + "\n 		}" + "\n 	}" + "\n }";
	}

	private static String getMadelbrot() {
		return " public class Mandelbrot {" + "\n 	public static void main(String[] args) {" + "\n 		MandelbrotGUI gui = new MandelbrotGUI();"
				+ "\n 		Generator g = new Generator();" + "\n" + "\n 		while (true) {" + "\n 			switch (gui.getCommand()) {"
				+ "\n 			case MandelbrotGUI.RENDER:" + "\n				 g.render(gui);" + "\n 				break;" + "\n 			case MandelbrotGUI.RESET:"
				+ "\n 				gui.resetPlane();" + "\n 				gui.clearPlane();" + "\n 				break;" + "\n 			case MandelbrotGUI.ZOOM:"
				+ "\n				g.render(gui);" + "\n 				break;" + "\n 			case MandelbrotGUI.QUIT:" + "\n 				System.exit(0);" + "\n 			}" + "\n		}"
				+ "\n 	}" + "\n }";

	}

	private static String getSquare() {
		return " import se.lth.cs.ptdc.shapes.Shape;" + "\n import se.lth.cs.ptdc.window.SimpleWindow;" + "\n "
				+ "\n 	public class Square extends Shape {" + "\n 		protected int side;" + "\n " + "\n 		public Square(int x, int y, int side) {"
				+ "\n 			super(x, y);" + "\n 			this.side = side;" + "\n 		}" + "\n " + "\n 	public void draw(SimpleWindow w) {"
				+ "\n 		w.moveTo(x, y);" + "\n 		w.lineTo(x+side, y);" + "\n 		w.lineTo(x+side, y+side);" + "\n 		w.lineTo(x, y+side);"
				+ "\n 		w.lineTo(x, y);" + "\n 	}" + "\n }";

	}
}
