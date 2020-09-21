/*    */ package com.linlongyx.sanguo.webgame.processors.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DrawRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardRewardProcessor
/*    */   extends ProcessorBase<DrawCardRewardRequest, DrawCardRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new DrawCardRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DrawCardRewardRequest request) {
/* 31 */     int drawId = request.drawId;
/* 32 */     int num = request.num;
/* 33 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 34 */     if (!drawCardParameter.isActOpen(drawId)) {
/* 35 */       return 11801;
/*    */     }
/* 37 */     DrawRewardBean drawRewardBean = (DrawRewardBean)JsonTableService.getJsonData(num, DrawRewardBean.class);
/* 38 */     if (null == drawRewardBean) {
/* 39 */       return 11802;
/*    */     }
/* 41 */     DrawCardComponent drawCardComponent = (DrawCardComponent)playerSession.getPlayer().createIfNotExist(DrawCardComponent.class);
/* 42 */     DrawCardEntity drawCardEntity = drawCardComponent.getEntity(drawId);
/* 43 */     Set<Integer> counts = drawCardEntity.getCounts();
/* 44 */     if (counts.contains(Integer.valueOf(num))) {
/* 45 */       return 11803;
/*    */     }
/* 47 */     if (drawCardEntity.getRound() < num) {
/* 48 */       return 11804;
/*    */     }
/* 50 */     counts.add(Integer.valueOf(num));
/* 51 */     drawCardEntity.setCounts(counts);
/* 52 */     drawCardComponent.updateCountsDB(drawId);
/*    */     
/* 54 */     FinanceUtil.reward(FinanceUtil.transform(drawRewardBean.getDrawAward()), playerSession.getPlayer(), ResourceEvent.DrawCardTotalReward, true);
/*    */     
/* 56 */     ((DrawCardRewardResponse)this.response).drawId = drawId;
/* 57 */     ((DrawCardRewardResponse)this.response).num = num;
/*    */     
/* 59 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */