package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/deletedata")
public class DeleteData extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/servlet", "test", "root");
			
			String sql="DELETE from employee where id=?";
			PreparedStatement p=c.prepareStatement(sql);
			p.setInt(1, id);
			int rows=p.executeUpdate();
			if(rows>0)
			{
				res.setContentType("text/html");
	            PrintWriter out = res.getWriter();
	            out.println("<h4>Data deleted successfully!</h4>");
				System.out.println("Delete Succesful!!"+rows+"deleted from the table");
			}
			else
			{
				res.setContentType("text/html");
	            PrintWriter out = res.getWriter();
	            out.println("<h4>something went wrong!</h4>");
				System.out.println("No rows are deleted");
			}
			
		} catch (Exception e) {
		}
	}

}
