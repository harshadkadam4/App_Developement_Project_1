import javax.swing.*;
import java.sql.*;

class s_report extends JFrame
{
	String colHeads[]={"Book_id","Name","Contact","Date","Time","Dept","Status"};
	String data[][];
	JTable table;
	
	Connection cn;
	ResultSet rs;
	Statement stm;
	int rs_cnt,i;
	
	s_report()
	{
		setTitle("Seminar Hall Booking Report");
		setSize(700,400);
		setLocation(350,250);
		
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_project","root","1234");
			stm=cn.createStatement();
			rs=stm.executeQuery("select count(*) from seminar_book");
			rs.next();
			rs_cnt=rs.getInt(1);
			
			data = new String [rs_cnt][7];
			
			rs=stm.executeQuery("select * from seminar_book");
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
		new s_report();
	}
}