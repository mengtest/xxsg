/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetAssistInBattleListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetAssistInBattleListResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class GetAssistInBattleListProcessor extends ProcessorBase<GetAssistInBattleListRequest, GetAssistInBattleListResponse> {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new GetAssistInBattleListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetAssistInBattleListRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 33)) {
/* 25 */       return 10061;
/*    */     }
/* 27 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 28 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/* 29 */     while (generalComponent.getAssistInBattle().size() < partnerParameter.getMaxAssist()) {
/* 30 */       generalComponent.getAssistInBattle().add(Long.valueOf(0L));
/*    */     }
/* 32 */     generalComponent.setAssistInBattle(generalComponent.getAssistInBattle());
/* 33 */     for (Iterator<Long> iterator = generalComponent.getAssistInBattle().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 34 */       ((GetAssistInBattleListResponse)this.response).list.add(Long.valueOf(pid)); }
/*    */     
/* 36 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 37 */     taskComponent.refreshSchedule(TaskType.AssistPartner, 0, 0L);
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\GetAssistInBattleListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */