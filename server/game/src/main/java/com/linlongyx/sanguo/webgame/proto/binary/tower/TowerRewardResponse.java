/*    */ package com.linlongyx.sanguo.webgame.proto.binary.tower;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class TowerRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int todayNormalReward;
/*    */   public int todayCCYReward;
/*    */   
/*    */   public TowerRewardResponse() {
/* 23 */     this.eventResponseId = 26802;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TowerRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 26802;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeInt(out, this.todayNormalReward);
/* 37 */     ProtocolUtil.writeInt(out, this.todayCCYReward);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.todayNormalReward = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.todayCCYReward = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerRewardResponse clone() throws CloneNotSupportedException {
/* 49 */     TowerRewardResponse clone = (TowerRewardResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.todayNormalReward = 0;
/* 57 */     this.todayCCYReward = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"todayNormalReward\":" + this.todayNormalReward + ",\"todayCCYReward\":" + this.todayCCYReward + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\tower\TowerRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */