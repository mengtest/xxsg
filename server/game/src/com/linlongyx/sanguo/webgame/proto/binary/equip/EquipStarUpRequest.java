/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ public class EquipStarUpRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long pid;
/*    */   public long equipId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.pid);
/* 30 */     ProtocolUtil.writeLong(out, this.equipId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.equipId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipStarUpRequest clone() throws CloneNotSupportedException {
/* 41 */     EquipStarUpRequest clone = (EquipStarUpRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.pid = 0L;
/* 48 */     this.equipId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"pid\":" + this.pid + ",\"equipId\":" + this.equipId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipStarUpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */