/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyGroupFightData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyGroupPlayerData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class DestinyMatchGroupInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<DestinyGroupPlayerData> playerList = new ArrayList<>();
/* 21 */   public ArrayList<DestinyGroupFightData> fightRecordList = new ArrayList<>();
/*    */   
/*    */   public DestinyMatchGroupInfoResponse() {
/* 24 */     this.eventResponseId = 27409;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.playerList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       DestinyGroupPlayerData tmp_0 = this.playerList.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 39 */     int size_1 = this.fightRecordList.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 41 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 43 */       DestinyGroupFightData tmp_1 = this.fightRecordList.get(index_1);
/* 44 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       DestinyGroupPlayerData tmp_0 = new DestinyGroupPlayerData();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.playerList.add(tmp_0);
/*    */     } 
/*    */     
/* 59 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 60 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 62 */       DestinyGroupFightData tmp_1 = new DestinyGroupFightData();
/* 63 */       tmp_1.unserial(in);
/* 64 */       this.fightRecordList.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyMatchGroupInfoResponse clone() throws CloneNotSupportedException {
/* 70 */     DestinyMatchGroupInfoResponse clone = (DestinyMatchGroupInfoResponse)super.clone();
/* 71 */     int size_0 = this.playerList.size();
/* 72 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 74 */       DestinyGroupPlayerData tmp_0 = this.playerList.get(index_0);
/* 75 */       clone.playerList.add(tmp_0.clone());
/*    */     } 
/* 77 */     int size_1 = this.fightRecordList.size();
/* 78 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 80 */       DestinyGroupFightData tmp_1 = this.fightRecordList.get(index_1);
/* 81 */       clone.fightRecordList.add(tmp_1.clone());
/*    */     } 
/* 83 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 88 */     this.playerList.clear();
/* 89 */     this.fightRecordList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "{\"playerList\":" + this.playerList.toString() + ",\"fightRecordList\":" + this.fightRecordList.toString() + "}";
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyMatchGroupInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */