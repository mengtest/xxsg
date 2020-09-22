/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MarryInsApplyStruct
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.playerId);
/* 19 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 25 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MarryInsApplyStruct clone() throws CloneNotSupportedException {
/* 30 */     MarryInsApplyStruct clone = (MarryInsApplyStruct)super.clone();
/* 31 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 36 */     this.playerId = 0L;
/* 37 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MarryInsApplyStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */