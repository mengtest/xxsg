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
/*    */ public class ChannelGiftGetRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int id;
/*    */   public String openId;
/*    */   public String openKey;
/*    */   public String pf;
/*    */   public String platform;
/*    */   public String gameUID;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.id);
/* 35 */     ProtocolUtil.writeUTFBinary(out, this.openId);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.openKey);
/* 37 */     ProtocolUtil.writeUTFBinary(out, this.pf);
/* 38 */     ProtocolUtil.writeUTFBinary(out, this.platform);
/* 39 */     ProtocolUtil.writeUTFBinary(out, this.gameUID);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.openId = ProtocolUtil.readUTFStr(in);
/* 46 */     this.openKey = ProtocolUtil.readUTFStr(in);
/* 47 */     this.pf = ProtocolUtil.readUTFStr(in);
/* 48 */     this.platform = ProtocolUtil.readUTFStr(in);
/* 49 */     this.gameUID = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChannelGiftGetRequest clone() throws CloneNotSupportedException {
/* 54 */     ChannelGiftGetRequest clone = (ChannelGiftGetRequest)super.clone();
/* 55 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 60 */     this.id = 0;
/* 61 */     this.openId = null;
/* 62 */     this.openKey = null;
/* 63 */     this.pf = null;
/* 64 */     this.platform = null;
/* 65 */     this.gameUID = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"id\":" + this.id + ",\"openId\":" + StringUtil.str2Str(this.openId) + ",\"openKey\":" + StringUtil.str2Str(this.openKey) + ",\"pf\":" + StringUtil.str2Str(this.pf) + ",\"platform\":" + StringUtil.str2Str(this.platform) + ",\"gameUID\":" + StringUtil.str2Str(this.gameUID) + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\ChannelGiftGetRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */