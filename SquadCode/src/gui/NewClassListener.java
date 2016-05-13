package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;

import ClientSide.ServerManager;
import Util.Doc;
import Util.Paket;


public class NewClassListener implements ActionListener {
	private ServerManager sm;
	private ProjectGUI gui;

	public NewClassListener(ProjectGUI gui, ServerManager sm) {
		this.sm = sm;
		this.gui = gui;
	}

	/**Creates a String with a new className and send that String to the output*/
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = JOptionPane.showInputDialog("Write a name for your new class", null);
		try {
			if(!(sm.getKeySet().contains(classTitle))){
				sm.sendPaket(new Paket("newclass", new Doc(classTitle, "".getBytes())));
			} else {
				gui.chat(new Doc("/SYSTEM", ": Classname already exists".getBytes()));
			}
		} catch (Exception e) {}
	}

}
