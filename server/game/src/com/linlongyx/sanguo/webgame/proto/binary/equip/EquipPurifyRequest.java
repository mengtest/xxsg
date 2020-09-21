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
/*    */ public class EquipPurifyRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long pid;
/*    */   public long mid;
/*    */   public int count;
/*    */   public int type;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeLong(out, this.pid);
/* 32 */     ProtocolUtil.writeLong(out, this.mid);
/* 33 */     ProtocolUtil.writeInt(out, this.count);
/* 34 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.count = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipPurifyRequest clone() throws CloneNotSupportedException {
/* 47 */     EquipPurifyRequest clone = (EquipPurifyRequest)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.pid = 0L;
/* 54 */     this.mid = 0L;
/* 55 */     this.count = 0;
/* 56 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"pid\":" + this.pid + ",\"mid\":" + this.mid + ",\"count\":" + this.count + ",\"type\":" + this.type + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipPurifyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */