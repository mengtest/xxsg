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
/*    */ public class TowerInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int curLayers;
/*    */   public int todayNormalReward;
/*    */   public int todayCCYReward;
/*    */   public int preLayers;
/*    */   
/*    */   public TowerInfoResponse() {
/* 24 */     this.eventResponseId = 26801;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TowerInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 26801;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.curLayers);
/* 37 */     ProtocolUtil.writeInt(out, this.todayNormalReward);
/* 38 */     ProtocolUtil.writeInt(out, this.todayCCYReward);
/* 39 */     ProtocolUtil.writeInt(out, this.preLayers);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.curLayers = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.todayNormalReward = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.todayCCYReward = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.preLayers = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerInfoResponse clone() throws CloneNotSupportedException {
/* 52 */     TowerInfoResponse clone = (TowerInfoResponse)super.clone();
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.curLayers = 0;
/* 59 */     this.todayNormalReward = 0;
/* 60 */     this.todayCCYReward = 0;
/* 61 */     this.preLayers = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"curLayers\":" + this.curLayers + ",\"todayNormalReward\":" + this.todayNormalReward + ",\"todayCCYReward\":" + this.todayCCYReward + ",\"preLayers\":" + this.preLayers + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\tower\TowerInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */