<%--
  Created by IntelliJ IDEA.
  User: QQQ
  Date: 16/2/17
  Time: ����9:43
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

<%
  String qm ="ygrzleirboawcbad"; //����QQ����
  String tu = "qq.com"; //������ĺ�׺����
  String tto="35522742@qq.com"; //�����ʼ�������
  String ttitle="test with attachment";
  String tcontent="���Ļ�Ա:zcdnsz ��� haokongjian ��mysql ���ݿ�,�����Ѿ����޸�Ϊ:123456 ���м�! ������ip:127.0.0.1 ,�粻�������˲���˵�����������Ѿ�й©,��������������ϵ! ! ";
  Properties props=new Properties();
  props.put("mail.smtp.host","smtp."+tu);//���ŵ���������������д�������ǹ�˾�����������Բ����޸ģ�

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
  message.addHeader("Content-type", "text/HTML; charset=UTF-8");
  message.addHeader("format", "flowed");
  message.addHeader("Content-Transfer-Encoding", "8bit");

//����Ϣ�������÷�����/�ռ���/����/����ʱ��
  InternetAddress from=new InternetAddress("35522742@"+tu); //�����115798090 ��Ϊ�����ŵ�QQ��
  message.setFrom(from);
  InternetAddress to=new InternetAddress(tto);
  message.setRecipient(Message.RecipientType.TO,to);
  message.setSubject(ttitle);
  message.setSentDate(new Date());

//����Ϣ������������
 // BodyPart mdp=new MimeBodyPart();//�½�һ������ż����ݵ�BodyPart����
 // mdp.setContent(tcontent,"text/html;charset=gb2312");//��BodyPart�����������ݺ͸�ʽ/���뷽ʽ
  Multipart mm=new MimeMultipart();//�½�һ��MimeMultipart�����������BodyPart����(��ʵ�Ͽ��Դ�Ŷ��)
//  mm.addBodyPart(mdp);//��BodyPart���뵽MimeMultipart������(���Լ�����BodyPart)

  BodyPart att_mdp = new MimeBodyPart();
  String filename = "/Users/QQQ/Desktop/test.txt";
  System.out.println("filename = " + filename);
  DataSource source = new FileDataSource(filename);
  //System.out.println("source = " + source.toString());
  att_mdp.setDataHandler(new DataHandler(source));
  att_mdp.setFileName(filename);
  mm.addBodyPart(att_mdp);

  message.setContent(mm);//��mm��Ϊ��Ϣ���������
  message.saveChanges();
  Transport transport=s.getTransport("smtp");
  transport.connect("smtp."+tu,"35522742",qm); //�����115798090ҲҪ�޸�Ϊ����QQ����

  transport.sendMessage(message,message.getAllRecipients());

  transport.close();
%>