/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int serverId;
/*    */   private long playerId;
/*    */   private long userId;
/*    */   private String playerName;
/*    */   private short level;
/*    */   public int vip;
/*    */   public int titleId;
/*    */   public int sex;
/*    */   public String head;
/*    */   
/*    */   public static long getSerialVersionUID() {
/* 23 */     return 1L;
/*    */   }
/*    */   
/*    */   public int getServerId() {
/* 27 */     return this.serverId;
/*    */   }
/*    */   
/*    */   public void setServerId(int serverId) {
/* 31 */     this.serverId = serverId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 35 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 39 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getUserId() {
/* 43 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(long userId) {
/* 47 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getPlayerName() {
/* 51 */     return this.playerName;
/*    */   }
/*    */   
/*    */   public void setPlayerName(String playerName) {
/* 55 */     this.playerName = playerName;
/*    */   }
/*    */   
/*    */   public short getLevel() {
/* 59 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(short level) {
/* 63 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getVip() {
/* 67 */     return this.vip;
/*    */   }
/*    */   
/*    */   public void setVip(int vip) {
/* 71 */     this.vip = vip;
/*    */   }
/*    */   
/*    */   public int getTitleId() {
/* 75 */     return this.titleId;
/*    */   }
/*    */   
/*    */   public void setTitleId(int titleId) {
/* 79 */     this.titleId = titleId;
/*    */   }
/*    */   
/*    */   public int getSex() {
/* 83 */     return this.sex;
/*    */   }
/*    */   
/*    */   public void setSex(int sex) {
/* 87 */     this.sex = sex;
/*    */   }
/*    */   
/*    */   public String getHead() {
/* 91 */     return this.head;
/*    */   }
/*    */   
/*    */   public void setHead(String head) {
/* 95 */     this.head = head;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\ChatPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */