/*     */ package com.linlongyx.sanguo.webgame.processors.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PlatformRewardParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskPlatformRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskPlatformRewardResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskPlatformRewardProcessor
/*     */   extends ProcessorBase<TaskPlatformRewardRequest, TaskPlatformRewardResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new TaskPlatformRewardResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TaskPlatformRewardRequest request) {
/*  32 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  33 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  34 */     PlatformRewardParameter platformRewardParameter = (PlatformRewardParameter)ParameterConstant.getParameter(72);
/*  35 */     if (request.type == 1) {
/*  36 */       if (!taskComponent.isRealName()) {
/*  37 */         taskComponent.setRealName(true);
/*  38 */         FinanceUtil.reward(loginParameter.getRealNameReward(), playerSession.getPlayer(), ResourceEvent.RealName, true);
/*  39 */         LogUtils.errorLog(new Object[] { "task realName", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  41 */     } else if (request.type == 2) {
/*  42 */       if (taskComponent.getFollow() == 0) {
/*  43 */         taskComponent.setFollow(1);
/*  44 */         String title = LanguageConstant.getLanguage(110);
/*  45 */         String content = LanguageConstant.getLanguage(111);
/*  46 */         MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), loginParameter.getFollowReward(), title, content);
/*  47 */         LogUtils.errorLog(new Object[] { "task Follow", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  49 */     } else if (request.type == 3) {
/*  50 */       if (PlayerUtil.getPlatformType() != 1) {
/*  51 */         return 10095;
/*     */       }
/*  53 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type))) {
/*  54 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/*  55 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/*  56 */         FinanceUtil.reward(loginParameter.getWeixinReward(), playerSession.getPlayer(), ResourceEvent.WeixinGuide, true);
/*  57 */         LogUtils.errorLog(new Object[] { "task weixinGuide", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  59 */     } else if (request.type == 4) {
/*  60 */       if (PlayerUtil.getPlatformType() != 1) {
/*  61 */         return 10095;
/*     */       }
/*  63 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type))) {
/*  64 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/*  65 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/*  66 */         FinanceUtil.reward(loginParameter.getWeixinFollowReward(), playerSession.getPlayer(), ResourceEvent.WeixinFollow, true);
/*  67 */         LogUtils.errorLog(new Object[] { "task weixinFollow", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  69 */     } else if (request.type == 5) {
/*  70 */       if (PlayerUtil.getActPlatform() != 3) {
/*  71 */         return 10095;
/*     */       }
/*  73 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type))) {
/*  74 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/*  75 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/*  76 */         FinanceUtil.reward(loginParameter.getAwyBindReward(), playerSession.getPlayer(), ResourceEvent.AwyBindReward, true);
/*  77 */         LogUtils.errorLog(new Object[] { "task awyshouji", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  79 */     } else if (request.type == 6) {
/*  80 */       if (PlayerUtil.getActPlatform() != 4) {
/*  81 */         return 10095;
/*     */       }
/*  83 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type)) || ((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() == 0) {
/*  84 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/*  85 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/*  86 */         FinanceUtil.reward(platformRewardParameter.getXinYueRewards(), playerSession.getPlayer(), ResourceEvent.XinYueReward, true);
/*  87 */         LogUtils.errorLog(new Object[] { "task xinyue", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*  89 */     } else if (request.type == 7) {
/*  90 */       UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/*  91 */       if (PlayerUtil.getActPlatform() != 4 && userComponent.getUid().equals("pcwb")) {
/*  92 */         return 10095;
/*     */       }
/*  94 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type)) || ((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() == 0) {
/*  95 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/*  96 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/*  97 */         FinanceUtil.reward(loginParameter.getPcwbReward(), playerSession.getPlayer(), ResourceEvent.PcWbReward, true);
/*  98 */         LogUtils.errorLog(new Object[] { "task pcwb", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/* 100 */     } else if (request.type == 8) {
/* 101 */       UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/* 102 */       if (PlayerUtil.getActPlatform() != 4) {
/* 103 */         return 10095;
/*     */       }
/* 105 */       if (!taskComponent.getPlatformReward().containsKey(Integer.valueOf(request.type)) || ((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() == 0) {
/* 106 */         taskComponent.getPlatformReward().put(Integer.valueOf(request.type), Integer.valueOf(((Integer)taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(request.type), Integer.valueOf(0))).intValue() + 1));
/* 107 */         taskComponent.setPlatformReward(taskComponent.getPlatformReward());
/* 108 */         FinanceUtil.reward(loginParameter.getQqwbReward(), playerSession.getPlayer(), ResourceEvent.QQWbReward, true);
/* 109 */         LogUtils.errorLog(new Object[] { "task qqwb", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */       } 
/*     */     } 
/* 112 */     ((TaskPlatformRewardResponse)this.response).type = request.type;
/* 113 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskPlatformRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */