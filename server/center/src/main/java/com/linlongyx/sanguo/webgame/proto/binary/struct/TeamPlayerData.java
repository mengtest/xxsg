/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String head;
/*    */   public String name;
/*    */   public short level;
/*    */   public long fightValue;
/*    */   public byte isLeader;
/*    */   public byte status;
/*    */   public byte isRobot;
/* 23 */   public ArrayList<TeamFighterData> fighterList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 30 */     ProtocolUtil.writeShort(out, this.level);
/* 31 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 32 */     ProtocolUtil.writeByte(out, this.isLeader);
/* 33 */     ProtocolUtil.writeByte(out, this.status);
/* 34 */     ProtocolUtil.writeByte(out, this.isRobot);
/*    */     
/* 36 */     int size_0 = this.fighterList.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       TeamFighterData tmp_0 = this.fighterList.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 48 */     this.head = ProtocolUtil.readUTFStr(in);
/* 49 */     this.name = ProtocolUtil.readUTFStr(in);
/* 50 */     this.level = ProtocolUtil.readUTFBinShort(in);
/* 51 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.isLeader = ProtocolUtil.readUTFBinByte(in);
/* 53 */     this.status = ProtocolUtil.readUTFBinByte(in);
/* 54 */     this.isRobot = ProtocolUtil.readUTFBinByte(in);
/*    */     
/* 56 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       TeamFighterData tmp_0 = new TeamFighterData();
/* 60 */       tmp_0.unserial(in);
/* 61 */       this.fighterList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamPlayerData clone() throws CloneNotSupportedException {
/* 67 */     TeamPlayerData clone = (TeamPlayerData)super.clone();
/* 68 */     int size_0 = this.fighterList.size();
/* 69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 71 */       TeamFighterData tmp_0 = this.fighterList.get(index_0);
/* 72 */       clone.fighterList.add(tmp_0.clone());
/*    */     } 
/* 74 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 79 */     this.playerId = 0L;
/* 80 */     this.head = null;
/* 81 */     this.name = null;
/* 82 */     this.level = 0;
/* 83 */     this.fightValue = 0L;
/* 84 */     this.isLeader = 0;
/* 85 */     this.status = 0;
/* 86 */     this.isRobot = 0;
/* 87 */     this.fighterList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"playerId\":" + this.playerId + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"level\":" + this.level + ",\"fightValue\":" + this.fightValue + ",\"isLeader\":" + this.isLeader + ",\"status\":" + this.status + ",\"isRobot\":" + this.isRobot + ",\"fighterList\":" + this.fighterList.toString() + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */