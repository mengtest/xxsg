/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AssistInBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AssistInBattleResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class AssistInBattleProcessor
/*    */   extends ProcessorBase<AssistInBattleRequest, AssistInBattleResponse> {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new AssistInBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AssistInBattleRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 33)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 34 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/* 35 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 36 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*    */     
/* 38 */     if (request.list.size() != partnerParameter.getMaxAssist()) {
/* 39 */       return 13304;
/*    */     }
/* 41 */     int canBattleNum = 0;
/* 42 */     int reallyCount = 0;
/* 43 */     ArrayList<Long> partnerList = new ArrayList<>(); Iterator<Integer> iterator1;
/* 44 */     for (iterator1 = partnerParameter.getAssistPos().values().iterator(); iterator1.hasNext(); ) { int level = ((Integer)iterator1.next()).intValue();
/* 45 */       if (playerComponent.getLevel() >= level) {
/* 46 */         canBattleNum++;
/*    */       } }
/*    */ 
/*    */     
/* 50 */     for (iterator1 = request.list.iterator(); iterator1.hasNext(); ) { long pid = ((Long)iterator1.next()).longValue();
/* 51 */       if (pid == 0L) {
/*    */         continue;
/*    */       }
/* 54 */       if (playerComponent.getFighters().indexOf(Long.valueOf(pid)) >= 0) {
/* 55 */         return 13301;
/*    */       } }
/*    */ 
/*    */     
/* 59 */     for (int i = 0; i < partnerParameter.getMaxAssist(); i++) {
/* 60 */       long pId = ((Long)request.list.get(i)).longValue();
/* 61 */       if (pId != 0L) {
/*    */ 
/*    */         
/* 64 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pId);
/* 65 */         if (null == partnerEntity) {
/* 66 */           return 13303;
/*    */         }
/* 68 */         if (partnerList.indexOf(Long.valueOf(pId)) >= 0) {
/* 69 */           return 11808;
/*    */         }
/* 71 */         partnerList.add(Long.valueOf(pId));
/* 72 */         reallyCount++;
/*    */       } 
/* 74 */     }  if (reallyCount > canBattleNum) {
/* 75 */       return 13304;
/*    */     }
/* 77 */     generalComponent.getAssistInBattle().clear();
/* 78 */     for (Iterator<Long> iterator = request.list.iterator(); iterator.hasNext(); ) { long pId = ((Long)iterator.next()).longValue();
/* 79 */       generalComponent.getAssistInBattle().add(Long.valueOf(pId)); }
/*    */     
/* 81 */     generalComponent.setAssistInBattle(generalComponent.getAssistInBattle());
/* 82 */     ((AssistInBattleResponse)this.response).list = new ArrayList(generalComponent.getAssistInBattle());
/* 83 */     AttrUpdateUtil.refreshAssist(playerSession);
/* 84 */     AttrUpdateUtil.refreshPartner(playerSession);
/* 85 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 86 */     taskComponent.refreshSchedule(TaskType.AssistPartner, 0, 0L);
/* 87 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\AssistInBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */