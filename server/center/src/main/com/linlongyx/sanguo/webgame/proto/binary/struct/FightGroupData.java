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
/*    */ public class FightGroupData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public String name;
/*    */   public int firstHandVal;
/* 18 */   public ArrayList<FighterData> fighters = new ArrayList<>();
/* 19 */   public ArrayList<FighterData> candidateFighters = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 24 */     ProtocolUtil.writeInt(out, this.firstHandVal);
/*    */     
/* 26 */     int size_0 = this.fighters.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       FighterData tmp_0 = this.fighters.get(index_0);
/* 31 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 34 */     int size_1 = this.candidateFighters.size();
/* 35 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 36 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 38 */       FighterData tmp_1 = this.candidateFighters.get(index_1);
/* 39 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.name = ProtocolUtil.readUTFStr(in);
/* 46 */     this.firstHandVal = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       FighterData tmp_0 = new FighterData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.fighters.add(tmp_0);
/*    */     } 
/*    */     
/* 56 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 59 */       FighterData tmp_1 = new FighterData();
/* 60 */       tmp_1.unserial(in);
/* 61 */       this.candidateFighters.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FightGroupData clone() throws CloneNotSupportedException {
/* 67 */     FightGroupData clone = (FightGroupData)super.clone();
/* 68 */     int size_0 = this.fighters.size();
/* 69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 71 */       FighterData tmp_0 = this.fighters.get(index_0);
/* 72 */       clone.fighters.add(tmp_0.clone());
/*    */     } 
/* 74 */     int size_1 = this.candidateFighters.size();
/* 75 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 77 */       FighterData tmp_1 = this.candidateFighters.get(index_1);
/* 78 */       clone.candidateFighters.add(tmp_1.clone());
/*    */     } 
/* 80 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 85 */     this.name = null;
/* 86 */     this.firstHandVal = 0;
/* 87 */     this.fighters.clear();
/* 88 */     this.candidateFighters.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "{\"name\":" + StringUtil.str2Str(this.name) + ",\"firstHandVal\":" + this.firstHandVal + ",\"fighters\":" + this.fighters.toString() + ",\"candidateFighters\":" + this.candidateFighters.toString() + "}";
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FightGroupData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */