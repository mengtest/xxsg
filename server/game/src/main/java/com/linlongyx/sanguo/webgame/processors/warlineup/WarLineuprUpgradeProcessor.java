/*     */ package com.linlongyx.sanguo.webgame.processors.warlineup;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WarZhenfaParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineuprUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineuprUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class WarLineuprUpgradeProcessor
/*     */   extends ProcessorBase<WarLineuprUpgradeRequest, WarLineuprUpgradeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new WarLineuprUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, WarLineuprUpgradeRequest request) {
/*  39 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/*  40 */     WarLineupEntity warLineupEntity = warLineupComponent.getEntity(request.warlineup);
/*  41 */     if (null == warLineupEntity) {
/*  42 */       return 15204;
/*     */     }
/*  44 */     int level = warLineupEntity.getLevel();
/*  45 */     this.oldLevel = warLineupEntity.getLevel();
/*  46 */     ArrayList<Integer> itemList = request.itemId;
/*  47 */     ArrayList<Integer> itemNums = request.ItemNum;
/*  48 */     WarZhenfaParameter warZhenfaParameter = (WarZhenfaParameter)ParameterConstant.getParameter(60);
/*  49 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  51 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  52 */     if (level >= playerComponent.getLevel()) {
/*  53 */       return 10086;
/*     */     }
/*  55 */     ZhenfaLevelBean zhenfaLevelBean = (ZhenfaLevelBean)JsonTableService.getJsonData(level, ZhenfaLevelBean.class);
/*  56 */     if (null == zhenfaLevelBean) {
/*  57 */       return 10006;
/*     */     }
/*  59 */     if (itemNums.isEmpty()) {
/*  60 */       return 19004;
/*     */     }
/*  62 */     if (zhenfaLevelBean.getExp() == 0) {
/*  63 */       return 19004;
/*     */     }
/*     */     
/*  66 */     long addExp = 0L;
/*  67 */     for (int i = 0; i < itemList.size(); i++) {
/*  68 */       if (!warZhenfaParameter.getExpMap().containsKey(itemList.get(i))) {
/*  69 */         return 10703;
/*     */       }
/*  71 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  72 */         return 10050;
/*     */       }
/*  74 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  75 */         return 13110;
/*     */       }
/*  77 */       addExp += (((Integer)warZhenfaParameter.getExpMap().get(itemList.get(i))).intValue() * ((Integer)itemNums.get(i)).intValue());
/*     */     } 
/*     */     
/*  80 */     long aExp = warLineupEntity.getExp() + addExp;
/*  81 */     boolean upLevel = false;
/*  82 */     while (aExp >= zhenfaLevelBean.getExp() && zhenfaLevelBean.getExp() != 0) {
/*  83 */       aExp -= zhenfaLevelBean.getExp();
/*  84 */       level++;
/*  85 */       upLevel = true;
/*  86 */       zhenfaLevelBean = (ZhenfaLevelBean)JsonTableService.getJsonData(level, ZhenfaLevelBean.class);
/*  87 */       if (level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  91 */     if (level > playerComponent.getLevel()) {
/*  92 */       return 13319;
/*     */     }
/*  94 */     for (int j = 0; j < itemList.size(); j++) {
/*  95 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.WarZhenfaUpgrade, true);
/*     */     }
/*     */     
/*  98 */     if (upLevel) {
/*  99 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 101 */       ArrayList<Integer> ids = warZhenfaParameter.GetExpList();
/* 102 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 103 */         int num = ((Integer)warZhenfaParameter.getExpMap().get(ids.get(k))).intValue();
/* 104 */         if (aExp >= num) {
/* 105 */           long count2 = aExp / num;
/* 106 */           aExp %= num;
/* 107 */           int itemId = ((Integer)ids.get(k)).intValue();
/* 108 */           long itemNum = count2;
/* 109 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 110 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 112 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/*     */         } 
/*     */       } 
/* 116 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 117 */       for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 118 */         Reward reward = new Reward();
/* 119 */         reward.type = 2;
/* 120 */         reward.id = itemId;
/* 121 */         reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 122 */         rewards.add(reward); }
/*     */       
/* 124 */       if (!rewards.isEmpty()) {
/* 125 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.WarZhenfaUpgrade, true);
/*     */       }
/*     */     } 
/* 128 */     warLineupEntity.setLevel(level);
/* 129 */     warLineupEntity.setExp((int)aExp);
/* 130 */     warLineupComponent.updateExpDB(warLineupEntity.getWarLineupId());
/* 131 */     this.newLevel = warLineupEntity.getLevel();
/* 132 */     if (this.oldLevel != this.newLevel) {
/* 133 */       AttrUpdateUtil.refreshWarLineup(playerSession);
/* 134 */       WarLineupUtil.updateWarLineupFightValue(warLineupEntity, playerSession, warLineupComponent, false);
/*     */     } 
/* 136 */     ((WarLineuprUpgradeResponse)this.response).warlineup = warLineupEntity.getWarLineupId();
/* 137 */     ((WarLineuprUpgradeResponse)this.response).level = warLineupEntity.getLevel();
/* 138 */     ((WarLineuprUpgradeResponse)this.response).exp = warLineupEntity.getExp();
/* 139 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 140 */     if (null != taskComponent) {
/* 141 */       taskComponent.refreshSchedule(TaskType.TotalZhenFaLevel, 0, 0L);
/*     */     }
/* 143 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\WarLineuprUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */