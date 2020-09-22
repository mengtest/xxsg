/*     */ package com.linlongyx.sanguo.webgame.app.cardbook;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CardBookComponent
/*     */   extends AbstractComponent<CardBookEntity>
/*     */ {
/*     */   public CardBookComponent(long playerId) {
/*  17 */     super(CardBookEntity.class);
/*  18 */     this.playerId = playerId;
/*  19 */     makeKey();
/*  20 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  25 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  30 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  35 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  40 */     if (time == 0) {
/*  41 */       setBottomCardAsk(0);
/*  42 */       setColorDanAsk(0);
/*  43 */       setGetGive(new HashMap<>());
/*  44 */       setGiveTimes(0);
/*  45 */       setEndTime(new HashMap<>());
/*  46 */       setItemAsk(new HashMap<>());
/*     */     } 
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCardColor() {
/*  52 */     return ((CardBookEntity)getEntity()).getCardColor();
/*     */   }
/*     */   
/*     */   public void setCardColor(Map<Integer, Integer> cardColor) {
/*  56 */     ((CardBookEntity)getEntity()).setCardColor(cardColor);
/*     */   }
/*     */   
/*     */   public int getBottomCardAsk() {
/*  60 */     return ((CardBookEntity)getEntity()).getBottomCardAsk();
/*     */   }
/*     */   
/*     */   public void setBottomCardAsk(int bottomCardAsk) {
/*  64 */     ((CardBookEntity)getEntity()).setBottomCardAsk(bottomCardAsk);
/*     */   }
/*     */   
/*     */   public int getColorDanAsk() {
/*  68 */     return ((CardBookEntity)getEntity()).getColorDanAsk();
/*     */   }
/*     */   
/*     */   public void setColorDanAsk(int colorDanAsk) {
/*  72 */     ((CardBookEntity)getEntity()).setColorDanAsk(colorDanAsk);
/*     */   }
/*     */   
/*     */   public int getLastToColor() {
/*  76 */     return ((CardBookEntity)getEntity()).getLastToColor();
/*     */   }
/*     */   
/*     */   public void setLastToColor(int lastToColor) {
/*  80 */     ((CardBookEntity)getEntity()).setLastToColor(lastToColor);
/*     */   }
/*     */   
/*     */   public int getRefToColor() {
/*  84 */     return ((CardBookEntity)getEntity()).getRefToColor();
/*     */   }
/*     */   
/*     */   public void setRefToColor(int refToColor) {
/*  88 */     ((CardBookEntity)getEntity()).setRefToColor(refToColor);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCardPair() {
/*  92 */     return ((CardBookEntity)getEntity()).getCardPair();
/*     */   }
/*     */   
/*     */   public void setCardPair(Map<Integer, Integer> cardPair) {
/*  96 */     ((CardBookEntity)getEntity()).setCardPair(cardPair);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getProcessMap() {
/* 100 */     return ((CardBookEntity)getEntity()).getProcessMap();
/*     */   }
/*     */   
/*     */   public void setProcessMap(Map<Integer, Integer> processMap) {
/* 104 */     ((CardBookEntity)getEntity()).setProcessMap(processMap);
/*     */   }
/*     */   
/*     */   public int getGiveTimes() {
/* 108 */     return ((CardBookEntity)getEntity()).getGiveTimes();
/*     */   }
/*     */   
/*     */   public void setGiveTimes(int giveTimes) {
/* 112 */     ((CardBookEntity)getEntity()).setGiveTimes(giveTimes);
/*     */   }
/*     */   
/*     */   public int getNextAskTime() {
/* 116 */     return ((CardBookEntity)getEntity()).getNextAskTime();
/*     */   }
/*     */   
/*     */   public void setNextAskTime(int nextAskTime) {
/* 120 */     ((CardBookEntity)getEntity()).setNextAskTime(nextAskTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Long, Long> getGetGive() {
/* 125 */     return ((CardBookEntity)getEntity()).getGetGive();
/*     */   }
/*     */   
/*     */   public void setGetGive(Map<Long, Long> getGive) {
/* 129 */     ((CardBookEntity)getEntity()).setGetGive(getGive);
/*     */   }
/*     */   
/*     */   public int getColorTime() {
/* 133 */     return ((CardBookEntity)getEntity()).getColorTime();
/*     */   }
/*     */   
/*     */   public void setColorTime(int colorTime) {
/* 137 */     ((CardBookEntity)getEntity()).setColorTime(colorTime);
/*     */   }
/*     */   
/*     */   public long getInfoId() {
/* 141 */     return ((CardBookEntity)getEntity()).getInfoId();
/*     */   }
/*     */   
/*     */   public void setInfoId(long infoId) {
/* 145 */     ((CardBookEntity)getEntity()).setInfoId(infoId);
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getEndTime() {
/* 149 */     return ((CardBookEntity)getEntity()).getEndTime();
/*     */   }
/*     */   
/*     */   public void setEndTime(Map<Long, Integer> endTime) {
/* 153 */     ((CardBookEntity)getEntity()).setEndTime(endTime);
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getItemAsk() {
/* 157 */     return ((CardBookEntity)getEntity()).getItemAsk();
/*     */   }
/*     */   
/*     */   public void setItemAsk(Map<Long, Integer> itemAsk) {
/* 161 */     ((CardBookEntity)getEntity()).setItemAsk(itemAsk);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLastCardColor() {
/* 165 */     return ((CardBookEntity)getEntity()).getLastCardColor();
/*     */   }
/*     */   
/*     */   public void setLastCardColor(Map<Integer, Integer> lastCardColor) {
/* 169 */     ((CardBookEntity)getEntity()).setLastCardColor(lastCardColor);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\cardbook\CardBookComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */