/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattlePlayerData;
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
/*    */ public class DestinyBattleInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<BattlePlayerData> battles = new ArrayList<>();
/*    */   public int robTimes;
/*    */   public int refreshTimes;
/*    */   
/*    */   public DestinyBattleInfoResponse() {
/* 25 */     this.eventResponseId = 27401;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.battles.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       BattlePlayerData tmp_0 = this.battles.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/* 39 */     ProtocolUtil.writeInt(out, this.robTimes);
/* 40 */     ProtocolUtil.writeInt(out, this.refreshTimes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       BattlePlayerData tmp_0 = new BattlePlayerData();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.battles.add(tmp_0);
/*    */     } 
/* 53 */     this.robTimes = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.refreshTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBattleInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     DestinyBattleInfoResponse clone = (DestinyBattleInfoResponse)super.clone();
/* 60 */     int size_0 = this.battles.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       BattlePlayerData tmp_0 = this.battles.get(index_0);
/* 64 */       clone.battles.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.battles.clear();
/* 72 */     this.robTimes = 0;
/* 73 */     this.refreshTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"battles\":" + this.battles.toString() + ",\"robTimes\":" + this.robTimes + ",\"refreshTimes\":" + this.refreshTimes + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBattleInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */