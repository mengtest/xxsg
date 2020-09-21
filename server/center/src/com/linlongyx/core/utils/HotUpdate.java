/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import com.sun.tools.attach.VirtualMachine;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.nio.channels.FileChannel;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HotUpdate
/*     */ {
/*  31 */   public static String SYSTEM_SEPARATE = System.getProperty("file.separator");
/*     */   
/*     */   private Resource basePath;
/*     */   
/*     */   private Resource agentPath;
/*     */   
/*     */   public void update() {
/*  38 */     String newFilePath = "";
/*     */     try {
/*  40 */       newFilePath = this.basePath.getFile().getPath();
/*  41 */     } catch (IOException e1) {
/*  42 */       e1.printStackTrace();
/*     */     } 
/*  44 */     try (FileInputStream fileInputStream = new FileInputStream(newFilePath + File.separator + "info.txt")) {
/*  45 */       BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
/*  46 */       StringBuilder sb = new StringBuilder();
/*  47 */       sb.append(newFilePath);
/*  48 */       String content = null;
/*  49 */       while ((content = reader.readLine()) != null) {
/*  50 */         sb.append("#").append(content);
/*     */       }
/*  52 */       String name = ManagementFactory.getRuntimeMXBean().getName();
/*  53 */       String pid = name.split("@")[0];
/*  54 */       VirtualMachine vm = VirtualMachine.attach(pid);
/*  55 */       String agent = this.agentPath.getFile().getPath();
/*  56 */       vm.loadAgent(agent, sb.toString());
/*  57 */       vm.detach();
/*  58 */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void fileChannelCopy(File s, File t) {
/*  64 */     FileInputStream fi = null;
/*  65 */     FileOutputStream fo = null;
/*  66 */     FileChannel in = null;
/*  67 */     FileChannel out = null;
/*     */     try {
/*  69 */       fi = new FileInputStream(s);
/*  70 */       fo = new FileOutputStream(t);
/*  71 */       in = fi.getChannel();
/*  72 */       out = fo.getChannel();
/*  73 */       in.transferTo(0L, in.size(), out);
/*  74 */     } catch (IOException e) {
/*  75 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  78 */         fi.close();
/*  79 */         in.close();
/*  80 */         fo.close();
/*  81 */         out.close();
/*  82 */       } catch (IOException e) {
/*  83 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource getBasePath() {
/*  92 */     return this.basePath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBasePath(Resource basePath) {
/*  99 */     this.basePath = basePath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource getAgentPath() {
/* 106 */     return this.agentPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAgentPath(Resource agentPath) {
/* 113 */     this.agentPath = agentPath;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\HotUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */