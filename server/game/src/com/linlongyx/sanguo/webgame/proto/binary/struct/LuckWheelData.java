/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckWheelData
/*    */ {
/*    */   public String playerName;
/* 13 */   public Reward cost = new Reward();
/* 14 */   public Reward reward = new Reward();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 19 */     this.cost.serial(out);
/* 20 */     this.reward.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 26 */     this.cost = new Reward();
/* 27 */     this.cost.unserial(in);
/* 28 */     this.reward = new Reward();
/* 29 */     this.reward.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckWheelData clone() throws CloneNotSupportedException {
/* 34 */     LuckWheelData clone = (LuckWheelData)super.clone();
/* 35 */     clone.cost = this.cost.clone();
/* 36 */     clone.reward = this.reward.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.playerName = null;
/* 43 */     this.cost.reset();
/* 44 */     this.reward.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"cost\":" + this.cost.toString() + ",\"reward\":" + this.reward.toString() + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LuckWheelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */