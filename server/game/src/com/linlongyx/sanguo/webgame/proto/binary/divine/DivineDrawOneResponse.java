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
/*    */ public class DivineDrawOneResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte hit;
/*    */   public String divineNum;
/*    */   public int divineTimes;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeByte(out, this.hit);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.divineNum);
/* 32 */     ProtocolUtil.writeInt(out, this.divineTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.hit = ProtocolUtil.readUTFBinByte(in);
/* 38 */     this.divineNum = ProtocolUtil.readUTFStr(in);
/* 39 */     this.divineTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineDrawOneResponse clone() throws CloneNotSupportedException {
/* 44 */     DivineDrawOneResponse clone = (DivineDrawOneResponse)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.hit = 0;
/* 51 */     this.divineNum = null;
/* 52 */     this.divineTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"hit\":" + this.hit + ",\"divineNum\":" + StringUtil.str2Str(this.divineNum) + ",\"divineTimes\":" + this.divineTimes + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\divine\DivineDrawOneResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */