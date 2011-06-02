package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import net.it_tim.dude_of_ping3.database.Hosts;

public class JSnmpLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	private static final float MAX_TEMPERATURE = 60.0f;
	private float temperature = 0.0f;

	public JSnmpLabel(Hosts host) {
		setLayout(new GridLayout(11, 1));
		temperature = new Float(Math.random()*60);
		JLabel label = new JLabel("Пристрій: " + host.getDescription());
		add(label);
		add(new JLabel("ІР адреса: " + host.getIp()));
		add(new JLabel("температура:" + new Float(temperature).toString()));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JLabel("test1"));
		add(new JButton("Reboot"));
		setTemperature(temperature);
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
		Float x = temperature;
		Float hue = new Float( ((Math.exp(2*(x-MAX_TEMPERATURE)/MAX_TEMPERATURE)*-1.0)+1)*0.3333 );
		setBorder(new LineBorder(Color.getHSBColor(hue, 1.0f, 1.0f), 20, false));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
