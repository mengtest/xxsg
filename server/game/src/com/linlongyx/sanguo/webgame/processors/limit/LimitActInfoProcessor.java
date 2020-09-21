/*    */ package com.linlongyx.sanguo.webgame.processors.limit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LimitData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LimitActInfoProcessor
/*    */   extends ProcessorBase<LimitActInfoRequest, LimitActInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new LimitActInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LimitActInfoRequest request) {
/* 34 */     int actId = request.actId;
/* 35 */     LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId, LimitActivityBean.class);
/* 36 */     if (null == limitActivityBean) {
/* 37 */       return 12701;
/*    */     }
/* 39 */     LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 40 */     FestivalTime festivalTime = limitActParameter.getFestivalTime(actId);
/* 41 */     if (null == festivalTime) {
/* 42 */       return 12701;
/*    */     }
/* 44 */     if (!limitActParameter.isActOpen(actId) && festivalTime.closeTime < TimeUtil.currentTime())
/*    */     {
/*    */ 
/*    */ 
/*    */       
/* 49 */       return 12702;
/*    */     }
/*    */     
/* 52 */     LimitComponent limitComponent = (LimitComponent)playerSession.getPlayer().createIfNotExist(LimitComponent.class);
/* 53 */     LimitEntity limitEntity = limitComponent.getEntity(actId);
/* 54 */     Map<Integer, Integer> rewardCounts = limitEntity.getRewardCounts();
/* 55 */     Map<Integer, Long> values = limitEntity.getValues();
/* 56 */     ArrayList<Integer> activityList = limitActivityBean.getActivityList();
/*    */     
/* 58 */     LimitUtil.countValue2(limitComponent, limitEntity, activityList);
/*    */ 
/*    */ 
/*    */     
/* 62 */     for (Integer itemId : activityList) {
/* 63 */       LimitData limitData = new LimitData();
/* 64 */       limitData.itemId = itemId.intValue();
/* 65 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 66 */       if (null != limitActivitylistBean) {
/* 67 */         limitData.value = ((Long)values.getOrDefault(itemId, Long.valueOf(0L))).longValue();
/*    */       } else {
/* 69 */         values.remove(itemId);
/*    */         continue;
/*    */       } 
/* 72 */       limitData.num = ((Integer)rewardCounts.getOrDefault(itemId, Integer.valueOf(0))).intValue();
/* 73 */       ((LimitActInfoResponse)this.response).list.add(limitData);
/*    */     } 
/*    */     
/* 76 */     ((LimitActInfoResponse)this.response).actId = actId;
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\limit\LimitActInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */