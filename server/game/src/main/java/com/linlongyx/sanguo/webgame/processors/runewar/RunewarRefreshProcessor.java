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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRefreshRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRefreshResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarRefreshProcessor
/*    */   extends ProcessorBase<RunewarRefreshRequest, RunewarRefreshResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new RunewarRefreshResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarRefreshRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 32 */       return 10061; 
/* 33 */     boolean open = CrossUtil.isRunewarOpen("RunewarRefreshProcessor");
/* 34 */     if (!open) {
/* 35 */       return 14503;
/*    */     }
/* 37 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 38 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/*    */     
/* 40 */     int curtime = TimeUtil.currentTime();
/* 41 */     if (runeComponent.getLastRecoverTime() == 0) {
/* 42 */       runeComponent.setLastRecoverTime(curtime);
/*    */     } else {
/* 44 */       int recoverHp = (curtime - runeComponent.getLastRecoverTime()) / runewarParameter.getCdTime();
/* 45 */       if (recoverHp > 0) {
/* 46 */         runeComponent.setHp((runeComponent.getHp() + recoverHp >= runewarParameter.getHp()) ? runewarParameter.getHp() : (runeComponent.getHp() + recoverHp));
/* 47 */         runeComponent.setLastRecoverTime(curtime);
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     if (runeComponent.getHp() == 0) {
/* 52 */       return 14502;
/*    */     }
/* 54 */     if (runeComponent.getLastRefreshTime() > 0 && runeComponent.getLastRefreshTime() + runewarParameter.getRefreshCd() > curtime) {
/* 55 */       return 14505;
/*    */     }
/* 57 */     runeComponent.setLastRefreshTime(curtime);
/* 58 */     List<RunewarPlayerData> dataList = CrossUtil.refreshPlayerCoordinate("RunewarRefreshProcessor", playerSession.getPlayer().getPlayerId());
/* 59 */     if (dataList == null) {
/* 60 */       return 14504;
/*    */     }
/* 62 */     ((RunewarRefreshResponse)this.response).dataList.addAll(dataList);
/* 63 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarRefreshProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */