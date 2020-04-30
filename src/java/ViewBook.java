


import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
                                            
		out.println("<div class='container'>");
		out.println("<table class='table table-bordered table-striped'>");
                out.println("<tr><th>ID</th><th>Name</th><th>Subject</th><th>Author</th><th>Publisher</th><th>Link</th><th>Edit</th><th>Delete</th></tr>");
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from books");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			
                            out.println("<tr><td>"+rs.getString("id")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("subject")+"</td><td>"+rs.getString("author")+"</td><td>"+rs.getString("publisher")+"</td><td><a href='"+rs.getString("link")+"'>"+rs.getString("link")+"</a></td><td><a href='EditBookForm?id="+rs.getString("id")+"'>Edit</a></td><td><a href='DeleteBook?id="+rs.getString("id")+"'>Delete</a></td></tr>");
							
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		out.println("</table>");
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}
