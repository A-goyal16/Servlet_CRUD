package crudoperation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/createtable")
public class createtable extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "test", "root");
			Statement s=c.createStatement();
			s.execute("create table employee(id INT PRIMARY KEY,name VARCHAR(25),age INT,dob VARCHAR(10),qualification VARCHAR(20))");
			System.out.println("create table succesfully");

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	

}
