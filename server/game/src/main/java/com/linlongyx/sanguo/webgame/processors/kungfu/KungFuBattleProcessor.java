/*    */ package com.linlongyx.sanguo.webgame.processors.kungfu;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuBattleResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KungFuBattleProcessor
/*    */   extends ProcessorBase<KungFuBattleRequest, KungFuBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new KungFuBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, KungFuBattleRequest request) {
/* 25 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/* 26 */       return 10061; 
/* 27 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/* 28 */     KungFuEntity kungFuEntity = kungFuComponent.getEntity(request.kungfu);
/* 29 */     if (null == kungFuEntity) {
/* 30 */       return 15005;
/*    */     }
/* 32 */     KungFuEntity kungFuEntity1 = kungFuComponent.getBattleKungFu();
/*    */     
/* 34 */     if (kungFuEntity.getBattle() == 0) {
/* 35 */       kungFuEntity.setBattle(1);
/* 36 */       kungFuComponent.updateBattleDB(request.kungfu);
/* 37 */       if (kungFuEntity1 != null) {
/* 38 */         kungFuEntity1.setBattle(0);
/* 39 */         kungFuComponent.updateBattleDB(kungFuEntity1.getKungFuId());
/*    */       } 
/*    */     } else {
/* 42 */       kungFuEntity.setBattle(0);
/* 43 */       kungFuComponent.updateBattleDB(request.kungfu);
/*    */     } 
/* 45 */     ((KungFuBattleResponse)this.response).kungfu = request.kungfu;
/* 46 */     ((KungFuBattleResponse)this.response).state = kungFuEntity.getBattle();
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\KungFuBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */