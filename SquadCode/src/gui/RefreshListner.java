package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshListner implements ActionListener {

	private ProjectGUI gui;

	public RefreshListner(ProjectGUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.refreshDoc();
	}

}
