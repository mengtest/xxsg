/*    */ package com.linlongyx.sanguo.webgame.processors.general;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralStarRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralStarRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralStarRankProcessor
/*    */   extends ProcessorBase<GeneralStarRankRequest, GeneralStarRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new GeneralStarRankResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GeneralStarRankRequest request) {
/* 32 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 33 */     int level = request.level;
/* 34 */     ArrayList<RankingData> list = new ArrayList<>();
/* 35 */     int rank = 0;
/* 36 */     int star = 0;
/* 37 */     if (level == 2) {
/* 38 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1201)) {
/* 39 */         return 10061;
/*    */       }
/* 41 */     } else if (level == 3 && 
/* 42 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 1202)) {
/* 43 */       return 10061;
/*    */     } 
/*    */     
/* 46 */     if (level == 1) {
/* 47 */       AbstractRank abstractRank = rankBaseService.getAbstractRank(RankingType.GENERAL_STAR.getType());
/* 48 */       list = new ArrayList<>(abstractRank.getRanks());
/* 49 */       rank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 50 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 51 */       star = generalComponent.getAllStar();
/* 52 */     } else if (level == 2) {
/* 53 */       AbstractRank abstractRank = rankBaseService.getAbstractRank(RankingType.GENERAL_STAR2.getType());
/* 54 */       list = new ArrayList<>(abstractRank.getRanks());
/* 55 */       rank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 56 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 57 */       star = generalComponent.getDifficultyStar();
/* 58 */     } else if (level == 3) {
/* 59 */       AbstractRank abstractRank = rankBaseService.getAbstractRank(RankingType.GENERAL_STAR3.getType());
/* 60 */       list = new ArrayList<>(abstractRank.getRanks());
/* 61 */       rank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 62 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 63 */       star = generalComponent.getNightmareStar();
/*    */     } 
/* 65 */     ((GeneralStarRankResponse)this.response).list = list;
/* 66 */     ((GeneralStarRankResponse)this.response).star = star;
/* 67 */     ((GeneralStarRankResponse)this.response).rank = rank;
/* 68 */     ((GeneralStarRankResponse)this.response).level = level;
/* 69 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralStarRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */