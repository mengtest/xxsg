/*     */ package com.linlongyx.sanguo.webgame.util;
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
/*     */ public class Base64Util
/*     */ {
/*     */   public static String encode(byte[] data) {
/*  16 */     return encode(data, data.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String encode(byte[] data, int length) {
/*  21 */     char[] out = new char[(length + 2) / 3 * 4];
/*     */     
/*  23 */     for (int i = 0, index = 0; i < length; i += 3, index += 4) {
/*     */       
/*  25 */       boolean quad = false;
/*  26 */       boolean trip = false;
/*     */       
/*  28 */       int val = 0xFF & data[i];
/*  29 */       val <<= 8;
/*  30 */       if (i + 1 < length) {
/*     */         
/*  32 */         val |= 0xFF & data[i + 1];
/*  33 */         trip = true;
/*     */       } 
/*  35 */       val <<= 8;
/*  36 */       if (i + 2 < length) {
/*     */         
/*  38 */         val |= 0xFF & data[i + 2];
/*  39 */         quad = true;
/*     */       } 
/*  41 */       out[index + 3] = alphabet[quad ? (val & 0x3F) : 64];
/*  42 */       val >>= 6;
/*  43 */       out[index + 2] = alphabet[trip ? (val & 0x3F) : 64];
/*  44 */       val >>= 6;
/*  45 */       out[index + 1] = alphabet[val & 0x3F];
/*  46 */       val >>= 6;
/*  47 */       out[index + 0] = alphabet[val & 0x3F];
/*     */     } 
/*     */     
/*  50 */     return new String(out);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] decode(String data) {
/*  56 */     int tempLen = data.length();
/*  57 */     for (int ix = 0; ix < data.length(); ix++) {
/*     */       
/*  59 */       if (data.charAt(ix) > 'ÿ' || codes[data.charAt(ix)] < 0) {
/*  60 */         tempLen--;
/*     */       }
/*     */     } 
/*     */     
/*  64 */     int len = tempLen / 4 * 3;
/*  65 */     if (tempLen % 4 == 3)
/*  66 */       len += 2; 
/*  67 */     if (tempLen % 4 == 2) {
/*  68 */       len++;
/*     */     }
/*  70 */     byte[] out = new byte[len];
/*     */     
/*  72 */     int shift = 0;
/*  73 */     int accum = 0;
/*  74 */     int index = 0;
/*     */ 
/*     */     
/*  77 */     for (int i = 0; i < data.length(); i++) {
/*     */       
/*  79 */       int value = (data.charAt(i) > 'ÿ') ? -1 : codes[data.charAt(i)];
/*     */       
/*  81 */       if (value >= 0) {
/*     */         
/*  83 */         accum <<= 6;
/*  84 */         shift += 6;
/*  85 */         accum |= value;
/*  86 */         if (shift >= 8) {
/*     */           
/*  88 */           shift -= 8;
/*  89 */           out[index++] = (byte)(accum >> shift & 0xFF);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (index != out.length)
/*     */     {
/*  98 */       return new byte[0];
/*     */     }
/*     */     
/* 101 */     return out;
/*     */   }
/*     */   
/* 104 */   private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
/*     */   
/* 106 */   private static byte[] codes = new byte[256];
/*     */   static {
/*     */     int i;
/* 109 */     for (i = 0; i < 256; i++)
/* 110 */       codes[i] = -1; 
/* 111 */     for (i = 65; i <= 90; i++)
/* 112 */       codes[i] = (byte)(i - 65); 
/* 113 */     for (i = 97; i <= 122; i++)
/* 114 */       codes[i] = (byte)(26 + i - 97); 
/* 115 */     for (i = 48; i <= 57; i++)
/* 116 */       codes[i] = (byte)(52 + i - 48); 
/* 117 */     codes[43] = 62;
/* 118 */     codes[47] = 63;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\Base64Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */