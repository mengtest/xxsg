/*     */ package com.linlongyx.sanguo.webgame.processors.Invitation;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.InviteBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShareListBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.InvitationParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.GetInvitationRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.GetInvitationRewardResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class GetInvitationRewardProcessor
/*     */   extends ProcessorBase<GetInvitationRewardRequest, GetInvitationRewardResponse> {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new GetInvitationRewardResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GetInvitationRewardRequest request) {
/*  32 */     InvitationComponent invitationComponent = (InvitationComponent)playerSession.getPlayer().createIfNotExist(InvitationComponent.class);
/*  33 */     InvitationParameter invitationParameter = (InvitationParameter)ParameterConstant.getParameter(36);
/*  34 */     if (request.type == 1) {
/*  35 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3601)) {
/*  36 */         return 10061;
/*     */       }
/*  38 */       ShareListBean shareListBean = (ShareListBean)JsonTableService.getJsonData(request.value, ShareListBean.class);
/*  39 */       if (null == shareListBean) {
/*  40 */         return 15007;
/*     */       }
/*  42 */       if (invitationComponent.getLevelNumMap().containsKey(Integer.valueOf(request.value))) {
/*  43 */         return 15012;
/*     */       }
/*  45 */       int num = 0;
/*  46 */       for (WxPlayerInfo wxPlayerInfo : invitationComponent.getWxInfo()) {
/*  47 */         if (wxPlayerInfo.level >= invitationParameter.getInviteLevelNum()) {
/*  48 */           num++;
/*     */         }
/*     */       } 
/*  51 */       if (num < shareListBean.getPlayerLevel()) {
/*  52 */         return 15003;
/*     */       }
/*  54 */       invitationComponent.getLevelNumMap().put(Integer.valueOf(request.value), Integer.valueOf(TimeUtil.currentTime()));
/*  55 */       invitationComponent.setLevelNumMap(invitationComponent.getLevelNumMap());
/*  56 */       FinanceUtil.reward(FinanceUtil.transform(shareListBean.getReward()), playerSession.getPlayer(), ResourceEvent.InviteNum, true);
/*  57 */     } else if (request.type == 2) {
/*     */       
/*  59 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3604)) {
/*  60 */         return 10061;
/*     */       }
/*  62 */       WxPlayerInfo wxPlayerInfo1 = null;
/*  63 */       long charge = 0L;
/*  64 */       long num = 0L;
/*  65 */       synchronized (invitationComponent) {
/*  66 */         for (WxPlayerInfo wxPlayerInfo : invitationComponent.getWxInfo()) {
/*  67 */           if (wxPlayerInfo.playerId == request.playerId && wxPlayerInfo.totalCharge > 0L) {
/*  68 */             charge = wxPlayerInfo.totalCharge;
/*  69 */             wxPlayerInfo1 = wxPlayerInfo;
/*  70 */             wxPlayerInfo.totalCharge = 0L;
/*     */             break;
/*     */           } 
/*     */         } 
/*  74 */         if (null == wxPlayerInfo1) {
/*  75 */           return 15008;
/*     */         }
/*  77 */         num = (long)Math.ceil(charge * invitationParameter.getInviteRate() / 10000.0D);
/*     */         
/*  79 */         invitationComponent.setWxInfo(invitationComponent.getWxInfo());
/*     */       } 
/*  81 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  82 */       Reward reward = new Reward();
/*  83 */       reward.type = 1;
/*  84 */       reward.id = CurrencyType.InviteCoin.getType();
/*  85 */       reward.num = num;
/*  86 */       rewards.add(reward);
/*     */       
/*  88 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.InviteCharge, true);
/*  89 */     } else if (request.type == 3) {
/*     */       
/*  91 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3602)) {
/*  92 */         return 10061;
/*     */       }
/*  94 */       InviteBean inviteBean = (InviteBean)JsonTableService.getJsonData(request.value, InviteBean.class);
/*  95 */       if (null == inviteBean) {
/*  96 */         return 15007;
/*     */       }
/*  98 */       if (invitationComponent.getNormalRewards().indexOf(Integer.valueOf(request.value)) >= 0) {
/*  99 */         return 15012;
/*     */       }
/* 101 */       FinanceUtil.reward(FinanceUtil.transform(inviteBean.getReward()), playerSession.getPlayer(), ResourceEvent.NormalInvite, true);
/* 102 */       invitationComponent.getNormalRewards().add(Integer.valueOf(request.value));
/* 103 */       invitationComponent.setNormalRewards(invitationComponent.getNormalRewards());
/* 104 */     } else if (request.type == 4) {
/*     */       
/* 106 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3603)) {
/* 107 */         return 10061;
/*     */       }
/* 109 */       if (invitationComponent.getNumMap().containsKey(Long.valueOf(request.playerId))) {
/* 110 */         return 15012;
/*     */       }
/* 112 */       WxPlayerInfo wxPlayerInfo1 = null;
/* 113 */       for (WxPlayerInfo wxPlayerInfo : invitationComponent.getWxInfo()) {
/* 114 */         if (wxPlayerInfo.playerId == request.playerId && wxPlayerInfo.level >= invitationParameter.getLevelCount()) {
/* 115 */           wxPlayerInfo1 = wxPlayerInfo;
/*     */           break;
/*     */         } 
/*     */       } 
/* 119 */       if (null == wxPlayerInfo1) {
/* 120 */         return 15008;
/*     */       }
/* 122 */       invitationComponent.getNumMap().put(Long.valueOf(request.playerId), Integer.valueOf(TimeUtil.currentTime()));
/* 123 */       invitationComponent.setNumMap(invitationComponent.getNumMap());
/* 124 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 125 */       Reward reward = new Reward();
/* 126 */       reward.type = 1;
/* 127 */       reward.id = CurrencyType.InviteCoin.getType();
/* 128 */       reward.num = invitationParameter.getInviteCount();
/* 129 */       rewards.add(reward);
/* 130 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.InviteLevel, true);
/*     */     } 
/* 132 */     ((GetInvitationRewardResponse)this.response).type = request.type;
/* 133 */     ((GetInvitationRewardResponse)this.response).value = request.value;
/* 134 */     ((GetInvitationRewardResponse)this.response).playerId = request.playerId;
/* 135 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\Invitation\GetInvitationRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */