import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

	class SendChatListener implements ActionListener {
		private JTextField newChatText;
		private PrintWriter out;

		public SendChatListener(JTextField newChatText, PrintWriter out) {
			this.newChatText = newChatText;
			this.out = out;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			out.println(newChatText.getText());
			newChatText.setText("");
		}
	}