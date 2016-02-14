package childbreath.servlet;

import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;


/**
 * Created by QQQ on 16/2/14.
 */
public class Downloadservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post!!");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get!!");
        // TODO Auto-generated method stub

        String id= request.getParameter("id");
        System.out.println("id= " + id);


        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String filename;
        if ( id.equals("1") ) {
            filename = "症状及峰流速值记录表.pdf";

        } else if ( id.equals("2")) {
            filename = "哮喘日记（哮喘儿童长期随访表）.pdf";
        } else {
            return;
        }
        //String filename="test.txt";
        String filepath = basePath+"resources/pdf/";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + filename + "\"");


        String filePath;
        if ( id.equals("1")) {
            filePath = "/Users/QQQ/症状及峰流速值记录表.pdf";
        } else if ( id.equals("2")) {
            filePath = "/Users/QQQ/哮喘日记（哮喘儿童长期随访表）.pdf";
        } else {
            return;
        }


         // use inline if you want to view the content in browser, helpful for
        // pdf file
        // response.setHeader("Content-Disposition","inline; filename=\"" +
        // filename + "\"");
        FileInputStream fileInputStream = new FileInputStream(filePath);
        System.out.println("111");

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        System.out.println("211");
        fileInputStream.close();
        out.close();

    }
}
