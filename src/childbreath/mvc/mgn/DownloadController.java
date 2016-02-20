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

			String p2 = request.getParameter("btn_download");
			System.out.println("p2 = " + p2 );

			if ( p1 == null && p2 == null) {
				return "download";
			}

			System.out.println("download method");
			String relativePath = request.getServletContext().getRealPath("") + "resources/pdf/";
			System.out.println("relativePath = " + relativePath);

			int d = 0;
			String s[] = request.getParameterValues("checkbox1");
			for (int i=0; i < s.length; i++){
					d = d + Integer.parseInt(s[i]);
			}

			String filepath;
			String customfilename;
			String fileType;

			if (d == 1) {
				filepath= relativePath + "哮喘日记（哮喘儿童长期随访表）.pdf";
				customfilename = "哮喘日记（哮喘儿童长期随访表）.pdf";
				fileType = "application/pdf";
			} else if ( d==2 ) {
				filepath= relativePath + "症状及峰流速值记录表.pdf";
				customfilename = "症状及峰流速值记录表.pdf";
				fileType = "application/pdf";
			} else if ( d==3 ){
				filepath= relativePath + "pack1.zip";
				customfilename = "pack1.zip";
				fileType = "application/zip";
			} else {
				return "download";
			}

			System.out.println("filepath = " + filepath);

			if ( p2 != null ) {
				System.out.println("download directly");
				//response.setContentType(fileType);
				response.setContentType(fileType + "; charset=GBK");

				// Make sure to show the download dialog
				//response.setHeader("Content-disposition","attachment; filename=\"" + customfilename + "\"");
				response.addHeader("Content-Disposition", "attachment; filename=" + new String(customfilename.getBytes("GB2312"), "ISO-8859-1"));

				File my_file = new File(filepath);

				// This should send the file to browser
				OutputStream out = response.getOutputStream();
				FileInputStream in = new FileInputStream(my_file);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > -1) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.flush();
				out.close();
				model.addAttribute("status","1");
				System.out.println("return status = 1");
				return "download";
			} else {
				String to_email = request.getParameter("inputEmail");
				System.out.println("send mail");
				return sendmail(model, to_email, filepath, customfilename);
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status","-1");
			return "download";
		}
	}

	public String sendmail(Model m, String tto, String filepath, String filename) {
		try {
			System.out.println("sendmail begin");
			String qm = "ygrzleirboawcbad"; //您的QQ密码
			String tu = "qq.com"; //你邮箱的后缀域名
			System.out.println("to = "+tto);
			//String tto = "35522742@qq.com"; //接收邮件的邮箱
			String ttitle = "【请勿回复】《呼吸天使》文档下载";
			String tcontent = "尊敬的用户您好；<br/><br/>附件中是您要的文件。请查收。<br/><br/>呼吸天使微信公众号<br/>";

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp." + tu);//发信的主机，这里我填写的是我们公司的主机！可以不用修改！

			props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
			props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
			props.put("mail.smtp.port", "995"); //SMTP Port

			System.out.println("props ready");


			Session s = Session.getInstance(props);
			System.out.println("session ready");

			s.setDebug(true);
			MimeMessage message = new MimeMessage(s);
//给消息对象设置发件人/收件人/主题/发信时间
			InternetAddress from = new InternetAddress("35522742@" + tu); //这里的115798090 改为您发信的QQ号
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

			BodyPart att_mdp = new MimeBodyPart();

			//String filename = "/Users/QQQ/症状及峰流速值记录表.pdf";
			System.out.println("filename = " + filename);
			DataSource source = new FileDataSource(filepath);
			//System.out.println("source = " + source.toString());
			att_mdp.setDataHandler(new DataHandler(source));

			att_mdp.setFileName(new String(filename.getBytes(), "ISO8859-1"));

			mm.addBodyPart(att_mdp);


			message.setContent(mm);//把mm作为消息对象的内容
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			transport.connect("smtp." + tu, "35522742", qm); //这里的115798090也要修改为您的QQ号码
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			m.addAttribute("status","2");
			System.out.println("return status=2");
			return "download";
		}
		catch (Exception e) {
			m.addAttribute("status","-2");
			e.printStackTrace();
			return "download";
		}
	}

}

