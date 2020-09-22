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
/*    */ public class EquipZhuUpRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long pid;
/*    */   public long mid;
/*    */   public int count;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.pid);
/* 31 */     ProtocolUtil.writeLong(out, this.mid);
/* 32 */     ProtocolUtil.writeInt(out, this.count);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.count = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipZhuUpRequest clone() throws CloneNotSupportedException {
/* 44 */     EquipZhuUpRequest clone = (EquipZhuUpRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.pid = 0L;
/* 51 */     this.mid = 0L;
/* 52 */     this.count = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"pid\":" + this.pid + ",\"mid\":" + this.mid + ",\"count\":" + this.count + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipZhuUpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */