/*     */ package com.linlongyx.sanguo.webgame.processors;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossBattleRankProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossBattleResourceNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossLogoutProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossOfflineNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossRewardNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossTeam.CrossTeamFighterChangeNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.fight.CrossFightRecordProcessor;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossExitBattleRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLoginRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLogoutRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordRequest;
/*     */ 
/*     */ public enum MsgProcessorRegister {
/*  17 */   CrossLogin((short)11302, CrossLoginProcessor.class, CrossLoginRequest.class),
/*     */ 
/*     */   
/*  20 */   CrossLogout((short)11303, CrossLogoutProcessor.class, CrossLogoutRequest.class),
/*     */ 
/*     */   
/*  23 */   CrossOfflineNotice((short)11304, CrossOfflineNoticeProcessor.class, CrossOfflineNoticeRequest.class),
/*     */ 
/*     */   
/*  26 */   CrossSyncPos((short)11305, CrossSyncPosProcessor.class, CrossSyncPosRequest.class),
/*     */ 
/*     */   
/*  29 */   CrossEnterBattle((short)11306, CrossEnterBattleProcessor.class, CrossEnterBattleRequest.class),
/*     */ 
/*     */   
/*  32 */   CrossExitBattle((short)11309, CrossExitBattleProcessor.class, CrossExitBattleRequest.class),
/*     */ 
/*     */   
/*  35 */   CrossBattleRebornNotice((short)11307, CrossBattleRebornNoticeProcessor.class, CrossBattleRebornNoticeRequest.class),
/*     */ 
/*     */   
/*  38 */   CrossBattleCollect((short)11308, CrossBattleCollectProcessor.class, CrossBattleCollectRequest.class),
/*     */ 
/*     */   
/*  41 */   CrossCreateTeam((short)11501, CrossCreateTeamProcessor.class, CrossCreateTeamRequest.class),
/*     */ 
/*     */   
/*  44 */   CrossJoinTeam((short)11503, CrossJoinTeamProcessor.class, CrossJoinTeamRequest.class),
/*     */ 
/*     */   
/*  47 */   CrossKickoutTeam((short)11507, CrossKickoutTeamProcessor.class, CrossKickoutTeamRequest.class),
/*     */ 
/*     */   
/*  50 */   CrossLeaveTeam((short)11502, CrossLeaveTeamProcessor.class, CrossLeaveTeamRequest.class),
/*     */ 
/*     */   
/*  53 */   CrossSendFighter((short)11508, CrossSendFighterProcessor.class, CrossSendFighterRequest.class),
/*     */ 
/*     */   
/*  56 */   CrossTeamFighterChangeNotice((short)11504, CrossTeamFighterChangeNoticeProcessor.class, CrossTeamFighterChangeNoticeRequest.class),
/*     */ 
/*     */   
/*  59 */   CrossTeamKickoutNotice((short)11505, CrossTeamKickoutNoticeProcessor.class, CrossTeamKickoutNoticeRequest.class),
/*     */ 
/*     */   
/*  62 */   CrossTeamPlayerDataNotice((short)11506, CrossTeamPlayerDataNoticeProcessor.class, CrossTeamPlayerDataNoticeRequest.class),
/*     */ 
/*     */   
/*  65 */   CrossFightRecord((short)10203, CrossFightRecordProcessor.class, CrossFightRecordRequest.class),
/*     */ 
/*     */   
/*  68 */   CrossBattleCollectReward((short)11310, CrossBattleCollectRewardProcessor.class, CrossBattleCollectRewardRequest.class),
/*     */ 
/*     */   
/*  71 */   CrossRewardNotice((short)11311, CrossRewardNoticeProcessor.class, CrossRewardNoticeRequest.class),
/*     */ 
/*     */   
/*  74 */   CrossChatByChannel((short)11312, CrossChatByChannelProcessor.class, CrossChatByChannelRequest.class),
/*     */ 
/*     */   
/*  77 */   CrossTeamDismissNotice((short)11509, CrossTeamDismissNoticeProcessor.class, CrossTeamDismissNoticeRequest.class),
/*     */ 
/*     */   
/*  80 */   CrossBattleCollectState((short)11313, CrossBattleCollectStateProcessor.class, CrossBattleCollectStateRequest.class),
/*     */ 
/*     */   
/*  83 */   CrossBattleResourceNotice((short)11314, CrossBattleResourceNoticeProcessor.class, CrossBattleResourceNoticeRequest.class),
/*     */ 
/*     */   
/*  86 */   CrossBattleRank((short)11316, CrossBattleRankProcessor.class, CrossBattleRankRequest.class),
/*     */ 
/*     */   
/*  89 */   CrossTeamListInfo((short)11510, CrossTeamListInfoProcessor.class, CrossTeamListInfoRequest.class);
/*     */   
/*     */   private short msgCode;
/*     */   
/*     */   private Class processor;
/*     */   
/*     */   private Class request;
/*     */   private boolean isClose;
/*     */   
/*     */   MsgProcessorRegister(short msgCode, Class processor, Class request) {
/*  99 */     this.msgCode = msgCode;
/* 100 */     this.processor = processor;
/* 101 */     this.request = request;
/* 102 */     this.isClose = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getMsgCode() {
/* 107 */     return this.msgCode;
/*     */   }
/*     */   
/*     */   private boolean isClose() {
/* 111 */     return this.isClose;
/*     */   }
/*     */   
/*     */   private void setClose(boolean isClose) {
/* 115 */     this.isClose = isClose;
/*     */   }
/*     */   
/*     */   public Class getMsgProcessor() {
/* 119 */     return this.processor;
/*     */   }
/*     */   
/*     */   public Class getRequest() {
/* 123 */     return this.request;
/*     */   }
/*     */   
/*     */   public void setRequest(Class request) {
/* 127 */     this.request = request;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\MsgProcessorRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */