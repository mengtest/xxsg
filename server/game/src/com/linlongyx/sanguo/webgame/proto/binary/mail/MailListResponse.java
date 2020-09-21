/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mail;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MailInfo;
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
/*    */ public class MailListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<MailInfo> mails = new ArrayList<>();
/*    */   
/*    */   public MailListResponse() {
/* 23 */     this.eventResponseId = 20501;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MailListResponse(short retCode) {
/* 28 */     this.eventResponseId = 20501;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.mails.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       MailInfo tmp_0 = this.mails.get(index_0);
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
/* 51 */       MailInfo tmp_0 = new MailInfo();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.mails.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MailListResponse clone() throws CloneNotSupportedException {
/* 59 */     MailListResponse clone = (MailListResponse)super.clone();
/* 60 */     int size_0 = this.mails.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       MailInfo tmp_0 = this.mails.get(index_0);
/* 64 */       clone.mails.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.mails.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"mails\":" + this.mails.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mail\MailListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */