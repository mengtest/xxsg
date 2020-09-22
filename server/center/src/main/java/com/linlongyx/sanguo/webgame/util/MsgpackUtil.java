/*    */ package com.linlongyx.sanguo.webgame.util;
/*    */ 
/*    */ import com.linlongyx.core.utils.GsonUtil;
/*    */ import java.io.IOException;
/*    */ import org.msgpack.MessagePack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MsgpackUtil
/*    */ {
/* 14 */   public static MessagePack pack = new MessagePack();
/*    */   
/*    */   public static byte[] writePack(byte[] bytes, String json) {
/*    */     try {
/* 18 */       bytes = pack.write(json);
/* 19 */     } catch (IOException e) {
/* 20 */       e.printStackTrace();
/*    */     } 
/* 22 */     return bytes;
/*    */   }
/*    */   
/*    */   public static byte[] writePack(String json) {
/* 26 */     byte[] bytes = new byte[0];
/*    */     try {
/* 28 */       bytes = pack.write(json);
/* 29 */     } catch (IOException e) {
/* 30 */       e.printStackTrace();
/*    */     } 
/* 32 */     return bytes;
/*    */   }
/*    */   
/*    */   public static <T> T readPack(byte[] bytes, Class<T> clazz) {
/*    */     try {
/* 37 */       return (T)GsonUtil.fromJson(pack.read(bytes).toString(), clazz);
/* 38 */     } catch (IOException e) {
/* 39 */       e.printStackTrace();
/*    */       
/* 41 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgam\\util\MsgpackUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */