package childbreath.mvc.mgn;

import datahandle.UserDataHandle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.sql.*;
import java.util.Date;
import  java.text.SimpleDateFormat;
import  java.util.*;
import  javax.mail.*;
import  javax.mail.internet.*;
import  javax.activation.DataHandler;
import  javax.activation.DataSource;
import  javax.activation.FileDataSource;
import javax.servlet.RequestDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;

@Controller
@RequestMapping("/mgn")
public class DownloadController {

	public DownloadController() {

	}

	@RequestMapping(value = "/download")
	public String download(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {

			String p1 = request.getParameter("btn_sendmail");
			System.out.println("p1 = " + p1 );

            if ( p1 == null) {
                return "download";
            }

			String relativePath = request.getServletContext().getRealPath("") + "resources/pdf/";
			System.out.println("relativePath = " + relativePath);


            return proceed_sendmail(model, request, response);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", "-1");
			return "download";
		}
	}

    public String proceed_sendmail(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String tto = request.getParameter("inputEmail");

            System.out.println("sendmail begin");
            String qm = "ygrzleirboawcbad"; //密码
            String tu = "qq.com"; //你邮箱的后缀域名
            System.out.println("to = "+tto);
            String ttitle = "【请勿回复】《呼吸天使》文档下载";
            String tcontent = "尊敬的用户您好；<br/><br/>附件中是您要的文件。请查收。<br/><br/>呼吸天使微信公众号<br/>";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp." + tu);

            props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
            props.put("mail.smtp.port", "995"); //SMTP Port

            System.out.println("props ready");

            Session sn = Session.getInstance(props);
            System.out.println("session ready");

            sn.setDebug(true);
            MimeMessage message = new MimeMessage(sn);
//给消息对象设置发件人/收件人/主题/发信时间
            InternetAddress from = new InternetAddress("35522742@" + tu);
            message.setFrom(from);
            InternetAddress to = new InternetAddress(tto);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(ttitle);
            message.setSentDate(new Date());
//给消息对象设置内容
            BodyPart mdp = new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
            mdp.setContent(tcontent, "text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
            Multipart mm = new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
//象(事实上可以存放多个)
            mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)



            // 添加附件
            String s[] = request.getParameterValues("checkbox1");
            for (int i=0; i < s.length; i++){
                int d = Integer.parseInt(s[i]);
                String filename="";
                String filepath = request.getServletContext().getRealPath("") + "resources/pdf/";
                if (d == 1) {
                    filepath = filepath + "哮喘日记(哮喘儿童长期随访表).pdf";
                    filename = "哮喘日记(哮喘儿童长期随访表).pdf";
                } else if (d==2) {
                    filepath= filepath + "症状及峰流速值记录表.pdf";
                    filename = "症状及峰流速值记录表.pdf";
                } else {
                    continue;
                }
                BodyPart att_mdp = new MimeBodyPart();
                System.out.println("filename = " + filename);
                DataSource source = new FileDataSource(filepath);
                att_mdp.setDataHandler(new DataHandler(source));
                att_mdp.setFileName(new String(filename.getBytes(), "ISO8859-1"));
                mm.addBodyPart(att_mdp);

            }

            message.setContent(mm);//把mm作为消息对象的内容
            message.saveChanges();
            Transport transport = sn.getTransport("smtp");
            transport.connect("smtp." + tu, "35522742", qm);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            model.addAttribute("status", "2");
            System.out.println("return status=2");
            return "download";

        }
        catch (Exception e) {
            model.addAttribute("status","-2");
            e.printStackTrace();
            return "download";
        }
    }

    /*
    public String proceed_download(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int inputStreamLength = 0;
            int length = 0;
            String filepath = request.getServletContext().getRealPath("") + "resources/pdf/";
            filepath += "a.pdf";
            File my_file = new File(filepath);
            System.out.println("a.pdf ready");
            java.io.FileInputStream in = new FileInputStream(my_file);
            while ((length = in.read(buffer)) > 0) {
                inputStreamLength += length;
                baos.write(buffer, 0, length);
            }
            System.out.println("buffer write finished");

            long fileLength = my_file.length();

            if (inputStreamLength > fileLength) {
                response.setContentLength(inputStreamLength);
            }
            System.out.println("fileLength="+fileLength);
            System.out.println("inputStreamLength="+inputStreamLength);

            if (response instanceof HttpServletResponse) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.reset();
                httpResponse.setHeader("Content-Type", "application/pdf");
                httpResponse.setHeader("Content-Length", String.valueOf(inputStreamLength));
                //httpResponse.setHeader("Content-Disposition", "\"" + getContentDisposition() + "\"" + ((getFileName() != null && !getFileName().isEmpty()) ? "; filename=\"" + getFileName() + "\"": ""));
                httpResponse.setHeader("Content-Disposition","attachment; filename=\"" + "a.pdf" + "\"");
            }
            System.out.println("response header set OK");

            response.getOutputStream().write(baos.toByteArray(), 0, (int)inputStreamLength);

            //finally
            response.getOutputStream().flush();
            System.out.println("response outputstream flush");

            //clear
            baos = null;
            close(in);
        } finally {
            // TODO Auto-generated catch block
            close(response.getOutputStream());
            System.out.println("close");
            return "download";
        }
    }

    private void close(Closeable resource) throws IOException {
        if (resource != null) {
            resource.close();
        }
    }
    */

    /*
    public String proceed_download(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {
            String p3 = request.getParameter("btn_view1");
            System.out.println("p3 = " + p3);

            String p4 = request.getParameter("btn_view2");
            System.out.println("p4 = " + p4);

            String filepath = request.getServletContext().getRealPath("") + "resources/pdf/";
            String filename;
            String fileType;

            if (p3 != null) {
                filepath = filepath + "哮喘日记(哮喘儿童长期随访表).pdf";
                filename = "哮喘日记(哮喘儿童长期随访表).pdf";
                fileType = "application/pdf";

            } else if (p4 != null) {
                filepath = filepath + "症状及峰流速值记录表.pdf";
                filename = "症状及峰流速值记录表.pdf";
                fileType = "application/pdf";
            } else {
                return "download";
            }

            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setContentType(fileType + "; charset=GBK");
            // Make sure to show the download dialog
            //response.setHeader("Content-disposition","attachment; filename=\"" + customfilename + "\"");
            response.addHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("GB2312"), "ISO-8859-1"));
            File my_file = new File(filepath);
            long fileLength = my_file.length();
            String flength = String.valueOf(fileLength);
            System.out.println("download directly: " + my_file);

            // This should send the file to browser
            java.io.OutputStream out = response.getOutputStream();
            java.io.FileInputStream in = new FileInputStream(my_file);
            System.out.println("download directly: fileinputstream");

            byte[] buffer = new byte[4096];
            int length;
            System.out.println("download directly: new buffer");
            int ii=0;
            while((length=in.read(buffer)) > -1){
                System.out.println("buffer"+ii+" = "+buffer.toString());
                ii++;
                out.write(buffer, 0, length);
            }
            System.out.println("download directly: write");
            in.close();
            out.flush();
            out.close();
            System.out.println("download directly: close");
            model.addAttribute("status", "1");
            System.out.println("return status = 1");
            return "download";
        }
        catch (Exception e) {
            model.addAttribute("status","-1");
            e.printStackTrace();
            return "download";
        }
    }
    */
}

