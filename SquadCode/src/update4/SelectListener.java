package update4;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Util.Doc;


public class SelectListener implements ListSelectionListener {
	private ProjectGUI gui;
	private HashMap<String, Doc> classes;

	public SelectListener(ProjectGUI gui, HashMap<String, Doc> classes2) {
		this.classes = classes2;
		this.gui = gui;
	}

	@Override
		public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
        	try {
				String newcode = new String(classes.get(gui.getSelectedClass()).getContent(), "UTF-8");
				gui.setCodeText(newcode);
			} catch (UnsupportedEncodingException e) {}
        }
    }

}
