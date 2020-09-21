/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
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
/*    */ public class TeamFighterChangeNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/* 21 */   public ArrayList<TeamFighterData> fighters = new ArrayList<>();
/*    */   
/*    */   public TeamFighterChangeNoticeResponse() {
/* 24 */     this.eventResponseId = 23709;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TeamFighterChangeNoticeResponse(short retCode) {
/* 29 */     this.eventResponseId = 23709;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */     
/* 38 */     int size_0 = this.fighters.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       TeamFighterData tmp_0 = new TeamFighterData();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.fighters.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamFighterChangeNoticeResponse clone() throws CloneNotSupportedException {
/* 62 */     TeamFighterChangeNoticeResponse clone = (TeamFighterChangeNoticeResponse)super.clone();
/* 63 */     int size_0 = this.fighters.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 67 */       clone.fighters.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.playerId = 0L;
/* 75 */     this.fighters.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"playerId\":" + this.playerId + ",\"fighters\":" + this.fighters.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamFighterChangeNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */