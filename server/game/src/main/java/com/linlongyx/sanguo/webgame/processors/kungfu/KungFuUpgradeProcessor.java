/*     */ package com.linlongyx.sanguo.webgame.processors.kungfu;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.KungfuLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.KungFuParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class KungFuUpgradeProcessor
/*     */   extends ProcessorBase<KungFuUpgradeRequest, KungFuUpgradeResponse> {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new KungFuUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, KungFuUpgradeRequest request) {
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/*  40 */       return 10061; 
/*  41 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/*  42 */     KungFuEntity kungFuEntity = kungFuComponent.getEntity(request.kungFu);
/*  43 */     if (null == kungFuEntity) {
/*  44 */       return 16009;
/*     */     }
/*  46 */     int level = kungFuEntity.getLevel();
/*  47 */     this.oldLevel = kungFuEntity.getLevel();
/*  48 */     ArrayList<Integer> itemList = request.itemId;
/*  49 */     ArrayList<Integer> itemNums = request.itemNum;
/*  50 */     KungFuParameter kungFuParameter = (KungFuParameter)ParameterConstant.getParameter(53);
/*  51 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  53 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  54 */     if (level >= playerComponent.getLevel()) {
/*  55 */       return 10086;
/*     */     }
/*  57 */     KungfuLevelBean kungfuLevelBean = (KungfuLevelBean)JsonTableService.getJsonData(level, KungfuLevelBean.class);
/*  58 */     if (null == kungfuLevelBean) {
/*  59 */       return 10006;
/*     */     }
/*  61 */     if (itemNums.isEmpty()) {
/*  62 */       return 19004;
/*     */     }
/*  64 */     if (kungfuLevelBean.getExp() == 0) {
/*  65 */       return 19004;
/*     */     }
/*     */     
/*  68 */     long addExp = 0L;
/*  69 */     for (int i = 0; i < itemList.size(); i++) {
/*  70 */       if (!kungFuParameter.getExpMap().containsKey(itemList.get(i))) {
/*  71 */         return 10703;
/*     */       }
/*  73 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  74 */         return 10050;
/*     */       }
/*  76 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  77 */         return 13110;
/*     */       }
/*  79 */       addExp += (((Integer)kungFuParameter.getExpMap().get(itemList.get(i))).intValue() * ((Integer)itemNums.get(i)).intValue());
/*     */     } 
/*     */     
/*  82 */     long aExp = kungFuEntity.getExp() + addExp;
/*  83 */     boolean upLevel = false;
/*  84 */     while (aExp >= kungfuLevelBean.getExp() && kungfuLevelBean.getExp() != 0) {
/*  85 */       aExp -= kungfuLevelBean.getExp();
/*  86 */       level++;
/*  87 */       upLevel = true;
/*  88 */       kungfuLevelBean = (KungfuLevelBean)JsonTableService.getJsonData(level, KungfuLevelBean.class);
/*  89 */       if (level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  93 */     if (level > playerComponent.getLevel()) {
/*  94 */       return 13319;
/*     */     }
/*  96 */     for (int j = 0; j < itemList.size(); j++) {
/*  97 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.KungFuUpgrade, true);
/*     */     }
/*     */ 
/*     */     
/* 101 */     if (upLevel) {
/* 102 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 104 */       ArrayList<Integer> ids = kungFuParameter.GetExpList();
/* 105 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 106 */         int num = ((Integer)kungFuParameter.getExpMap().get(ids.get(k))).intValue();
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
/* 128 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.KungFuUpgrade, true);
/*     */       }
/*     */     } 
/* 131 */     kungFuEntity.setLevel(level);
/* 132 */     kungFuComponent.updateLevelDB(kungFuEntity.getKungFuId());
/* 133 */     kungFuEntity.setExp((int)aExp);
/* 134 */     kungFuComponent.updateExpDB(kungFuEntity.getKungFuId());
/* 135 */     this.newLevel = kungFuEntity.getLevel();
/* 136 */     if (this.oldLevel != this.newLevel) {
/* 137 */       AttrUpdateUtil.refreshKungFu(playerSession);
/* 138 */       KungFuUtil.updateKungfuFightValue(kungFuEntity, playerSession, kungFuComponent, false);
/*     */     } 
/* 140 */     ((KungFuUpgradeResponse)this.response).kungFu = kungFuEntity.getKungFuId();
/* 141 */     ((KungFuUpgradeResponse)this.response).level = kungFuEntity.getLevel();
/* 142 */     ((KungFuUpgradeResponse)this.response).exp = kungFuEntity.getExp();
/* 143 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 144 */     if (null != taskComponent) {
/* 145 */       taskComponent.refreshSchedule(TaskType.TotalKungFuLevel, 0, 0L);
/*     */     }
/* 147 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\KungFuUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */