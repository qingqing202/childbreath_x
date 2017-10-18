package childbreath.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by QQQ on 17/10/16.
 */
@WebServlet(name = "ServletTest")
public class ServletTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test12345");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Translational//EN\">");
        out.println("<HTML>");
        out.println("<meta http-equiv=\"content-type\" content=\"test/html; charset=UTF-8\">");
        out.println("<HEAD><TITLE>A servlet</TITLE></HEAD>");
        out.println("  <BODY>");

        String requestURI = request.getRequestURI();
        out.println("<form action='" + requestURI + "' method = 'post'>");
        out.println("input your name : <input type = 'text' name ='name' />");
        out.println("<input type = 'submit' />");
        out.println("</form>");
        out.println("");

        String name = request.getParameter("name");
        if (name != null && name.trim().length() > 0) {
            out.println("hello " + name + ". welcome to the word of java web");
        }
        out.println(" </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
