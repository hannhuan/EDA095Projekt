
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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.Label;
import java.awt.Button;

public class StartGUI {
	private PrintWriter out;
	private String username;
	private HashMap<String, String> classes;
	private JFrame frame;
	private FlowLayout flowLayout;
	private SpringLayout layout;
	private JPanel mainPanel;
	private JPanel menuPanel;
	private JList<String> lobbyList;
	private DefaultListModel<String> listModel;
	private JTextField usernameTextfield;
	private JScrollPane lobbyScroll;
	private Label selectLabel;
	private Button btnStart;
	private Button btnStartnew;
	private Button btnHelp;
	private Label usernameLabel;
	private Socket clientsocket;

//	private List<ProjectGUI> guis;
	private ProjectGUI gui;

	public StartGUI(Socket clientsocket, PrintWriter out) {
		this.out = out;
		this.clientsocket = clientsocket;
		classes = new HashMap<String, String>();
		//guis = new ArrayList<ProjectGUI>();

		frame = new JFrame();
		frame.setBounds(0, 0, 741, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		layout = new SpringLayout();
		mainPanel.setLayout(layout);

		menuPanel = new JPanel();
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, mainPanel);
		flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(50);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, menuPanel, -8, SpringLayout.EAST, mainPanel);
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		menuPanel.setBackground(new Color(192, 192, 192));
		mainPanel.add(menuPanel);

		usernameTextfield = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, usernameTextfield, 6, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, usernameTextfield, 190, SpringLayout.WEST, mainPanel);
		usernameTextfield.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		usernameTextfield.setFont(new Font("Calibri", Font.PLAIN, 20));
		mainPanel.add(usernameTextfield);

		lobbyScroll = new JScrollPane();
		layout.putConstraint(SpringLayout.NORTH, lobbyScroll, 231, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, lobbyScroll, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, lobbyScroll, -8, SpringLayout.EAST, mainPanel);
		lobbyScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		lobbyScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(lobbyScroll);

		listModel = new DefaultListModel<String>();
		lobbyList = new JList<String>(listModel);
		lobbyScroll.setViewportView(lobbyList);
		layout.putConstraint(SpringLayout.WEST, lobbyList, 103, SpringLayout.EAST, usernameTextfield);
		layout.putConstraint(SpringLayout.EAST, lobbyList, -310, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, lobbyList, 6, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.SOUTH, lobbyList, -256, SpringLayout.SOUTH, mainPanel);
		lobbyList.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		lobbyList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		usernameLabel = new Label("Username:");
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 82, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, usernameLabel, -6, SpringLayout.WEST, usernameTextfield);
		layout.putConstraint(SpringLayout.SOUTH, usernameTextfield, 0, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 129, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, usernameLabel, -54, SpringLayout.NORTH, lobbyScroll);
		usernameLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));

		Label welcomeLabel = new Label("Welcome to SquadCode");
		welcomeLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		menuPanel.add(welcomeLabel);
		mainPanel.add(usernameLabel);

		selectLabel = new Label("Select a lobby:");
		layout.putConstraint(SpringLayout.SOUTH, selectLabel, -5, SpringLayout.NORTH, lobbyScroll);
		layout.putConstraint(SpringLayout.EAST, selectLabel, -267, SpringLayout.EAST, mainPanel);
		selectLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		mainPanel.add(selectLabel);

		Button btnLogin = new Button("Log In");
		layout.putConstraint(SpringLayout.EAST, usernameTextfield, -6, SpringLayout.WEST, btnLogin);
		layout.putConstraint(SpringLayout.NORTH, btnLogin, 129, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnLogin, -54, SpringLayout.NORTH, lobbyScroll);
		layout.putConstraint(SpringLayout.WEST, btnLogin, 554, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnLogin, -63, SpringLayout.EAST, mainPanel);
		btnLogin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		btnLogin.addActionListener(new LoginListner(this, out));
		mainPanel.add(btnLogin);

		btnStart = new Button("Start");
		layout.putConstraint(SpringLayout.NORTH, btnStart, 386, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, lobbyScroll, -6, SpringLayout.NORTH, btnStart);
		layout.putConstraint(SpringLayout.WEST, btnStart, -140, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnStart, -10, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnStart, 0, SpringLayout.EAST, menuPanel);
		btnStart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		btnStart.setBackground(new Color(240, 255, 240));
		btnStart.addActionListener(new StartListner(this, out));
		mainPanel.add(btnStart);

		btnStartnew = new Button("Start with new a new lobby");
		layout.putConstraint(SpringLayout.NORTH, btnStartnew, 6, SpringLayout.SOUTH, lobbyScroll);
		layout.putConstraint(SpringLayout.WEST, btnStartnew, 256, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnStartnew, -10, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnStartnew, -20, SpringLayout.WEST, btnStart);
		btnStartnew.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		btnStartnew.setBackground(new Color(240, 255, 255));
		btnStartnew.addActionListener(new StartListner(this, out));
		mainPanel.add(btnStartnew);

		btnHelp = new Button("Help");
		btnHelp.setBackground(new Color(248, 248, 255));
		layout.putConstraint(SpringLayout.NORTH, btnHelp, 6, SpringLayout.SOUTH, lobbyScroll);
		layout.putConstraint(SpringLayout.WEST, btnHelp, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnStart);
		layout.putConstraint(SpringLayout.EAST, btnHelp, 100, SpringLayout.WEST, mainPanel);
		btnHelp.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		mainPanel.add(btnHelp);

		frame.setVisible(true);
	}
	
	
	
	/**Clears the list of lobbys*/
	public void clearList() {
		listModel.clear();
	}

	/**Adds a lobbyName to the list of lobbys*/
	public void fillList(String lobbyName) {
		listModel.addElement(lobbyName);
	}

	/**Chats a message to the lobbyChat*/
	public void chat(String line) {
			gui.chat(line);
	}

	/**Starts a new GUI with a selected lobby and closes the StartGUI*/
	public void setProject() {
		username = usernameTextfield.getText();
		try {
			gui = new ProjectGUI(out, username, classes);
			frame.setVisible(false);
		} catch (IOException e) {}
	}
	
	/**Adds a new class to this lobby*/
	public void addNewClass(String classTitle) {
		gui.addNewClass(classTitle);
	}
	
	/**Removes the selected class*/
	public void removeClass(String classTitle) {
		gui.removeClass(classTitle);
	}

	/**@return the selected lobby from the lobbyList*/
	public String getSelectedLobby() {
		return lobbyList.getSelectedValue().toString();
	}

	/**Adding classes to the selected lobby*/
	public void loadProject(String classname, String codetext) {
		classes.put(classname, codetext);
	}

}
