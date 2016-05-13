package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import ClientSide.ServerManager;

public class RefreshListener implements ActionListener {

	private ProjectGUI gui;
	private ServerManager sm;
	
	public RefreshListener(ProjectGUI gui, ServerManager sm) {
		this.gui = gui;
		this.sm = sm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		sm.refreshDoc(gui.getSelectedClass());
		try {
			gui.setCodeText(new String(sm.getClass(gui.getSelectedClass()).getContent(), "UTF-8"));
		} catch (Exception e) {}		
	}
}
