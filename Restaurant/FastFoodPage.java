
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

class FastFoodPage extends JPanel implements ActionListener
{
	FastFood f;
	JPanel p[];
	JLabel pic[];
	JLabel name[];
	JLabel price[];
	JButton btn[];
	int row;
	Connection con;
	Statement stmnt;
	
	
	public FastFoodPage()
	{
        try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","Dracula888");
			stmnt = con.createStatement();
		}
		
		catch(SQLException e)
		{
			System.out.println("Unknown Error");
		}
		
		catch(Exception eg)
		{
			System.out.println("Unknown Error");
		}		
		
		setLayout(new GridLayout(9,4));
		setBackground(Color.BLACK);
		
		f = new FastFood();
		row = (f.FastFoodList).getRowCount();
		
		p = new JPanel[row];
		pic = new JLabel[row];
		name = new JLabel[row];
		price = new JLabel[row];
		btn = new JButton[row];
		
		for(int i=0;i<row;i++)
		{	
			p[i] = new JPanel();
			p[i].setLayout(new BoxLayout(p[i],BoxLayout.PAGE_AXIS));
			p[i].add(Box.createRigidArea(new Dimension(50,50)));
			p[i].setBackground(new Color(0, 0, 0));
			
			pic[i] = new JLabel((ImageIcon)((f.FastFoodList).getValueAt(i,4)));
			name[i] = new JLabel(" - " + (String)(f.FastFoodList).getValueAt(i,1) + " -");
			name[i].setFont(new Font("Bell MT", Font.BOLD, 22));
			name[i].setForeground(new Color(255, 250, 240));
			
			price[i] = new JLabel(" RMB " + ((Double)((f.FastFoodList).getValueAt(i,2))).toString());
			price[i].setFont(new Font("Bodoni MT", Font.PLAIN, 18));
			price[i].setForeground(new Color(255, 250, 240));
			
			btn[i] = new JButton("ADD TO CART");
			btn[i].setFont(new Font("Verdana", Font.PLAIN, 12));
			
			p[i].add(name[i]);
			p[i].add(price[i]);
			p[i].add(pic[i]);
			p[i].add(btn[i]);
			
			add(p[i]);
			
			btn[i].addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent a)
	{
		   for(int i=0;i<row;i++)
			{
				if(a.getSource() == btn[i])
				{
					btn[i].setEnabled(false);
					String Name = (String)(f.FastFoodList).getValueAt(i,1);
					Double Price = (Double)(f.FastFoodList).getValueAt(i,2);
						
						try
						{
							stmnt.executeUpdate("INSERT INTO `Temp_Order` (`Product_Name`, `Product_Price`) VALUES ('"+Name+"', '"+Price+"');");
						}
//						catch(SQLException e)
//						{
//							JOptionPane.showMessageDialog(null,"You have already inserted this item");
//						}
						catch(Exception eg)
						{
							System.out.println("Unknown Error");
						}
				}
			}
	}
}