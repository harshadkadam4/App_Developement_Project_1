import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class jivraj_cancel extends JFrame implements ActionListener
{
	JTextField ct1;
	JLabel cl1;
	JButton cb1;
	
	String id;
	String cstatus="Available";
	
	
	Connection cn;
	Statement stm;
	PreparedStatement prstm;
	ResultSet rs;
	
	
	jivraj_cancel()
	{
		setTitle("Jivraj Book");
		setSize(1500,1500);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		cl1 = new JLabel("Enter Booking Id");
		
		
		ct1=new JTextField(20);
		
		cb1 = new JButton("CANCEL");
		
		
		cb1.addActionListener(this);
		
		add(cl1); 
		add(ct1);
		add(cb1);
		
		cl1.setBounds(600,200,200,30);
		ct1.setBounds(710,200,200,30);
		
		cb1.setBounds(655,250,200,30);
		
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
		
		
		
			if(e.getSource()==cb1)
			{
				id=ct1.getText();
				
				try
		{
			
			prstm = cn.prepareStatement("update jivraj_book set status='"+cstatus+"' where book_id="+id+"");
			prstm.execute();
			
			//stm1=cn.createStatement();
			//rs1=stm1.executeQuery("select book_id from jivraj_book where name='"+name+"'");
			
			stm=cn.createStatement();
			rs=stm.executeQuery("select book_id from jivraj_book where book_id="+id+" and status='"+cstatus+"'");
			
			rs.last();
			int count=rs.getRow();
				if(count>0)
				{
					String id = rs.getString(1);
					JOptionPane.showMessageDialog(null,"Booking Cancelled");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Enter Correct Booking Id");
				}
			}
		
		catch(Exception ex)
		{
			System.out.println(ex);
		}
			}
	}
	
	
	public static void main(String [] args)
	{
		new jivraj_cancel();
	}
}