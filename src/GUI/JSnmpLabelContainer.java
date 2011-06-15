package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import net.it_tim.dude_of_ping3.database.DatabaseHome;
import net.it_tim.dude_of_ping3.database.Hosts;

public class JSnmpLabelContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	public JSnmpLabelContainer(String iconText, JTabbedPane tabbedPane) {
		super(new BorderLayout(0, 0));
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		tabbedPane.addTab(iconText, this);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Додати");
		toolBar.add(btnNewButton);
		btnNewButton.setFocusable(false);
		btnNewButton.setIcon(new ImageIcon(DoP_GUI.class.getResource("/net/it_tim/dude_of_ping3/icons/Add_Square.png")));
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		
		JButton button = new JButton("Видалити");
		toolBar.add(button);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component component: panel.getComponents()) {
					if (component instanceof JSnmpLabel && component.isFocusOwner()) {
						panel.remove(component);
					}
					panel.updateUI();
				}
			}
		});
		button.setIcon(new ImageIcon(DoP_GUI.class.getResource("/net/it_tim/dude_of_ping3/icons/Remove_Square.png")));
		
		DatabaseHome dh = new DatabaseHome();
		List<Hosts> host_list= dh.getAll(Hosts.class);
		for (Hosts host: host_list) { 
			JSnmpLabel snmpLabel = new JSnmpLabel(host);
			snmpLabel.setTemperature(25);
			snmpLabel.setPowered(true);
			snmpLabel.setDoorOpened(true);
			snmpLabel.setKnocked(true);
			panel.add(snmpLabel);
		}
		
	}

}
