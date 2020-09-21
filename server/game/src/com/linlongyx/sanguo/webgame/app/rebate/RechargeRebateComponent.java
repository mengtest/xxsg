/*     */ package com.linlongyx.sanguo.webgame.app.rebate;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RechargeRebateComponent
/*     */   extends AbstractMapComponent<RechargeRebateEntity>
/*     */ {
/*  22 */   public ArrayList<Reward> reward = new ArrayList<>();
/*  23 */   public LinkedList<KeyValue> records = new LinkedList<>();
/*     */   
/*  25 */   public static int MAX_PERSONAL_RECORDS = 0;
/*     */   
/*     */   public RechargeRebateComponent(long playerId) {
/*  28 */     super(RechargeRebateEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  33 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  38 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<KeyValue> getRecords() {
/*  43 */     return new ArrayList<>(this.records);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRecords(KeyValue record) {
/*  51 */     if (MAX_PERSONAL_RECORDS == 0) {
/*  52 */       ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/*  53 */       MAX_PERSONAL_RECORDS = chargeRebateParameter.getServiceRecord();
/*     */     } 
/*  55 */     this.records.addLast(record);
/*  56 */     int extra = this.records.size() - MAX_PERSONAL_RECORDS;
/*  57 */     while (extra > 0) {
/*  58 */       this.records.removeFirst();
/*  59 */       extra--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getReward() {
/*  64 */     return this.reward;
/*     */   }
/*     */   
/*     */   public void setReward(ArrayList<Reward> reward) {
/*  68 */     this.reward = reward;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  73 */     if (time == 0) {
/*  74 */       resetActivity();
/*     */     }
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public RechargeRebateEntity getEntity(int id) {
/*  81 */     RechargeRebateEntity rechargeRebateEntity = (RechargeRebateEntity)getEntity(String.valueOf(id));
/*  82 */     if (null == rechargeRebateEntity) {
/*  83 */       rechargeRebateEntity = new RechargeRebateEntity(this.playerId, id);
/*  84 */       addEntity((IEntity)rechargeRebateEntity);
/*  85 */       saveToDB();
/*     */     } 
/*  87 */     return rechargeRebateEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetActivity() {
/*  95 */     ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/*  96 */     List<Integer> list = chargeRebateParameter.getActId(false);
/*  97 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/*  98 */       RechargeRebateEntity rechargeRebateEntity = (RechargeRebateEntity)getEntity(String.valueOf(actId));
/*  99 */       if (null != rechargeRebateEntity) {
/* 100 */         if (rechargeRebateEntity.getCharge() > 0L) {
/* 101 */           LogUtil.errorLog(new Object[] { "rechargeRebateEntity", Long.valueOf(this.playerId), Integer.valueOf(rechargeRebateEntity.getId()), Long.valueOf(rechargeRebateEntity.getCharge()), Long.valueOf(rechargeRebateEntity.getRefChare()), rechargeRebateEntity.getReward().toString(), Integer.valueOf(rechargeRebateEntity.getScore()) });
/*     */         }
/* 103 */         rechargeRebateEntity.setRefChare(0L);
/* 104 */         rechargeRebateEntity.setCharge(0L);
/* 105 */         rechargeRebateEntity.setScore(0);
/* 106 */         rechargeRebateEntity.setReward(new HashMap<>());
/* 107 */         rechargeRebateEntity.setTimes(0);
/* 108 */         updateRefChareDB(actId);
/* 109 */         updateChargeDB(actId);
/* 110 */         updateScoreDB(actId);
/* 111 */         updateTimesDB(actId);
/* 112 */         updateRewardDB(actId);
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public void updateRefChareDB(int id) {
/* 118 */     getProxy().setUpdate(String.valueOf(id), "refChare");
/*     */   }
/*     */   public void updateChargeDB(int id) {
/* 121 */     getProxy().setUpdate(String.valueOf(id), "charge");
/*     */   }
/*     */   public void updateScoreDB(int id) {
/* 124 */     getProxy().setUpdate(String.valueOf(id), "score");
/*     */   }
/*     */   public void updateTimesDB(int id) {
/* 127 */     getProxy().setUpdate(String.valueOf(id), "times");
/*     */   }
/*     */   public void updateRewardDB(int id) {
/* 130 */     getProxy().setUpdate(String.valueOf(id), "reward");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rebate\RechargeRebateComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */