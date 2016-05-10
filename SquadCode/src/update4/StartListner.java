package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class StartListner implements ActionListener {
	private StartGUI start;
	private ObjectOutputStream oos;
	
	public StartListner(StartGUI start, ObjectOutputStream oos) {
		this.start = start;
		this.oos = oos;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String lobbyName = start.getSelectedLobby();
		if(lobbyName != null){
			//oos.println("$*" + lobbyName);
		}
	}

}
