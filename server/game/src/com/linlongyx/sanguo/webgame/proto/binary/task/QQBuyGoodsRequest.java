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
/*    */ public class QQBuyGoodsRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int goodsId;
/*    */   public int num;
/*    */   public String openId;
/*    */   public String openKey;
/*    */   public String pf;
/*    */   public String platform;
/*    */   public String gameUID;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.goodsId);
/* 38 */     ProtocolUtil.writeInt(out, this.num);
/* 39 */     ProtocolUtil.writeUTFBinary(out, this.openId);
/* 40 */     ProtocolUtil.writeUTFBinary(out, this.openKey);
/* 41 */     ProtocolUtil.writeUTFBinary(out, this.pf);
/* 42 */     ProtocolUtil.writeUTFBinary(out, this.platform);
/* 43 */     ProtocolUtil.writeUTFBinary(out, this.gameUID);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.goodsId = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.openId = ProtocolUtil.readUTFStr(in);
/* 52 */     this.openKey = ProtocolUtil.readUTFStr(in);
/* 53 */     this.pf = ProtocolUtil.readUTFStr(in);
/* 54 */     this.platform = ProtocolUtil.readUTFStr(in);
/* 55 */     this.gameUID = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public QQBuyGoodsRequest clone() throws CloneNotSupportedException {
/* 60 */     QQBuyGoodsRequest clone = (QQBuyGoodsRequest)super.clone();
/* 61 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 66 */     this.type = 0;
/* 67 */     this.goodsId = 0;
/* 68 */     this.num = 0;
/* 69 */     this.openId = null;
/* 70 */     this.openKey = null;
/* 71 */     this.pf = null;
/* 72 */     this.platform = null;
/* 73 */     this.gameUID = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"type\":" + this.type + ",\"goodsId\":" + this.goodsId + ",\"num\":" + this.num + ",\"openId\":" + StringUtil.str2Str(this.openId) + ",\"openKey\":" + StringUtil.str2Str(this.openKey) + ",\"pf\":" + StringUtil.str2Str(this.pf) + ",\"platform\":" + StringUtil.str2Str(this.platform) + ",\"gameUID\":" + StringUtil.str2Str(this.gameUID) + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\QQBuyGoodsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */