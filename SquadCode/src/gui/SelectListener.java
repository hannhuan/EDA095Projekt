package gui;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ClientSide.ServerManager;
import Util.Doc;

public class SelectListener implements ListSelectionListener {
	private ProjectGUI gui;
	private ServerManager sm;

	public SelectListener(ProjectGUI gui, ServerManager sm) {
		this.sm = sm;
		this.gui = gui;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (!arg0.getValueIsAdjusting()) {
			try {
				String newcode = new String(sm.getClass(gui.getSelectedClass()).getContent(), "UTF-8");
				gui.setCodeText(newcode);
				
			} catch (Exception e) {
			}
		}
	}

}
