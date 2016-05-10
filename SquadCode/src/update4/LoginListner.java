package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class LoginListner implements ActionListener {
	private StartGUI gui;
	private ObjectOutputStream oos;
	
	public LoginListner(StartGUI gui, ObjectOutputStream oos){
		this.gui = gui;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.clearList();
//		out.println("?");
//		out.flush();
	}

}
