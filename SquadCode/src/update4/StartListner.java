package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;


public class StartListner implements ActionListener {
	private StartGUI start;
	private PrintWriter out;
	
	public StartListner(StartGUI start, PrintWriter out) {
		this.start = start;
		this.out = out;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String lobbyName = start.getSelectedLobby();
		if(lobbyName != null){
			out.println("$*" + lobbyName);
		}
	}

}
