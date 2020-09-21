/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.activation.DataHandler;
/*     */ import javax.activation.FileDataSource;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.BodyPart;
/*     */ import javax.mail.Message;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import javax.mail.internet.MimeMultipart;
/*     */ import javax.mail.internet.MimeUtility;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public class MailUtil {
/*  27 */   private static String sysEmailAccount = "linshuhong@linlongyx.com";
/*  28 */   private static String sysEmailPassword = "krd2Futey6wYkFyT";
/*  29 */   private static String smtpHost = "smtp.exmail.qq.com";
/*     */   
/*  31 */   private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
/*     */   
/*     */   public static void sendMail(String subject, String html, String[] receivers) {
/*  34 */     sendMail(subject, html, receivers, null, true, (byte)0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMail(String subject, String html, String[] receivers, String smtpPort, boolean withSSL, byte type) {
/*  39 */     Properties properties = new Properties();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     properties.setProperty("mail.transport.protocol", "smtp");
/*  46 */     properties.setProperty("mail.smtp.host", smtpHost);
/*  47 */     properties.setProperty("mail.smtp.auth", "true");
/*  48 */     properties.setProperty("mail.smtp.port", StringUtils.isEmpty(smtpPort) ? "465" : smtpPort);
/*  49 */     if (withSSL) {
/*  50 */       properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
/*  51 */       properties.setProperty("mail.smtp.socketFactory.fallback", "false");
/*  52 */       properties.setProperty("mail.smtp.socketFactory.port", StringUtils.isEmpty(smtpPort) ? "465" : smtpPort);
/*     */     } 
/*     */     try {
/*     */       MimeMessage msg;
/*  56 */       InternetAddress[] addresses = new InternetAddress[receivers.length];
/*  57 */       for (int i = 0; i < receivers.length; i++) {
/*  58 */         addresses[i] = new InternetAddress(receivers[i], "system", "UTF-8");
/*     */       }
/*     */       
/*  61 */       Session session = Session.getInstance(properties);
/*  62 */       if (logger.isDebugEnabled()) {
/*  63 */         session.setDebug(true);
/*     */       }
/*     */ 
/*     */       
/*  67 */       switch (type) {
/*     */         case 1:
/*  69 */           msg = createMultiMimeMessage(session, sysEmailAccount, addresses, subject, html);
/*     */           break;
/*     */         default:
/*  72 */           msg = createMimeMessage(session, sysEmailAccount, addresses, subject, html); break;
/*     */       } 
/*  74 */       Transport transport = session.getTransport();
/*  75 */       transport.connect(sysEmailAccount, sysEmailPassword);
/*     */       
/*  77 */       transport.sendMessage((Message)msg, msg.getAllRecipients());
/*     */       
/*  79 */       transport.close();
/*     */     }
/*  81 */     catch (UnsupportedEncodingException|MessagingException|MalformedURLException e) {
/*  82 */       e.printStackTrace();
/*  83 */       LogUtils.errorLog(new Object[] { e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static MimeMessage createMimeMessage(Session session, String sender, InternetAddress[] addresses, String subject, String html) throws UnsupportedEncodingException, MessagingException {
/*  90 */     MimeMessage message = new MimeMessage(session);
/*     */ 
/*     */     
/*  93 */     message.setFrom((Address)new InternetAddress(sender, "系统", "UTF-8"));
/*     */ 
/*     */     
/*  96 */     message.setRecipients(MimeMessage.RecipientType.TO, (Address[])addresses);
/*     */ 
/*     */ 
/*     */     
/* 100 */     message.setSubject(subject + "_服务器启动通知", "UTF-8");
/*     */ 
/*     */     
/* 103 */     message.setContent(html, "text/html;charset=UTF-8");
/*     */ 
/*     */     
/* 106 */     message.setSentDate(new Date());
/*     */ 
/*     */     
/* 109 */     message.saveChanges();
/* 110 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   private static MimeMessage createMultiMimeMessage(Session session, String sender, InternetAddress[] addresses, String subject, String html) throws UnsupportedEncodingException, MessagingException, MalformedURLException {
/* 115 */     MimeMessage message = new MimeMessage(session);
/*     */ 
/*     */     
/* 118 */     message.setFrom((Address)new InternetAddress(sender, "系统", "UTF-8"));
/*     */ 
/*     */     
/* 121 */     message.setRecipients(MimeMessage.RecipientType.TO, (Address[])addresses);
/*     */ 
/*     */ 
/*     */     
/* 125 */     message.setSubject(subject + "_服务器启动通知", "UTF-8");
/*     */ 
/*     */ 
/*     */     
/* 129 */     MimeBodyPart image = new MimeBodyPart();
/* 130 */     DataHandler dataHandler = new DataHandler(new URL("https://i0.hdslb.com/bfs/bangumi/a48979a3e3196fc33df796132072c0bd171f4918.jpg@240w_320h.jpg"));
/* 131 */     image.setDataHandler(dataHandler);
/* 132 */     image.setContentID("sys_image");
/*     */ 
/*     */     
/* 135 */     MimeBodyPart text = new MimeBodyPart();
/* 136 */     text.setContent("图片<br/><img src='cid:sys_image'/>", "text/html;charset=UTF-8");
/*     */ 
/*     */     
/* 139 */     MimeMultipart text_image_mix = new MimeMultipart();
/* 140 */     text_image_mix.addBodyPart((BodyPart)text);
/* 141 */     text_image_mix.addBodyPart((BodyPart)image);
/* 142 */     text_image_mix.setSubType("related");
/*     */ 
/*     */ 
/*     */     
/* 146 */     MimeBodyPart text_image = new MimeBodyPart();
/* 147 */     text_image.setContent((Multipart)text_image_mix);
/*     */ 
/*     */     
/* 150 */     MimeBodyPart attachment = new MimeBodyPart();
/* 151 */     DataHandler dh = new DataHandler(new FileDataSource("/Users/linshuhong/develop/project/java/sys/doc/README.md"));
/* 152 */     attachment.setDataHandler(dh);
/* 153 */     attachment.setFileName(MimeUtility.encodeText(dh.getName()));
/*     */ 
/*     */ 
/*     */     
/* 157 */     MimeMultipart mbp = new MimeMultipart();
/* 158 */     mbp.addBodyPart((BodyPart)text_image);
/* 159 */     mbp.addBodyPart((BodyPart)attachment);
/* 160 */     mbp.setSubType("mixed");
/*     */ 
/*     */     
/* 163 */     message.setContent((Multipart)mbp);
/*     */ 
/*     */     
/* 166 */     message.setSentDate(new Date());
/*     */ 
/*     */     
/* 169 */     message.saveChanges();
/* 170 */     return message;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 174 */     String[] address = { "xxxx@linlongyx.com", "xxxxxx@linlongyx.com" };
/*     */ 
/*     */ 
/*     */     
/* 178 */     sendMail("三群", "xxx服启动失败", address);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\MailUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */