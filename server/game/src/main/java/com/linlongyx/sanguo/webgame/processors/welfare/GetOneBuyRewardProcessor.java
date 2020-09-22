/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OneGiftboxBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetOneBuyRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetOneBuyRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetOneBuyRewardProcessor
/*    */   extends ProcessorBase<GetOneBuyRewardRequest, GetOneBuyRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GetOneBuyRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetOneBuyRewardRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6401))
/* 33 */       return 10061; 
/* 34 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 35 */     OneGiftboxBean oneGiftboxBean = (OneGiftboxBean)JsonTableService.getJsonData(request.oneBuyId, OneGiftboxBean.class);
/* 36 */     if (null == oneGiftboxBean) {
/* 37 */       return 15001;
/*    */     }
/* 39 */     if (welfareComponent.getOneBuyCharge() < request.oneBuyId) {
/* 40 */       return 15003;
/*    */     }
/* 42 */     if (welfareComponent.getOneBuyReward().containsKey(Integer.valueOf(request.oneBuyId))) {
/* 43 */       return 15002;
/*    */     }
/* 45 */     welfareComponent.getOneBuyReward().put(Integer.valueOf(request.oneBuyId), Integer.valueOf(TimeUtil.currentTime()));
/* 46 */     welfareComponent.setOneBuyReward(welfareComponent.getOneBuyReward());
/* 47 */     FinanceUtil.reward(FinanceUtil.transform(oneGiftboxBean.getReward()), playerSession.getPlayer(), ResourceEvent.OneBuyReward, true);
/* 48 */     LogUtil.errorLog(new Object[] { "GetOneBuyRewardProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.oneBuyId), welfareComponent.getOneBuyReward().keySet().toString() });
/* 49 */     ((GetOneBuyRewardResponse)this.response).gotReward = new ArrayList(welfareComponent.getOneBuyReward().keySet());
/* 50 */     ((GetOneBuyRewardResponse)this.response).oneBuyId = request.oneBuyId;
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GetOneBuyRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */