/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamFighterData;
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
/*    */ public class CrossTeamFighterChangeNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/* 21 */   public ArrayList<TeamFighterData> fighters = new ArrayList<>();
/*    */   
/*    */   public CrossTeamFighterChangeNoticeResponse() {
/* 24 */     this.eventResponseId = 21504;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */     
/* 32 */     int size_0 = this.fighters.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       TeamFighterData tmp_0 = new TeamFighterData();
/* 49 */       tmp_0.unserial(in);
/* 50 */       this.fighters.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossTeamFighterChangeNoticeResponse clone() throws CloneNotSupportedException {
/* 56 */     CrossTeamFighterChangeNoticeResponse clone = (CrossTeamFighterChangeNoticeResponse)super.clone();
/* 57 */     int size_0 = this.fighters.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 61 */       clone.fighters.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.playerId = 0L;
/* 69 */     this.fighters.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"playerId\":" + this.playerId + ",\"fighters\":" + this.fighters.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossTeam\CrossTeamFighterChangeNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */