/*    */ package com.linlongyx.core.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.xerial.snappy.Snappy;
/*    */ 
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
/* 16 */       return Snappy.compress(str.getBytes("UTF-8"));
/* 17 */     } catch (IOException e) {
/* 18 */       e.printStackTrace();
/* 19 */       return null;
/*    */     } 
/*    */   }
/*    */   public static String decompress(byte[] bytes) {
/*    */     try {
/* 24 */       return new String(Snappy.uncompress(bytes));
/* 25 */     } catch (IOException e) {
/* 26 */       e.printStackTrace();
/* 27 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] lz4compress(String str) {
/*    */     try {
/* 33 */       return Snappy.compress(str.getBytes("UTF-8"));
/* 34 */     } catch (IOException e) {
/* 35 */       e.printStackTrace();
/* 36 */       return null;
/*    */     } 
/*    */   }
/*    */   public static String lz4decompress(byte[] bytes) {
/*    */     try {
/* 41 */       return new String(Snappy.uncompress(bytes));
/* 42 */     } catch (IOException e) {
/* 43 */       e.printStackTrace();
/* 44 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 49 */     String json = "{\"userId\":92,\"players\":[{\"playerId\":10000002,\"name\":\"usrname_92\"},{\"playerId\":10000002,\"name\":\"playername_1\"}]}";
/* 50 */     byte[] bytes = compress(json);
/* 51 */     System.out.println(bytes.length);
/* 52 */     System.out.println(decompress(bytes));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\CompressUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */