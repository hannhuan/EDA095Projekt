package testchatsystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

public class ProjectGUI {

	private JFrame frame;

	public static void main(String[] args) {
		ProjectGUI window = new ProjectGUI();
		window.initialize();
	}

	public ProjectGUI() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		SpringLayout sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);

		JPanel menuPanel = new JPanel();
		sl_mainPanel.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, mainPanel);
		FlowLayout flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(50);
		sl_mainPanel.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, menuPanel, -8, SpringLayout.EAST, mainPanel);
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		menuPanel.setBackground(new Color(192, 192, 192));
		mainPanel.add(menuPanel);
		

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setBackground(new Color(135, 206, 235));
		menuPanel.add(btnRefresh);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, btnRefresh, 10, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, btnRefresh, -280, SpringLayout.EAST, mainPanel);
		btnRefresh.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		sl_mainPanel.putConstraint(SpringLayout.WEST, btnRefresh, 319, SpringLayout.EAST, btnRefresh);
		btnRefresh.addActionListener(new RefreshListner());
		
		JButton btnSubmit = new JButton("SUBMIT");
		menuPanel.add(btnSubmit);
		btnSubmit.setBackground(new Color(144, 238, 144));
		sl_mainPanel.putConstraint(SpringLayout.NORTH, btnSubmit, 10, SpringLayout.NORTH, mainPanel);
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		sl_mainPanel.putConstraint(SpringLayout.WEST, btnSubmit, 131, SpringLayout.WEST, mainPanel);
		btnSubmit.addActionListener(new SubmitListner());

		JButton btnFormat = new JButton("FORMAT");
		btnFormat.setBackground(new Color(238, 130, 238));
		menuPanel.add(btnFormat);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, btnFormat, 10, SpringLayout.NORTH, mainPanel);
		btnFormat.setFont(new Font("Segoe UI Semibold", Font.BOLD, 34));
		sl_mainPanel.putConstraint(SpringLayout.WEST, btnFormat, 302, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, btnFormat, -432, SpringLayout.EAST, mainPanel);
		btnFormat.addActionListener(new FormatListner());

		JList projectList = new JList();
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, menuPanel, -11, SpringLayout.NORTH, projectList);
		sl_mainPanel.putConstraint(SpringLayout.WEST, projectList, 10, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, projectList, 300, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, projectList, 118, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, projectList, -10, SpringLayout.SOUTH, mainPanel);
		projectList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(projectList);

		JTextArea textArea = new JTextArea();
		textArea.setTabSize(6);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, textArea, 11, SpringLayout.SOUTH, menuPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, textArea, 6, SpringLayout.EAST, projectList);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, textArea, -10, SpringLayout.SOUTH, mainPanel);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 21));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(textArea);
		
		JList userList = new JList();
		sl_mainPanel.putConstraint(SpringLayout.EAST, textArea, -6, SpringLayout.WEST, userList);
		sl_mainPanel.putConstraint(SpringLayout.WEST, userList, -218, SpringLayout.EAST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, userList, -8, SpringLayout.EAST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, userList, 118, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, userList, -10, SpringLayout.SOUTH, mainPanel);
		userList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.add(userList);
	}
}
