package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;


public class LoginListner implements ActionListener {
	private StartGUI gui;
	private PrintWriter out;
	
	public LoginListner(StartGUI gui, PrintWriter out){
		this.gui = gui;
		this.out = out;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.clearList();
		out.println("?");
		out.flush();
	}

}
