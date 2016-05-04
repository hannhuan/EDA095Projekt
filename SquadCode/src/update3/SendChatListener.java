package update3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

	class SendChatListener implements ActionListener {
		private JTextField newChatText;
		private PrintWriter out;
		private String username;
		

		public SendChatListener(JTextField newChatText, String username, PrintWriter out) {
			this.newChatText = newChatText;
			this.out = out;
			this.username = username;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			out.println(username + ": " + newChatText.getText());
			newChatText.setText("");
		}
	}