/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RacePlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PlayerData playerData;
/*    */   private int racePoint;
/*    */   private int updateTime;
/*    */   
/*    */   public RacePlayerData() {}
/*    */   
/*    */   public RacePlayerData(PlayerData playerData) {
/* 19 */     this.playerData = playerData;
/* 20 */     this.updateTime = TimeUtil.currentTime();
/*    */   }
/*    */   
/*    */   public PlayerData getPlayerData() {
/* 24 */     return this.playerData;
/*    */   }
/*    */   
/*    */   public void setPlayerData(PlayerData playerData) {
/* 28 */     this.playerData = playerData;
/*    */   }
/*    */   
/*    */   public int getRacePoint() {
/* 32 */     return this.racePoint;
/*    */   }
/*    */   
/*    */   public void setRacePoint(int racePoint) {
/* 36 */     this.racePoint = racePoint;
/*    */   }
/*    */   
/*    */   public int getUpdateTime() {
/* 40 */     return this.updateTime;
/*    */   }
/*    */   
/*    */   public void setUpdateTime(int updateTime) {
/* 44 */     this.updateTime = updateTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\RacePlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */