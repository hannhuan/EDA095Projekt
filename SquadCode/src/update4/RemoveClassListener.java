package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class RemoveClassListener implements ActionListener {
	private ProjectGUI gui;
	private ObjectOutputStream oos;

	public RemoveClassListener(ProjectGUI gui, ObjectOutputStream oos) {
		this.gui = gui;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = gui.getSelectedClass();
		//oos.println("§-" + classTitle);
	}

}
