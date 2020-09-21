/*    */ package com.linlongyx.sanguo.client.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WebsocketUtil
/*    */ {
/*    */   public static void writeBinByte(ByteBuf out, byte val) {
/* 13 */     out.writeByte(1);
/* 14 */     out.writeByte(val);
/*    */   }
/*    */   public static void writeBinByte(ByteBuf out, short val) {
/* 17 */     out.writeByte(2);
/* 18 */     out.writeShort(val);
/*    */   }
/*    */   public static void writeBinByte(ByteBuf out, int val) {
/* 21 */     out.writeByte(3);
/* 22 */     out.writeInt(val);
/*    */   }
/*    */   public static void writeBinByte(ByteBuf out, long val) {
/* 25 */     out.writeByte(4);
/* 26 */     out.writeLong(val);
/*    */   }
/*    */   public static void writeBinByte(ByteBuf out, float val) {
/* 29 */     out.writeByte(5);
/* 30 */     out.writeFloat(val);
/*    */   }
/*    */   public static void writeBinByte(ByteBuf out, double val) {
/* 33 */     out.writeByte(6);
/* 34 */     out.writeDouble(val);
/*    */   }
/*    */   
/*    */   public static void writeUTFBinary(ByteBuf out, String str) {
/* 38 */     if (null == str) str = ""; 
/* 39 */     byte[] bytearr = null;
/*    */     try {
/* 41 */       bytearr = str.getBytes("utf-8");
/* 42 */     } catch (Exception exception) {}
/*    */     
/* 44 */     if (bytearr != null) {
/* 45 */       out.writeInt(bytearr.length);
/* 46 */       out.writeBytes(bytearr);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\net\WebsocketUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */