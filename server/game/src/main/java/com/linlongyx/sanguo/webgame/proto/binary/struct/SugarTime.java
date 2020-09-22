/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SugarTime
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int id;
/*    */   public int startTime;
/*    */   public int middleTime;
/*    */   public int endTime;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.id);
/* 21 */     ProtocolUtil.writeInt(out, this.startTime);
/* 22 */     ProtocolUtil.writeInt(out, this.middleTime);
/* 23 */     ProtocolUtil.writeInt(out, this.endTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.startTime = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.middleTime = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SugarTime clone() throws CloneNotSupportedException {
/* 36 */     SugarTime clone = (SugarTime)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.id = 0;
/* 43 */     this.startTime = 0;
/* 44 */     this.middleTime = 0;
/* 45 */     this.endTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"id\":" + this.id + ",\"startTime\":" + this.startTime + ",\"middleTime\":" + this.middleTime + ",\"endTime\":" + this.endTime + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SugarTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */