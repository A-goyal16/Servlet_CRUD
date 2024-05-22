package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/inserttable")
public class InsertData  extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String dob=req.getParameter("dob");
		String qualification=req.getParameter("qualification");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "test", "root");
			
			PreparedStatement statement=c.prepareStatement("select id from employee where id=?");
			statement.setInt(1, Integer.parseInt(id));
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				req.setAttribute("errormessage", "id is already present");
			}
			
			else {
				PreparedStatement p=c.prepareStatement("insert into employee(id,name,age,dob,qualification) values(?,?,?,?,?)");
				p.setInt(1, Integer.parseInt(id));
				p.setString(2, name);
				p.setInt(3, Integer.parseInt(age));
				p.setString(4, dob);
				p.setString(5, qualification);
				p.execute();
				
				res.setContentType("text/html");
	            PrintWriter out = res.getWriter();
	            out.println("<h4>Data inserted successfully!</h4>");
				System.out.println("data inserted succesfully");
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		RequestDispatcher rd=req.getRequestDispatcher("insert.jsp");
		rd.include(req, res);
	}

}
