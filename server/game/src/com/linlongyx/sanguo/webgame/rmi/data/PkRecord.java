/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FightGroupData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HpData;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PkRecord
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte type;
/*    */   public String pkId;
/*    */   public long winner;
/*    */   public byte result;
/* 19 */   public ArrayList<FightGroupData> lGroups = new ArrayList<>();
/* 20 */   public ArrayList<FightGroupData> rGroups = new ArrayList<>();
/* 21 */   public ArrayList<BoutPlayData> boutPlayData = new ArrayList<>();
/* 22 */   public ArrayList<HpData> fightersHp = new ArrayList<>();
/*    */   
/*    */   public String debugStr;
/*    */   public long totalHurt;
/*    */   
/*    */   public String toString() {
/* 28 */     return "PkRecord{type=" + this.type + ", pkId='" + this.pkId + '\'' + ", winner=" + this.winner + ", result=" + this.result + ", lGroups=" + this.lGroups + ", rGroups=" + this.rGroups + ", boutPlayData=" + this.boutPlayData + ", fightersHp=" + this.fightersHp + ", debugStr='" + this.debugStr + '\'' + ", totalHurt=" + this.totalHurt + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\PkRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */