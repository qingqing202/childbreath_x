package childbreath.servlet;

import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;


/**
 * Created by QQQ on 16/2/14.
 */
public class Downloadservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post is not allowed");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String relativePath = getServletContext().getRealPath("") + "resources/pdf/";
        System.out.println("relativePath = " + relativePath);


        String id = request.getParameter("id");

        String filepath;
        String customfilename;
        String fileType;
        if (id.equals("1")) {
            filepath= relativePath + "哮喘日记(哮喘儿童长期随访表).pdf";
            customfilename = "哮喘日记(哮喘儿童长期随访表).pdf";
            fileType = "application/pdf";
        } else if (id.equals("2")) {
            filepath= relativePath + "症状及峰流速值记录表.pdf";
            customfilename = "症状及峰流速值记录表.pdf";
            fileType = "application/pdf";
        } else {
            return;
        }

        System.out.println("filepath = " + filepath);

        //response.setContentType(fileType);
        response.setContentType( fileType + "; charset=GBK");

        // Make sure to show the download dialog
        //response.setHeader("Content-disposition","attachment; filename=\"" + customfilename + "\"");
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(customfilename.getBytes("GB2312"),"ISO-8859-1"));

        File my_file = new File(filepath);

        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > -1){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        out.close();
    }
}
