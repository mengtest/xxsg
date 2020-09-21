/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunnerData
/*    */ {
/*    */   public byte guid;
/*    */   public int distance;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 16 */     ProtocolUtil.writeByte(out, this.guid);
/* 17 */     ProtocolUtil.writeInt(out, this.distance);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 22 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/* 23 */     this.distance = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunnerData clone() throws CloneNotSupportedException {
/* 28 */     RunnerData clone = (RunnerData)super.clone();
/* 29 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 34 */     this.guid = 0;
/* 35 */     this.distance = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     String retVal = "{\"guid\":" + this.guid + ",\"distance\":" + this.distance + "}";
/* 41 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RunnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */