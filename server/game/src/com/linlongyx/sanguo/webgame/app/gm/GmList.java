/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import org.springframework.core.io.Resource;
/*    */ 
/*    */ public class GmList {
/*    */   private Resource basePath;
/*    */   
/*    */   public void setBasePath(Resource basePath) {
/* 14 */     this.basePath = basePath;
/*    */   }
/*    */   
/*    */   public String getGmList() {
/* 18 */     StringBuilder sb = new StringBuilder();
/* 19 */     String newFilePath = "";
/*    */     try {
/* 21 */       newFilePath = this.basePath.getFile().getPath();
/* 22 */     } catch (IOException e) {
/* 23 */       e.printStackTrace();
/*    */     } 
/* 25 */     try (FileInputStream fileInputStream = new FileInputStream(newFilePath + File.separator + "gm.txt")) {
/* 26 */       BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
/*    */       String content;
/* 28 */       while ((content = reader.readLine()) != null) {
/* 29 */         sb.append("\n").append(content);
/*    */       }
/* 31 */     } catch (IOException e) {
/* 32 */       e.printStackTrace();
/*    */     } 
/* 34 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\GmList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */