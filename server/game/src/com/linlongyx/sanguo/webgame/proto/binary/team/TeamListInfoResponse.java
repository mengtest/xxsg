/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamData;
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
/*    */ public class TeamListInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<TeamData> teamList = new ArrayList<>();
/*    */   public int restTimes;
/*    */   
/*    */   public TeamListInfoResponse() {
/* 24 */     this.eventResponseId = 23701;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TeamListInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 23701;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.teamList.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       TeamData tmp_0 = this.teamList.get(index_0);
/* 42 */       tmp_0.serial(out);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.restTimes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       TeamData tmp_0 = new TeamData();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.teamList.add(tmp_0);
/*    */     } 
/* 57 */     this.restTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamListInfoResponse clone() throws CloneNotSupportedException {
/* 62 */     TeamListInfoResponse clone = (TeamListInfoResponse)super.clone();
/* 63 */     int size_0 = this.teamList.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       TeamData tmp_0 = this.teamList.get(index_0);
/* 67 */       clone.teamList.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.teamList.clear();
/* 75 */     this.restTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"teamList\":" + this.teamList.toString() + ",\"restTimes\":" + this.restTimes + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamListInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */