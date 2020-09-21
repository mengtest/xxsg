/*     */ package com.linlongyx.sanguo.webgame.app.draw;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ 
/*     */ public class DrawCardComponent
/*     */   extends AbstractMapComponent<DrawCardEntity>
/*     */ {
/*     */   public DrawCardComponent(long playerId) {
/*  19 */     super(DrawCardEntity.class, playerId);
/*     */   }
/*     */   
/*     */   public String getType() {
/*  23 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  28 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  33 */     if (time == 0);
/*     */     
/*  35 */     return true;
/*     */   }
/*     */   
/*     */   public void updateRoundDB(int id) {
/*  39 */     getProxy().setUpdate(String.valueOf(id), "round");
/*     */   }
/*     */   
/*     */   public void updateFreeCountDB(int id) {
/*  43 */     getProxy().setUpdate(String.valueOf(id), "freeCount");
/*     */   }
/*     */   
/*     */   public void updateTotalPlayDB(int id) {
/*  47 */     getProxy().setUpdate(String.valueOf(id), "totalPlay");
/*     */   }
/*     */   
/*     */   public void updateOpenCardsDB(int id) {
/*  51 */     getProxy().setUpdate(String.valueOf(id), "openCards");
/*     */   }
/*     */   
/*     */   public void updateCountsDB(int id) {
/*  55 */     getProxy().setUpdate(String.valueOf(id), "counts");
/*     */   }
/*     */   
/*     */   public void updateTimesDB(int id) {
/*  59 */     getProxy().setUpdate(String.valueOf(id), "times");
/*     */   }
/*     */   
/*     */   public void updateAwardsDB(int id) {
/*  63 */     getProxy().setUpdate(String.valueOf(id), "awards");
/*     */   }
/*     */   
/*     */   public void updateRefreshCountDB(int id) {
/*  67 */     getProxy().setUpdate(String.valueOf(id), "refreshCount");
/*     */   }
/*     */   
/*     */   public void updateNumSetDB(int id) {
/*  71 */     getProxy().setUpdate(String.valueOf(id), "numSet");
/*     */   }
/*     */   
/*     */   public void updateActTimeDB(int id) {
/*  75 */     getProxy().setUpdate(String.valueOf(id), "actTime");
/*     */   }
/*     */   
/*     */   public DrawCardEntity getEntity(int id) {
/*  79 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  80 */     FestivalTime festivalTime = drawCardParameter.getActTime(id);
/*  81 */     DrawCardEntity drawCardEntity = (DrawCardEntity)getEntity(String.valueOf(id));
/*  82 */     if (null == drawCardEntity) {
/*  83 */       drawCardEntity = new DrawCardEntity(this.playerId, id);
/*  84 */       drawCardEntity.setRound(0);
/*  85 */       drawCardEntity.setFreeTime(TimeUtil.currentTime());
/*  86 */       if (null != festivalTime) {
/*  87 */         drawCardEntity.setActTime(festivalTime.startTime);
/*     */       }
/*  89 */       addEntity((IEntity)drawCardEntity);
/*  90 */       saveToDB();
/*     */     } 
/*  92 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(this.playerId);
/*  93 */     if (null != festivalTime && drawCardEntity.getActTime() < festivalTime.startTime) {
/*  94 */       drawCardEntity.setRound(0);
/*  95 */       drawCardEntity.setTotalPlay(0);
/*  96 */       drawCardEntity.setRefreshCount(0);
/*  97 */       drawCardEntity.setOpenCards(new HashMap<>());
/*  98 */       drawCardEntity.setCounts(new HashSet<>());
/*  99 */       drawCardEntity.setTimes(new HashMap<>());
/* 100 */       drawCardEntity.setAwards(new HashMap<>());
/* 101 */       drawCardEntity.setNumSet(new HashSet<>());
/*     */       
/* 103 */       drawCardEntity.setActTime(festivalTime.startTime);
/* 104 */       updateRoundDB(id);
/* 105 */       updateTotalPlayDB(id);
/* 106 */       updateRefreshCountDB(id);
/* 107 */       updateOpenCardsDB(id);
/* 108 */       updateCountsDB(id);
/* 109 */       updateTimesDB(id);
/* 110 */       updateAwardsDB(id);
/* 111 */       updateNumSetDB(id);
/* 112 */       updateActTimeDB(id);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return drawCardEntity;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\draw\DrawCardComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */