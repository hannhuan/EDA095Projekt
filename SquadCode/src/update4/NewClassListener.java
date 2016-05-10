package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Util.Doc;
import Util.Paket;


public class NewClassListener implements ActionListener {
	private ObjectOutputStream oos;
	private HashMap<String, Doc> classes;

	public NewClassListener(ObjectOutputStream oos, HashMap<String, Doc> classes2) {
		this.oos = oos;
		this.classes = classes2;
	}

	/**Creates a String with a new className and send that String to the output*/
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = JOptionPane.showInputDialog("Write a name for your new class", null);
		//oos.println("§+" + classTitle);
		try {
			oos.writeObject(new Paket("newclass", new Doc(classTitle, "".getBytes())));
		} catch (IOException e) {}
	}

}
