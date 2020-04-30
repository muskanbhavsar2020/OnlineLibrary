
import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Student Section</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ResultSet rs = null;
        try {

            Connection con = DB.getCon();

            PreparedStatement ps = con.prepareStatement("select * from student where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("student", "true");

                request.getRequestDispatcher("navstudent.html").include(request, response);
                request.getRequestDispatcher("studenthome.html").include(request, response);

            } else {
                request.getRequestDispatcher("navhome.html").include(request, response);
                out.println("<div class='container'>");
                out.println("<h3>Username or password error</h3>");
                request.getRequestDispatcher("studentlogin.html").include(request, response);
                out.println("</div>");
            }
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        request.getRequestDispatcher("footer.html").include(request, response);
        out.close();
    }

}
