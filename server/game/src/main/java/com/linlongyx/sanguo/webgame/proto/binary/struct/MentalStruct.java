/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MentalStruct
/*    */   implements Serializable
/*    */ {
/*    */   public int mentalId;
/*    */   public int level;
/*    */   public int progress;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.mentalId);
/* 19 */     ProtocolUtil.writeInt(out, this.level);
/* 20 */     ProtocolUtil.writeInt(out, this.progress);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.mentalId = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MentalStruct clone() throws CloneNotSupportedException {
/* 32 */     MentalStruct clone = (MentalStruct)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.mentalId = 0;
/* 39 */     this.level = 0;
/* 40 */     this.progress = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{\"mentalId\":" + this.mentalId + ",\"level\":" + this.level + ",\"progress\":" + this.progress + "}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MentalStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */