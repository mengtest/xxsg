/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBoxBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetBoxRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetBoxRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetBoxRewardProcessor
/*    */   extends ProcessorBase<GetBoxRewardRequest, GetBoxRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new GetBoxRewardResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetBoxRewardRequest request) {
/* 27 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 28 */     if (unparalleledComponent.getLayerBox().indexOf(Integer.valueOf(request.boxId)) > 0) {
/* 29 */       return 14006;
/*    */     }
/* 31 */     if (request.boxId > unparalleledComponent.getCurrMaxStar()) {
/* 32 */       return 14007;
/*    */     }
/* 34 */     WushuangBoxBean wushuangBoxBean = (WushuangBoxBean)JsonTableService.getJsonData(request.boxId, WushuangBoxBean.class);
/* 35 */     if (null == wushuangBoxBean) {
/* 36 */       return 14008;
/*    */     }
/* 38 */     unparalleledComponent.getLayerBox().add(Integer.valueOf(request.boxId));
/* 39 */     unparalleledComponent.setLayerBox(unparalleledComponent.getLayerBox());
/* 40 */     FinanceUtil.reward(FinanceUtil.transform(wushuangBoxBean.getStarReward()), playerSession.getPlayer(), ResourceEvent.UnparBoxReward, false);
/* 41 */     ((GetBoxRewardResponse)this.response).layerBox.addAll(unparalleledComponent.getLayerBox());
/* 42 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\GetBoxRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */