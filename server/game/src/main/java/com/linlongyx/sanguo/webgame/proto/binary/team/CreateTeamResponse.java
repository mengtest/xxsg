/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
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
/*    */ public class CreateTeamResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long teamId;
/* 21 */   public ArrayList<TeamPlayerData> players = new ArrayList<>();
/*    */   
/*    */   public CreateTeamResponse() {
/* 24 */     this.eventResponseId = 23702;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CreateTeamResponse(short retCode) {
/* 29 */     this.eventResponseId = 23702;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeLong(out, this.teamId);
/*    */     
/* 38 */     int size_0 = this.players.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       TeamPlayerData tmp_0 = this.players.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       TeamPlayerData tmp_0 = new TeamPlayerData();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.players.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CreateTeamResponse clone() throws CloneNotSupportedException {
/* 62 */     CreateTeamResponse clone = (CreateTeamResponse)super.clone();
/* 63 */     int size_0 = this.players.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       TeamPlayerData tmp_0 = this.players.get(index_0);
/* 67 */       clone.players.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.teamId = 0L;
/* 75 */     this.players.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"teamId\":" + this.teamId + ",\"players\":" + this.players.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\CreateTeamResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */