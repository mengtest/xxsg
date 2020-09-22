/*    */ package com.linlongyx.sanguo.webgame.proto.binary.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class TowerOwnerInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int towerLevel;
/* 21 */   public ArrayList<RankingData> towerOwnerList = new ArrayList<>();
/*    */   
/*    */   public TowerOwnerInfoResponse() {
/* 24 */     this.eventResponseId = 26701;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.towerLevel);
/*    */     
/* 32 */     int size_0 = this.towerOwnerList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       RankingData tmp_0 = this.towerOwnerList.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.towerLevel = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       RankingData tmp_0 = new RankingData();
/* 49 */       tmp_0.unserial(in);
/* 50 */       this.towerOwnerList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerOwnerInfoResponse clone() throws CloneNotSupportedException {
/* 56 */     TowerOwnerInfoResponse clone = (TowerOwnerInfoResponse)super.clone();
/* 57 */     int size_0 = this.towerOwnerList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       RankingData tmp_0 = this.towerOwnerList.get(index_0);
/* 61 */       clone.towerOwnerList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.towerLevel = 0;
/* 69 */     this.towerOwnerList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"towerLevel\":" + this.towerLevel + ",\"towerOwnerList\":" + this.towerOwnerList.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\towerOwner\TowerOwnerInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */