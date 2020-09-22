/*     */ package com.linlongyx.sanguo.webgame.app.cardbook;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_cardbook", prefix = "cardbook_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class CardBookEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  19 */   private Map<Integer, Integer> cardColor = new HashMap<>();
/*  20 */   private Map<Integer, Integer> lastCardColor = new HashMap<>();
/*     */   private int bottomCardAsk;
/*     */   private int colorDanAsk;
/*     */   private int lastToColor;
/*     */   private int refToColor;
/*  25 */   private Map<Integer, Integer> cardPair = new HashMap<>();
/*  26 */   private Map<Integer, Integer> processMap = new HashMap<>();
/*     */   private int giveTimes;
/*     */   private int nextAskTime;
/*     */   private long infoId;
/*  30 */   private Map<Long, Integer> endTime = new HashMap<>();
/*  31 */   private Map<Long, Long> getGive = new HashMap<>();
/*  32 */   private Map<Long, Integer> itemAsk = new HashMap<>();
/*     */   private int colorTime;
/*     */   
/*     */   public CardBookEntity(long playerId) {
/*  36 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  40 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCardColor() {
/*  44 */     return this.cardColor;
/*     */   }
/*     */   
/*     */   public void setCardColor(Map<Integer, Integer> cardColor) {
/*  48 */     this.cardColor = cardColor;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLastCardColor() {
/*  52 */     return this.lastCardColor;
/*     */   }
/*     */   
/*     */   public void setLastCardColor(Map<Integer, Integer> lastCardColor) {
/*  56 */     this.lastCardColor = lastCardColor;
/*     */   }
/*     */   
/*     */   public int getBottomCardAsk() {
/*  60 */     return this.bottomCardAsk;
/*     */   }
/*     */   
/*     */   public void setBottomCardAsk(int bottomCardAsk) {
/*  64 */     this.bottomCardAsk = bottomCardAsk;
/*     */   }
/*     */   
/*     */   public int getColorDanAsk() {
/*  68 */     return this.colorDanAsk;
/*     */   }
/*     */   
/*     */   public void setColorDanAsk(int colorDanAsk) {
/*  72 */     this.colorDanAsk = colorDanAsk;
/*     */   }
/*     */   
/*     */   public int getLastToColor() {
/*  76 */     return this.lastToColor;
/*     */   }
/*     */   
/*     */   public void setLastToColor(int lastToColor) {
/*  80 */     this.lastToColor = lastToColor;
/*     */   }
/*     */   
/*     */   public int getRefToColor() {
/*  84 */     return this.refToColor;
/*     */   }
/*     */   
/*     */   public void setRefToColor(int refToColor) {
/*  88 */     this.refToColor = refToColor;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCardPair() {
/*  92 */     return this.cardPair;
/*     */   }
/*     */   
/*     */   public void setCardPair(Map<Integer, Integer> cardPair) {
/*  96 */     this.cardPair = cardPair;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getProcessMap() {
/* 100 */     return this.processMap;
/*     */   }
/*     */   
/*     */   public void setProcessMap(Map<Integer, Integer> processMap) {
/* 104 */     this.processMap = processMap;
/*     */   }
/*     */   
/*     */   public int getGiveTimes() {
/* 108 */     return this.giveTimes;
/*     */   }
/*     */   
/*     */   public void setGiveTimes(int giveTimes) {
/* 112 */     this.giveTimes = giveTimes;
/*     */   }
/*     */   
/*     */   public int getNextAskTime() {
/* 116 */     return this.nextAskTime;
/*     */   }
/*     */   
/*     */   public void setNextAskTime(int nextAskTime) {
/* 120 */     this.nextAskTime = nextAskTime;
/*     */   }
/*     */   
/*     */   public long getInfoId() {
/* 124 */     return this.infoId;
/*     */   }
/*     */   
/*     */   public void setInfoId(long infoId) {
/* 128 */     this.infoId = infoId;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getEndTime() {
/* 132 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Map<Long, Integer> endTime) {
/* 136 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getItemAsk() {
/* 140 */     return this.itemAsk;
/*     */   }
/*     */   
/*     */   public void setItemAsk(Map<Long, Integer> itemAsk) {
/* 144 */     this.itemAsk = itemAsk;
/*     */   }
/*     */   
/*     */   public Map<Long, Long> getGetGive() {
/* 148 */     return this.getGive;
/*     */   }
/*     */   
/*     */   public void setGetGive(Map<Long, Long> getGive) {
/* 152 */     this.getGive = getGive;
/*     */   }
/*     */   
/*     */   public int getColorTime() {
/* 156 */     return this.colorTime;
/*     */   }
/*     */   
/*     */   public void setColorTime(int colorTime) {
/* 160 */     this.colorTime = colorTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 165 */     return "CardBookEntity{cardColor=" + this.cardColor + "lastCardColor=" + this.lastCardColor + "bottomCardAsk=" + this.bottomCardAsk + "colorDanAsk=" + this.colorDanAsk + "lastToColor=" + this.lastToColor + "refToColor=" + this.refToColor + "cardPair=" + this.cardPair + "processMap=" + this.processMap + "giveTimes=" + this.giveTimes + "nextAskTime=" + this.nextAskTime + "infoId=" + this.infoId + "endTime=" + this.endTime + "getGive=" + this.getGive + "itemAsk=" + this.itemAsk + "colorTime=" + this.colorTime + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\cardbook\CardBookEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */