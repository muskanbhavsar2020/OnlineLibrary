


import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("name");
		String sub=request.getParameter("sub");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
                                             String link=request.getParameter("link");
                                             int id=Integer.parseInt(request.getParameter("id"));
		
                                             try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update books set name=?,subject=?,author=?,publisher=?,link=? where id=?");
			ps.setString(1,name);
                                                                    ps.setString(2,sub);
			ps.setString(3,author);
			ps.setString(4,publisher);
			ps.setString(5,link);
			ps.setInt(6,id);
			ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println("Error: "+e);}
		
                                             
		response.sendRedirect("ViewBook");
	}

}
