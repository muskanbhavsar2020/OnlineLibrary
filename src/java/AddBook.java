
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

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Upload Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        request.getRequestDispatcher("navadmin.html").include(request, response);

        out.println("<div class='container'>");
        String name = request.getParameter("name");
        String sub = request.getParameter("subject");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String link = request.getParameter("link");

        try {

            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("insert into books(name,subject,author,publisher,link) values(?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, sub);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setString(5, link);
            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3>Book Upload successful</h3>");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        request.getRequestDispatcher("addbookform.html").include(request, response);
        out.println("</div>");

        request.getRequestDispatcher("footer.html").include(request, response);
        out.close();
    }

}
