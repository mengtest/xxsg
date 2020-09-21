/*    */ package com.linlongyx.sanguo.webgame.processors.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankAct;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRankActNoticeProcessor
/*    */   extends ProcessorBase<CrossRankActNoticeRequest, CrossRankActNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new CrossRankActNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRankActNoticeRequest request) {
/* 32 */     Map<Integer, Byte> actList = CrossRankActUtil.getCrossRankActList();
/* 33 */     if (actList.isEmpty()) {
/* 34 */       return 0;
/*    */     }
/* 36 */     for (Map.Entry<Integer, Byte> kv : actList.entrySet()) {
/* 37 */       IntIntValue intIntValue = new IntIntValue();
/* 38 */       intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 39 */       intIntValue.value = ((Byte)kv.getValue()).byteValue();
/* 40 */       ((CrossRankActNoticeResponse)this.response).list.add(intIntValue);
/*    */     } 
/*    */     
/* 43 */     CrossRankActComponent crossRankActComponent = (CrossRankActComponent)playerSession.getPlayer().createIfNotExist(CrossRankActComponent.class);
/* 44 */     for (IntIntValue act : ((CrossRankActNoticeResponse)this.response).list) {
/* 45 */       CrossRankActEntity entity = crossRankActComponent.getEntity(act.key);
/* 46 */       if (entity == null)
/* 47 */         continue;  Map<Integer, Integer> states = entity.getStates();
/* 48 */       RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(act.key, RankingKuafuBean.class);
/* 49 */       if (null == bean) {
/*    */         continue;
/*    */       }
/* 52 */       if (CrossRankActUtil.isActOpen(bean) && !CrossRankActUtil.isActClose(bean)) {
/* 53 */         for (Map.Entry<Integer, Integer> entry : states.entrySet()) {
/* 54 */           if (1 == ((Integer)entry.getValue()).intValue() && bean.getEntryConditions() != ((Integer)entry.getKey()).intValue()) {
/* 55 */             PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.CrossRankAct.getSys(), act.key);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       }
/* 60 */       if (CrossRankActUtil.isActClose(bean) && act.value != RankAct.RankActState.Expire.getState()) {
/* 61 */         act.value = RankAct.RankActState.Expire.getState();
/*    */       }
/*    */     } 
/* 64 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRankAct\CrossRankActNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */