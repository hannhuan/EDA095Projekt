package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import ClientSide.ServerManager;
import Util.Doc;
import Util.Paket;


public class SubmitListener implements ActionListener {
	private ProjectGUI gui;
	private ServerManager sm;
	
	public SubmitListener(ProjectGUI gui, ServerManager sm) {
		this.gui = gui;
		this.sm = sm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sm.saveSubmit(gui.getSelectedClass(), gui.getCodeText());
			
			if(sm.allowedToSubmit(gui.getSelectedClass())){
				sm.sendPaket(new Paket("submit", new Doc(gui.getSelectedClass(), gui.getCodeText().getBytes())));
			} else {
				gui.chat(new Doc("/SYSTEM", ": Cant submit while having received code!".getBytes()));
			}
			
		} catch (Exception e1) {}
	}

}
