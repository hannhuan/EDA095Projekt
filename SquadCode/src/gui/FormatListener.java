package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Util.Doc;


public class FormatListener implements ActionListener {
	private ProjectGUI gui;
	
	public FormatListener(ProjectGUI gui) {
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		gui.chat(new Doc("/SYSTEM", " Feature only available in the premium version".getBytes()));
	}
}
