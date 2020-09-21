/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarFightersInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarFightersInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarFightersInfoProcessor
/*    */   extends ProcessorBase<RunewarFightersInfoRequest, RunewarFightersInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new RunewarFightersInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarFightersInfoRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 30 */       return 10061; 
/* 31 */     if (request.playerId <= 0L) {
/* 32 */       return 11808;
/*    */     }
/*    */     
/* 35 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 36 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 37 */     int curtime = TimeUtil.currentTime();
/* 38 */     if (runeComponent.getLastRecoverTime() == 0) {
/* 39 */       runeComponent.setLastRecoverTime(curtime);
/*    */     } else {
/* 41 */       int recoverHp = (curtime - runeComponent.getLastRecoverTime()) / runewarParameter.getCdTime();
/* 42 */       if (recoverHp > 0) {
/* 43 */         runeComponent.setHp((runeComponent.getHp() + recoverHp >= runewarParameter.getHp()) ? runewarParameter.getHp() : (runeComponent.getHp() + recoverHp));
/* 44 */         runeComponent.setLastRecoverTime(curtime);
/*    */       } 
/*    */     } 
/* 47 */     RunewarFightersData runewarFightersData = CrossUtil.getRunewarFighterData("RunewarFightersInfoProcessor", playerSession.getPlayer().getPlayerId(), request.playerId);
/* 48 */     if (runewarFightersData == null) {
/* 49 */       return 14501;
/*    */     }
/* 51 */     ((RunewarFightersInfoResponse)this.response).data = runewarFightersData;
/* 52 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarFightersInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */