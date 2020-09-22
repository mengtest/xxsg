/*     */ package com.linlongyx.sanguo.webgame.processors.stage;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StageLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.StageParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class StageUpgradeProcessor
/*     */   extends ProcessorBase<StageUpgradeRequest, StageUpgradeResponse> {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new StageUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, StageUpgradeRequest request) {
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/*  40 */       return 10061; 
/*  41 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/*  42 */     StageEntity stageEntity = stageComponent.getEntity(request.stage);
/*  43 */     if (null == stageEntity) {
/*  44 */       return 16009;
/*     */     }
/*  46 */     int level = stageEntity.getLevel();
/*  47 */     this.oldLevel = stageEntity.getLevel();
/*  48 */     ArrayList<Integer> itemList = request.itemId;
/*  49 */     ArrayList<Integer> itemNums = request.itemNum;
/*  50 */     StageParameter stageParameter = (StageParameter)ParameterConstant.getParameter(54);
/*  51 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  53 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  54 */     if (level >= playerComponent.getLevel()) {
/*  55 */       return 10086;
/*     */     }
/*  57 */     StageLevelBean stageLevelBean = (StageLevelBean)JsonTableService.getJsonData(level, StageLevelBean.class);
/*  58 */     if (null == stageLevelBean) {
/*  59 */       return 10006;
/*     */     }
/*  61 */     if (itemNums.isEmpty()) {
/*  62 */       return 19004;
/*     */     }
/*  64 */     if (stageLevelBean.getExp() == 0) {
/*  65 */       return 19004;
/*     */     }
/*     */     
/*  68 */     long addExp = 0L;
/*  69 */     for (int i = 0; i < itemList.size(); i++) {
/*  70 */       if (!stageParameter.getExpMap().containsKey(itemList.get(i))) {
/*  71 */         return 10703;
/*     */       }
/*  73 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  74 */         return 10050;
/*     */       }
/*  76 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  77 */         return 13110;
/*     */       }
/*  79 */       addExp += (((Integer)stageParameter.getExpMap().get(itemList.get(i))).intValue() * ((Integer)itemNums.get(i)).intValue());
/*     */     } 
/*     */     
/*  82 */     long aExp = stageEntity.getExp() + addExp;
/*  83 */     boolean upLevel = false;
/*  84 */     while (aExp >= stageLevelBean.getExp() && stageLevelBean.getExp() != 0) {
/*  85 */       aExp -= stageLevelBean.getExp();
/*  86 */       level++;
/*  87 */       upLevel = true;
/*  88 */       stageLevelBean = (StageLevelBean)JsonTableService.getJsonData(level, StageLevelBean.class);
/*  89 */       if (level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  93 */     if (level > playerComponent.getLevel()) {
/*  94 */       return 13319;
/*     */     }
/*  96 */     for (int j = 0; j < itemList.size(); j++) {
/*  97 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.StageUpgrade, true);
/*     */     }
/*     */ 
/*     */     
/* 101 */     if (upLevel) {
/* 102 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 104 */       ArrayList<Integer> ids = stageParameter.GetExpList();
/* 105 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 106 */         int num = ((Integer)stageParameter.getExpMap().get(ids.get(k))).intValue();
/* 107 */         if (aExp >= num) {
/* 108 */           long count2 = aExp / num;
/* 109 */           aExp %= num;
/* 110 */           int itemId = ((Integer)ids.get(k)).intValue();
/* 111 */           long itemNum = count2;
/* 112 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 113 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 115 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/*     */         } 
/*     */       } 
/* 119 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 120 */       for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 121 */         Reward reward = new Reward();
/* 122 */         reward.type = 2;
/* 123 */         reward.id = itemId;
/* 124 */         reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 125 */         rewards.add(reward); }
/*     */       
/* 127 */       if (!rewards.isEmpty()) {
/* 128 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.StageUpgrade, true);
/*     */       }
/*     */     } 
/* 131 */     stageEntity.setLevel(level);
/* 132 */     stageComponent.updateLevelDB(stageEntity.getId());
/* 133 */     stageEntity.setExp((int)aExp);
/* 134 */     stageComponent.updateExpDB(stageEntity.getId());
/* 135 */     this.newLevel = stageEntity.getLevel();
/* 136 */     if (this.oldLevel != this.newLevel) {
/* 137 */       AttrUpdateUtil.refreshStage(playerSession);
/* 138 */       StageUtil.updateStageFightValue(stageEntity, playerSession, stageComponent, false);
/*     */     } 
/* 140 */     ((StageUpgradeResponse)this.response).stage = stageEntity.getId();
/* 141 */     ((StageUpgradeResponse)this.response).level = stageEntity.getLevel();
/* 142 */     ((StageUpgradeResponse)this.response).exp = stageEntity.getExp();
/* 143 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 144 */     if (null != taskComponent) {
/* 145 */       taskComponent.refreshSchedule(TaskType.TotalStageLevel, 0, 0L);
/*     */     }
/* 147 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\StageUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */