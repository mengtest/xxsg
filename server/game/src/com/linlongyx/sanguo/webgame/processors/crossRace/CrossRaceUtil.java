/*     */ package com.linlongyx.sanguo.webgame.processors.crossRace;
/*     */ 
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CrossRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CrossRankRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRaceParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TabNotice;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.TabNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class CrossRaceUtil
/*     */ {
/*     */   public static void uploadPlayerRaceData(IPlayer player) {
/*  31 */     PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/*  32 */     if (playerComponent == null)
/*  33 */       return;  CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/*  34 */     if (playerComponent.getLevel() < crossRaceParameter.getLevel()) {
/*     */       return;
/*     */     }
/*  37 */     PlayerData playerData = CrossUtil.buildLocalPlayerData(player.getPlayerId());
/*  38 */     Fibers.createExecutorService().execute(() -> CrossUtil.playerJoinRace("CrossRaceUtil::uploadPlayerRaceData", playerData));
/*     */   }
/*     */   
/*     */   public static CrossRankBean chooseTargetBean(int point) {
/*  42 */     CrossRankBean targetBean = null;
/*  43 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(CrossRankBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  44 */       targetBean = (CrossRankBean)JsonTableService.getJsonData(id, CrossRankBean.class);
/*  45 */       if (targetBean.getScore() > point) {
/*     */         break;
/*     */       } }
/*     */     
/*  49 */     return targetBean;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void balanceOnlinePlayerRace() {
/*  54 */     int curRaceId = CrossUtil.getCurRaceId("balanceOnlinePlayerRace");
/*  55 */     for (Long playerId : LookUpService.getOnlinePlayer()) {
/*  56 */       IPlayer player = LookUpService.getByPlayerId(playerId.longValue());
/*  57 */       CrossRaceComponent crossRaceComponent = (CrossRaceComponent)player.getComponent(CrossRaceComponent.class);
/*  58 */       if (crossRaceComponent != null && 
/*  59 */         crossRaceComponent.getPoint() > 0 && crossRaceComponent.getRaceId() == curRaceId) {
/*  60 */         crossRaceComponent.setBalance((byte)1);
/*  61 */         CrossRankBean crossRankBean = chooseTargetBean(crossRaceComponent.getPoint());
/*  62 */         if (crossRankBean != null) {
/*     */           
/*  64 */           String title = LanguageConstant.getLanguage(2801);
/*  65 */           String content = LanguageConstant.getAndReplaceLanguage(2802, new String[] { crossRankBean.getDesc() });
/*  66 */           MailUtil.sendSysMail(player.getPlayerId(), FinanceUtil.transform(crossRankBean.getRankReward()), title, content);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void balancePrevRace(IPlayer player) {
/*  74 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)player.getComponent(CrossRaceComponent.class);
/*  75 */     if (crossRaceComponent == null) {
/*     */       return;
/*     */     }
/*     */     
/*  79 */     if (crossRaceComponent.getPoint() <= 0 || crossRaceComponent.getBalance() == 1) {
/*     */       return;
/*     */     }
/*  82 */     int curState = CrossUtil.getRaceState("balancePrevRace"), curRaceId = CrossUtil.getCurRaceId("balancePrevRace");
/*  83 */     if (crossRaceComponent.getBalance() == 0) {
/*     */       
/*  85 */       if (crossRaceComponent.getRaceId() > 0 && curRaceId == crossRaceComponent.getRaceId() && curState == 2) {
/*  86 */         crossRaceComponent.setBalance((byte)1);
/*  87 */         CrossRankBean crossRankBean = chooseTargetBean(crossRaceComponent.getPoint());
/*  88 */         if (crossRankBean != null) {
/*     */ 
/*     */           
/*  91 */           String title = LanguageConstant.getLanguage(2801);
/*  92 */           String content = LanguageConstant.getAndReplaceLanguage(2802, new String[] { crossRankBean.getDesc() });
/*  93 */           MailUtil.sendSysMail(player.getPlayerId(), FinanceUtil.transform(crossRankBean.getRankReward()), title, content);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       if (crossRaceComponent.getRaceId() > 0 && crossRaceComponent.getRaceId() != curRaceId) {
/*  99 */         crossRaceComponent.setBalance((byte)1);
/* 100 */         CrossRankBean crossRankBean = chooseTargetBean(crossRaceComponent.getPoint());
/*     */ 
/*     */         
/* 103 */         if (crossRankBean != null) {
/*     */           
/* 105 */           String title = LanguageConstant.getLanguage(2801);
/* 106 */           String content = LanguageConstant.getAndReplaceLanguage(2802, new String[] { crossRankBean.getDesc() });
/* 107 */           MailUtil.sendSysMail(player.getPlayerId(), FinanceUtil.transform(crossRankBean.getRankReward()), title, content);
/*     */         } 
/*     */         
/* 110 */         if (!crossRaceComponent.getJoinReward().isEmpty()) {
/* 111 */           ArrayList<Reward> rewardList = new ArrayList<>();
/* 112 */           for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)crossRaceComponent.getJoinReward().entrySet()) {
/* 113 */             if (((Integer)kv.getValue()).intValue() == 1) {
/* 114 */               crossRaceComponent.getJoinReward().put(kv.getKey(), Integer.valueOf(2));
/* 115 */               CrossRankRewardBean crossRankRewardBean = (CrossRankRewardBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), CrossRankRewardBean.class);
/* 116 */               rewardList.addAll(FinanceUtil.transform(crossRankRewardBean.getReward()));
/*     */             } 
/*     */           } 
/* 119 */           if (!rewardList.isEmpty()) {
/* 120 */             String title = LanguageConstant.getLanguage(2803);
/* 121 */             String content = LanguageConstant.getLanguage(2804);
/* 122 */             MailUtil.sendSysMail(player.getPlayerId(), rewardList, title, content);
/*     */           } 
/*     */         } 
/* 125 */         crossRaceComponent.resetRaceData(curRaceId);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean sendCrossRaceReward(long playerId, int racePoint, int rank) {
/* 132 */     LogUtil.errorLog(new Object[] { "cross race reward", Long.valueOf(playerId), Integer.valueOf(racePoint), Integer.valueOf(rank) });
/*     */     
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendCrossRaceNotice(int type) {
/* 139 */     TabNoticeResponse resp = new TabNoticeResponse();
/* 140 */     resp.sys = TabNotice.crossRace.getSys();
/* 141 */     resp.index = TabNotice.crossRace.getIndex();
/* 142 */     LookUpService.broadcast((ResponseBase)resp);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRace\CrossRaceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */