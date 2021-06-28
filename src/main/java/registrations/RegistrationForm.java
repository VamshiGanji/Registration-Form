package registrations;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.sql.*;
public class RegistrationForm extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		try
		{
			String myDriver="com.mysql.jdbc.Driver";
			String myUrl="jdbc:mysql://localhost:3306/registrations";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");
			PrintWriter pw=res.getWriter();
			String name=req.getParameter("name");
			String mobile=req.getParameter("mobile");
			String gender=req.getParameter("gender");
			String email=req.getParameter("email");
			String pwd=req.getParameter("pwd");
			res.setContentType("text/html");
			String query="INSERT into registrations(Name,Mobile,Gender,Email,Password) "
			+ "VALUES(?,?,?,?,?)";
			PreparedStatement st=conn.prepareStatement(query);
			st.setString(1,name);
			st.setString(2,mobile);
			st.setString(3,gender);
			st.setString(4,email);
			st.setString(5,pwd);
			int i=st.executeUpdate();
			if(i>0)
			{	
				pw.println("<center><h4 style=color:green;>Registration Sucessfull</h4></center>");
				pw.println("<center><h1 style= background-color:teal; color:#32a84e; height:50; width:1350;>Ground Booking Details</h1></center>");
				pw.println("<div><center><table>");
				pw.println("<tr><th>Name:</th><td>"+name+"</td></tr>");
				pw.println("<tr><th>Contact:</th><td>"+mobile+"</td></tr>");
				pw.println("<tr><th>Gender:</th><td>"+gender+"</td></tr>");
				pw.println("<tr><th>Email:</th><td>"+email+"</td></tr>");
				pw.println("</table></center></div>");
			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
