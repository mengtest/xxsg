/*    */ package com.linlongyx.sanguo.webgame.processors.rank;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rank.RankingInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rank.RankingInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*    */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class RankingInfoProcessor
/*    */   extends ProcessorBase<RankingInfoRequest, RankingInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new RankingInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankingInfoRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 35)) {
/* 34 */       return 10061;
/*    */     }
/* 36 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 37 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 38 */     AbstractRank abstractRank = rankBaseService.getAbstractRank(request.type);
/* 39 */     ((RankingInfoResponse)this.response).type = request.type;
/* 40 */     ((RankingInfoResponse)this.response).myRank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 41 */     ((RankingInfoResponse)this.response).datas = new ArrayList(abstractRank.getRanks());
/* 42 */     long first = abstractRank.getFirst();
/* 43 */     ModelData firstModel = new ModelData();
/*    */     
/* 45 */     if (0L != first) {
/* 46 */       firstModel.playerId = first;
/* 47 */       PlayerComponent firstComponent = (PlayerComponent)LookUpService.getComponent(first, PlayerComponent.class);
/* 48 */       MountsComponent firstMountsComponent = (MountsComponent)LookUpService.getComponent(first, MountsComponent.class);
/* 49 */       if (null != firstComponent) {
/* 50 */         firstModel.title = firstComponent.getWearTitle();
/* 51 */         firstModel.fashion = 0;
/* 52 */         firstModel.sex = firstComponent.getSex();
/* 53 */         firstModel.nickname = "";
/* 54 */         if (firstMountsComponent != null) {
/* 55 */           firstModel.mounts = firstMountsComponent.getTurnMounts();
/*    */         }
/* 57 */         if (firstComponent.getWorships().size() >= request.type) {
/* 58 */           ((RankingInfoResponse)this.response).firstWorship = firstComponent.getWorship(request.type);
/*    */         } else {
/* 60 */           ((RankingInfoResponse)this.response).firstWorship = 0;
/*    */         } 
/*    */       } 
/*    */       
/* 64 */       if (null == firstComponent && request.type == RankingType.ARENA.getType())
/*    */       {
/*    */ 
/*    */ 
/*    */         
/* 69 */         DBIncrementService service = (DBIncrementService)AppContext.getBean("dbIncrementService");
/* 70 */         long worships = service.generate("arena_worships", 0L).longValue();
/* 71 */         ((RankingInfoResponse)this.response).firstWorship = (int)worships;
/*    */       }
/*    */     
/* 74 */     } else if (request.type == RankingType.ARENA.getType()) {
/* 75 */       DBIncrementService service = (DBIncrementService)AppContext.getBean("dbIncrementService");
/* 76 */       long worships = service.generate("arena_worships", 0L).longValue();
/* 77 */       ((RankingInfoResponse)this.response).firstWorship = (int)worships;
/*    */     } 
/*    */     
/* 80 */     ((RankingInfoResponse)this.response).isWorship = playerComponent.getIsWorship(request.type);
/* 81 */     ((RankingInfoResponse)this.response).worships = playerComponent.getWorship(request.type);
/* 82 */     ((RankingInfoResponse)this.response).firstModel = firstModel;
/* 83 */     ((RankingInfoResponse)this.response).worldLevel = RankingLevel.getWorldLevel();
/* 84 */     ((RankingInfoResponse)this.response).titleId = playerComponent.getWearTitle();
/*    */     
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rank\RankingInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */