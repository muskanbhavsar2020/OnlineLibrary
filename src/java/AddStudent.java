
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

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Add Student</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        request.getRequestDispatcher("navadmin.html").include(request, response);
        out.println("<div class='container'>");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String smobile = request.getParameter("mobile");
        long mobile = Long.parseLong(smobile);

        try {

            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("insert into student(name,email,password,mobile) values(?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setLong(4, mobile);
            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        out.print("<h4>Student added successfully</h4>");
        request.getRequestDispatcher("studentregform.html").include(request, response);

        out.println("</div>");
        request.getRequestDispatcher("footer.html").include(request, response);
        out.close();
    }

}
