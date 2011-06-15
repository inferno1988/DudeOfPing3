package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

public class DoP_GUI {
	private JFrame frame;
	private JPanel panel;
	private JTabbedPane tabbedPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoP_GUI window = new DoP_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DoP_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 250));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Групи пристроїв", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JFolderIcon folderIcon = new JFolderIcon("test", tabbedPane);
		panel.add(folderIcon);
		
		JFolderIcon folderIcon_1 = new JFolderIcon("tits", tabbedPane);
		panel.add(folderIcon_1);
		
		JFolderIcon folderIcon_2 = new JFolderIcon("pron", tabbedPane);
		panel.add(folderIcon_2);
		
		frame.setBounds(100, 100, 982, 806);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Файл");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Вихід");
		mnFile.add(mntmExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		menuBar.add(panel_1);
		frame.setLocationRelativeTo(null);
	}
}

/*
 * 				
 */ 
