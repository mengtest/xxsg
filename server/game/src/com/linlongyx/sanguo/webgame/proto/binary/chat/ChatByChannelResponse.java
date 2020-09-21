/*    */ package com.linlongyx.sanguo.webgame.proto.binary.chat;
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
/*    */ public class ChatByChannelResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<ChatInfo> list = new ArrayList<>();
/*    */   
/*    */   public ChatByChannelResponse() {
/* 23 */     this.eventResponseId = 20604;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChatByChannelResponse(short retCode) {
/* 28 */     this.eventResponseId = 20604;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.list.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ChatInfo tmp_0 = this.list.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       ChatInfo tmp_0 = new ChatInfo();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatByChannelResponse clone() throws CloneNotSupportedException {
/* 59 */     ChatByChannelResponse clone = (ChatByChannelResponse)super.clone();
/* 60 */     int size_0 = this.list.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       ChatInfo tmp_0 = this.list.get(index_0);
/* 64 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"list\":" + this.list.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\chat\ChatByChannelResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */