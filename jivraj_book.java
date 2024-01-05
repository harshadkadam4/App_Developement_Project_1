import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class jivraj_book extends JFrame implements ActionListener,ItemListener
{
	JTextField bt1,bt2,bt3,bt4;
	JLabel bl1,bl2,bl3,bl4,bl5;
	JButton bb1;
	JComboBox bcb1;
	String item[]={"9AM to 12PM","12PM to 3PM","3PM to 6PM"};
	

	
	String name,date,time,dept,cont;
	String status="booked";
	
	
	Connection cn;
	Statement stm,stm1;
	PreparedStatement prstm;
	ResultSet rs,rs1;
	
	
	jivraj_book()
	{
		setTitle("Jivraj Book");
		setSize(1500,1500);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		bl1 = new JLabel("Name");
		bl2 = new JLabel("Contact");
		bl3 = new JLabel("Date");
		bl4 = new JLabel("Time");
		bl5 = new JLabel("Department");
		
		
		bt1=new JTextField(20);
		bt2=new JTextField(20);
		bt3=new JTextField(20);
		bt4=new JTextField(20);
		
		bb1 = new JButton("BOOK");
		
		bcb1 = new JComboBox(item);
	
		
		bb1.addActionListener(this);
		bcb1.addItemListener(this);
		
		add(bl1); add(bl2); add(bl3); add(bl4); add(bl5); 
		add(bt1); add(bt2); add(bt3); add(bt4); 
		add(bb1);
		add(bcb1);
		
		bl1.setBounds(600,200,200,30);
		bt1.setBounds(710,200,200,30);
		
		bl2.setBounds(600,250,200,30);
		bt2.setBounds(710,250,200,30);
		
		bl3.setBounds(600,300,200,30);
		bt3.setBounds(710,300,200,30);
		
		bl4.setBounds(600,350,200,30);
		bcb1.setBounds(710,350,200,30);
		
		bl5.setBounds(600,400,200,30);
		bt4.setBounds(710,400,200,30);
		
		bb1.setBounds(655,450,200,30);
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
		}
		catch(Exception exe)
		{
			System.out.println(exe);
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		name=bt1.getText();
		cont=bt2.getText();
		date=bt3.getText().toString();
		time = bcb1.getSelectedItem().toString();
		dept=bt4.getText();
		
		try
		{
			if(e.getSource()==bb1)
			{
			
			
			//stm1=cn.createStatement();
			//rs1=stm1.executeQuery("select book_id from jivraj_book where name='"+name+"'");
			
			stm=cn.createStatement();
			rs=stm.executeQuery("select date from jivraj_book where date='"+date+"' and time='"+time+"' and status='"+status+"'");
			
			rs.last();
			int count=rs.getRow();
				if(count>0)
				{
					JOptionPane.showMessageDialog(null,"Hall is Already Booked on your Entered Date or Time");
				}
				else
				{
					prstm = cn.prepareStatement("insert into jivraj_book(name,contact,date,time,dept,status) values('"+name+"',"+cont+",'"+date+"','"+time+"','"+dept+"','"+status+"')");
					prstm.execute();
					
					stm1=cn.createStatement();
					rs1=stm1.executeQuery("select book_id from jivraj_book where date='"+date+"' and time='"+time+"'");
					rs1.next();
					String id = rs1.getString(1);
					JOptionPane.showMessageDialog(null,"Booked Successfully Booking id ="+id);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	public void itemStateChanged(ItemEvent ee)
	{
		
	}
	
	public static void main(String [] args)
	{
		new jivraj_book();
	}
}