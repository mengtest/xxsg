/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedlistBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedRecruitInfoProcessor
/*    */   extends ProcessorBase<RedRecruitInfoRequest, RedRecruitInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new RedRecruitInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RedRecruitInfoRequest request) {
/* 28 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 29 */     int type = request.type;
/* 30 */     if (type == 1) {
/* 31 */       if (recruitComponent.getToday().isEmpty() || recruitComponent.getTommorow().isEmpty()) {
/* 32 */         recruitComponent.refreshRedRecruit(type);
/*    */       }
/* 34 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 35 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, 1);
/* 36 */       if (arrayList.isEmpty()) {
/* 37 */         return 14023;
/*    */       }
/* 39 */       int actId = ((Integer)arrayList.get(0)).intValue();
/*    */       
/* 41 */       ((RedRecruitInfoResponse)this.response).refreshNum = recruitComponent.getRefreshNum();
/* 42 */       ((RedRecruitInfoResponse)this.response).today.addAll(recruitComponent.getToday());
/* 43 */       ((RedRecruitInfoResponse)this.response).tommorow.addAll(recruitComponent.getTommorow());
/* 44 */       ((RedRecruitInfoResponse)this.response).score = recruitComponent.getScore();
/* 45 */       ((RedRecruitInfoResponse)this.response).boxList.addAll(recruitComponent.getBoxList());
/* 46 */       ((RedRecruitInfoResponse)this.response).free = recruitComponent.getFree();
/* 47 */       ((RedRecruitInfoResponse)this.response).actId = actId;
/* 48 */     } else if (type == 2) {
/* 49 */       if (recruitComponent.getGoldToday().isEmpty() || recruitComponent.getGoldTommorow().isEmpty()) {
/* 50 */         recruitComponent.refreshRedRecruit(type);
/*    */       }
/* 52 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 53 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, type);
/* 54 */       int actId = 0;
/* 55 */       if (!arrayList.isEmpty()) {
/* 56 */         actId = ((Integer)arrayList.get(0)).intValue();
/* 57 */         RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/* 58 */         if (recruitRedlistBean.getActivityList().get(0) != recruitComponent.getGoldToday().get(0) || recruitRedlistBean.getActivityList().get(0) != recruitComponent.getTommorow().get(0)) {
/* 59 */           recruitComponent.getGoldTommorow().clear();
/* 60 */           recruitComponent.getGoldTommorow().add(recruitRedlistBean.getActivityList().get(0));
/* 61 */           recruitComponent.setGoldTommorow(recruitComponent.getGoldTommorow());
/* 62 */           recruitComponent.getGoldToday().clear();
/* 63 */           recruitComponent.getGoldToday().add(recruitRedlistBean.getActivityList().get(0));
/* 64 */           recruitComponent.setGoldToday(recruitComponent.getGoldToday());
/*    */         } 
/*    */       } 
/* 67 */       ((RedRecruitInfoResponse)this.response).refreshNum = recruitComponent.getGoldRefreshNum();
/* 68 */       ((RedRecruitInfoResponse)this.response).today.addAll(recruitComponent.getGoldToday());
/* 69 */       ((RedRecruitInfoResponse)this.response).tommorow.addAll(recruitComponent.getGoldTommorow());
/* 70 */       ((RedRecruitInfoResponse)this.response).score = recruitComponent.getGoldScore();
/* 71 */       ((RedRecruitInfoResponse)this.response).boxList.addAll(recruitComponent.getGoldBoxList());
/* 72 */       ((RedRecruitInfoResponse)this.response).free = recruitComponent.getGoldFree();
/* 73 */       ((RedRecruitInfoResponse)this.response).actId = actId;
/*    */     } 
/* 75 */     ((RedRecruitInfoResponse)this.response).type = type;
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RedRecruitInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */