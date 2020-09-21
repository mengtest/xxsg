/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
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
/* 19 */     ProtocolUtil.writeByte(out, this.type);
/* 20 */     ProtocolUtil.writeInt(out, this.id);
/* 21 */     ProtocolUtil.writeLong(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 27 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.num = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public Reward clone() throws CloneNotSupportedException {
/* 33 */     Reward clone = (Reward)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.type = 0;
/* 40 */     this.id = 0;
/* 41 */     this.num = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + ",\"num\":" + this.num + "}";
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\Reward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */