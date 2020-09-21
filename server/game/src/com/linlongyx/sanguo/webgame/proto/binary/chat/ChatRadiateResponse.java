/*    */ package com.linlongyx.sanguo.webgame.proto.binary.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class ChatRadiateResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int languageId;
/* 21 */   public ArrayList<String> list = new ArrayList<>();
/*    */   
/*    */   public ChatRadiateResponse() {
/* 24 */     this.eventResponseId = 20601;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChatRadiateResponse(short retCode) {
/* 29 */     this.eventResponseId = 20601;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.languageId);
/*    */     
/* 38 */     int size_0 = this.list.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       String tmp_0 = this.list.get(index_0);
/* 43 */       ProtocolUtil.writeUTFBinary(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.languageId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       String tmp_0 = ProtocolUtil.readUTFStr(in);
/* 55 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatRadiateResponse clone() throws CloneNotSupportedException {
/* 61 */     ChatRadiateResponse clone = (ChatRadiateResponse)super.clone();
/* 62 */     int size_0 = this.list.size();
/* 63 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 65 */       String tmp_0 = this.list.get(index_0);
/* 66 */       clone.list.add(tmp_0);
/*    */     } 
/* 68 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 73 */     this.languageId = 0;
/* 74 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     String retVal = "{\"languageId\":" + this.languageId + ",\"list\":" + StringUtil.strArray2Str(this.list) + "}";
/* 80 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\chat\ChatRadiateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */