import java.awt.*;
import java.awt.print.*;
import java.sql.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;

public class report extends JFrame implements ActionListener,ItemListener
{
	JTextField t1,t2;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3,b4,print;
	
	String item[]={"Jivraj","Seminar"};
	JComboBox cb1,cb2;
	
	//availability
	String colHeads[]={"Book_id","Name","Contact","Date","Time","Dept","Status","Event"};
	String data[][];
	JTable table;
	int i,rs_cnt;
	
	
	Connection cn;
	ResultSet rs,rs1;
	Statement stm,stm1;
	PreparedStatement prstm;
	String sql;
	
	
	report()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setTitle("Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		
		
		l1 = new JLabel("Enter Date");
		l2 = new JLabel("Enter Dept. Name");
		l3 = new JLabel("Select Hall");
		l4 = new JLabel("Select Hall");
		
		b1 = new JButton("Jivraj Report");
		b2 = new JButton("Seminar Hall Report");
		b3 = new JButton("Date Wise Report");
		b4 = new JButton("Dept. Wise Report");
		print = new JButton("Print");
		
		cb1 = new JComboBox(item);
		cb2 = new JComboBox(item);
		
		t1 = new JTextField(30);
		t2 = new JTextField(30);
		
		
		l1.setBounds(850,20,160,20);
		t1.setBounds(805,50,160,35);
		
		l3.setBounds(850,80,160,35);
		cb1.setBounds(805,110,160,35);
		
		l2.setBounds(1130,20,160,20);
		t2.setBounds(1105,50,160,35);
		
		l4.setBounds(1140,80,160,35);
		cb2.setBounds(1105,110,160,35);
		
		b1.setBounds(200,200,170,60);
		b2.setBounds(500,200,170,60);
		b3.setBounds(800,200,170,60);
		b4.setBounds(1100,200,170,60);
		print.setBounds(750,650,100,40);
		print.setVisible(false);
		
		try
		{
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		add(l1); add(l2); add(l3); add(l4);
		add(t1); add(t2);
		add(b1); add(b2); add(b3); add(b4); add(print);
		add(cb1); add(cb2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);	
		b4.addActionListener(this);	
		print.addActionListener(this);	
		
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
			print.setVisible(true);

			try
		{
			String stat="booked";
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			rs=stm.executeQuery("select count(*) from jivraj_book");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from jivraj_book order by date");
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			add(jsp);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		
		
		//second
		
		
		if(e.getSource()==b2)
		{
			print.setVisible(true);
			try
		{
			String stat="booked";
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			rs=stm.executeQuery("select count(*) from seminar_book");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from seminar_book order by date");
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			add(jsp);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		
		
		
		if(e.getSource()==b3)
		{
			print.setVisible(true);
			String date = t1.getText();
			String hall = cb1.getSelectedItem().toString();
			if(date=="")
			{
				JOptionPane.showMessageDialog(null,"Please Enter Date");
			}
			else
			{
			try
		    {
			String stat="booked";
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			
			/*rs=stm.executeQuery("select count(*) from jivraj_book where date='"+date+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][7];
			
			rs=stm.executeQuery("select * from jivraj_book where date='"+date+"' order by date");
			*/
			if(hall=="Seminar")
			{
			rs=stm.executeQuery("select count(*) from seminar_book where date='"+date+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from seminar_book where date='"+date+"' order by date");
			
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			add(jsp);
			}
			else if(hall=="Jivraj")
			{
			rs=stm.executeQuery("select count(*) from jivraj_book where date='"+date+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from jivraj_book where date='"+date+"' order by date");
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			add(jsp);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
			}
		}
		
		
		if(e.getSource()==b4)
		{
			print.setVisible(true);
			String dept = t2.getText();
			String hall = cb2.getSelectedItem().toString();
			
			try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			
			if(hall=="Seminar")
			{
			rs=stm.executeQuery("select count(*) from seminar_book where dept='"+dept+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from seminar_book where dept='"+dept+"' order by date");
			
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			print.setVisible(true);
			add(jsp);
			
			
			}
			else if(hall=="Jivraj")
			{
			rs=stm.executeQuery("select count(*) from jivraj_book where dept='"+dept+"'");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][8];
			
			rs=stm.executeQuery("select * from jivraj_book where dept='"+dept+"' order by date");
			//this i amit ke naam
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				data[i][6]=rs.getString(7);
				data[i][7]=rs.getString(8);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			jsp.setBounds(360,300,900,300);
			add(jsp);
			//TablePrinter printer = new TablePrinter(table);
			//printer.printTable();
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
			
		if(e.getSource()==print)
		{
			MessageFormat h1 = new MessageFormat("Seminar Hall Booking Report");
			MessageFormat f1 = new MessageFormat("");
			try
			{
			table.print(JTable.PrintMode.FIT_WIDTH,h1,f1);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		
		
		
	}
	
	public void itemStateChanged(ItemEvent ee)
	{
		
	}
	
	
	public static void main(String []args)
	{
		new report();
	}
}