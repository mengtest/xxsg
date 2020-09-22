/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuardData
/*    */   implements Serializable
/*    */ {
/*    */   public int part;
/* 14 */   public BestItem bestItem = new BestItem();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.part);
/* 19 */     this.bestItem.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.part = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.bestItem = new BestItem();
/* 26 */     this.bestItem.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GuardData clone() throws CloneNotSupportedException {
/* 31 */     GuardData clone = (GuardData)super.clone();
/* 32 */     clone.bestItem = this.bestItem.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.part = 0;
/* 39 */     this.bestItem.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"part\":" + this.part + ",\"bestItem\":" + this.bestItem.toString() + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GuardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */