/*     */ package com.linlongyx.sanguo.webgame.processors.limit;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LimitCountType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRewardResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LimitActRewardProcessor
/*     */   extends ProcessorBase<LimitActRewardRequest, LimitActRewardResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  34 */     this.response = (ResponseBase)new LimitActRewardResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, LimitActRewardRequest request) {
/*  39 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  40 */     int actId = request.actId;
/*  41 */     int itemId = request.itemId;
/*  42 */     LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId, LimitActivityBean.class);
/*  43 */     if (null == limitActivityBean) {
/*  44 */       return 12701;
/*     */     }
/*  46 */     if (!limitActivityBean.getActivityList().contains(Integer.valueOf(itemId))) {
/*  47 */       return 12701;
/*     */     }
/*  49 */     LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId, LimitActivitylistBean.class);
/*  50 */     if (null == limitActivitylistBean) {
/*  51 */       return 12701;
/*     */     }
/*  53 */     if (1 == limitActivitylistBean.getDisplayType() || 1 == limitActivitylistBean.getRewardType() || 3 == limitActivitylistBean.getRewardType()) {
/*  54 */       return 12703;
/*     */     }
/*  56 */     LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/*  57 */     FestivalTime festivalTime = limitActParameter.getFestivalTime(actId);
/*  58 */     if (null == festivalTime) {
/*  59 */       return 12701;
/*     */     }
/*  61 */     if (!limitActParameter.isActOpen(actId) && festivalTime.closeTime < TimeUtil.currentTime()) {
/*  62 */       return 12702;
/*     */     }
/*  64 */     LimitComponent limitComponent = (LimitComponent)playerSession.getPlayer().createIfNotExist(LimitComponent.class);
/*  65 */     LimitEntity limitEntity = limitComponent.getEntity(actId);
/*  66 */     Map<Integer, Integer> rewardCounts = limitEntity.getRewardCounts();
/*  67 */     int num = limitActivitylistBean.getNum();
/*  68 */     int count = ((Integer)rewardCounts.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/*  69 */     if (count >= num) {
/*  70 */       return 12704;
/*     */     }
/*  72 */     if (limitActivitylistBean.getVipLimit() > playerComponent.getVip()) {
/*  73 */       return 10088;
/*     */     }
/*  75 */     Map<Integer, Long> values = limitEntity.getValues();
/*  76 */     long myValue = ((Long)values.getOrDefault(Integer.valueOf(limitActivitylistBean.getId()), Long.valueOf(0L))).longValue();
/*  77 */     int changeNnum = 0;
/*  78 */     if (limitActivitylistBean.getTargetType() == 0) {
/*     */       short code;
/*  80 */       if (request.num <= 0) {
/*  81 */         changeNnum = 1;
/*     */       } else {
/*  83 */         changeNnum = request.num;
/*     */       } 
/*  85 */       if (changeNnum > num) {
/*  86 */         return 12704;
/*     */       }
/*  88 */       if (count + changeNnum > num) {
/*  89 */         return 12704;
/*     */       }
/*     */       
/*  92 */       if (2 == limitActivitylistBean.getRewardType()) {
/*  93 */         BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  94 */         code = CostUtil.checkRewards(FinanceUtil.transform2(limitActivitylistBean.getCost(), changeNnum), playerSession, bagComponent);
/*     */       } else {
/*  96 */         code = CostUtil.handleCostRewards(FinanceUtil.transform2(limitActivitylistBean.getCost(), changeNnum), playerSession, ResourceEvent.LimitAct);
/*     */       } 
/*  98 */       if (code != 0) {
/*  99 */         return code;
/*     */       }
/*     */     } else {
/* 102 */       changeNnum = 1;
/* 103 */       long requireValue = limitActivitylistBean.getTarget();
/* 104 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(limitActivitylistBean.getTargetType(), TaskTypeBean.class);
/* 105 */       if (null == taskTypeBean) {
/* 106 */         return 10006;
/*     */       }
/* 108 */       if (taskTypeBean.getCount() == LimitCountType.singleDone.getType()) {
/* 109 */         if (myValue < requireValue) {
/* 110 */           return 12705;
/*     */         }
/* 112 */       } else if (taskTypeBean.getCount() == LimitCountType.singleEqual.getType()) {
/* 113 */         if (myValue > requireValue) {
/* 114 */           return 12705;
/*     */         }
/* 116 */       } else if (taskTypeBean.getCount() == LimitCountType.cumDone.getType()) {
/* 117 */         if (myValue != requireValue) {
/* 118 */           return 12705;
/*     */         }
/* 120 */       } else if (taskTypeBean.getCount() == LimitCountType.cumMoreDone.getType() && 
/* 121 */         count >= myValue) {
/* 122 */         return 12704;
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     count += changeNnum;
/* 127 */     rewardCounts.put(Integer.valueOf(itemId), Integer.valueOf(count));
/* 128 */     limitEntity.setRewardCounts(rewardCounts);
/* 129 */     limitComponent.updateRewardCountsDB(actId);
/*     */     
/* 131 */     FinanceUtil.reward(FinanceUtil.transform2(limitActivitylistBean.getAward(), changeNnum), playerSession.getPlayer(), ResourceEvent.LimitAct, true);
/*     */     
/* 133 */     ((LimitActRewardResponse)this.response).actId = actId;
/* 134 */     ((LimitActRewardResponse)this.response).data.itemId = itemId;
/* 135 */     ((LimitActRewardResponse)this.response).data.num = count;
/* 136 */     ((LimitActRewardResponse)this.response).data.value = myValue;
/* 137 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\limit\LimitActRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */