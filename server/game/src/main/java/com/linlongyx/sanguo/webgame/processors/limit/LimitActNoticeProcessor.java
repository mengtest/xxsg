/*     */ package com.linlongyx.sanguo.webgame.processors.limit;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LimitCountType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LimitActNoticeProcessor
/*     */   extends ProcessorBase<LimitActNoticeRequest, LimitActNoticeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  33 */     this.response = (ResponseBase)new LimitActNoticeResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, LimitActNoticeRequest request) {
/*  38 */     LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/*  39 */     List<Integer> list = limitActParameter.getActId(true, true);
/*  40 */     Map<Integer, FestivalTime> festivalTimes = limitActParameter.getFestivalTimes();
/*  41 */     LimitComponent limitComponent = (LimitComponent)playerSession.getPlayer().createIfNotExist(LimitComponent.class);
/*  42 */     for (Integer actId : list) {
/*  43 */       LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId.intValue(), LimitActivityBean.class);
/*  44 */       if (festivalTimes.containsKey(actId)) {
/*  45 */         ((LimitActNoticeResponse)this.response).list.add(festivalTimes.get(actId));
/*     */       }
/*  47 */       if (limitActivityBean.getType() == 4 || limitActivityBean.getType() == 3) {
/*     */         continue;
/*     */       }
/*  50 */       ArrayList<Integer> activityList = limitActivityBean.getActivityList();
/*  51 */       LimitEntity limitEntity = limitComponent.getEntity(actId.intValue());
/*  52 */       Map<Integer, Integer> rewardCounts = limitEntity.getRewardCounts();
/*  53 */       Map<Integer, Long> values = limitEntity.getValues();
/*  54 */       for (Integer itemId : activityList) {
/*  55 */         if (checkRed(itemId.intValue(), rewardCounts, values, playerSession)) {
/*  56 */           LimitUtil.sendRed(playerSession, actId.intValue());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     return 0;
/*     */   }
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
/*     */   private boolean checkRed(int itemId, Map<Integer, Integer> rewardCounts, Map<Integer, Long> values, IPlayerSession playerSession) {
/*  74 */     LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId, LimitActivitylistBean.class);
/*  75 */     if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType() || 1 == limitActivitylistBean.getRewardType() || 3 == limitActivitylistBean.getRewardType()) {
/*  76 */       return false;
/*     */     }
/*  78 */     int num = limitActivitylistBean.getNum();
/*  79 */     int count = ((Integer)rewardCounts.getOrDefault(Integer.valueOf(limitActivitylistBean.getId()), Integer.valueOf(0))).intValue();
/*  80 */     if (count >= num) {
/*  81 */       return false;
/*     */     }
/*  83 */     if (limitActivitylistBean.getTargetType() == 0) {
/*  84 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  85 */       short code = CostUtil.checkRewards(FinanceUtil.transform(limitActivitylistBean.getCost()), playerSession, bagComponent);
/*  86 */       if (code != 0) {
/*  87 */         return false;
/*     */       }
/*     */     } else {
/*  90 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(limitActivitylistBean.getTargetType(), TaskTypeBean.class);
/*  91 */       if (taskTypeBean != null) {
/*  92 */         long requireValue = Integer.valueOf(limitActivitylistBean.getTarget()).intValue();
/*  93 */         long myValue = ((Long)values.getOrDefault(Integer.valueOf(limitActivitylistBean.getId()), Long.valueOf(0L))).longValue();
/*  94 */         if (taskTypeBean.getCount() == LimitCountType.singleDone.getType() && myValue < requireValue)
/*  95 */           return false; 
/*  96 */         if (taskTypeBean.getCount() == LimitCountType.singleEqual.getType() && myValue > requireValue)
/*  97 */           return false; 
/*  98 */         if (taskTypeBean.getCount() == LimitCountType.cumMoreDone.getType() && count >= limitActivitylistBean.getNum())
/*  99 */           return false; 
/* 100 */         if (taskTypeBean.getCount() == LimitCountType.cumDone.getType() && myValue != requireValue) {
/* 101 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 105 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\limit\LimitActNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */