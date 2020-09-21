/*     */ package com.linlongyx.sanguo.webgame.app.cat;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FortuneCatComponent
/*     */   extends AbstractMapComponent<FortuneCatEntity>
/*     */ {
/*  21 */   private ArrayList<Reward> rewards = new ArrayList<>();
/*     */   
/*     */   public FortuneCatComponent(long playerId) {
/*  24 */     super(FortuneCatEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  29 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  34 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  39 */     if (time == 0) {
/*  40 */       resetExpired();
/*     */     }
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetExpired() {
/*  49 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/*  50 */     List<Integer> list = fortuneCatParameter.getActId(false);
/*  51 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  52 */       FortuneCatEntity fortuneCatEntity = getEntity(id);
/*  53 */       resetAll(fortuneCatEntity); }
/*     */   
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getRewards() {
/*  58 */     return this.rewards;
/*     */   }
/*     */   
/*     */   public void setRewards(ArrayList<Reward> rewards) {
/*  62 */     this.rewards.addAll(rewards);
/*     */   }
/*     */   
/*     */   public void resetRewards() {
/*  66 */     this.rewards.clear();
/*     */   }
/*     */   
/*     */   private void resetAll(FortuneCatEntity fortuneCatEntity) {
/*  70 */     if (null == fortuneCatEntity) {
/*     */       return;
/*     */     }
/*  73 */     if (fortuneCatEntity.getActCharge() > 0L || fortuneCatEntity.getCount() > 0) {
/*  74 */       LogUtil.errorLog(new Object[] { "fortuneCatEntityResetAll", Long.valueOf(this.playerId), Integer.valueOf(fortuneCatEntity.getId()), Long.valueOf(fortuneCatEntity.getActCharge()), Integer.valueOf(fortuneCatEntity.getCount()), Integer.valueOf(fortuneCatEntity.getMinCCY()), Integer.valueOf(fortuneCatEntity.getMaxCCY()) });
/*     */     }
/*  76 */     fortuneCatEntity.setCount(0);
/*  77 */     fortuneCatEntity.setMinCCY(0);
/*  78 */     fortuneCatEntity.setMaxCCY(0);
/*  79 */     fortuneCatEntity.setActCharge(0L);
/*  80 */     updateActChargeDB(fortuneCatEntity.getId());
/*  81 */     updateCountDB(fortuneCatEntity.getId());
/*  82 */     updateMaxAndMinCCYDB(fortuneCatEntity.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCountDB(int id) {
/*  87 */     getProxy().setUpdate(String.valueOf(id), "count");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/*  91 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public void updateActChargeDB(int id) {
/*  95 */     getProxy().setUpdate(String.valueOf(id), "actCharge");
/*     */   }
/*     */   
/*     */   public void updateMaxAndMinCCYDB(int id) {
/*  99 */     getProxy().setUpdate(String.valueOf(id), "maxCCY");
/* 100 */     getProxy().setUpdate(String.valueOf(id), "minCCY");
/*     */   }
/*     */   
/*     */   public FortuneCatEntity getEntity(int id) {
/* 104 */     FortuneCatEntity fortuneCatEntity = (FortuneCatEntity)getEntity(String.valueOf(id));
/* 105 */     if (null == fortuneCatEntity) {
/* 106 */       fortuneCatEntity = new FortuneCatEntity(this.playerId, id);
/* 107 */       addEntity((IEntity)fortuneCatEntity);
/* 108 */       saveToDB();
/*     */     } 
/* 110 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/* 111 */     FestivalTime festivalTime = fortuneCatParameter.getFestivalTime(id);
/* 112 */     if (null != festivalTime) {
/* 113 */       boolean nowOpen = fortuneCatParameter.isActOpen(id);
/* 114 */       if (nowOpen && !fortuneCatEntity.isOpen()) {
/* 115 */         resetAll(fortuneCatEntity);
/* 116 */         fortuneCatEntity.setOpen(true);
/* 117 */         addTodayRecharge(fortuneCatEntity);
/* 118 */         updateOpenDB(id);
/* 119 */       } else if (!nowOpen && fortuneCatEntity.isOpen()) {
/* 120 */         resetAll(fortuneCatEntity);
/* 121 */         fortuneCatEntity.setOpen(false);
/* 122 */         updateOpenDB(id);
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     return fortuneCatEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTodayRecharge(FortuneCatEntity fortuneCatEntity) {
/* 135 */     IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/* 136 */     if (null == iPlayer) {
/*     */       return;
/*     */     }
/* 139 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 140 */     if (extendComponent.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/*     */       return;
/*     */     }
/* 143 */     long todayRecharge = extendComponent.getTodayRecharge();
/* 144 */     fortuneCatEntity.setActCharge(fortuneCatEntity.getActCharge() + todayRecharge);
/* 145 */     updateActChargeDB(fortuneCatEntity.getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\cat\FortuneCatComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */