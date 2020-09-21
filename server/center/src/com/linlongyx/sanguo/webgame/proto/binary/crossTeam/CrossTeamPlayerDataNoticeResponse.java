/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamPlayerData;
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
/*    */ public class CrossTeamPlayerDataNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<TeamPlayerData> players = new ArrayList<>();
/*    */   
/*    */   public CrossTeamPlayerDataNoticeResponse() {
/* 23 */     this.eventResponseId = 21506;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.players.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       TeamPlayerData tmp_0 = this.players.get(index_0);
/* 35 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       TeamPlayerData tmp_0 = new TeamPlayerData();
/* 46 */       tmp_0.unserial(in);
/* 47 */       this.players.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossTeamPlayerDataNoticeResponse clone() throws CloneNotSupportedException {
/* 53 */     CrossTeamPlayerDataNoticeResponse clone = (CrossTeamPlayerDataNoticeResponse)super.clone();
/* 54 */     int size_0 = this.players.size();
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       TeamPlayerData tmp_0 = this.players.get(index_0);
/* 58 */       clone.players.add(tmp_0.clone());
/*    */     } 
/* 60 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.players.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"players\":" + this.players.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossTeam\CrossTeamPlayerDataNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */