/*     */ package com.linlongyx.sanguo.webgame.processors.offices;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MilitaryParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpOthersRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpOthersResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class HelpOthersProcessor extends ProcessorBase<HelpOthersRequest, HelpOthersResponse> {
/*     */   protected void initResponse() {
/*  26 */     this.response = (ResponseBase)new HelpOthersResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, HelpOthersRequest request) {
/*  31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80))
/*  32 */       return 10061; 
/*  33 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  34 */     if (0L == groupMemberComponent.getId()) {
/*  35 */       return 11101;
/*     */     }
/*  37 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/*  38 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*  39 */     if (null == groupEntity) {
/*  40 */       return 11101;
/*     */     }
/*     */     
/*  43 */     MilitaryParameter militaryParameter = (MilitaryParameter)ParameterConstant.getParameter(51);
/*  44 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/*  45 */     if (militaryOfficeComponent.getOfficeHelpTimes() >= militaryParameter.getHelpTimes()) {
/*  46 */       return 13004;
/*     */     }
/*  48 */     for (Iterator<Long> iterator = request.list.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/*  49 */       if (!groupEntity.getOfficeList().containsKey(Long.valueOf(playerId))) {
/*     */         continue;
/*     */       }
/*  52 */       if (playerId == playerSession.getPlayer().getPlayerId()) {
/*     */         continue;
/*     */       }
/*  55 */       if (!groupEntity.getMemberList().contains(Long.valueOf(playerId))) {
/*     */         continue;
/*     */       }
/*  58 */       KeyValue keyValue = (KeyValue)groupEntity.getOfficeList().get(Long.valueOf(playerId));
/*  59 */       int count = Integer.parseInt(keyValue.valueStr);
/*  60 */       if (count >= militaryParameter.getLimitHelp()) {
/*     */         continue;
/*     */       }
/*  63 */       if (militaryOfficeComponent.getOfficeHelpTimes() >= militaryParameter.getHelpTimes()) {
/*     */         continue;
/*     */       }
/*  66 */       if (militaryOfficeComponent.getHelpList().containsKey(Long.valueOf(playerId))) {
/*  67 */         KeyValue kv = (KeyValue)militaryOfficeComponent.getHelpList().get(Long.valueOf(playerId));
/*  68 */         if (kv.key == keyValue.key && kv.value == keyValue.value) {
/*  69 */           if (request.list.size() == 1) {
/*  70 */             return 22806;
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } 
/*  76 */       MilitaryOfficeComponent targetMilitaryOfficeComponent1 = (MilitaryOfficeComponent)LookUpService.getComponent(playerId, MilitaryOfficeComponent.class);
/*  77 */       if (null == targetMilitaryOfficeComponent1) {
/*  78 */         return 11808;
/*     */       }
/*  80 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData((int)keyValue.key, MilitaryBean.class);
/*  81 */       MilitaryBean.LevelBean levelBean = null;
/*  82 */       if (null != militaryBean.getLevel().get(Integer.valueOf((int)keyValue.value))) {
/*  83 */         levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf((int)keyValue.value));
/*     */       }
/*     */       
/*  86 */       synchronized (targetMilitaryOfficeComponent1) {
/*  87 */         if (null != levelBean) {
/*  88 */           int addTime = MilitaryUtil.getTime(targetMilitaryOfficeComponent1.getPlayerId(), MilitaryInsType.MilitaryHelp.getType(), levelBean.getTime());
/*  89 */           targetMilitaryOfficeComponent1.setEndTime(targetMilitaryOfficeComponent1.getEndTime() - (int)(levelBean.getTime() * militaryParameter.getRefuseTime() / 10000.0D) - addTime);
/*  90 */           targetMilitaryOfficeComponent1.setCurrJobHelped(targetMilitaryOfficeComponent1.getCurrJobHelped() + 1);
/*  91 */           targetMilitaryOfficeComponent1.saveAllToDB();
/*     */         } 
/*     */       } 
/*  94 */       count++;
/*  95 */       keyValue.valueStr = count + "";
/*  96 */       if (count >= militaryParameter.getLimitHelp()) {
/*  97 */         groupService.removeOffices(groupEntity, playerId);
/*     */       } else {
/*  99 */         groupService.updateOffices(groupEntity, playerId, keyValue);
/*     */       } 
/* 101 */       militaryOfficeComponent.setOfficeHelpTimes(militaryOfficeComponent.getOfficeHelpTimes() + 1);
/* 102 */       militaryOfficeComponent.getHelpList().put(Long.valueOf(playerId), keyValue);
/* 103 */       militaryOfficeComponent.setHelpList(militaryOfficeComponent.getHelpList());
/* 104 */       FinanceUtil.reward(militaryParameter.getAssistReward(), playerSession.getPlayer(), ResourceEvent.MilitaryHelpReward, true);
/* 105 */       ((HelpOthersResponse)this.response).list.add(keyValue);
/* 106 */       ((HelpOthersResponse)this.response).helpTimes = militaryOfficeComponent.getOfficeHelpTimes(); }
/*     */     
/* 108 */     MilitaryUtil.pushHelp(groupEntity, ((HelpOthersResponse)this.response).list);
/* 109 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\HelpOthersProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */