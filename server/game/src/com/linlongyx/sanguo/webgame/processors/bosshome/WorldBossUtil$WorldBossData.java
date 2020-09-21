/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldBossData
/*    */ {
/*    */   public int timeKey;
/*    */   public int insId;
/*    */   public Map<Byte, Long> curHp;
/*    */   public long maxHp;
/*    */   public byte status;
/*    */   public int killNum;
/*    */   public int nextTime;
/*    */   public int openTime;
/*    */   
/*    */   public String toString() {
/* 85 */     return "WorldBossData{timeKey=" + this.timeKey + ", insId=" + this.insId + ", curHp=" + this.curHp + ", maxHp=" + this.maxHp + ", status=" + this.status + ", killNum=" + this.killNum + ", nextTime=" + this.nextTime + ", openTime=" + this.openTime + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldBossUtil$WorldBossData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */