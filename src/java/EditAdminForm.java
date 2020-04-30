


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/EditAdminForm")
public class EditAdminForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Admin Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		String sid=request.getParameter("id");
		
		
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from admin where id="+sid);
			ResultSet rs=ps.executeQuery();
			
                        out.print("<form action='EditAdmin' method='post' style='width:300px'>");
		
                        while(rs.next()){
			
                                             out.print("<div class='form-group'>");
		out.print("<label for='name1'>ID</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(1)+"' name='id' id='name1' placeholder='ID' readonly/>");
		out.print("</div>");                      
		out.print("<div class='form-group'>");
		out.print("<label for='name1'>Name</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(2)+"' name='name' id='name1' placeholder='Name'/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='email1'>Email</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(3)+"'  name='email' id='email1' placeholder='Email'/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='password1'>Password</label>");
		out.print("<input type='password' class='form-control' value='"+rs.getString(4)+"'  name='password' id='password1' placeholder='Password'/>");
		out.print("</div>  ");
		out.print("<div class='form-group'>");
		out.print("<label for='mobile1'>Mobile</label>");
		out.print("<input type='number' class='form-control' value='"+rs.getString(5)+"'  name='mobile' id='mobile1' placeholder='Mobile'/>");
		out.print("</div>");
                                            
		
		
		
							
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
                
		out.print("<button type='submit' class='btn btn-primary' style='background-color: greenyellow;color: black'>Update</button>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}
