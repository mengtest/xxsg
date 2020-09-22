/*     */ package com.linlongyx.sanguo.webgame.processors.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.PlayerCurrency;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MainTaskBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskCompleteRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskCompleteResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class TaskCompleteProcessor extends ProcessorBase<TaskCompleteRequest, TaskCompleteResponse> {
/*     */   protected void initResponse() {
/*  32 */     this.response = (ResponseBase)new TaskCompleteResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TaskCompleteRequest request) {
/*  37 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  38 */     int oldId = taskComponent.getId();
/*  39 */     ((TaskCompleteResponse)this.response).taskId = oldId;
/*     */     
/*  41 */     MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(taskComponent.getId(), MainTaskBean.class);
/*  42 */     if (mainTaskBean == null || taskComponent.getSchedule() < mainTaskBean.getNum()) {
/*  43 */       return 12201;
/*     */     }
/*  45 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  46 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  47 */     if (!MContext.getPlatform().equals("weixin")) {
/*  48 */       if (playerComponent.getLevel() < mainTaskBean.getLevelLimit()) {
/*  49 */         return 12202;
/*     */       }
/*     */     }
/*  52 */     else if (MContext.getServerIdInt() > loginParameter.getLimitServer() && 
/*  53 */       playerComponent.getLevel() < mainTaskBean.getLevelLimit()) {
/*  54 */       return 12202;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     MainTaskBean nextTaskBean = (MainTaskBean)JsonTableService.getJsonData(mainTaskBean.getNextId(), MainTaskBean.class);
/*  64 */     if (null != nextTaskBean) {
/*  65 */       taskComponent.setId(nextTaskBean.getId());
/*  66 */       ((TaskCompleteResponse)this.response).taskId = taskComponent.getId();
/*     */     } else {
/*  68 */       ((TaskCompleteResponse)this.response).taskId = mainTaskBean.getNextId();
/*     */     } 
/*     */ 
/*     */     
/*  72 */     FinanceUtil.reward(FinanceUtil.transform(mainTaskBean.getReward()), playerSession.getPlayer(), ResourceEvent.TaskReward, true);
/*     */ 
/*     */     
/*  75 */     if (oldId == loginParameter.getDefaultTaskId()) {
/*  76 */       String title = LanguageConstant.getLanguage(103);
/*  77 */       String content = LanguageConstant.getLanguage(104);
/*  78 */       MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), loginParameter.getWelcomeReward(), title, content);
/*     */       
/*  80 */       sendPlatFormChargeMail(playerSession, playerComponent, loginParameter);
/*     */     } 
/*     */     
/*  83 */     LogUtil.gameLog(LogType.TASK, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(oldId), Integer.valueOf(1), TimeUtil.getFormatDate() });
/*  84 */     LogUtil.gameLog(LogType.TASK, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(taskComponent.getId()), Integer.valueOf(-1), TimeUtil.getFormatDate() });
/*  85 */     return 0;
/*     */   }
/*     */   
/*     */   public void process(IPlayerSession playerSession, TaskCompleteRequest request) throws Exception {
/*  89 */     short retCode = handleRequest(playerSession, request);
/*  90 */     ((TaskCompleteResponse)this.response).setRetCode(retCode);
/*     */ 
/*     */     
/*  93 */     if (retCode == 12201) {
/*  94 */       ((TaskCompleteResponse)this.response).setRetCode((short)0);
/*  95 */       playerSession.sendMessage(this.response);
/*     */       return;
/*     */     } 
/*  98 */     playerSession.sendMessage(this.response);
/*  99 */     if (retCode != 0) {
/*     */       return;
/*     */     }
/* 102 */     FunctionOpenConstant.checkFunctionOpen(playerSession);
/* 103 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 104 */     taskComponent.setSchedule(0L);
/*     */     
/* 106 */     TaskUtil.updateSchedule(playerSession);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlatFormChargeMail(IPlayerSession playerSession, PlayerComponent playerComponent, LoginParameter loginParameter) {
/* 118 */     long ccy = PlayerCurrency.get(playerComponent, CurrencyType.PlatformCharge);
/* 119 */     if (ccy > 0L) {
/*     */       
/* 121 */       long addNum = ccy * loginParameter.getRebateMultiple() / 10000L;
/*     */ 
/*     */       
/* 124 */       long addTotalCharge = ccy / 100L * loginParameter.getVipRebateMultiple() / 10000L;
/*     */       
/* 126 */       ArrayList<Reward> rewards = new ArrayList<>();
/*     */       
/* 128 */       Reward reward = new Reward();
/* 129 */       reward.type = 1;
/* 130 */       reward.id = CurrencyType.PlatformCharge.getType();
/* 131 */       reward.num = ccy;
/* 132 */       rewards.add(reward);
/*     */       
/* 134 */       Reward reward2 = new Reward();
/* 135 */       reward2.type = 1;
/* 136 */       reward2.id = CurrencyType.CCY.getType();
/* 137 */       reward2.num = addNum;
/* 138 */       rewards.add(reward2);
/*     */       
/* 140 */       Reward reward3 = new Reward();
/* 141 */       reward3.type = 1;
/* 142 */       reward3.id = CurrencyType.TotalCharge.getType();
/* 143 */       reward3.num = addTotalCharge;
/* 144 */       rewards.add(reward3);
/* 145 */       String title1 = LanguageConstant.getLanguage(112);
/* 146 */       String content2 = LanguageConstant.getLanguage(113);
/* 147 */       MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), rewards, title1, content2);
/* 148 */       PlayerCurrency.set(playerComponent, CurrencyType.PlatformCharge, 0L);
/* 149 */       LogUtils.errorLog(new Object[] { "PlayerBGPReard", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(ccy), Long.valueOf(addNum) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskCompleteProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */