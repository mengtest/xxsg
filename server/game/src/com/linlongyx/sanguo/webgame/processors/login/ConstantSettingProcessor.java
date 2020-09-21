/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.QuestionnaireBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.ConstantSettingRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.ConstantSettingResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstantSettingProcessor
/*     */   extends ProcessorBase<ConstantSettingRequest, ConstantSettingResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new ConstantSettingResponse(); } protected short handleRequest(IPlayerSession playerSession, ConstantSettingRequest request) { GroupMemberComponent groupMemberComponent; GroupService groupService;
/*     */     GroupEntity groupEntity;
/*     */     Set<Integer> question;
/*     */     QuestionnaireBean questionnaireBean;
/*     */     LoginParameter loginParameter;
/*  36 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  37 */     switch (request.type) {
/*     */       case 0:
/*  39 */         extendComponent.setSpeedRadio(request.value);
/*  40 */         ((ConstantSettingResponse)this.response).value = extendComponent.getSpeedRadio();
/*     */         break;
/*     */       case 1:
/*  43 */         if (request.value == 0) {
/*  44 */           extendComponent.setChoose(false);
/*     */         } else {
/*  46 */           extendComponent.setChoose(true);
/*     */         } 
/*  48 */         ((ConstantSettingResponse)this.response).value = extendComponent.isChoose() ? 1 : 0;
/*     */         break;
/*     */       case 2:
/*  51 */         if (extendComponent.getSkipTimes() <= 0) {
/*  52 */           return 13004;
/*     */         }
/*  54 */         extendComponent.setSkipTimes(extendComponent.getSkipTimes() - 1);
/*  55 */         ((ConstantSettingResponse)this.response).value = extendComponent.getSkipTimes();
/*     */         break;
/*     */       case 3:
/*  58 */         if (request.value != 0 && request.value != 1) {
/*  59 */           return 11808;
/*     */         }
/*  61 */         groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  62 */         if (0L == groupMemberComponent.getId()) {
/*  63 */           return 11101;
/*     */         }
/*  65 */         groupService = (GroupService)MContext.getBean("groupService");
/*  66 */         groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*  67 */         if (null == groupEntity) {
/*  68 */           return 11101;
/*     */         }
/*  70 */         if (groupMemberComponent.getPosition() != 1 && groupMemberComponent.getPosition() != 2) {
/*  71 */           return 11110;
/*     */         }
/*  73 */         groupEntity.setAutoAdd(request.value);
/*  74 */         ((ConstantSettingResponse)this.response).value = request.value;
/*     */         break;
/*     */       case 4:
/*  77 */         if (PlayerUtil.getActPlatform() != 1) {
/*  78 */           return 10095;
/*     */         }
/*  80 */         question = extendComponent.getQuestionnaire();
/*  81 */         if (question.contains(Integer.valueOf(request.value))) {
/*  82 */           return 10091;
/*     */         }
/*  84 */         questionnaireBean = (QuestionnaireBean)JsonTableService.getJsonData(request.value, QuestionnaireBean.class);
/*  85 */         if (questionnaireBean == null) {
/*  86 */           return 11808;
/*     */         }
/*  88 */         extendComponent.getQuestionnaire().add(Integer.valueOf(request.value));
/*  89 */         extendComponent.setQuestionnaire(extendComponent.getQuestionnaire());
/*     */         
/*  91 */         loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */         
/*  93 */         FinanceUtil.reward(loginParameter.getQuestionReward(), playerSession.getPlayer(), ResourceEvent.Questionnaire, true);
/*  94 */         ((ConstantSettingResponse)this.response).value = request.value;
/*     */         break;
/*     */       
/*     */       case 5:
/*  98 */         extendComponent.setClientSets(request.strValue);
/*  99 */         ((ConstantSettingResponse)this.response).strValue = request.strValue;
/*     */         break;
/*     */       
/*     */       case 6:
/* 103 */         if (extendComponent.getShortCut() != 2) {
/* 104 */           extendComponent.setShortCut(request.value);
/* 105 */           if (request.value == 2) {
/*     */             
/* 107 */             LoginParameter loginParameter1 = (LoginParameter)ParameterConstant.getParameter(0);
/*     */             
/* 109 */             FinanceUtil.reward(loginParameter1.getShortCutReward(), playerSession.getPlayer(), ResourceEvent.ShortCut, true);
/*     */           } 
/* 111 */           ((ConstantSettingResponse)this.response).value = request.value;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 116 */     ((ConstantSettingResponse)this.response).type = request.type;
/* 117 */     return 0; }
/*     */ 
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\ConstantSettingProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */