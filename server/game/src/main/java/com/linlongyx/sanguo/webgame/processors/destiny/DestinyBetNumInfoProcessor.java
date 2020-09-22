/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetNumInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetNumInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBetNumInfoProcessor
/*    */   extends ProcessorBase<DestinyBetNumInfoRequest, DestinyBetNumInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new DestinyBetNumInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBetNumInfoRequest request) {
/* 27 */     HashMap<Long, Integer> betNumMap = CrossUtil.getBetNum("DestinyBetMatchInfoProcessor::getBetNum", request.pkId);
/*    */ 
/*    */ 
/*    */     
/* 31 */     if (betNumMap != null) {
/* 32 */       for (Map.Entry<Long, Integer> kv : betNumMap.entrySet()) {
/* 33 */         LongIntValue value = new LongIntValue();
/* 34 */         value.key = ((Long)kv.getKey()).longValue();
/* 35 */         value.value = ((Integer)kv.getValue()).intValue();
/* 36 */         ((DestinyBetNumInfoResponse)this.response).playerBetList.add(value);
/*    */       } 
/*    */     }
/* 39 */     ((DestinyBetNumInfoResponse)this.response).pkId = request.pkId;
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBetNumInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */