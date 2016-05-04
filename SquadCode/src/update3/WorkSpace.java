import java.util.HashMap;


public class WorkSpace {
	private HashMap<String, String> classes;
	private String value;
	
	public WorkSpace(){
		classes = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getWorkSpace(){
		value = " import java.util.Scanner;"
		+ "\n import se.lth.cs.ptdc.window.SimpleWindow;"
		+ "\n"
		+ "\n public class TurtleRace {"
		+ "\n 	public static void main(String[] args) {"
		+ "\n 		Scanner scan = new Scanner(System.in);"
		+ "\n"
		+ "\n 	System.out.println(\"Skriv in storleken på ditt fönster\");"
		+ "\n 	int size = scan.nextInt();"
		+ "\n"
		+ "\n 	SimpleWindow w = new SimpleWindow(size, size, \"Turtle Race\");"
		+ "\n	 RaceTrack track = new RaceTrack((int) Math.round(size * 0.9),"
		+ "\n		 (int) Math.round(size * 0.1));"
		+ "\n	 track.draw(w);"
		+ "\n"
		+ "\n 	Turtle t1 = new Turtle(w, (int) Math.round(w.getWidth() / 3),"
		+ "\n 		track.getYStart());"
		+ "\n 	Turtle t2 = new Turtle(w, (int) Math.round(w.getWidth() / 3 * 2),"
		+ "\n 		track.getYStart());"
		+ "\n	 RacingEvent event = new RacingEvent(track, t1, t2);"
		+"\n"
		+ "\n 	while (true) {"
		+ "\n		 w.waitForEvent();"
		+ "\n		 if (w.getEventType() == SimpleWindow.MOUSE_EVENT)"
		+ "\n 			event.start();"
		+ "\n 		}"
		+"\n 	}"
		+"\n }";				
		classes.put("TurtleRace", value);
		
		value = " public class Mandelbrot {"
		+ "\n 	public static void main(String[] args) {"
		+ "\n 		MandelbrotGUI gui = new MandelbrotGUI();"
		+ "\n 		Generator g = new Generator();"

    	+ "\n 		while (true) {"
      	+ "\n 			switch (gui.getCommand()) {"
      	+ "\n 			case MandelbrotGUI.RENDER:"
        + "\n				 g.render(gui);"
        + "\n 				break;"
        + "\n 			case MandelbrotGUI.RESET:"
        + "\n 				gui.resetPlane();"
        + "\n 				gui.clearPlane();"
        + "\n 				break;"
        + "\n 			case MandelbrotGUI.ZOOM:"
        + "\n				g.render(gui);"
        + "\n 				break;"
        + "\n 			case MandelbrotGUI.QUIT:"
        + "\n 				System.exit(0);"
        + "\n 			}"
	    + "\n		}"
	    + "\n 	}"
		+ "\n }";
		classes.put("Mandelbrot", value);
		
		value = " import se.lth.cs.ptdc.shapes.Shape;"
		+ "\n import se.lth.cs.ptdc.window.SimpleWindow;"
		+"\n "
		+ "\n 	public class Square extends Shape {"
		+ "\n 		protected int side;"
		+ "\n "
		+ "\n 		public Square(int x, int y, int side) {"
		+ "\n 			super(x, y);"
		+ "\n 			this.side = side;"
		+ "\n 		}"
		+"\n "
		+ "\n 	public void draw(SimpleWindow w) {"
		+ "\n 		w.moveTo(x, y);"
		+ "\n 		w.lineTo(x+side, y);"
		+ "\n 		w.lineTo(x+side, y+side);"
		+ "\n 		w.lineTo(x, y+side);"
		+ "\n 		w.lineTo(x, y);"
		+ "\n 	}"
		+ "\n }";
		
		classes.put("SquareClass", value);
		
		return classes;
	}
	

}
