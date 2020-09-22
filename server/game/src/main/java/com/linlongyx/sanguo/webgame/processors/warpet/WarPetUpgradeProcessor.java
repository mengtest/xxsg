/*     */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PetLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WarPetParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class WarPetUpgradeProcessor
/*     */   extends ProcessorBase<WarPetUpgradeRequest, WarPetUpgradeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new WarPetUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, WarPetUpgradeRequest request) {
/*  39 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/*  40 */     WarPetEntity warPetEntity = warPetComponent.getEntity(request.warPet);
/*  41 */     if (null == warPetEntity) {
/*  42 */       return 16004;
/*     */     }
/*  44 */     int level = warPetEntity.getLevel();
/*  45 */     this.oldLevel = warPetEntity.getLevel();
/*  46 */     ArrayList<Integer> itemList = request.itemId;
/*  47 */     ArrayList<Integer> itemNums = request.ItemNum;
/*  48 */     WarPetParameter warPetParameter = (WarPetParameter)ParameterConstant.getParameter(30);
/*  49 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  51 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  52 */     if (level >= playerComponent.getLevel()) {
/*  53 */       return 10086;
/*     */     }
/*  55 */     PetLevelBean petLevelBean = (PetLevelBean)JsonTableService.getJsonData(level, PetLevelBean.class);
/*  56 */     if (null == petLevelBean) {
/*  57 */       return 10006;
/*     */     }
/*  59 */     if (itemNums.isEmpty()) {
/*  60 */       return 19004;
/*     */     }
/*  62 */     if (petLevelBean.getExp() == 0) {
/*  63 */       return 19004;
/*     */     }
/*  65 */     long addExp = 0L;
/*  66 */     for (int i = 0; i < itemList.size(); i++) {
/*  67 */       if (!warPetParameter.getExpMap().containsKey(itemList.get(i))) {
/*  68 */         return 10703;
/*     */       }
/*  70 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  71 */         return 10050;
/*     */       }
/*  73 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  74 */         return 13110;
/*     */       }
/*  76 */       addExp += (((Integer)warPetParameter.getExpMap().get(itemList.get(i))).intValue() * ((Integer)itemNums.get(i)).intValue());
/*     */     } 
/*     */     
/*  79 */     long aExp = warPetEntity.getExp() + addExp;
/*  80 */     boolean upLevel = false;
/*  81 */     while (aExp >= petLevelBean.getExp() && petLevelBean.getExp() != 0) {
/*  82 */       aExp -= petLevelBean.getExp();
/*  83 */       level++;
/*  84 */       upLevel = true;
/*  85 */       petLevelBean = (PetLevelBean)JsonTableService.getJsonData(level, PetLevelBean.class);
/*  86 */       if (level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  90 */     if (level > playerComponent.getLevel()) {
/*  91 */       return 13319;
/*     */     }
/*  93 */     for (int j = 0; j < itemList.size(); j++) {
/*  94 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.WarPetUpgrade, true);
/*     */     }
/*     */ 
/*     */     
/*  98 */     if (upLevel) {
/*  99 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 101 */       ArrayList<Integer> ids = warPetParameter.GetExpList();
/* 102 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 103 */         int num = ((Integer)warPetParameter.getExpMap().get(ids.get(k))).intValue();
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
/* 125 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.WarPetUpgrade, true);
/*     */       }
/*     */     } 
/* 128 */     warPetEntity.setLevel(level);
/* 129 */     warPetEntity.setExp((int)aExp);
/* 130 */     warPetComponent.updateExpDB(warPetEntity.getWarPetId());
/* 131 */     this.newLevel = warPetEntity.getLevel();
/* 132 */     if (this.oldLevel != this.newLevel) {
/*     */ 
/*     */       
/* 135 */       AttrUpdateUtil.refreshWarPet(playerSession);
/* 136 */       WarPetUtil.updateWarPetFightValue(warPetEntity, playerSession, warPetComponent, false);
/*     */     } 
/*     */     
/* 139 */     ((WarPetUpgradeResponse)this.response).warPet = warPetEntity.getWarPetId();
/* 140 */     ((WarPetUpgradeResponse)this.response).level = warPetEntity.getLevel();
/* 141 */     ((WarPetUpgradeResponse)this.response).exp = warPetEntity.getExp();
/* 142 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 143 */     if (null != taskComponent)
/*     */     {
/*     */       
/* 146 */       taskComponent.refreshSchedule(TaskType.WarPetFeed, 0, 1L);
/*     */     }
/* 148 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */