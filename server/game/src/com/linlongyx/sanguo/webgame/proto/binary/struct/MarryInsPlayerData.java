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
/*    */ public class MarryInsPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public String name;
/*    */   public String head;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeLong(out, this.playerId);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 27 */     this.name = ProtocolUtil.readUTFStr(in);
/* 28 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MarryInsPlayerData clone() throws CloneNotSupportedException {
/* 33 */     MarryInsPlayerData clone = (MarryInsPlayerData)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.playerId = 0L;
/* 40 */     this.name = null;
/* 41 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MarryInsPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */