/*    */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankActNoticeProcessor
/*    */   extends ProcessorBase<RankActNoticeRequest, RankActNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new RankActNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankActNoticeRequest request) {
/* 35 */     ((RankActNoticeResponse)this.response).list = RankActUtil.getRankActList(true);
/* 36 */     RankActComponent rankActComponent = (RankActComponent)playerSession.getPlayer().createIfNotExist(RankActComponent.class);
/* 37 */     for (Integer id : ((RankActNoticeResponse)this.response).list) {
/* 38 */       RankActEntity rankActEntity = rankActComponent.getEntity(id.intValue());
/* 39 */       Map<Integer, Integer> states = rankActEntity.getStates();
/* 40 */       RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(id.intValue(), RankingActivityBean.class);
/* 41 */       if (null == rankingActivityBean) {
/*    */         continue;
/*    */       }
/* 44 */       for (Map.Entry<Integer, Integer> entry : states.entrySet()) {
/* 45 */         if (1 == ((Integer)entry.getValue()).intValue() && rankingActivityBean.getEntryConditions() != ((Integer)entry.getKey()).intValue()) {
/* 46 */           PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.RankAct.getSys(), id.intValue());
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 52 */     int close = TimeUtil.getZeroTimeStamp(MContext.getOpenTimeInt()) + loginParameter.getRankActClose() * 86400;
/* 53 */     if (!MContext.isHeFu()) {
/* 54 */       ((RankActNoticeResponse)this.response).closeTime = close;
/*    */     }
/* 56 */     ((RankActNoticeResponse)this.response).allList = new ArrayList(RankActUtil.rankActMap.keySet());
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankActNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */