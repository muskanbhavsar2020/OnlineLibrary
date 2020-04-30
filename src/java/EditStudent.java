


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


@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		long mobile=Long.parseLong(request.getParameter("mobile"));
                int id=Integer.parseInt(request.getParameter("id"));
                                             
		
                                             try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update student set name=?,email=?,password=?,mobile=? where id=?");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,password);
			ps.setLong(4,mobile);
			ps.setInt(5,id);
			ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
                                             
		response.sendRedirect("ViewStudent");
	}

}
