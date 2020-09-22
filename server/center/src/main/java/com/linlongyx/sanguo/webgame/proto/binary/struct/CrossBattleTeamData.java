/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleTeamData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long teamId;
/*    */   public long playerId;
/*    */   public int camp;
/*    */   public int state;
/*    */   public int time;
/*    */   public int x;
/*    */   public int y;
/* 22 */   public ArrayList<CrossBattlePlayerData> players = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 26 */     ProtocolUtil.writeLong(out, this.teamId);
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/* 28 */     ProtocolUtil.writeInt(out, this.camp);
/* 29 */     ProtocolUtil.writeInt(out, this.state);
/* 30 */     ProtocolUtil.writeInt(out, this.time);
/* 31 */     ProtocolUtil.writeInt(out, this.x);
/* 32 */     ProtocolUtil.writeInt(out, this.y);
/*    */     
/* 34 */     int size_0 = this.players.size();
/* 35 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 38 */       CrossBattlePlayerData tmp_0 = this.players.get(index_0);
/* 39 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 47 */     this.camp = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.x = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.y = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       CrossBattlePlayerData tmp_0 = new CrossBattlePlayerData();
/* 57 */       tmp_0.unserial(in);
/* 58 */       this.players.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleTeamData clone() throws CloneNotSupportedException {
/* 64 */     CrossBattleTeamData clone = (CrossBattleTeamData)super.clone();
/* 65 */     int size_0 = this.players.size();
/* 66 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 68 */       CrossBattlePlayerData tmp_0 = this.players.get(index_0);
/* 69 */       clone.players.add(tmp_0.clone());
/*    */     } 
/* 71 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 76 */     this.teamId = 0L;
/* 77 */     this.playerId = 0L;
/* 78 */     this.camp = 0;
/* 79 */     this.state = 0;
/* 80 */     this.time = 0;
/* 81 */     this.x = 0;
/* 82 */     this.y = 0;
/* 83 */     this.players.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"teamId\":" + this.teamId + ",\"playerId\":" + this.playerId + ",\"camp\":" + this.camp + ",\"state\":" + this.state + ",\"time\":" + this.time + ",\"x\":" + this.x + ",\"y\":" + this.y + ",\"players\":" + this.players.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CrossBattleTeamData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */