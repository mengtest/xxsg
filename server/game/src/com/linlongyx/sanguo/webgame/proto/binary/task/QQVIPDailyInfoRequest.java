/*    */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class QQVIPDailyInfoRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String openId;
/*    */   public String openKey;
/*    */   public String pf;
/*    */   public String platform;
/*    */   public String gameUID;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.openId);
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.openKey);
/* 35 */     ProtocolUtil.writeUTFBinary(out, this.pf);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.platform);
/* 37 */     ProtocolUtil.writeUTFBinary(out, this.gameUID);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.openId = ProtocolUtil.readUTFStr(in);
/* 43 */     this.openKey = ProtocolUtil.readUTFStr(in);
/* 44 */     this.pf = ProtocolUtil.readUTFStr(in);
/* 45 */     this.platform = ProtocolUtil.readUTFStr(in);
/* 46 */     this.gameUID = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public QQVIPDailyInfoRequest clone() throws CloneNotSupportedException {
/* 51 */     QQVIPDailyInfoRequest clone = (QQVIPDailyInfoRequest)super.clone();
/* 52 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 57 */     this.openId = null;
/* 58 */     this.openKey = null;
/* 59 */     this.pf = null;
/* 60 */     this.platform = null;
/* 61 */     this.gameUID = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"openId\":" + StringUtil.str2Str(this.openId) + ",\"openKey\":" + StringUtil.str2Str(this.openKey) + ",\"pf\":" + StringUtil.str2Str(this.pf) + ",\"platform\":" + StringUtil.str2Str(this.platform) + ",\"gameUID\":" + StringUtil.str2Str(this.gameUID) + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\QQVIPDailyInfoRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */