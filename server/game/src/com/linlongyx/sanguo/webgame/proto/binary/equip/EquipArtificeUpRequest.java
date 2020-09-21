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
/*    */ public class EquipArtificeUpRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public long pid;
/*    */   public long mid;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/* 31 */     ProtocolUtil.writeLong(out, this.pid);
/* 32 */     ProtocolUtil.writeLong(out, this.mid);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipArtificeUpRequest clone() throws CloneNotSupportedException {
/* 44 */     EquipArtificeUpRequest clone = (EquipArtificeUpRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.type = 0;
/* 51 */     this.pid = 0L;
/* 52 */     this.mid = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"type\":" + this.type + ",\"pid\":" + this.pid + ",\"mid\":" + this.mid + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipArtificeUpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */