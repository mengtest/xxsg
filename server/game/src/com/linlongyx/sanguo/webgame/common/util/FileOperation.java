/*     */ package com.linlongyx.sanguo.webgame.common.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileOperation
/*     */ {
/*     */   public static boolean createFile(File fileName) throws Exception {
/*  17 */     boolean flag = false;
/*     */     try {
/*  19 */       if (!fileName.exists()) {
/*  20 */         fileName.createNewFile();
/*  21 */         flag = true;
/*     */       } 
/*  23 */     } catch (Exception e) {
/*  24 */       e.printStackTrace();
/*     */     } 
/*  26 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readTxtFile(File fileName) throws Exception {
/*  36 */     String result = null;
/*  37 */     FileReader fileReader = null;
/*  38 */     BufferedReader bufferedReader = null;
/*     */     try {
/*  40 */       fileReader = new FileReader(fileName);
/*  41 */       bufferedReader = new BufferedReader(fileReader);
/*     */       try {
/*  43 */         String read = null;
/*  44 */         while ((read = bufferedReader.readLine()) != null) {
/*  45 */           result = result + read + "\r\n";
/*     */         }
/*  47 */       } catch (Exception e) {
/*  48 */         e.printStackTrace();
/*     */       } 
/*  50 */     } catch (Exception e) {
/*  51 */       e.printStackTrace();
/*     */     } finally {
/*  53 */       if (bufferedReader != null) {
/*  54 */         bufferedReader.close();
/*     */       }
/*  56 */       if (fileReader != null) {
/*  57 */         fileReader.close();
/*     */       }
/*     */     } 
/*  60 */     System.out.println("读取出来的文件内容是：\r\n" + result);
/*  61 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean writeTxtFile(String content, File fileName) throws Exception {
/*  66 */     RandomAccessFile mm = null;
/*  67 */     boolean flag = false;
/*  68 */     FileOutputStream o = null;
/*     */     try {
/*  70 */       o = new FileOutputStream(fileName);
/*  71 */       o.write(content.getBytes("UTF-8"));
/*  72 */       o.close();
/*     */ 
/*     */       
/*  75 */       flag = true;
/*  76 */     } catch (Exception e) {
/*     */       
/*  78 */       e.printStackTrace();
/*     */     } finally {
/*  80 */       if (mm != null) {
/*  81 */         mm.close();
/*     */       }
/*     */     } 
/*  84 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void contentToTxt(String filePath, String content) {
/*  89 */     String str = new String();
/*  90 */     String s1 = new String();
/*     */     try {
/*  92 */       File f = new File(filePath);
/*  93 */       if (f.exists()) {
/*  94 */         System.out.print("文件存在");
/*     */       } else {
/*  96 */         System.out.print("文件不存在");
/*  97 */         f.createNewFile();
/*     */       } 
/*  99 */       BufferedReader input = new BufferedReader(new FileReader(f));
/*     */       
/* 101 */       while ((str = input.readLine()) != null) {
/* 102 */         s1 = s1 + str + "\n";
/*     */       }
/* 104 */       System.out.println(s1);
/* 105 */       input.close();
/* 106 */       s1 = s1 + content;
/*     */       
/* 108 */       BufferedWriter output = new BufferedWriter(new FileWriter(f));
/* 109 */       output.write(s1);
/* 110 */       output.close();
/* 111 */     } catch (Exception e) {
/* 112 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\FileOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */