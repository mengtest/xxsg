/*    */ package com.linlongyx.core.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import sun.net.util.IPAddressUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static String strArray2Str(String[] strs) {
/* 14 */     String res = "[";
/* 15 */     for (String s : strs) {
/* 16 */       res = res + "\"" + s + "\",";
/*    */     }
/* 18 */     res = res.substring(0, res.length() - 1);
/* 19 */     res = res + "]";
/* 20 */     return res;
/*    */   }
/*    */   
/*    */   public static String strArray2Str(ArrayList<String> strs) {
/* 24 */     String res = "[";
/* 25 */     for (String s : strs) {
/* 26 */       res = res + "\"" + s + "\",";
/*    */     }
/* 28 */     res = res.substring(0, res.length() - 1);
/* 29 */     res = res + "]";
/* 30 */     return res;
/*    */   }
/*    */   
/*    */   public static String str2Str(String str) {
/* 34 */     if (null == str) return "null"; 
/* 35 */     return "\"" + str + "\"";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String padLeft(String str, int len) {
/* 45 */     String pad = "0x00000000000000";
/* 46 */     return (len > str.length() && len <= 16 && len >= 0) ? (pad.substring(0, len - str.length()) + str) : str;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int IpStr2IpNum(String ipStr) {
/* 52 */     byte[] ipBytes = (ipStr == null) ? null : IPAddressUtil.textToNumericFormatV4(ipStr);
/* 53 */     if (ipBytes == null)
/*    */     {
/* 55 */       return -1;
/*    */     }
/* 57 */     int addr = ipBytes[3] & 0xFF;
/* 58 */     addr |= ipBytes[2] << 8 & 0xFF00;
/* 59 */     addr |= ipBytes[1] << 16 & 0xFF0000;
/* 60 */     addr |= ipBytes[0] << 24 & 0xFF000000;
/* 61 */     return addr;
/*    */   }
/*    */   
/*    */   public static String IpNum2IpStr(int ipInt) {
/* 65 */     return String.valueOf(ipInt >> 24 & 0xFF) + '.' + (ipInt >> 16 & 0xFF) + '.' + (ipInt >> 8 & 0xFF) + '.' + (ipInt & 0xFF);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\cor\\utils\StringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */