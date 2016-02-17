<%--
  Created by IntelliJ IDEA.
  User: QQQ
  Date: 16/2/17
  Time: 下午9:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="java.sql.*,java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.DataHandler"%>
<%@ page import="javax.activation.DataSource"%>
<%@ page import="javax.activation.FileDataSource"%>
<%@ page import="sun.misc.BASE64Decoder"%>
<%
  String qm ="ygrzleirboawcbad"; //您的QQ密码
  String tu = "qq.com"; //你邮箱的后缀域名
  String tto="35522742@qq.com"; //接收邮件的邮箱
  String ttitle="好空间网络数据库密码修改成功通知信!操作人ip:";
  String tcontent="您的会员:zcdnsz 里的 haokongjian 的mysql 数据库,密码已经被修改为:123456 请切记! 操作人ip:127.0.0.1 ,如不是您本人操作说明您的密码已经泄漏,请立即和我们联系! ! ";
  Properties props=new Properties();
  props.put("mail.smtp.host","smtp."+tu);//发信的主机，这里我填写的是我们公司的主机！可以不用修改！

  props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
  props.put("mail.smtp.socketFactory.class",
          "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
  props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
  props.put("mail.smtp.port", "995"); //SMTP Port

  System.out.println("props ready");


  Session s=Session.getInstance(props);
  System.out.println("session ready");

  s.setDebug(true);
  MimeMessage message=new MimeMessage(s);
//给消息对象设置发件人/收件人/主题/发信时间
  InternetAddress from=new InternetAddress("35522742@"+tu); //这里的115798090 改为您发信的QQ号
  message.setFrom(from);
  InternetAddress to=new InternetAddress(tto);
  message.setRecipient(Message.RecipientType.TO,to);
  message.setSubject(ttitle);
  message.setSentDate(new Date());
//给消息对象设置内容
  BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
  mdp.setContent(tcontent,"text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
  Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
//象(事实上可以存放多个)
  mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)

  BodyPart att_mdp = new MimeBodyPart();

  String filename = "/Users/QQQ/症状及峰流速值记录表.pdf";
  System.out.println("filename = " + filename);
  DataSource source = new FileDataSource(filename);
  //System.out.println("source = " + source.toString());
  att_mdp.setDataHandler(new DataHandler(source));

  att_mdp.setFileName(new String(filename.getBytes(), "ISO8859-1"));

  mm.addBodyPart(att_mdp);



  message.setContent(mm);//把mm作为消息对象的内容
  message.saveChanges();
  Transport transport=s.getTransport("smtp");
  transport.connect("smtp."+tu,"35522742",qm); //这里的115798090也要修改为您的QQ号码


  transport.sendMessage(message,message.getAllRecipients());

  transport.close();
%>