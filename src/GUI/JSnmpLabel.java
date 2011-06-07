package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.it_tim.dude_of_ping3.database.Hosts;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class JSnmpLabel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final float MAX_TEMPERATURE = 60.0f;
	private float temperature = 0.0f;
	private JSnmpInternalStatusPanel internalStatusPanel = new JSnmpInternalStatusPanel(
			"Status:");

	public JSnmpLabel(Hosts host) {
		setLayout(new BorderLayout(0, 0));
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		JMenu mnDevice = new JMenu("Device");
		menuBar.add(mnDevice);
		JMenuItem mntmReboot = new JMenuItem("Reboot");
		mnDevice.add(mntmReboot);
		JPanel panel = new JPanel(new GridLayout(3, 1));
		add(panel, BorderLayout.CENTER);
		temperature = new Float(Math.random() * 60);
		JPanel label = new JSnmpInternalPanel("Пристрій:", host
				.getDescription());
		panel.add(label);
		panel.add(new JSnmpInternalPanel("ІР адреса:", host.getIp()));
		panel.add(new JSnmpInternalPanel("Температура:", new Float(temperature)
				.toString()));
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(panel1, BorderLayout.EAST);
		panel1.add(internalStatusPanel);
		setTemperature(temperature);
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
		Float x = temperature;
		Float hue = new Float(((Math.exp(2 * (x - MAX_TEMPERATURE)
				/ MAX_TEMPERATURE) * -1.0) + 1) * 0.3333);
		setBorder(new LineBorder(Color.getHSBColor(hue, 1.0f, 1.0f), 10, false));
	}

	public void setDoorOpened(boolean isClosed) {
		internalStatusPanel.isDoorClosed(isClosed);
	}

	public void setPowered(boolean isPowered) {
		internalStatusPanel.isPowered(isPowered);
	}

	public void setKnocked(boolean isKnocked) {
		internalStatusPanel.isKnocked(isKnocked);
	}

	class JSnmpInternalPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JSnmpInternalLabel caption = new JSnmpInternalLabel();
		private JSnmpInternalLabel value = new JSnmpInternalLabel();

		public JSnmpInternalPanel(String caption, String value) {
			super();
			setLayout(new FlowLayout(FlowLayout.LEFT));
			this.caption.setText(caption);
			this.value.setText(value);
			add(this.caption);
			add(this.value);
		}

		public JSnmpInternalPanel(String caption) {
			super();
			setLayout(new FlowLayout(FlowLayout.LEFT));
			this.caption.setText(caption);
			add(this.caption);
			add(this.value);
		}
	}

	class JSnmpInternalStatusPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JSnmpInternalLabel caption = new JSnmpInternalLabel();
		private JLabel door = new JLabel();
		private JLabel power = new JLabel();
		private JLabel knocked = new JLabel();

		public JSnmpInternalStatusPanel(String caption) {
			super();
			setLayout(new FlowLayout(FlowLayout.LEFT));
			this.caption.setText(caption);
			add(this.caption);
		}

		public void isDoorClosed(boolean isClosed) {
			if (isClosed) {
				remove(door);
				door
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/door_closed.png")));
				add(door);
			} else {
				remove(door);
				door
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/door_opened.png")));
				add(door);
			}
			updateUI();
		}

		public void isPowered(boolean isPowered) {
			if (isPowered) {
				remove(power);
				power
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/power_on.png")));
				add(power);
			} else {
				remove(power);
				power
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/power_off.png")));
				add(power);
			}
			updateUI();
		}

		public void isKnocked(boolean isKnocked) {
			if (isKnocked) {
				remove(knocked);
				knocked
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/knocked.png")));
				add(knocked);
			} else {
				remove(knocked);
			}
			updateUI();
		}
	}

	class JSnmpInternalLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public JSnmpInternalLabel() {
			super();
			addMouseEvents();
		}

		public JSnmpInternalLabel(String text) {
			super(text);
			addMouseEvents();
		}

		private void addMouseEvents() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setForeground(Color.RED);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setForeground(Color.BLACK);
				}
			});
		}
	}

}
