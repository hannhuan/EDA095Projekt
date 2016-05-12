package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Util.Doc;
import Util.Paket;


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
		System.out.println("Rensar fönster");
		//oos.println("§-" + classTitle);
		try {
			oos.writeObject(new Paket("removeclass", new Doc(classTitle, "".getBytes())));
		} catch (IOException e) {}
	}

}
