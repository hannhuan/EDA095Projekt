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
import Util.Doc;
import Util.Paket;
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
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class ProjectGUI {
	private ObjectOutputStream oos;
	private String username;
	private Map<String, Doc> classes;
	private JFrame frame;
	private FlowLayout flowLayout;
	private SpringLayout layout;
	private JPanel mainPanel;
	private JPanel menuPanel;
	private JList<String> projectList;
	private DefaultListModel<String> listModel;
	private JTextArea codeArea;
	private JTextArea chatArea;
	private JTextField writeMessage;
	private JButton btnRefresh;
	private JButton btnSubmit;
	private JButton btnFormat;
	private JButton btnNewClass;
	private JScrollPane codeScroll;
	private JScrollPane chatScroll;
	private DefaultCaret caret;
	private JButton btnRemoveClass;

	/** CONSTRUCTOR: Creates the ProjectGUI */
	public ProjectGUI(ObjectOutputStream oos, String username, HashMap<String, Doc> classes) throws IOException {
		this.oos = oos;
		this.username = username;
		this.classes = classes;

		frame = new JFrame();
		frame.setBounds(0, 0, 900, 500);
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

		btnRefresh = new JButton("REFRESH");
		btnRefresh.setBackground(new Color(135, 206, 235));
		layout.putConstraint(SpringLayout.NORTH, btnRefresh, 10, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnRefresh, -280, SpringLayout.EAST, mainPanel);
		btnRefresh.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		layout.putConstraint(SpringLayout.WEST, btnRefresh, 319, SpringLayout.EAST, btnRefresh);
		btnRefresh.addActionListener(new RefreshListner(this));
		menuPanel.add(btnRefresh);

		btnSubmit = new JButton("SUBMIT");
		layout.putConstraint(SpringLayout.NORTH, btnSubmit, 10, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, btnSubmit, 131, SpringLayout.WEST, mainPanel);
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		btnSubmit.setBackground(new Color(144, 238, 144));
		btnSubmit.addActionListener(new SubmitListner(this, oos));
		menuPanel.add(btnSubmit);

		btnFormat = new JButton("FORMAT");
		layout.putConstraint(SpringLayout.NORTH, btnFormat, 10, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, btnFormat, 302, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnFormat, -432, SpringLayout.EAST, mainPanel);
		btnFormat.setBackground(new Color(238, 130, 238));
		btnFormat.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		btnFormat.addActionListener(new FormatListner(this));
		menuPanel.add(btnFormat);

		btnNewClass = new JButton("NEW CLASS");
		layout.putConstraint(SpringLayout.NORTH, btnNewClass, 11, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, btnNewClass, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnNewClass, 61, SpringLayout.SOUTH, menuPanel);
		btnNewClass.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		btnNewClass.setBackground(new Color(255, 255, 255));
		btnNewClass.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewClass.addActionListener(new NewClassListener(oos, classes));
		mainPanel.add(btnNewClass);

		listModel = new DefaultListModel<String>();
		projectList = new JList<String>(listModel);
		layout.putConstraint(SpringLayout.NORTH, projectList, 6, SpringLayout.SOUTH, btnNewClass);
		layout.putConstraint(SpringLayout.WEST, projectList, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, projectList, 300, SpringLayout.WEST, mainPanel);
		projectList.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		projectList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		projectList.addListSelectionListener(new SelectListener(this, classes));
		mainPanel.add(projectList);

		codeArea = new JTextArea();
		codeArea.setTabSize(6);
		codeArea.setEditable(true);
		codeArea.setFont(new Font("Courier New", Font.PLAIN, 21));
		codeArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		codeScroll = new JScrollPane(codeArea);
		layout.putConstraint(SpringLayout.EAST, btnNewClass, -6, SpringLayout.WEST, codeScroll);
		layout.putConstraint(SpringLayout.NORTH, codeScroll, 121, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, codeScroll, 306, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, codeScroll, -10, SpringLayout.SOUTH, mainPanel);
		codeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		codeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainPanel.add(codeScroll);
		codeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		writeMessage = new JTextField();
		layout.putConstraint(SpringLayout.EAST, codeScroll, -6, SpringLayout.WEST, writeMessage);
		layout.putConstraint(SpringLayout.WEST, writeMessage, -298, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, writeMessage, -8, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, writeMessage, -44, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, writeMessage, -10, SpringLayout.SOUTH, mainPanel);
		writeMessage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		writeMessage.setFont(new Font("Calibri", Font.PLAIN, 20));
		writeMessage.addActionListener(new SendChatListener(this));
		mainPanel.add(writeMessage);

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setFont(new Font("Calibri", Font.PLAIN, 20));
		chatArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		chatScroll = new JScrollPane(chatArea);
		layout.putConstraint(SpringLayout.NORTH, chatScroll, 11, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, chatScroll, -290, SpringLayout.EAST, menuPanel);
		layout.putConstraint(SpringLayout.SOUTH, chatScroll, -6, SpringLayout.NORTH, writeMessage);
		layout.putConstraint(SpringLayout.EAST, chatScroll, 0, SpringLayout.EAST, menuPanel);
		chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		caret = (DefaultCaret) chatArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		mainPanel.add(chatScroll);

		JPanel panel = new JPanel();
		mainPanel.add(panel);

		btnRemoveClass = new JButton("REMOVE CLASS");
		layout.putConstraint(SpringLayout.SOUTH, projectList, -6, SpringLayout.NORTH, btnRemoveClass);
		layout.putConstraint(SpringLayout.WEST, btnRemoveClass, 0, SpringLayout.WEST, menuPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnRemoveClass, 0, SpringLayout.SOUTH, codeScroll);
		layout.putConstraint(SpringLayout.EAST, btnRemoveClass, 0, SpringLayout.EAST, btnNewClass);
		btnRemoveClass.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		btnRemoveClass.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRemoveClass.setBackground(Color.WHITE);
		btnRemoveClass.addActionListener(new RemoveClassListener(this, oos));
		mainPanel.add(btnRemoveClass);

		fillList();
		frame.setVisible(true);
	
		serverSendChat();
	}

	/** Fills the CodeArea with the specific text codeText */
	public void setCodeText(String codeText) {
		codeArea.setText(codeText);
	}

	/** Edits the CodeArea */
	public void editCode() {
		String newCode = codeArea.getText();
		String title = projectList.getSelectedValue().toString();
		classes.put(title, new Doc(title, newCode.getBytes()));
		codeArea.setText(newCode);
	}

	/** Adds a line to the chatArea */
	public void chat(Doc doc) {
		try {
			chatArea.append(doc.getTitle() + ": " + new String(doc.getContent(), "UTF-8") + "\n");
		} catch (UnsupportedEncodingException e) {}
	}

	/** Fills the projectList with all the classes in from this lobby */
	public void fillList() {
		listModel.clear();
		for (String classTitle : classes.keySet()) {
			listModel.addElement(classTitle);
		}
	}

	/** Sends a chatMessage */
	public void sendChat() {
		try {
			oos.writeObject(new Paket("chat", new Doc(username, writeMessage.getText().getBytes())));
			writeMessage.setText("");
		} catch (Exception e) {
		}
	}
	
	public void serverSendChat(){
		try {
			oos.writeObject(new Paket("chat", new Doc("/SERVER", (" User " + username + " connected").getBytes())));
			writeMessage.setText("");
		} catch (Exception e) {
		}
	}

	/**
	 * @return the selected class from the projectList
	 */
	public String getSelectedClass() {
		return projectList.getSelectedValue();
	}

	/**
	 * @return the text in the codeArea
	 */
	public String getCodeText() {
		if (codeArea.getText() == null) {
			return "";
		}
		return codeArea.getText();
	}

	/** Adds a new class to the projectList */
	public void addNewClass(Doc doc) {
		if(!(classes.containsKey(doc.getTitle()))){
			classes.put(doc.getTitle(), new Doc(doc.getTitle(), "".getBytes()));
			listModel.addElement(doc.getTitle());
			chat(new Doc("/SYSTEM", " Class added".getBytes()));
		} else {
		chat(new Doc("/SYSTEM", ": Class already exists".getBytes()));
		}
	}

	/** Removes the selected class from the projectList */
	public void removeClass(Doc doc) {
		classes.remove(doc.getTitle());
		setCodeText("");
		fillList();
	}

	/**receives the Document doc from Server*/
	public void receiveDoc(Doc doc) {
		if(classes.containsKey(doc.getTitle())){
			classes.get(doc.getTitle()).setnewContent(doc.getContent());
		} else {
			addNewClass(doc);
		}
		chat(new Doc("/SYSTEM", ("New submit in: " + doc.getTitle()).getBytes()));
	}
	public void refreshDoc(){
		String classTitle = getSelectedClass();
		classes.get(classTitle).refresh();
		
		try {
			//System.out.println(new String(classes.get(classTitle).getContent(), "UTF-8"));
			setCodeText(new String(classes.get(classTitle).getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean allowedToSubmit(){
		return classes.get(getSelectedClass()).ifNewest();
	}
	
	public void saveSubmit(){
		if(allowedToSubmit()){
		classes.get(getSelectedClass()).setnewContent(getCodeText().getBytes());
		classes.get(getSelectedClass()).refresh();		
		}
	}
}
