import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class NewClassListener implements ActionListener {
	private PrintWriter out;
	private HashMap<String, String> classes;

	public NewClassListener(PrintWriter out, HashMap<String, String> classes) {
		this.out = out;
		this.classes = classes;
	}

	/**Creates a String with a new className and send that String to the output*/
	public void actionPerformed(ActionEvent arg0) {
		String classTitle = JOptionPane.showInputDialog("Write a name for your new class", null);
		out.println("§+" + classTitle);
	}

}
