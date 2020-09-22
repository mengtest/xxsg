/*    */ package com.linlongyx.sanguo.webgame.processors.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupBattleResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarLineupBattleProcessor
/*    */   extends ProcessorBase<WarLineupBattleRequest, WarLineupBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new WarLineupBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarLineupBattleRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 57))
/* 29 */       return 10061; 
/* 30 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/* 31 */     WarLineupEntity newWarLineupEntity = warLineupComponent.getEntity(request.warlineup);
/* 32 */     if (null == newWarLineupEntity) {
/* 33 */       return 15005;
/*    */     }
/* 35 */     WarLineupEntity oldWarLineupEntity = warLineupComponent.getBattleWarLineup();
/*    */ 
/*    */ 
/*    */     
/* 39 */     if (newWarLineupEntity.getBattle() == 0) {
/* 40 */       newWarLineupEntity.setBattle(1);
/* 41 */       warLineupComponent.updateBattleDB(request.warlineup);
/* 42 */       if (oldWarLineupEntity != null) {
/* 43 */         oldWarLineupEntity.setBattle(0);
/* 44 */         warLineupComponent.updateBattleDB(oldWarLineupEntity.getWarLineupId());
/*    */       } 
/*    */     } else {
/* 47 */       newWarLineupEntity.setBattle(0);
/* 48 */       warLineupComponent.updateBattleDB(request.warlineup);
/*    */     } 
/*    */     
/* 51 */     Fibers.createExecutorService().submit(() -> {
/*    */           WarLineupEntity entity = warLineupComponent.getBattleWarLineup();
/*    */           Pair<Integer, Integer> zhenfa = null;
/*    */           if (entity != null) {
/*    */             zhenfa = new Pair(Integer.valueOf(entity.getWarLineupId()), Integer.valueOf(entity.getStar()));
/*    */           }
/*    */           CrossUtil.updateZhenfa("WarLineupBattleProcessor", playerSession.getPlayer().getPlayerId(), zhenfa);
/*    */         });
/* 59 */     ((WarLineupBattleResponse)this.response).warlineup = request.warlineup;
/* 60 */     ((WarLineupBattleResponse)this.response).battleState = newWarLineupEntity.getBattle();
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\WarLineupBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */