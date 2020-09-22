/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.StartResearchRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.StartResearchResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ public class StartResearchProcessor
/*    */   extends ProcessorBase<StartResearchRequest, StartResearchResponse> {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new StartResearchResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, StartResearchRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80)) {
/* 29 */       return 10061;
/*    */     }
/* 31 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 32 */     if (militaryOfficeComponent.getCurrJobId() != 0) {
/* 33 */       return 22801;
/*    */     }
/* 35 */     if (militaryOfficeComponent.getEndTime() > TimeUtil.currentTime()) {
/* 36 */       return 22801;
/*    */     }
/* 38 */     MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(request.officeId, MilitaryBean.class);
/* 39 */     if (null == militaryBean) {
/* 40 */       return 11808;
/*    */     }
/* 42 */     if (militaryBean.getLimit() == 0) {
/* 43 */       return 10061;
/*    */     }
/* 45 */     int level = ((Integer)militaryOfficeComponent.getOfficeList().getOrDefault(Integer.valueOf(request.officeId), Integer.valueOf(0))).intValue();
/* 46 */     MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf(level));
/* 47 */     MilitaryBean.LevelBean levelBean11 = (MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf(level + 1));
/* 48 */     if (levelBean.getTime() == 0 || levelBean11 == null) {
/* 49 */       return 10011;
/*    */     }
/* 51 */     for (MilitaryBean.LevelBean.PreconditionBean p : levelBean.getPrecondition()) {
/* 52 */       if (militaryOfficeComponent.getOfficeList().get(Integer.valueOf(p.getId())) == null || ((Integer)militaryOfficeComponent.getOfficeList().get(Integer.valueOf(p.getId()))).intValue() < p.getNum()) {
/* 53 */         return 22805;
/*    */       }
/*    */     } 
/* 56 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 57 */     short res = CostUtil.check(levelBean.getItem(), playerSession, bagComponent);
/* 58 */     if (res != 0) {
/* 59 */       return res;
/*    */     }
/* 61 */     CostUtil.cost(levelBean.getItem(), playerSession, bagComponent, ResourceEvent.Recruit);
/* 62 */     militaryOfficeComponent.setCurrJobId(request.officeId);
/* 63 */     militaryOfficeComponent.setEndTime(TimeUtil.currentTime() + levelBean.getTime());
/* 64 */     militaryOfficeComponent.setStatus(0);
/* 65 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 66 */     if (null != taskComponent) {
/* 67 */       taskComponent.refreshSchedule(TaskType.MilitaryResearch, 0, 1L);
/*    */     }
/*    */     
/* 70 */     ((StartResearchResponse)this.response).officeId = request.officeId;
/* 71 */     ((StartResearchResponse)this.response).endTime = militaryOfficeComponent.getEndTime();
/* 72 */     ((StartResearchResponse)this.response).status = militaryOfficeComponent.getStatus();
/* 73 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\StartResearchProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */