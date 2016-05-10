package update4;

import java.util.HashMap;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SelectListener implements ListSelectionListener {
	private ProjectGUI gui;
	private HashMap<String, String> classes;

	public SelectListener(ProjectGUI gui, HashMap<String, String> classes) {
		this.classes = classes;
		this.gui = gui;
	}

	@Override
		public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
        	gui.setCodeText(classes.get(gui.getSelectedClass()));
        }
    }

}
