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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarInfoProcessor
/*    */   extends ProcessorBase<RunewarInfoRequest, RunewarInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new RunewarInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 33 */       return 10061; 
/* 34 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 35 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 36 */     int curtime = TimeUtil.currentTime();
/* 37 */     if (runeComponent.getLastRecoverTime() == 0) {
/* 38 */       runeComponent.setLastRecoverTime(curtime);
/*    */     } else {
/* 40 */       int recoverHp = (curtime - runeComponent.getLastRecoverTime()) / runewarParameter.getCdTime();
/* 41 */       if (recoverHp > 0) {
/* 42 */         runeComponent.setHp((runeComponent.getHp() + recoverHp >= runewarParameter.getHp()) ? runewarParameter.getHp() : (runeComponent.getHp() + recoverHp));
/* 43 */         runeComponent.setLastRecoverTime(curtime);
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     List<RunewarPlayerData> list = CrossUtil.getRunewardPlayerData("RunewarInfoProcessor", playerSession.getPlayer().getPlayerId());
/* 48 */     for (RunewarPlayerData data : list) {
/* 49 */       if (data.playerId == playerSession.getPlayer().getPlayerId()) {
/* 50 */         data.hp = runeComponent.getHp();
/* 51 */         data.point = runeComponent.getPoint();
/*    */         break;
/*    */       } 
/*    */     } 
/* 55 */     ((RunewarInfoResponse)this.response).dataList = new ArrayList<>(list);
/* 56 */     ((RunewarInfoResponse)this.response).time = runeComponent.getLastRecoverTime();
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */