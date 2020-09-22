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
/*    */ public class MainInsTipData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int rank;
/*    */   public String playerName;
/*    */   public long value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.rank);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 22 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 29 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MainInsTipData clone() throws CloneNotSupportedException {
/* 34 */     MainInsTipData clone = (MainInsTipData)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.rank = 0;
/* 41 */     this.playerName = null;
/* 42 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{\"rank\":" + this.rank + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"value\":" + this.value + "}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MainInsTipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */