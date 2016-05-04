
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.JTextField;

public class ProjectGUI {
	private PrintWriter out;
	private String username;
	private Map<String, String> classes;
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
	
	public ProjectGUI(PrintWriter out, String username, HashMap<String, String> classes) {
		this.out = out;
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
		menuPanel.add(btnRefresh);
		layout.putConstraint(SpringLayout.NORTH, btnRefresh, 10, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnRefresh, -280, SpringLayout.EAST, mainPanel);
		btnRefresh.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		layout.putConstraint(SpringLayout.WEST, btnRefresh, 319, SpringLayout.EAST, btnRefresh);
		btnRefresh.addActionListener(new RefreshListner(this));
		
		btnSubmit = new JButton("SUBMIT");
		menuPanel.add(btnSubmit);
		btnSubmit.setBackground(new Color(144, 238, 144));
		layout.putConstraint(SpringLayout.NORTH, btnSubmit, 10, SpringLayout.NORTH, mainPanel);
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		layout.putConstraint(SpringLayout.WEST, btnSubmit, 131, SpringLayout.WEST, mainPanel);
		btnSubmit.addActionListener(new SubmitListner(this));

		btnFormat = new JButton("FORMAT");
		btnFormat.setBackground(new Color(238, 130, 238));
		menuPanel.add(btnFormat);
		layout.putConstraint(SpringLayout.NORTH, btnFormat, 10, SpringLayout.NORTH, mainPanel);
		btnFormat.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		layout.putConstraint(SpringLayout.WEST, btnFormat, 302, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, btnFormat, -432, SpringLayout.EAST, mainPanel);
		btnFormat.addActionListener(new FormatListner(this));
		
		btnNewClass = new JButton("NEW CLASS");
		layout.putConstraint(SpringLayout.NORTH, btnNewClass, 11, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, btnNewClass, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, btnNewClass, 61, SpringLayout.SOUTH, menuPanel);
		btnNewClass.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		btnNewClass.setBackground(new Color(255, 255, 255));
		btnNewClass.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(btnNewClass);

		listModel = new DefaultListModel<String>();
		projectList = new JList<String>(listModel);
		layout.putConstraint(SpringLayout.NORTH, projectList, 6, SpringLayout.SOUTH, btnNewClass);
		layout.putConstraint(SpringLayout.SOUTH, projectList, -10, SpringLayout.SOUTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, projectList, 10, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, projectList, 300, SpringLayout.WEST, mainPanel);
		projectList.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		projectList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		projectList.addListSelectionListener(new SelectListener(this));
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
		writeMessage.addActionListener(new SendChatListener(writeMessage, username, out));
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
		caret = (DefaultCaret)chatArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		mainPanel.add(chatScroll);
		
		fillList();
		frame.setVisible(true);
	}

	public void setCodeText() {
		String codeText = classes.get(projectList.getSelectedValue().toString());
		codeArea.setText(codeText);
	}
	
	public void editCode() {
		String newCode = codeArea.getText();
		classes.put(projectList.getSelectedValue().toString(), newCode);
		codeArea.setText(newCode);		
	}
	
	public void chat(String line){
		chatArea.append(line + "\n");
	}
	
	public void fillList(){
		for (String classTitle : classes.keySet()){
			listModel.addElement(classTitle);
		}
	}
}
