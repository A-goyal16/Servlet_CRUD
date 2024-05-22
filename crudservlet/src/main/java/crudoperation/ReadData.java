package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/readtabledata")
public class ReadData extends GenericServlet
{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "test", "root");
			PreparedStatement p=c.prepareStatement("select * from employee where id=?");
			p.setInt(1, Integer.parseInt(id));
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				int fetchid=rs.getInt("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String dob=rs.getString("dob");
				String qualification=rs.getString("qualification");
				
				
				res.setContentType("text/html");
	            PrintWriter out = res.getWriter();
	            out.println("<h4>Data fetch successfully!</h4>");
	            out.println("id: " + fetchid + "\n" + "name: " + name + "\n" + "age: " + age + "\n" + "dob: " + dob + "\n" + "qualification: " + qualification);
	
			}		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
