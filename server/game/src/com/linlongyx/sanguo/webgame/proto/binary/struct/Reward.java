/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Reward
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte type;
/*    */   public int id;
/*    */   public long num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeByte(out, this.type);
/* 21 */     ProtocolUtil.writeInt(out, this.id);
/* 22 */     ProtocolUtil.writeLong(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 28 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.num = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public Reward clone() throws CloneNotSupportedException {
/* 34 */     Reward clone = (Reward)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.type = 0;
/* 41 */     this.id = 0;
/* 42 */     this.num = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + ",\"num\":" + this.num + "}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\Reward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */