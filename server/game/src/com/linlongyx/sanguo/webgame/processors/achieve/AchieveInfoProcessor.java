/*     */ package com.linlongyx.sanguo.webgame.processors.achieve;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.achieve.AchieveComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FameBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.AchieveParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.AchieveData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AchieveInfoProcessor
/*     */   extends ProcessorBase<AchieveInfoRequest, AchieveInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new AchieveInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, AchieveInfoRequest request) {
/*  35 */     AchieveComponent achieveComponent = (AchieveComponent)playerSession.getPlayer().createIfNotExist(AchieveComponent.class);
/*  36 */     ((AchieveInfoResponse)this.response).point = achieveComponent.getPoint();
/*  37 */     ((AchieveInfoResponse)this.response).process = new ArrayList(achieveComponent.getProcessReward());
/*  38 */     Set<Integer> fameReward = achieveComponent.getFameReward();
/*  39 */     Map<Integer, Long> values = achieveComponent.getValue();
/*  40 */     Set<Integer> fameDone = achieveComponent.getFameDone();
/*  41 */     AchieveParameter achieveParameter = (AchieveParameter)ParameterConstant.getParameter(4);
/*  42 */     Map<Integer, List<Integer>> configs = achieveParameter.getConfigs();
/*  43 */     for (Map.Entry<Integer, List<Integer>> entry : configs.entrySet()) {
/*  44 */       int type = ((Integer)entry.getKey()).intValue();
/*  45 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(type, TaskTypeBean.class);
/*  46 */       List<Integer> list = entry.getValue();
/*  47 */       if (list.isEmpty() || null == taskTypeBean) {
/*     */         continue;
/*     */       }
/*  50 */       if (taskTypeBean.getCountType() == 0) {
/*  51 */         AchieveUtil.refreshAchieve(TaskType.getTaskType(taskTypeBean.getTaskType()), 0, 0L, playerSession.getPlayer().getPlayerId());
/*     */       }
/*  53 */       int select = getfameId(list, fameReward);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  60 */       if (-1 != select) {
/*  61 */         boolean isDone; AchieveData achieveData = new AchieveData();
/*  62 */         achieveData.id = select;
/*     */ 
/*     */         
/*  65 */         if (fameReward.contains(Integer.valueOf(select))) {
/*  66 */           isDone = true;
/*  67 */           achieveData.status = 2;
/*  68 */         } else if (fameDone.contains(Integer.valueOf(select))) {
/*  69 */           isDone = true;
/*  70 */           achieveData.status = 1;
/*     */         } else {
/*  72 */           isDone = false;
/*  73 */           achieveData.status = 0;
/*     */         } 
/*     */         
/*  76 */         FameBean fameBean = (FameBean)JsonTableService.getJsonData(select, FameBean.class);
/*  77 */         if (isDone && null != fameBean) {
/*  78 */           achieveData.value = fameBean.getNumber();
/*     */         } else {
/*  80 */           achieveData.value = ((Long)values.getOrDefault(Integer.valueOf(select), Long.valueOf(0L))).longValue();
/*     */         } 
/*  82 */         ((AchieveInfoResponse)this.response).datas.add(achieveData);
/*     */       } 
/*     */     } 
/*  85 */     return 0;
/*     */   }
/*     */   
/*     */   public int getfameId(List<Integer> list, Set<Integer> fameReward) {
/*  89 */     int select = -1;
/*  90 */     int tempid = 0;
/*  91 */     for (Integer id : list) {
/*  92 */       FameBean fameBean = (FameBean)JsonTableService.getJsonData(id.intValue(), FameBean.class);
/*  93 */       if (null == fameBean) {
/*     */         continue;
/*     */       }
/*  96 */       if (fameBean.getOrder() == 0 && !fameReward.contains(id)) {
/*  97 */         select = id.intValue();
/*     */         break;
/*     */       } 
/* 100 */       if (fameBean.getOrder() != 0 && fameReward.contains(Integer.valueOf(fameBean.getOrder())) && id.intValue() > tempid) {
/* 101 */         select = id.intValue();
/* 102 */         tempid = id.intValue();
/*     */       } 
/*     */     } 
/* 105 */     return select;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\achieve\AchieveInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */