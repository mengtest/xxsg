/*    */ package com.linlongyx.sanguo.webgame.proto.binary.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class DivineInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public String divineNum;
/*    */   public int divineTimes;
/*    */   public int time;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.actId);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.divineNum);
/* 33 */     ProtocolUtil.writeInt(out, this.divineTimes);
/* 34 */     ProtocolUtil.writeInt(out, this.time);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.divineNum = ProtocolUtil.readUTFStr(in);
/* 41 */     this.divineTimes = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineInfoResponse clone() throws CloneNotSupportedException {
/* 47 */     DivineInfoResponse clone = (DivineInfoResponse)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.actId = 0;
/* 54 */     this.divineNum = null;
/* 55 */     this.divineTimes = 0;
/* 56 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"actId\":" + this.actId + ",\"divineNum\":" + StringUtil.str2Str(this.divineNum) + ",\"divineTimes\":" + this.divineTimes + ",\"time\":" + this.time + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\divine\DivineInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */