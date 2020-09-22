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
/*    */ public class TeamPlayerFighterInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<TeamFighterData> fighters = new ArrayList<>();
/*    */   
/*    */   public TeamPlayerFighterInfoResponse() {
/* 23 */     this.eventResponseId = 23708;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TeamPlayerFighterInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 23708;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.fighters.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       TeamFighterData tmp_0 = new TeamFighterData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.fighters.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamPlayerFighterInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     TeamPlayerFighterInfoResponse clone = (TeamPlayerFighterInfoResponse)super.clone();
/* 60 */     int size_0 = this.fighters.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       TeamFighterData tmp_0 = this.fighters.get(index_0);
/* 64 */       clone.fighters.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.fighters.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"fighters\":" + this.fighters.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamPlayerFighterInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */