import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class SubmitListner implements ActionListener {
	private ProjectGUI gui;

	public SubmitListner(ProjectGUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.editCode();
	}

}
