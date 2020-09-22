/*    */ package com.linlongyx.sanguo.webgame.proto.binary.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
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
/*    */ public class TowerOwnerLayerStateResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public RankingData layerData = new RankingData();
/*    */   public int state;
/*    */   
/*    */   public TowerOwnerLayerStateResponse() {
/* 23 */     this.eventResponseId = 26706;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     this.layerData.serial(out);
/* 30 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.layerData = new RankingData();
/* 36 */     this.layerData.unserial(in);
/* 37 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerOwnerLayerStateResponse clone() throws CloneNotSupportedException {
/* 42 */     TowerOwnerLayerStateResponse clone = (TowerOwnerLayerStateResponse)super.clone();
/* 43 */     clone.layerData = this.layerData.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.layerData.reset();
/* 50 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"layerData\":" + this.layerData.toString() + ",\"state\":" + this.state + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\towerOwner\TowerOwnerLayerStateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */