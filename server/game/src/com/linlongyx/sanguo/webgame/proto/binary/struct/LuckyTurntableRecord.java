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
/*    */ public class LuckyTurntableRecord
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String playerName;
/*    */   public int itemId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 21 */     ProtocolUtil.writeInt(out, this.itemId);
/* 22 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 28 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyTurntableRecord clone() throws CloneNotSupportedException {
/* 34 */     LuckyTurntableRecord clone = (LuckyTurntableRecord)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.playerName = null;
/* 41 */     this.itemId = 0;
/* 42 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"itemId\":" + this.itemId + ",\"num\":" + this.num + "}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LuckyTurntableRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */