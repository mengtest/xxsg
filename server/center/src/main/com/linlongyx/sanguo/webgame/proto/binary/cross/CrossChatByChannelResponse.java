/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossChatByChannelResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<ChatInfo> list = new ArrayList<>();
/*    */   
/*    */   public CrossChatByChannelResponse() {
/* 23 */     this.eventResponseId = 21312;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.list.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       ChatInfo tmp_0 = this.list.get(index_0);
/* 35 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       ChatInfo tmp_0 = new ChatInfo();
/* 46 */       tmp_0.unserial(in);
/* 47 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossChatByChannelResponse clone() throws CloneNotSupportedException {
/* 53 */     CrossChatByChannelResponse clone = (CrossChatByChannelResponse)super.clone();
/* 54 */     int size_0 = this.list.size();
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       ChatInfo tmp_0 = this.list.get(index_0);
/* 58 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 60 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"list\":" + this.list.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossChatByChannelResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */