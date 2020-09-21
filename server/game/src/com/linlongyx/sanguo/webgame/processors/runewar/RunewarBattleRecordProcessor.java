/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarBattleRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarBattleRecordResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarBattleRecordProcessor
/*    */   extends ProcessorBase<RunewarBattleRecordRequest, RunewarBattleRecordResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new RunewarBattleRecordResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarBattleRecordRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 25 */       return 10061; 
/* 26 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 27 */     ((RunewarBattleRecordResponse)this.response).records.addAll(runeComponent.getRecords());
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarBattleRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */