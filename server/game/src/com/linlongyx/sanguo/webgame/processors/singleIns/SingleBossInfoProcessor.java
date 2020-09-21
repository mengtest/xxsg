/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.SingleBossInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.SingleBossInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleBossInfoProcessor
/*    */   extends ProcessorBase<SingleBossInfoRequest, SingleBossInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new SingleBossInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SingleBossInfoRequest request) {
/* 25 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 300)) {
/* 26 */       return 10061;
/*    */     }
/* 28 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class); Iterator<Integer> iterator;
/* 29 */     for (iterator = singleInsComponent.getBossMap().keySet().iterator(); iterator.hasNext(); ) { int bossId = ((Integer)iterator.next()).intValue();
/* 30 */       ((SingleBossInfoResponse)this.response).passBoss.add(Integer.valueOf(bossId)); }
/*    */     
/* 32 */     for (iterator = singleInsComponent.getTimesMap().keySet().iterator(); iterator.hasNext(); ) { int bossId = ((Integer)iterator.next()).intValue();
/* 33 */       KeyValue keyValue = new KeyValue();
/* 34 */       keyValue.key = bossId;
/* 35 */       keyValue.value = ((Integer)singleInsComponent.getTimesMap().get(Integer.valueOf(bossId))).intValue();
/* 36 */       keyValue.valueStr = (new StringBuilder()).append(singleInsComponent.getBuyTimeMap().getOrDefault(Integer.valueOf(bossId), Integer.valueOf(0))).append("").toString();
/* 37 */       ((SingleBossInfoResponse)this.response).bossTimes.add(keyValue); }
/*    */     
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\SingleBossInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */