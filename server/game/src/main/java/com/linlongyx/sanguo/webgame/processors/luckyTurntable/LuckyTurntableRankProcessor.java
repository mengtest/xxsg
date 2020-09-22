/*    */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckyTurntableRankProcessor
/*    */   extends ProcessorBase<LuckyTurntableRankRequest, LuckyTurntableRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new LuckyTurntableRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyTurntableRankRequest request) {
/* 29 */     int actId = request.actId;
/* 30 */     LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 31 */     LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(actId);
/* 32 */     ((LuckyTurntableRankResponse)this.response).point = (luckyTurntableEntity == null) ? 0 : luckyTurntableEntity.getLuckyPoint();
/* 33 */     List<LuckyTurntableUtil.LuckyTurntableData> rankDataList = LuckyTurntableUtil.getRankList(actId);
/* 34 */     for (LuckyTurntableUtil.LuckyTurntableData data : rankDataList) {
/* 35 */       KeyValue kv = new KeyValue();
/* 36 */       kv.key = data.playerId;
/* 37 */       kv.value = data.totalPoint;
/* 38 */       kv.valueStr = data.playerName;
/* 39 */       ((LuckyTurntableRankResponse)this.response).rankList.add(kv);
/*    */     } 
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */