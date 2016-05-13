package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.DefaultCaret;

import ClientSide.ServerListener;
import ClientSide.ServerManager;

import javax.swing.JList;

import java.awt.Component;

import javax.swing.Box;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.JTextField;

import Util.Doc;

import java.awt.Label;
import java.awt.Button;

public class LogIn {
	private JFrame frame;
	private FlowLayout flowLayout;
	private SpringLayout layout;
	private JPanel mainPanel;
	private JPanel menuPanel;
	private JTextField usernameField;
	private JTextField machineField;
	private JTextField portField;
	private Label machineLabel;
	private Label portLabel;
	
	public LogIn() {

		frame = new JFrame();
		frame.setBounds(0, 0, 607, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		layout = new SpringLayout();
		mainPanel.setLayout(layout);

		menuPanel = new JPanel();
		layout.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, mainPanel);
		flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(50);
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		menuPanel.setBackground(new Color(192, 192, 192));
		mainPanel.add(menuPanel);

		usernameField = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, usernameField, 129, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, -6, SpringLayout.NORTH, usernameField);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 0, SpringLayout.EAST, usernameField);
		layout.putConstraint(SpringLayout.EAST, usernameField, -10, SpringLayout.EAST, mainPanel);
		usernameField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		usernameField.setFont(new Font("Courier New", Font.PLAIN, 30));
		mainPanel.add(usernameField);

		Label welcomeLabel = new Label("Welcome to SquadCode");
		welcomeLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		menuPanel.add(welcomeLabel);
		
		machineField = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, machineField, 183, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, machineField, -116, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, usernameField, -6, SpringLayout.NORTH, machineField);
		layout.putConstraint(SpringLayout.WEST, machineField, 125, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, machineField, -10, SpringLayout.EAST, mainPanel);
		machineField.setFont(new Font("Courier New", Font.PLAIN, 30));
		machineField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(machineField);
		
		portField = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, portField, 6, SpringLayout.SOUTH, machineField);
		layout.putConstraint(SpringLayout.WEST, portField, 0, SpringLayout.WEST, usernameField);
		layout.putConstraint(SpringLayout.SOUTH, portField, -62, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, portField, -10, SpringLayout.EAST, mainPanel);
		portField.setFont(new Font("Courier New", Font.PLAIN, 30));
		portField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(portField);
		
		machineLabel = new Label("Machine:");
		layout.putConstraint(SpringLayout.EAST, machineLabel, -6, SpringLayout.WEST, machineField);
		layout.putConstraint(SpringLayout.WEST, machineLabel, 10, SpringLayout.WEST, mainPanel);
		machineLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		mainPanel.add(machineLabel);
		
		Label usernameLabel = new Label("Username:");
		layout.putConstraint(SpringLayout.NORTH, machineLabel, 6, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.SOUTH, usernameLabel, -170, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 6, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, usernameField, 6, SpringLayout.EAST, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, mainPanel);
		usernameLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		mainPanel.add(usernameLabel);
		
		JButton btnStart = new JButton("S T A R T");
		btnStart.setBackground(new Color(204, 255, 153));
		btnStart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		layout.putConstraint(SpringLayout.NORTH, btnStart, 6, SpringLayout.SOUTH, portField);
		layout.putConstraint(SpringLayout.WEST, btnStart, 0, SpringLayout.WEST, menuPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnStart, -8, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnStart, -10, SpringLayout.EAST, mainPanel);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String username = usernameField.getText();
					String machine = machineField.getText();
					String port = portField.getText();
					
					Socket clientsocket = new Socket(machine, Integer.parseInt(port));
					ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
					ObjectInputStream ois = new ObjectInputStream(clientsocket.getInputStream());
					
					ServerManager sm = new ServerManager(oos, username, new HashMap<String, Doc>());
					
					ProjectGUI proj = new ProjectGUI(sm);
					ServerListener listener = new ServerListener(ois, sm, proj);
					listener.start();
					frame.setVisible(false);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Not able to connect!");
				}
			}
		});
		mainPanel.add(btnStart);
		
		portLabel = new Label("Port:");
		layout.putConstraint(SpringLayout.SOUTH, machineLabel, -6, SpringLayout.NORTH, portLabel);
		layout.putConstraint(SpringLayout.NORTH, portLabel, 60, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, portLabel, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, portLabel, -62, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, portLabel, -6, SpringLayout.WEST, portField);
		portLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		mainPanel.add(portLabel);

		frame.setVisible(true);
	}
}
