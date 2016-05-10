package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import Util.Doc;
import Util.Paket;


public class SubmitListner implements ActionListener {
	private ProjectGUI gui;
	private ObjectOutputStream oos;

	public SubmitListner(ProjectGUI gui, ObjectOutputStream oos) {
		this.gui = gui;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			oos.writeObject(new Paket("submit", new Doc(gui.getSelectedClass(), gui.getCodeText().getBytes())));
		} catch (IOException e1) {}
		//gui.editCode();
	}

}
