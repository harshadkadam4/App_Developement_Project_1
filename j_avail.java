import javax.swing.*;
import java.sql.*;

class j_avail extends JFrame
{
	String colHeads[]={"Date","Time"};
	String data[][];
	JTable table;
	
	Connection cn;
	ResultSet rs;
	Statement stm;
	int rs_cnt,i;
	
	j_avail() 
	{
		setTitle("Availability");
		setSize(300,400);
		setLocation(200,330);
		
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			rs=stm.executeQuery("select count(*) from jivraj_book");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][2];
			
			rs=stm.executeQuery("select date,time from jivraj_book order by date");
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				i++;
			}
			
			table = new JTable(data,colHeads);
			table.setEnabled(false);
			
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(table,v,h);
			add(jsp);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		new j_avail();
	}
}