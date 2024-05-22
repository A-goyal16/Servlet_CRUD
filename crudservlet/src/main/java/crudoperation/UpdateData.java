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

@WebServlet("/UpdateData")
public class UpdateData extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "test", "root");
            String columnName = req.getParameter("column_name"); 
            String columnValue = req.getParameter("column_value"); 
            int id = Integer.parseInt(req.getParameter("id")); 
            String sql = "UPDATE employee SET " + columnName + " = ? WHERE id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, columnValue);
            p.setInt(2, id);
            int rowsAffected = p.executeUpdate();

            if (rowsAffected > 0) {
            	res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println("<h4>Data updated successfully!</h4>");
                System.out.println("Update successful! " + rowsAffected + " rows updated.");
            } else {
            	res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println("<h4>something went wrong</h4>");
                System.out.println("No rows updated!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
