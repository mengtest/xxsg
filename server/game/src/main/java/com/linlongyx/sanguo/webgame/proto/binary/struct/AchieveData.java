/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AchieveData
/*    */ {
/*    */   public int id;
/*    */   public long value;
/*    */   public int status;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.id);
/* 18 */     ProtocolUtil.writeLong(out, this.value);
/* 19 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AchieveData clone() throws CloneNotSupportedException {
/* 31 */     AchieveData clone = (AchieveData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.id = 0;
/* 38 */     this.value = 0L;
/* 39 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"id\":" + this.id + ",\"value\":" + this.value + ",\"status\":" + this.status + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AchieveData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */