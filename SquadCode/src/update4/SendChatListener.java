package update4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

	class SendChatListener implements ActionListener {
		private ProjectGUI gui;

		public SendChatListener(ProjectGUI gui) {
			this.gui = gui;
		}

		public void actionPerformed(ActionEvent arg0) {
			gui.sendChat();
		}
	}