/*     */ package com.linlongyx.sanguo.webgame.processors.souls;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class SoulsUpgradeProcessor
/*     */   extends ProcessorBase<SoulsUpgradeRequest, SoulsUpgradeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new SoulsUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, SoulsUpgradeRequest request) {
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 55))
/*  40 */       return 10061; 
/*  41 */     SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/*  42 */     SoulsEntity soulsEntity = soulsComponent.getEntity(request.soulsId);
/*  43 */     if (null == soulsEntity) {
/*  44 */       return 16009;
/*     */     }
/*     */     
/*  47 */     int level = soulsEntity.getLevel();
/*  48 */     if (0 == level) {
/*  49 */       return 16009;
/*     */     }
/*     */     
/*  52 */     this.oldLevel = soulsEntity.getLevel();
/*  53 */     ArrayList<Integer> itemList = request.itemList;
/*  54 */     ArrayList<Integer> itemNums = request.numList;
/*  55 */     SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/*  56 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  58 */     SoulsLevelBean soulsLevelBean = (SoulsLevelBean)JsonTableService.getJsonData(level, SoulsLevelBean.class);
/*  59 */     if (null == soulsLevelBean) {
/*  60 */       return 10006;
/*     */     }
/*  62 */     SoulsLevelBean soulsLevelBean2 = (SoulsLevelBean)JsonTableService.getJsonData(level + 1, SoulsLevelBean.class);
/*  63 */     if (null == soulsLevelBean2) {
/*  64 */       return 10011;
/*     */     }
/*  66 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(soulsEntity.getId(), SoulsStarBean.class);
/*  67 */     int breankPar = ((SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(soulsEntity.getStar()))).getBreakPar();
/*  68 */     if ((!itemList.isEmpty() && itemNums.isEmpty()) || itemList.size() != itemNums.size()) {
/*  69 */       return 19004;
/*     */     }
/*  71 */     long addExp = 0L;
/*  72 */     for (int i = 0; i < itemList.size(); i++) {
/*  73 */       int itemId = ((Integer)itemList.get(i)).intValue();
/*  74 */       int itemNum = ((Integer)itemNums.get(i)).intValue();
/*  75 */       if (!soulsParameter.getExpMap().containsKey(Integer.valueOf(itemId))) {
/*  76 */         return 10703;
/*     */       }
/*  78 */       if (bagComponent.getItemNum(itemId) < ((Integer)itemNums.get(i)).intValue()) {
/*  79 */         return 10050;
/*     */       }
/*  81 */       if (((Integer)itemNums.get(i)).intValue() < 0) {
/*  82 */         return 13110;
/*     */       }
/*  84 */       addExp += (((Integer)soulsParameter.getExpMap().get(Integer.valueOf(itemId))).intValue() * itemNum);
/*     */     } 
/*     */     
/*  87 */     long aExp = soulsEntity.getExp() + addExp;
/*  88 */     ArrayList<Reward> cost = new ArrayList<>();
/*  89 */     if (level % soulsParameter.getRankLevel() == 0) {
/*  90 */       cost.addAll(FinanceUtil.transformRatio(soulsLevelBean.getConsumeClass(), breankPar));
/*     */     }
/*  92 */     if (CostUtil.checkRewards(cost, playerSession, bagComponent) != 0) {
/*  93 */       return 10050;
/*     */     }
/*  95 */     ArrayList<Reward> costs = new ArrayList<>();
/*  96 */     while (aExp >= soulsLevelBean.getExp() && CostUtil.checkRewards(cost, playerSession, bagComponent) == 0) {
/*  97 */       if (level % soulsParameter.getRankLevel() == 0) {
/*  98 */         costs = new ArrayList<>();
/*  99 */         costs.addAll(cost);
/*     */       } 
/* 101 */       aExp -= soulsLevelBean.getExp();
/* 102 */       level++;
/* 103 */       soulsLevelBean = (SoulsLevelBean)JsonTableService.getJsonData(level, SoulsLevelBean.class);
/* 104 */       SoulsLevelBean soulsLevelBeanAdd = (SoulsLevelBean)JsonTableService.getJsonData(level + 1, SoulsLevelBean.class);
/* 105 */       if (null == soulsLevelBeanAdd) {
/*     */         break;
/*     */       }
/* 108 */       if (level % soulsParameter.getRankLevel() == 0) {
/* 109 */         cost.addAll(FinanceUtil.transformRatio(soulsLevelBean.getConsumeClass(), breankPar));
/*     */       }
/*     */     } 
/*     */     
/* 113 */     for (int j = 0; j < itemList.size(); j++) {
/* 114 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.SoulsUpgrade, true);
/*     */     }
/* 116 */     CostUtil.costs(costs, playerSession, bagComponent, ResourceEvent.SoulsUpgrade);
/*     */     
/* 118 */     if (addExp > 0L) {
/* 119 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 121 */       ArrayList<Integer> ids = soulsParameter.GetExpList();
/* 122 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 123 */         int num = ((Integer)soulsParameter.getExpMap().get(ids.get(k))).intValue();
/* 124 */         if (aExp >= num) {
/* 125 */           long count2 = aExp / num;
/* 126 */           aExp %= num;
/* 127 */           int itemId = ((Integer)ids.get(k)).intValue();
/* 128 */           long itemNum = count2;
/* 129 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 130 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 132 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/*     */         } 
/*     */       } 
/* 136 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 137 */       for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 138 */         Reward reward = new Reward();
/* 139 */         reward.type = 2;
/* 140 */         reward.id = itemId;
/* 141 */         reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 142 */         rewards.add(reward); }
/*     */       
/* 144 */       if (!rewards.isEmpty()) {
/* 145 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.SoulsUpgrade, true);
/*     */       }
/*     */     } 
/* 148 */     if (aExp >= 2147483647L) {
/* 149 */       aExp = 2147483646L;
/*     */     }
/* 151 */     soulsEntity.setLevel(level);
/* 152 */     soulsComponent.updateLevelDB(soulsEntity.getId());
/* 153 */     soulsEntity.setExp((int)aExp);
/* 154 */     soulsComponent.updateExpDB(soulsEntity.getId());
/* 155 */     this.newLevel = soulsEntity.getLevel();
/* 156 */     if (this.oldLevel != this.newLevel) {
/* 157 */       AttrUpdateUtil.refreshSouls(playerSession);
/*     */     }
/* 159 */     ((SoulsUpgradeResponse)this.response).soulsId = soulsEntity.getId();
/* 160 */     ((SoulsUpgradeResponse)this.response).currLevel = soulsEntity.getLevel();
/* 161 */     ((SoulsUpgradeResponse)this.response).exp = soulsEntity.getExp();
/* 162 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\souls\SoulsUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */