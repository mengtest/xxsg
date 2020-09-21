/*    */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookRankInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookRankInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardBookRankInfoProcessor
/*    */   extends ProcessorBase<CardBookRankInfoRequest, CardBookRankInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new CardBookRankInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CardBookRankInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/* 31 */       return 10061; 
/* 32 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 33 */     AbstractRank abstractRank = rankBaseService.getAbstractRank(RankingType.CARDBOOK.getType());
/* 34 */     ((CardBookRankInfoResponse)this.response).rankList = new ArrayList(abstractRank.getRanks());
/* 35 */     ((CardBookRankInfoResponse)this.response).myRank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 36 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/* 37 */     ((CardBookRankInfoResponse)this.response).stars = cardBookComponent.getLastToColor();
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookRankInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */