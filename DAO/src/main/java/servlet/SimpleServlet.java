package servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SimpleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = "Test Servlet1 with Utilities";
        out.println(Util.headWithTitle(title) +
                "<body bgcolor=\"#fdf5e6\">\n" +
                "<h1>" + title + "</h1>\n" +
                "<p>Simple servlet for testing.</p>\n" +
                "</body></html>");
    }
}
