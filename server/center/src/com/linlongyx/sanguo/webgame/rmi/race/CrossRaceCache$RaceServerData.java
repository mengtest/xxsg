/*    */ package com.linlongyx.sanguo.webgame.rmi.race;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class RaceServerData
/*    */ {
/*    */   private int serverId;
/*    */   private int zoneId;
/*    */   private int worldLevel;
/*    */   
/*    */   public RaceServerData() {}
/*    */   
/*    */   public RaceServerData(int serverId, int worldLevel) {
/* 42 */     this(serverId, 0, worldLevel);
/*    */   }
/*    */   
/*    */   public RaceServerData(int serverId, int zoneId, int worldLevel) {
/* 46 */     this.serverId = serverId;
/* 47 */     this.zoneId = zoneId;
/* 48 */     this.worldLevel = worldLevel;
/*    */   }
/*    */   
/*    */   public int getServerId() {
/* 52 */     return this.serverId;
/*    */   }
/*    */   
/*    */   public void setServerId(int serverId) {
/* 56 */     this.serverId = serverId;
/*    */   }
/*    */   
/*    */   public int getZoneId() {
/* 60 */     return this.zoneId;
/*    */   }
/*    */   
/*    */   public void setZoneId(int zoneId) {
/* 64 */     this.zoneId = zoneId;
/*    */   }
/*    */   
/*    */   public int getWorldLevel() {
/* 68 */     return this.worldLevel;
/*    */   }
/*    */   
/*    */   public void setWorldLevel(int worldLevel) {
/* 72 */     this.worldLevel = worldLevel;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\race\CrossRaceCache$RaceServerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */