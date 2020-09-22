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
/*    */ 
/*    */ public class DestinyPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int destinyPoint;
/*    */   public long fightValue;
/*    */   public int quality;
/* 21 */   public ArrayList<Integer> fighterList = new ArrayList<>();
/* 22 */   public ModelData modelData = new ModelData();
/*    */   
/*    */   public byte win;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 29 */     ProtocolUtil.writeInt(out, this.destinyPoint);
/* 30 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 31 */     ProtocolUtil.writeInt(out, this.quality);
/*    */     
/* 33 */     int size_0 = this.fighterList.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       int tmp_0 = ((Integer)this.fighterList.get(index_0)).intValue();
/* 38 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 40 */     this.modelData.serial(out);
/* 41 */     ProtocolUtil.writeByte(out, this.win);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 47 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 48 */     this.destinyPoint = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 56 */       this.fighterList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 58 */     this.modelData = new ModelData();
/* 59 */     this.modelData.unserial(in);
/* 60 */     this.win = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyPlayerData clone() throws CloneNotSupportedException {
/* 65 */     DestinyPlayerData clone = (DestinyPlayerData)super.clone();
/* 66 */     int size_0 = this.fighterList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       int tmp_0 = ((Integer)this.fighterList.get(index_0)).intValue();
/* 70 */       clone.fighterList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 72 */     clone.modelData = this.modelData.clone();
/* 73 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 78 */     this.playerId = 0L;
/* 79 */     this.playerName = null;
/* 80 */     this.destinyPoint = 0;
/* 81 */     this.fightValue = 0L;
/* 82 */     this.quality = 0;
/* 83 */     this.fighterList.clear();
/* 84 */     this.modelData.reset();
/* 85 */     this.win = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"destinyPoint\":" + this.destinyPoint + ",\"fightValue\":" + this.fightValue + ",\"quality\":" + this.quality + ",\"fighterList\":" + this.fighterList.toString() + ",\"modelData\":" + this.modelData.toString() + ",\"win\":" + this.win + "}";
/* 91 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DestinyPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */