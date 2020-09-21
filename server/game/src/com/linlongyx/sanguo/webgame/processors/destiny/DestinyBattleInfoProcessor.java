/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattlePlayerData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DestinyBattleInfoProcessor
/*    */   extends ProcessorBase<DestinyBattleInfoRequest, DestinyBattleInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new DestinyBattleInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBattleInfoRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74)) {
/* 30 */       return 10061;
/*    */     }
/* 32 */     DestinyComponent destinyComponent = (DestinyComponent)playerSession.getPlayer().createIfNotExist(DestinyComponent.class);
/* 33 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/*    */     
/* 35 */     int curTime = TimeUtil.currentTime();
/* 36 */     int curHour = TimeUtil.getTimestampHour(curTime);
/* 37 */     int lastHour = (destinyComponent.getLastResetTime() == 0) ? 0 : TimeUtil.getTimestampHour(destinyComponent.getLastResetTime());
/* 38 */     int curDayTime = TimeUtil.getYearMonthDay(curTime);
/* 39 */     int lastDayTime = (destinyComponent.getLastResetTime() == 0) ? TimeUtil.getYearMonthDay(curTime) : TimeUtil.getYearMonthDay(destinyComponent.getLastResetTime());
/* 40 */     if (curDayTime != lastDayTime || (0 <= lastHour && lastHour < 12 && curHour >= 12)) {
/* 41 */       destinyComponent.setLastResetTime(curTime);
/* 42 */       destinyComponent.addRobTimes(destinyParameter.getInitRobTimes() - destinyComponent.getRobTimes());
/* 43 */       destinyComponent.refreshRecomment(destinyParameter.getPlayersSize(), true);
/* 44 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.DestinyTimes.getSys(), RedNoticeConstant.DestinyTimes.getIndex());
/*    */     
/*    */     }
/* 47 */     else if (destinyComponent.getRecommentsCacheData().isEmpty() || destinyComponent.getRecommentsCacheData().size() < destinyParameter.getPlayersSize()) {
/* 48 */       destinyComponent.refreshRecomment(destinyParameter.getPlayersSize() - destinyComponent.getBattles().size(), false);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 53 */     ArrayList<DestinyPlayerData> recomments = new ArrayList<>(destinyComponent.getRecommentsCacheData().values());
/* 54 */     if (null != recomments && !recomments.isEmpty()) {
/* 55 */       for (DestinyPlayerData destinyPlayerData : recomments) {
/* 56 */         BattlePlayerData battlePlayerData = DestinyUtil.tranform(destinyPlayerData);
/* 57 */         battlePlayerData.win = ((Byte)destinyComponent.getBattles().get(Long.valueOf(battlePlayerData.playerId))).byteValue();
/* 58 */         battlePlayerData.quality = (destinyPlayerData.getQuality() == 999) ? 60 : destinyPlayerData.getQuality();
/* 59 */         ((DestinyBattleInfoResponse)this.response).battles.add(battlePlayerData);
/*    */       } 
/*    */     }
/*    */     
/* 63 */     ((DestinyBattleInfoResponse)this.response).robTimes = destinyComponent.getRobTimes();
/* 64 */     ((DestinyBattleInfoResponse)this.response).refreshTimes = destinyComponent.getRefreshTimes();
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBattleInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */