import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class option extends JFrame implements ActionListener
{
	JButton b1,b2;
	JMenuBar mbr;
	JMenu m1,m2;
	JMenuItem mi1,mi2,mi3;
	
	option()
	{
		setSize(1600,1500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Halls");
		setLayout(null);
		
		
		b1 = new JButton("Jivraj Hall");
		b2 = new JButton("Seminar Hall");
		
		mbr = new JMenuBar();
		m1 = new JMenu("Home");
		m2 = new JMenu("Report");
		
		mi1 = new JMenuItem("Home");
		mi2 = new JMenuItem("Report");
		
		
		m1.add(mi1);
		m2.add(mi2);
		
		mbr.add(m1);
		mbr.add(m2);
		
		setJMenuBar(mbr);
		
		add(b1);
		add(b2);
		
		b1.setBounds(570,280,200,180);
		b2.setBounds(830,280,200,180);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
		mi2.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			new jivraj();
			this.setVisible(false);
		}
		if(e.getSource()==b2)
		{
			new seminar();
			this.setVisible(false);
		}
		if(e.getSource()==mi2)
		{
			new report();
			this.setVisible(false);
		}
		
	}
	
	public static void main(String []args)
	{
		new option();
	}
}