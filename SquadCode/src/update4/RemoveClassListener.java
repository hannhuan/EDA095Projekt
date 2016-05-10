package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;


public class RemoveClassListener implements ActionListener {
	private ProjectGUI gui;
	private PrintWriter out;

	public RemoveClassListener(ProjectGUI gui, PrintWriter out) {
		this.gui = gui;
		this.out = out;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = gui.getSelectedClass();
		out.println("§-" + classTitle);
	}

}
