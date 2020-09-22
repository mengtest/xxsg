/*     */ package com.linlongyx.sanguo.webgame.processors.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RuneParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneRedNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RuneItem;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuneUtil
/*     */ {
/*     */   public static RuneItem tranform(RuneBagEntity runeBagEntity) {
/*  37 */     RuneItem runeItem = new RuneItem();
/*  38 */     runeItem.mid = runeBagEntity.getMid();
/*  39 */     runeItem.itemId = runeBagEntity.getItemId();
/*  40 */     runeItem.level = runeBagEntity.getLevel();
/*  41 */     runeItem.hole = runeBagEntity.getHole();
/*  42 */     return runeItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sysList(IPlayerSession playerSession) {
/*  49 */     ArrayList<Long> list = new ArrayList<>();
/*     */     
/*  51 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/*  52 */     RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/*  53 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  54 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  55 */     ArrayList<RuneBagEntity> bags = runeBagComponent.getNotInstall();
/*     */     
/*  57 */     boolean isRed = false;
/*     */ 
/*     */ 
/*     */     
/*  61 */     if (bags.isEmpty()) {
/*     */       return;
/*     */     }
/*  64 */     RuneParameter runeParameter = (RuneParameter)ParameterConstant.getParameter(69);
/*     */     
/*  66 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  67 */       Set<Integer> types = new HashSet<>();
/*  68 */       if (pid == 0L) {
/*     */         continue;
/*     */       }
/*  71 */       boolean hashHole = false;
/*  72 */       if (pid == -1L) {
/*  73 */         for (int i = 1; i <= runeParameter.getHoleLimit(); i++) {
/*  74 */           if (playerComponent.getLevel() >= runeParameter.getHoleLevel(i) && ((Long)runeComponent.getRuneMap().getOrDefault(Integer.valueOf(i), Long.valueOf(0L))).longValue() == 0L) {
/*  75 */             hashHole = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*  79 */         for (Iterator<Long> iterator1 = runeComponent.getRuneMap().values().iterator(); iterator1.hasNext(); ) { long mid = ((Long)iterator1.next()).longValue();
/*  80 */           RuneBagEntity runeBagEntity = runeBagComponent.getEntity(mid);
/*     */           
/*  82 */           RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/*  83 */           if (null == runeBean)
/*     */             continue; 
/*  85 */           types.add(Integer.valueOf(runeBean.getType()));
/*     */           
/*  87 */           if (runeBagEntity.getLevel() < runeBean.getMlevel()) {
/*  88 */             RuneLevelBean levelBean = (RuneLevelBean)JsonTableService.getJsonData(runeBagEntity.getLevel() + 1, RuneLevelBean.class);
/*     */             
/*  90 */             BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  91 */             ArrayList<Reward> rewards = new ArrayList<>();
/*  92 */             rewards = FinanceUtil.transformRatio(levelBean.getUpCost(), runeBean.getCostCoin());
/*     */             
/*  94 */             short code = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  95 */             if (0 == code) {
/*  96 */               list.add(Long.valueOf(pid));
/*     */               break;
/*     */             } 
/*     */           }  }
/*     */       
/*     */       } else {
/* 102 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 103 */         if (null == partnerEntity) {
/*     */           continue;
/*     */         }
/* 106 */         for (int i = 1; i <= runeParameter.getHoleLimit(); i++) {
/* 107 */           if (playerComponent.getLevel() >= runeParameter.getHoleLevel(i) && ((Long)partnerEntity.getRuneMap().getOrDefault(Integer.valueOf(i), Long.valueOf(0L))).longValue() == 0L) {
/* 108 */             hashHole = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 112 */         for (Iterator<Long> iterator1 = partnerEntity.getRuneMap().values().iterator(); iterator1.hasNext(); ) { long mid = ((Long)iterator1.next()).longValue();
/* 113 */           RuneBagEntity runeBagEntity = runeBagComponent.getEntity(mid);
/*     */           
/* 115 */           RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/* 116 */           if (null == runeBean)
/*     */             continue; 
/* 118 */           types.add(Integer.valueOf(runeBean.getType()));
/*     */           
/* 120 */           if (runeBagEntity.getLevel() < runeBean.getMlevel()) {
/* 121 */             RuneLevelBean levelBean = (RuneLevelBean)JsonTableService.getJsonData(runeBagEntity.getLevel() + 1, RuneLevelBean.class);
/* 122 */             BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 123 */             ArrayList<Reward> rewards = new ArrayList<>();
/* 124 */             rewards = FinanceUtil.transformRatio(levelBean.getUpCost(), runeBean.getCostCoin());
/*     */             
/* 126 */             short code = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 127 */             if (0 == code) {
/* 128 */               list.add(Long.valueOf(pid));
/*     */               break;
/*     */             } 
/*     */           }  }
/*     */       
/*     */       } 
/* 134 */       if (list.indexOf(Long.valueOf(pid)) >= 0) {
/*     */         continue;
/*     */       }
/* 137 */       if (hashHole) {
/* 138 */         for (RuneBagEntity runeBagEntity : bags) {
/* 139 */           RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/* 140 */           if (!types.contains(Integer.valueOf(runeBean.getType()))) {
/* 141 */             isRed = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 146 */       if (hashHole && isRed) {
/* 147 */         list.add(Long.valueOf(pid));
/*     */       } }
/*     */     
/* 150 */     if (!list.isEmpty()) {
/* 151 */       sendRedNotice(playerSession, list);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendRedNotice(IPlayerSession playerSession, ArrayList<Long> list) {
/* 163 */     RuneRedNoticeResponse response = new RuneRedNoticeResponse();
/* 164 */     response.pids = new ArrayList<>(list);
/* 165 */     playerSession.sendMessage((ResponseBase)response);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */