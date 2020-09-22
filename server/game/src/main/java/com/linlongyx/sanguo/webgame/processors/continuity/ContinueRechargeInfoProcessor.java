/*    */ package com.linlongyx.sanguo.webgame.processors.continuity;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContinueRechargeInfoProcessor
/*    */   extends ProcessorBase<ContinueRechargeInfoRequest, ContinueRechargeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new ContinueRechargeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ContinueRechargeInfoRequest request) {
/* 35 */     int id = request.id;
/* 36 */     ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id, ContinFillingBean.class);
/* 37 */     if (null == continFillingBean) {
/* 38 */       return 12101;
/*    */     }
/* 40 */     ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/* 41 */     FestivalTime festivalTime = continuityParameter.getFestivalTime(id);
/* 42 */     if (null == festivalTime) {
/* 43 */       return 12101;
/*    */     }
/* 45 */     if (!continuityParameter.checkActOpen(id)) {
/* 46 */       return 12102;
/*    */     }
/* 48 */     ContinuityComponent continuityComponent = (ContinuityComponent)playerSession.getPlayer().createIfNotExist(ContinuityComponent.class);
/* 49 */     ContinuityEntity continuityEntity = continuityComponent.getContinuityEntity(id);
/* 50 */     Map<Integer, Integer> states = continuityEntity.getStates();
/*    */     
/* 52 */     List<Integer> list = new ArrayList<>(continFillingBean.getRankList());
/* 53 */     list.add(Integer.valueOf(-1));
/* 54 */     for (Integer tid : list) {
/* 55 */       IntIntValue intIntValue = new IntIntValue();
/* 56 */       intIntValue.key = tid.intValue();
/* 57 */       intIntValue.value = ((Integer)states.getOrDefault(tid, Integer.valueOf(0))).intValue();
/* 58 */       ((ContinueRechargeInfoResponse)this.response).list.add(intIntValue);
/*    */     } 
/* 60 */     int days = TimeUtil.getDayDisTime(festivalTime.startTime);
/*    */     
/* 62 */     ((ContinueRechargeInfoResponse)this.response).value = ((Long)continuityEntity.getDateCharges().getOrDefault(Integer.valueOf(days), Long.valueOf(0L))).longValue();
/* 63 */     ((ContinueRechargeInfoResponse)this.response).id = id;
/* 64 */     ((ContinueRechargeInfoResponse)this.response).time = festivalTime.endTime;
/* 65 */     ((ContinueRechargeInfoResponse)this.response).days = days;
/*    */     
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\continuity\ContinueRechargeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */