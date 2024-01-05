import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class seminar extends JFrame implements ActionListener,ItemListener
{
	JLabel l1,l2;
	JButton b1,b2,b3;
	JPanel p1;
	int i,rs_cnt;
	
	
	//availability
	String colHeads[]={"Date","Time"};
	String data[][];
	JTable table;
	
	
	//booking
	JTextField bt1,bt2,bt3,bt4,bt5;
	JLabel bl1,bl2,bl3,bl4,bl5,bl6;
	JButton bb1;
	JComboBox bcb1;
	String item[]={"9AM to 12PM","12PM to 3PM","3PM to 6PM"};
	
	String name,date,time,dept,cont,event;
	String status="booked";
	
	Connection cn;
	ResultSet rs,rs1;
	Statement stm,stm1;
	PreparedStatement prstm;
	String sql;
	
	
	//cancel
	JTextField ct1;
	JLabel cl1;
	JButton cb1;
	
	String id;
	String cstatus="Available";
	
	seminar()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setTitle("Seminar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		
		
		l1 = new JLabel("SEMINAR HALL");
		l2 = new JLabel("The seating capacity is around 125 - 130 members.");
		b1 = new JButton("Check Availbility");
		b2 = new JButton("Book Hall");
		b3 = new JButton("Cancel Booking");
		
		
		l1.setBounds(100,50,160,30);
		l2.setBounds(100,80,400,100);
		b1.setBounds(100,160,150,60);
		b2.setBounds(100,240,150,60);
		b3.setBounds(100,320,150,60);
		
		try
		{
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		add(l1);
		add(l2);
		add(b1); add(b2); add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);	
		
		
		
		//booking
		bl1 = new JLabel("Name");
		bl2 = new JLabel("Contact");
		bl3 = new JLabel("Date");
		bl4 = new JLabel("Time");
		bl5 = new JLabel("Department");
		bl6 = new JLabel("Event Name");
		
		
		bt1=new JTextField(20);
		bt2=new JTextField(20);
		bt3=new JTextField(20);
		bt4=new JTextField(20);
		bt5=new JTextField(20);
		
		bb1 = new JButton("BOOK");
		
		bcb1 = new JComboBox(item);
	
		
		bb1.addActionListener(this);
		bcb1.addItemListener(this);
		
		add(bl1); add(bl2); add(bl3); add(bl4); add(bl5); add(bl6); 
		add(bt1); add(bt2); add(bt3); add(bt4); add(bt5);
		add(bb1);
		add(bcb1);
		
		//cancel
		cl1 = new JLabel("Enter Booking Id");
		
		ct1=new JTextField(20);
		
		cb1 = new JButton("CANCEL");
		
		
		cb1.addActionListener(this);
		
		cl1.setBounds(640,200,200,30);
		ct1.setBounds(750,200,200,30);
		
		cb1.setBounds(685,250,200,30);
		
		addWindowListener(new WindowAdapter ()
		{
			public void windowClosing(WindowEvent e)
			{
				new option();
				setVisible(false);
			}
		}
		);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
		{
			try
		{
			String stat="booked";
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			rs=stm.executeQuery("select count(*) from seminar_book where status='"+stat+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][2];
			
			rs=stm.executeQuery("select date,time from seminar_book where status='"+stat+"' order by date");
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				i++;
			}
			
			rs.next();
			if(rs.isAfterLast())
					rs.first();
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(300,160,300,200);
			add(jsp);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		
		if(e.getSource()==b2)
		{
			//jsp.setVisible(false);
			bt1.setVisible(true); bt2.setVisible(true); bt3.setVisible(true); bt4.setVisible(true); bt5.setVisible(true);
			bl1.setVisible(true); bl2.setVisible(true); bl3.setVisible(true); bl4.setVisible(true);
			bl5.setVisible(true); bl6.setVisible(true);
			bb1.setVisible(true);
			bcb1.setVisible(true);
			cl1.setVisible(false);
			ct1.setVisible(false);
			cb1.setVisible(false);
			
		bl1.setBounds(620,200,200,30);
		bt1.setBounds(710,200,200,30);
		
		bl2.setBounds(620,250,200,30);
		bt2.setBounds(710,250,200,30);
		
		bl3.setBounds(620,300,200,30);
		bt3.setBounds(710,300,200,30);
		
		bl4.setBounds(620,350,200,30);
		bcb1.setBounds(710,350,200,30);
		
		bl5.setBounds(620,400,200,30);
		bt4.setBounds(710,400,200,30);
		
		bl6.setBounds(620,450,200,30);
		bt5.setBounds(710,450,200,30);
		
		bb1.setBounds(675,500,200,30);
		}
		
		if(e.getSource()==bb1)
		{	
	
		name=bt1.getText();
		cont=bt2.getText();
		date=bt3.getText().toString();
		time = bcb1.getSelectedItem().toString();
		dept=bt4.getText();
		event=bt5.getText();
		
		try
		{
			//stm1=cn.createStatement();
			//rs1=stm1.executeQuery("select book_id from jivraj_book where name='"+name+"'");
			
			stm=cn.createStatement();
			rs=stm.executeQuery("select date from seminar_book where date='"+date+"' and time='"+time+"' and status='"+status+"'");
			
			rs.last();
			int count=rs.getRow();
				if(count>0)
				{
					JOptionPane.showMessageDialog(null,"Hall is Already Booked on your Entered Date or Time");
				}
				else
				{
					prstm = cn.prepareStatement("insert into seminar_book(name,contact,date,time,dept,status,event) values('"+name+"',"+cont+",'"+date+"','"+time+"','"+dept+"','"+status+"','"+event+"')");
					prstm.execute();
					
					stm1=cn.createStatement();
					rs1=stm1.executeQuery("select book_id from seminar_book where date='"+date+"' and time='"+time+"' and status='"+status+"'");
					rs1.next();
					String id = rs1.getString(1);
					JOptionPane.showMessageDialog(null,"Booked Successfully Booking id ="+id);
				}
		}
		catch(Exception exe)
		{
			System.out.println(exe);
		}
		}
		
		if(e.getSource()==b3)
		{
			bt1.setVisible(false); bt2.setVisible(false); bt3.setVisible(false); bt4.setVisible(false); bt5.setVisible(false);
			bl1.setVisible(false); bl2.setVisible(false); bl3.setVisible(false); bl4.setVisible(false);
			bl5.setVisible(false);
			bl6.setVisible(false);
			bb1.setVisible(false);
			bcb1.setVisible(false);
			//jsp.setVisible(false);
			
			cl1.setVisible(true);
			ct1.setVisible(true);
			cb1.setVisible(true);
			
			add(cl1);
			add(ct1);
			add(cb1);
		}
		
		if(e.getSource()==cb1)
		{
				id=ct1.getText();
				
				try
		{
			
			prstm = cn.prepareStatement("update seminar_book set status='"+cstatus+"' where book_id="+id+"");
			prstm.execute();
			
			//stm1=cn.createStatement();
			//rs1=stm1.executeQuery("select book_id from jivraj_book where name='"+name+"'");
			
			stm=cn.createStatement();
			rs=stm.executeQuery("select book_id from seminar_book where book_id="+id+" and status='"+cstatus+"'");
			
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
		
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		}
		
	}
	
	public void itemStateChanged(ItemEvent ee)
	{
		
	}
	
	
	public static void main(String []args)
	{
		new seminar();
	}
}