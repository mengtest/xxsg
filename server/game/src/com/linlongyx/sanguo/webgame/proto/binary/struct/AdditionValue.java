/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdditionValue
/*    */ {
/*    */   public int AdditionSys;
/*    */   public int additionType;
/*    */   public int value;
/*    */   public int time;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.AdditionSys);
/* 19 */     ProtocolUtil.writeInt(out, this.additionType);
/* 20 */     ProtocolUtil.writeInt(out, this.value);
/* 21 */     ProtocolUtil.writeInt(out, this.time);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.AdditionSys = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.additionType = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.value = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AdditionValue clone() throws CloneNotSupportedException {
/* 34 */     AdditionValue clone = (AdditionValue)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.AdditionSys = 0;
/* 41 */     this.additionType = 0;
/* 42 */     this.value = 0;
/* 43 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"AdditionSys\":" + this.AdditionSys + ",\"additionType\":" + this.additionType + ",\"value\":" + this.value + ",\"time\":" + this.time + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AdditionValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */