/*    */ package com.linlongyx.sanguo.webgame.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.xerial.snappy.Snappy;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompressUtil
/*    */ {
/*    */   public static byte[] compress(String str) {
/*    */     try {
/* 15 */       return Snappy.compress(str.getBytes("UTF-8"));
/* 16 */     } catch (IOException e) {
/* 17 */       e.printStackTrace();
/* 18 */       return null;
/*    */     } 
/*    */   }
/*    */   public static String decompress(byte[] bytes) {
/*    */     try {
/* 23 */       return new String(Snappy.uncompress(bytes));
/* 24 */     } catch (IOException e) {
/* 25 */       e.printStackTrace();
/* 26 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] lz4compress(String str) {
/*    */     try {
/* 32 */       return Snappy.compress(str.getBytes("UTF-8"));
/* 33 */     } catch (IOException e) {
/* 34 */       e.printStackTrace();
/* 35 */       return null;
/*    */     } 
/*    */   }
/*    */   public static String lz4decompress(byte[] bytes) {
/*    */     try {
/* 40 */       return new String(Snappy.uncompress(bytes));
/* 41 */     } catch (IOException e) {
/* 42 */       e.printStackTrace();
/* 43 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 48 */     String json = "{\"userId\":92,\"players\":[{\"playerId\":10000002,\"name\":\"usrname_92\"},{\"playerId\":10000002,\"name\":\"playername_1\"}]}";
/* 49 */     byte[] bytes = compress(json);
/* 50 */     System.out.println(bytes.length);
/* 51 */     System.out.println(decompress(bytes));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\CompressUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */