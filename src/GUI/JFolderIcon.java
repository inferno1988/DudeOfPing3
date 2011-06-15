package GUI;

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


public class JFolderIcon extends JPanel implements MouseListener,
		FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private String iconText;
	private final int BORDER_SIZE = 2;
	JLabel lblNewLabel = new JLabel("");
	
	public JFolderIcon(String iconText, JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
		this.iconText = iconText;
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(BORDER_SIZE,BORDER_SIZE,BORDER_SIZE,BORDER_SIZE));
		setBackground(new Color(0, 0, 0, 0));
		lblNewLabel.setOpaque(false);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setIcon(new ImageIcon(DoP_GUI.class.getResource("/net/it_tim/dude_of_ping3/icons/Folder-Closed-Green-icon.png")));
		add(lblNewLabel, BorderLayout.NORTH);
		
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
		setBorder(new LineBorder(new Color(0, 0, 0), BORDER_SIZE, true));
		setBackground(new Color(0, 0, 255, 50));
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(new EmptyBorder(BORDER_SIZE,BORDER_SIZE,BORDER_SIZE,BORDER_SIZE));
		setBackground(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isEnabled())
			requestFocusInWindow();
		if (e.getClickCount() == 2 && isEnabled()) {
			new JSnmpLabelContainer(iconText, tabbedPane);
			lblNewLabel.setIcon(new ImageIcon(DoP_GUI.class.getResource("/net/it_tim/dude_of_ping3/icons/Folder-Open-Red-icon.png")));
			setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isFocusOwner() && isEnabled()) {
			setBorder(new LineBorder(new Color(224, 224, 224), BORDER_SIZE,
					true));
			setBackground(new Color(0, 0, 255, 20));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isFocusOwner() && isEnabled()) {
			setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
					BORDER_SIZE));
			setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
