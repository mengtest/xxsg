/*     */ package com.linlongyx.sanguo.webgame.processors.tower;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.TabNotice;
/*     */ import com.linlongyx.sanguo.webgame.constant.TowerCountType;
/*     */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.TabNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TowerUtil
/*     */ {
/*     */   public static byte checkCodition(IPlayer player, TowerBean towerBean, int next, long hp) {
/*  34 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  35 */     byte result = 1;
/*  36 */     if (towerBean.getCondition() == TowerCountType.allSurvival.getType()) {
/*  37 */       if (PlayerUtil.getBattleNum(playerComponent) != next) {
/*  38 */         result = 0;
/*     */       }
/*  40 */     } else if (towerBean.getCondition() == TowerCountType.surp60.getType()) {
/*  41 */       if (totalHp(player) * towerBean.getVictory() / 10000.0D >= hp) {
/*  42 */         result = 0;
/*     */       }
/*  44 */     } else if (towerBean.getCondition() == TowerCountType.deathLessTwo.getType() && 
/*  45 */       PlayerUtil.getBattleNum(playerComponent) - next > towerBean.getVictory()) {
/*  46 */       result = 0;
/*     */     } 
/*     */     
/*  49 */     return result;
/*     */   }
/*     */   
/*     */   public static long totalHp(IPlayer player) {
/*  53 */     long hp = 0L;
/*  54 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  55 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  56 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  57 */       if (pid == -1L) {
/*  58 */         hp += playerComponent.getPlayerAttrUp().getAttrByType(AttributeType.HP.getType(), -1L); continue;
/*  59 */       }  if (pid != 0L) {
/*  60 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  61 */         if (null != partnerEntity) {
/*  62 */           hp += partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.HP.getType(), pid);
/*     */         }
/*     */       }  }
/*     */     
/*  66 */     return hp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getToHp(List<IFighter> fighters) {
/*  75 */     long hp = 0L;
/*  76 */     for (IFighter iFighter : fighters) {
/*  77 */       hp += iFighter.getHP();
/*     */     }
/*  79 */     return hp;
/*     */   }
/*     */   
/*     */   public static void addTowerDefRecord(int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) {
/*  83 */     TowerComponent towerComponent = (TowerComponent)LookUpService.getComponent(playerId, TowerComponent.class);
/*  84 */     if (towerComponent == null) {
/*     */       return;
/*     */     }
/*  87 */     towerComponent.addRecord(DestinyUtil.tranform(type, time, layerId, win, pkPlayerId, pkPlayerName));
/*  88 */     if (!win) {
/*  89 */       if (LookUpService.isOnline(playerId)) {
/*  90 */         sendTowerOwnerTabNotice(playerId, false);
/*  91 */         towerComponent.setNeedSend((byte)0);
/*     */       } else {
/*  93 */         towerComponent.setNeedSend((byte)1);
/*     */       } 
/*     */     }
/*  96 */     if (!LookUpService.isOnline(playerId)) {
/*  97 */       towerComponent.saveToDB();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void sendTowerOwnerTabNotice(long playerId, boolean isEnterGame) {
/* 102 */     if (isEnterGame) {
/* 103 */       TowerComponent towerComponent = (TowerComponent)LookUpService.getComponent(playerId, TowerComponent.class);
/* 104 */       if (towerComponent != null && towerComponent.getNeedSend() == 1) {
/* 105 */         towerComponent.setNeedSend((byte)0);
/* 106 */         TabNoticeResponse resp = new TabNoticeResponse();
/* 107 */         resp.sys = TabNotice.towerOwner.getSys();
/* 108 */         resp.index = TabNotice.towerOwner.getIndex();
/* 109 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */       } 
/*     */     } else {
/* 112 */       TabNoticeResponse resp = new TabNoticeResponse();
/* 113 */       resp.sys = TabNotice.towerOwner.getSys();
/* 114 */       resp.index = TabNotice.towerOwner.getIndex();
/* 115 */       LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\tower\TowerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */