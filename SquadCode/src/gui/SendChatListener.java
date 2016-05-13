package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

import ClientSide.ServerManager;

	class SendChatListener implements ActionListener {
		private ProjectGUI gui;
		private ServerManager sm;

		public SendChatListener(ProjectGUI gui, ServerManager sm) {
			this.gui = gui;
			this.sm = sm;
		}

		public void actionPerformed(ActionEvent arg0) {
			sm.sendChat(gui.getChatMsg());
		}
	}