


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
@WebServlet("/EditBookForm")
public class EditBookForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		String bid=request.getParameter("id");
		
		
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from books where id="+bid);
			ResultSet rs=ps.executeQuery();
			
                        out.print("<form action='EditBook' method='post' style='width:300px'>");
		
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
		out.print("<label for='email1'>Subject</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(3)+"'  name='sub' id='email1' placeholder='Subject'/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='password1'>Author</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(4)+"'  name='author' id='password1' placeholder='Author'/>");
		out.print("</div>  ");
		out.print("<div class='form-group'>");
		out.print("<label for='mobile1'>Publisher</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(5)+"'  name='publisher' id='mobile1' placeholder='Publisher'/>");
		out.print("</div>");
                                             out.print("<div class='form-group'>");
		out.print("<label for='mobile1'>Link</label>");
		out.print("<input type='text' class='form-control' value='"+rs.getString(6)+"'  name='link' id='mobile1' placeholder='Link'/>");
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
