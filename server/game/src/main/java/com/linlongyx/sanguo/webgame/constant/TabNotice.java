/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.TabNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum TabNotice
/*    */ {
/* 18 */   worldBoss(302, 1, "世界boss"),
/* 19 */   crossBattle(81, 1, "三国争霸"),
/* 20 */   crossRace(28, 1, "跨服段位赛"),
/* 21 */   towerOwner(67, 1, "塔主争夺"),
/* 22 */   team(37, 1, "组队副本双倍时间");
/*    */   
/*    */   private int sys;
/*    */   
/*    */   private int index;
/*    */   private String name;
/*    */   
/*    */   TabNotice(int sys, int index, String name) {
/* 30 */     this.sys = sys;
/* 31 */     this.index = index;
/* 32 */     this.name = name;
/*    */   }
/*    */   
/*    */   public int getSys() {
/* 36 */     return this.sys;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 40 */     return this.index;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   
/*    */   public static void sendSysOpenTabNotice(long playerId) {
/* 48 */     IPlayer player = LookUpService.getByPlayerId(playerId);
/* 49 */     if (player != null) {
/* 50 */       if (FunctionOpenConstant.isFunctionOpen(player.getSession(), 302) && 
/* 51 */         !WorldBossUtil.isClose()) {
/* 52 */         TabNoticeResponse resp = new TabNoticeResponse();
/* 53 */         resp.sys = worldBoss.getSys();
/* 54 */         resp.index = worldBoss.getIndex();
/* 55 */         player.getSession().sendMessage((ResponseBase)resp);
/*    */       } 
/*    */       
/* 58 */       if (FunctionOpenConstant.isFunctionOpen(player.getSession(), 28) && 
/* 59 */         CrossUtil.isRaceOpen("TabNotice::sendSysOpenTabNotice")) {
/* 60 */         TabNoticeResponse resp = new TabNoticeResponse();
/* 61 */         resp.sys = crossRace.getSys();
/* 62 */         resp.index = crossRace.getIndex();
/* 63 */         player.getSession().sendMessage((ResponseBase)resp);
/*    */       } 
/*    */       
/* 66 */       if (FunctionOpenConstant.isFunctionOpen(player.getSession(), 81) && 
/* 67 */         CrossUtil.isBattleOpen("TabNotice::sendSysOpenTabNotice")) {
/* 68 */         TabNoticeResponse resp = new TabNoticeResponse();
/* 69 */         resp.sys = crossBattle.getSys();
/* 70 */         resp.index = crossBattle.getIndex();
/* 71 */         player.getSession().sendMessage((ResponseBase)resp);
/*    */       } 
/*    */       
/* 74 */       if (FunctionOpenConstant.isFunctionOpen(player.getSession(), 37)) {
/* 75 */         TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 76 */         if (teamParameter.isInDoubleTime(TimeUtil.getNowMinutes())) {
/* 77 */           TabNoticeResponse resp = new TabNoticeResponse();
/* 78 */           resp.sys = team.getSys();
/* 79 */           resp.index = team.getIndex();
/* 80 */           player.getSession().sendMessage((ResponseBase)resp);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\TabNotice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */