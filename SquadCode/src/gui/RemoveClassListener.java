package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import ClientSide.ServerManager;
import Util.Doc;
import Util.Paket;


public class RemoveClassListener implements ActionListener {
	private ProjectGUI gui;
	private ServerManager sm;

	public RemoveClassListener(ProjectGUI gui, ServerManager sm) {
		this.gui = gui;
		this.sm = sm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = gui.getSelectedClass();
		try {
			sm.sendPaket(new Paket("removeclass", new Doc(classTitle, "".getBytes())));
		} catch (Exception e) {}
	}

}
