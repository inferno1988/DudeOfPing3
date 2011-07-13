package net.it_tim.dude_of_ping3.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JFolderIcon extends JPanel implements MouseListener, FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private String iconText;
	private final int BORDER_SIZE = 2;
	JLabel lblNewLabel = new JLabel("");
	private final Color bgMouseOver = new Color(0, 0, 255, 20);
	private final Color bgSelected = new Color(0, 0, 255, 50);
	private final EmptyBorder emptyBorder = new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
			BORDER_SIZE);
	private final LineBorder blackBorder = new LineBorder(new Color(0, 0, 0), BORDER_SIZE, true);
	private final LineBorder grayBorder = new LineBorder(new Color(224, 224, 224), BORDER_SIZE,
			true);

	public JFolderIcon(String iconText, JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
		this.iconText = iconText;
		setLayout(new BorderLayout(0, 0));
		setBorder(emptyBorder);
		setBackground(null);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(false);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel
				.setIcon(new ImageIcon(
						DoP_GUI.class
								.getResource("/net/it_tim/dude_of_ping3/icons/Folder-Closed-Green-icon.png")));
		add(lblNewLabel, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel(iconText);
		lblNewLabel_1.setOpaque(false);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1, BorderLayout.SOUTH);
		setFocusable(true);
		addFocusListener(this);
		addMouseListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(blackBorder);
		setBackground(bgSelected);
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(emptyBorder);
		setBackground(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isEnabled()) {
			requestFocusInWindow();
		}
		if (e.getClickCount() == 2 && isEnabled()) {
			new JSnmpLabelContainer(iconText, tabbedPane);
			lblNewLabel
					.setIcon(new ImageIcon(
							DoP_GUI.class
									.getResource("/net/it_tim/dude_of_ping3/icons/Folder-Open-Red-icon.png")));
			setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isFocusOwner() && isEnabled()) {
			setBorder(grayBorder);
			setBackground(bgMouseOver);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isFocusOwner() && isEnabled()) {
			setBorder(emptyBorder);
			setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
