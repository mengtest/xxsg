/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRewardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRewardInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarRewardInfoProcessor
/*    */   extends ProcessorBase<RunewarRewardInfoRequest, RunewarRewardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RunewarRewardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarRewardInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 28 */       return 10061; 
/* 29 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 30 */     if (request.type == 1) {
/* 31 */       for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)runeComponent.getDailyReward().entrySet()) {
/* 32 */         IntIntValue intIntValue = new IntIntValue();
/* 33 */         intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 34 */         intIntValue.value = ((Integer)kv.getValue()).intValue();
/* 35 */         ((RunewarRewardInfoResponse)this.response).rewardList.add(intIntValue);
/*    */       } 
/* 37 */       ((RunewarRewardInfoResponse)this.response).progress = runeComponent.getTimes();
/* 38 */     } else if (request.type == 2) {
/* 39 */       for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)runeComponent.getStageReward().entrySet()) {
/* 40 */         IntIntValue intIntValue = new IntIntValue();
/* 41 */         intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 42 */         intIntValue.value = ((Integer)kv.getValue()).intValue();
/* 43 */         ((RunewarRewardInfoResponse)this.response).rewardList.add(intIntValue);
/*    */       } 
/* 45 */       ((RunewarRewardInfoResponse)this.response).progress = runeComponent.getPoint();
/*    */     } 
/* 47 */     ((RunewarRewardInfoResponse)this.response).type = request.type;
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarRewardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */