package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.it_tim.dude_of_ping3.database.DatabaseHome;
import net.it_tim.dude_of_ping3.database.Hosts;

import java.awt.Color;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.FlowLayout;

public class DoP_GUI {
	private JFrame frame;
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		DatabaseHome dh = new DatabaseHome();
		List<Hosts> host_list= dh.getAll(Hosts.class);
		
		for (Hosts host: host_list) { 
			JSnmpLabel snmpLabel = new JSnmpLabel(host);
			panel.add(snmpLabel);
		}

		frame.setBounds(100, 100, 753, 806);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
	}

}
