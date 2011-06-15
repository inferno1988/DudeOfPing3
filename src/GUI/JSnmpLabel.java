package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.it_tim.dude_of_ping3.database.Hosts;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

public class JSnmpLabel extends JPanel implements MouseListener,
FocusListener {

	private static final long serialVersionUID = 1L;
	private static final float MAX_TEMPERATURE = 60.0f;
	private JSnmpInternalStatusPanel internalStatusPanel = new JSnmpInternalStatusPanel(
			"Status:");
	private JSnmpInternalPanel temperaturePanel = new JSnmpInternalPanel("Температура:");

	public JSnmpLabel(Hosts host) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setLayout(new BorderLayout(0, 0));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFocusable(false);
		add(menuBar, BorderLayout.NORTH);
		JMenu mnDevice = new JMenu("Пристрій");
		mnDevice.setFocusable(false);
		menuBar.add(mnDevice);
		JMenuItem mntmReboot = new JMenuItem("Перезавантажити");
		mnDevice.add(mntmReboot);
		
		Component horizontalStrut = Box.createHorizontalStrut(253);
		menuBar.add(horizontalStrut);
		
		JButton btnShowInfo = new JButton("Інформація");
		btnShowInfo.setFocusable(false);
		btnShowInfo.setPreferredSize(new Dimension(120, 20));
		menuBar.add(btnShowInfo);
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(200, 60));
		add(panel, BorderLayout.CENTER);
		JPanel label = new JSnmpInternalPanel("Ім\'я пристрою:", host
				.getDescription());
		panel.add(label);
		panel.add(new JSnmpInternalPanel("ІР адреса:", host.getIp()));
		panel.add(temperaturePanel);
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(200, 10));
		add(panel1, BorderLayout.EAST);
		panel1.add(internalStatusPanel);
		setFocusable(true);
		setBackground(new Color(224, 224, 224));
		setVisible(true);
		addMouseListener(this);
		addFocusListener(this);
	}
	
	public void setTemperature(float temperature) {
		temperaturePanel.setValue(new Float(temperature).toString());
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
			setOpaque(false);
		}

		public JSnmpInternalPanel(String caption) {
			super();
			setLayout(new FlowLayout(FlowLayout.LEFT));
			this.caption.setText(caption);
			add(this.caption);
			add(this.value);
			setOpaque(false);
		}
		
		public void setValue(String text) {
			this.value.setText(text);
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
			setOpaque(false);
		}

		public void isDoorClosed(boolean isClosed) {
			if (isClosed) {
				remove(door);
				door
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/door_closed.png")));
				add(door);
				door.setToolTipText("Двері зачинені");
			} else {
				remove(door);
				door
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/door_opened.png")));
				add(door);
				door.setToolTipText("Двері відчинені");
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
				power.setToolTipText("Живлення в нормі");
			} else {
				remove(power);
				power
						.setIcon(new ImageIcon(
								DoP_GUI.class
										.getResource("/net/it_tim/dude_of_ping3/icons/power_off.png")));
				add(power);
				power.setToolTipText("Живлення відсутнє");
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
				knocked.setToolTipText("Датчик удару");
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
			setOpaque(false);
		}

		public JSnmpInternalLabel(String text) {
			super(text);
			setOpaque(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		requestFocusInWindow();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isFocusOwner())
			setBackground(new Color(255, 0, 0, 20));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (!isFocusOwner())
			setBackground(new Color(224, 224, 224));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		setBackground(Color.PINK);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		setBackground(new Color(224, 224, 224));
	}
}
