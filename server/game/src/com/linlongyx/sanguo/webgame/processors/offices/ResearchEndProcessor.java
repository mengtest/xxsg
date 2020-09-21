/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.ResearchEndRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.ResearchEndResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ResearchEndProcessor
/*    */   extends ProcessorBase<ResearchEndRequest, ResearchEndResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new ResearchEndResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ResearchEndRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80)) {
/* 35 */       return 10061;
/*    */     }
/* 37 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 38 */     if (militaryOfficeComponent.getCurrJobId() == 0 || request.officeId != militaryOfficeComponent.getCurrJobId()) {
/* 39 */       return 22802;
/*    */     }
/* 41 */     MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(request.officeId, MilitaryBean.class);
/* 42 */     if (null == militaryBean) {
/* 43 */       return 10006;
/*    */     }
/* 45 */     if (TimeUtil.currentTime() < militaryOfficeComponent.getEndTime()) {
/* 46 */       return 15003;
/*    */     }
/*    */     
/* 49 */     int level = ((Integer)militaryOfficeComponent.getOfficeList().getOrDefault(Integer.valueOf(request.officeId), Integer.valueOf(0))).intValue();
/* 50 */     level++;
/* 51 */     MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf(level));
/* 52 */     if (null == levelBean) {
/* 53 */       return 10006;
/*    */     }
/* 55 */     int type = levelBean.getTypeS();
/*    */     
/* 57 */     if (militaryOfficeComponent.getTypeMap().containsKey(Integer.valueOf(type)) && !((Set)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(type))).contains(Integer.valueOf(request.officeId))) {
/* 58 */       ((Set<Integer>)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(type))).add(Integer.valueOf(request.officeId));
/* 59 */     } else if (!militaryOfficeComponent.getTypeMap().containsKey(Integer.valueOf(type))) {
/* 60 */       Set<Integer> set = new HashSet<>();
/* 61 */       set.add(Integer.valueOf(request.officeId));
/* 62 */       militaryOfficeComponent.getTypeMap().put(Integer.valueOf(type), set);
/*    */     } 
/* 64 */     militaryOfficeComponent.setEndTime(0);
/* 65 */     militaryOfficeComponent.setCurrJobId(0);
/* 66 */     militaryOfficeComponent.setTypeMap(militaryOfficeComponent.getTypeMap());
/* 67 */     militaryOfficeComponent.getOfficeList().put(Integer.valueOf(request.officeId), Integer.valueOf(level));
/* 68 */     militaryOfficeComponent.setOfficeList(militaryOfficeComponent.getOfficeList());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 78 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 79 */     if (0L != groupMemberComponent.getId()) {
/* 80 */       GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 81 */       GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 82 */       if (null != groupEntity) {
/* 83 */         groupService.removeOffices(groupEntity, playerSession.getPlayer().getPlayerId());
/*    */       }
/*    */     } 
/* 86 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 87 */     if (null != taskComponent) {
/* 88 */       taskComponent.refreshSchedule(TaskType.TotalMilitaryResearch, 0, 0L);
/*    */     }
/*    */     
/* 91 */     LogUtil.errorLog(new Object[] { "ResearchEndProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), militaryOfficeComponent.getOfficeList().toString(), militaryOfficeComponent.getTypeMap().toString() });
/* 92 */     AttrUpdateUtil.refreshMilitary(playerSession);
/* 93 */     ((ResearchEndResponse)this.response).officeId = request.officeId;
/* 94 */     ((ResearchEndResponse)this.response).currJobId = militaryOfficeComponent.getCurrJobId();
/* 95 */     ((ResearchEndResponse)this.response).level = ((Integer)militaryOfficeComponent.getOfficeList().getOrDefault(Integer.valueOf(request.officeId), Integer.valueOf(0))).intValue();
/* 96 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\ResearchEndProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */