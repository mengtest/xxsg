/*     */ package com.linlongyx.sanguo.webgame.processors.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RetrieveBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FindRewardData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardGetRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardGetResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FindRewardGetProcessor
/*     */   extends ProcessorBase<FindRewardGetRequest, FindRewardGetResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  29 */     this.response = (ResponseBase)new FindRewardGetResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, FindRewardGetRequest request) {
/*  34 */     int type = request.type;
/*  35 */     int value = request.value;
/*  36 */     int costType = request.costType;
/*  37 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  38 */     if (type != 0) {
/*  39 */       FindRewardType findRewardType = FindRewardType.getFindRewardType(type);
/*  40 */       if (null == findRewardType) {
/*  41 */         return 12215;
/*     */       }
/*  43 */       FindRewardData findRewardData = TaskUtil.getFindRewardData(findRewardType, playerSession, true);
/*  44 */       if (null == findRewardData) {
/*  45 */         return 12216;
/*     */       }
/*     */       
/*  48 */       if (value <= 0 || value > findRewardData.maxValue - findRewardData.curValue) {
/*  49 */         return 12217;
/*     */       }
/*  51 */       if (findRewardData.maxValue <= findRewardData.curValue) {
/*  52 */         return 12216;
/*     */       }
/*  54 */       if (costType != 1 && costType != 3) {
/*  55 */         return 11808;
/*     */       }
/*  57 */       RetrieveBean retrieveBean = (RetrieveBean)JsonTableService.getJsonData(type, RetrieveBean.class);
/*  58 */       if (null == retrieveBean) {
/*  59 */         return 10006;
/*     */       }
/*  61 */       ArrayList<Reward> costs = new ArrayList<>();
/*  62 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  63 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  64 */       if (costType == 1) {
/*  65 */         rewards.addAll(FinanceUtil.transformRewardList(findRewardData.list, value));
/*  66 */         rewards = FinanceUtil.transformRewardFloor(rewards, retrieveBean.getFreePro());
/*     */       } 
/*  68 */       if (costType == 2) {
/*  69 */         costs.addAll(FinanceUtil.transformRewardList(findRewardData.goldCost, value));
/*  70 */         short code = CostUtil.checkRewards(costs, playerSession, bagComponent);
/*  71 */         if (0 != code) {
/*  72 */           return code;
/*     */         }
/*  74 */         rewards.addAll(FinanceUtil.transformRewardList(findRewardData.list, value));
/*  75 */         rewards = FinanceUtil.transformRewardFloor(rewards, retrieveBean.getGoldPro());
/*  76 */       } else if (costType == 3) {
/*  77 */         costs.addAll(FinanceUtil.transformRewardList(findRewardData.moneyCost, value));
/*  78 */         short code = CostUtil.checkRewards(costs, playerSession, bagComponent);
/*  79 */         if (0 != code) {
/*  80 */           return code;
/*     */         }
/*  82 */         rewards.addAll(FinanceUtil.transformRewardList(findRewardData.list, value));
/*  83 */         rewards = FinanceUtil.transformRewardFloor(rewards, retrieveBean.getMoneyPro());
/*     */       } 
/*     */       
/*  86 */       CostUtil.costs(costs, playerSession, bagComponent, ResourceEvent.FindCost);
/*  87 */       int nextValue = findRewardData.curValue + value;
/*  88 */       findRewardData.curValue = nextValue;
/*  89 */       singleInsComponent.saveFindTimes(false, type, value);
/*  90 */       ((FindRewardGetResponse)this.response).data = findRewardData;
/*  91 */       ((FindRewardGetResponse)this.response).list = new ArrayList<>(rewards);
/*  92 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.FindReward, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 272 */     else if (type == 0) {
/*     */       
/* 274 */       ArrayList<Reward> costs = new ArrayList<>();
/* 275 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 276 */       Map<Integer, Integer> typeTime = new HashMap<>();
/* 277 */       for (FindRewardType type1 : FindRewardType.values()) {
/* 278 */         FindRewardData findRewardData = TaskUtil.getFindRewardData(type1, playerSession, true);
/* 279 */         if (null != findRewardData) {
/* 280 */           int time = findRewardData.maxValue - findRewardData.curValue;
/* 281 */           typeTime.put(Integer.valueOf(type1.getType()), Integer.valueOf(time));
/* 282 */           RetrieveBean retrieveBean = (RetrieveBean)JsonTableService.getJsonData(type1.getType(), RetrieveBean.class);
/* 283 */           if (time > 0 && null != retrieveBean) {
/* 284 */             costs.addAll(FinanceUtil.transformRewardList(findRewardData.moneyCost, time));
/* 285 */             rewards.addAll(FinanceUtil.transformRewardList(findRewardData.list, time));
/* 286 */             rewards = FinanceUtil.transformRewardFloor(rewards, retrieveBean.getMoneyPro());
/*     */           } 
/*     */         } 
/*     */       } 
/* 290 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 291 */       FinanceUtil.mergeRewardList(costs);
/* 292 */       short code = CostUtil.checkRewards(costs, playerSession, bagComponent);
/* 293 */       if (code != 0) {
/* 294 */         return code;
/*     */       }
/* 296 */       FinanceUtil.mergeRewardList(rewards);
/*     */       
/* 298 */       CostUtil.costs(costs, playerSession, bagComponent, ResourceEvent.FindCost);
/* 299 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.FindReward, true);
/* 300 */       for (Iterator<Integer> iterator = typeTime.keySet().iterator(); iterator.hasNext(); ) { int findtype = ((Integer)iterator.next()).intValue();
/* 301 */         singleInsComponent.saveFindTimes(false, findtype, ((Integer)typeTime.get(Integer.valueOf(findtype))).intValue()); }
/*     */       
/* 303 */       ((FindRewardGetResponse)this.response).list = new ArrayList<>(rewards);
/*     */     } 
/* 305 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\FindRewardGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */