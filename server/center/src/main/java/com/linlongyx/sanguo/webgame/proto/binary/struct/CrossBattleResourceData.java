/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleResourceData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public int resourceId;
/*    */   public int num;
/*    */   public int state;
/*    */   public byte own;
/*    */   public int camp;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeLong(out, this.playerId);
/* 23 */     ProtocolUtil.writeInt(out, this.resourceId);
/* 24 */     ProtocolUtil.writeInt(out, this.num);
/* 25 */     ProtocolUtil.writeInt(out, this.state);
/* 26 */     ProtocolUtil.writeByte(out, this.own);
/* 27 */     ProtocolUtil.writeInt(out, this.camp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 33 */     this.resourceId = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.own = ProtocolUtil.readUTFBinByte(in);
/* 37 */     this.camp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleResourceData clone() throws CloneNotSupportedException {
/* 42 */     CrossBattleResourceData clone = (CrossBattleResourceData)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.playerId = 0L;
/* 49 */     this.resourceId = 0;
/* 50 */     this.num = 0;
/* 51 */     this.state = 0;
/* 52 */     this.own = 0;
/* 53 */     this.camp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"playerId\":" + this.playerId + ",\"resourceId\":" + this.resourceId + ",\"num\":" + this.num + ",\"state\":" + this.state + ",\"own\":" + this.own + ",\"camp\":" + this.camp + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CrossBattleResourceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */