


import connection.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                                            
                                            try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from student where id=?");
			ps.setInt(1,Integer.parseInt(request.getParameter("id")));
                                                                    ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
            
            
		response.sendRedirect("ViewStudent");
	}
}