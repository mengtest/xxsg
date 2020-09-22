/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpaRoomData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long sroomId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.sroomId);
/* 19 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.sroomId = ProtocolUtil.readUTFBinLong(in);
/* 25 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpaRoomData clone() throws CloneNotSupportedException {
/* 30 */     SpaRoomData clone = (SpaRoomData)super.clone();
/* 31 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 36 */     this.sroomId = 0L;
/* 37 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "{\"sroomId\":" + this.sroomId + ",\"num\":" + this.num + "}";
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SpaRoomData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */