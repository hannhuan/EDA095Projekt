package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class NewClassListener implements ActionListener {
	private ObjectOutputStream oos;
	private HashMap<String, String> classes;

	public NewClassListener(ObjectOutputStream oos, HashMap<String, String> classes) {
		this.oos = oos;
		this.classes = classes;
	}

	/**Creates a String with a new className and send that String to the output*/
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = JOptionPane.showInputDialog("Write a name for your new class", null);
		//oos.println("§+" + classTitle);
	}

}
