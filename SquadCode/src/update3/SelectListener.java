package update3;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SelectListener implements ListSelectionListener {
	private ProjectGUI gui;
	

	public SelectListener(ProjectGUI gui) {
		this.gui = gui;
	}

	@Override
		public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
        	gui.setCodeText();
        }
    }

}
