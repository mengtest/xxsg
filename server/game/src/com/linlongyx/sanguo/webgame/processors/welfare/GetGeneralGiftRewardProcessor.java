/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterNeedBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralGiftParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetGeneralGiftRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetGeneralGiftRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class GetGeneralGiftRewardProcessor
/*    */   extends ProcessorBase<GetGeneralGiftRewardRequest, GetGeneralGiftRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GetGeneralGiftRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetGeneralGiftRewardRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 46))
/* 31 */       return 10061; 
/* 32 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 33 */     FighterNeedBean fighterNeedBean = (FighterNeedBean)JsonTableService.getJsonData(request.id, FighterNeedBean.class);
/* 34 */     GeneralGiftParameter generalGiftParameter = (GeneralGiftParameter)ParameterConstant.getParameter(46);
/* 35 */     if (null == fighterNeedBean) {
/* 36 */       return 15001;
/*    */     }
/* 38 */     if (welfareComponent.getGerenalGift().contains(Integer.valueOf(request.id))) {
/* 39 */       return 15002;
/*    */     }
/* 41 */     if (TimeUtil.currentTime() > WelfareUtil.getGiftTime(generalGiftParameter.getOpenDay())) {
/* 42 */       return 12702;
/*    */     }
/* 44 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 45 */     boolean hash = partnerComponent.checkPartner(request.id);
/* 46 */     if (!hash) {
/* 47 */       return 15003;
/*    */     }
/* 49 */     welfareComponent.getGerenalGift().add(Integer.valueOf(request.id));
/* 50 */     welfareComponent.setGerenalGift(welfareComponent.getGerenalGift());
/* 51 */     FinanceUtil.reward(FinanceUtil.transform(fighterNeedBean.getReward()), playerSession.getPlayer(), ResourceEvent.GeneralGift, true);
/* 52 */     ((GetGeneralGiftRewardResponse)this.response).id = request.id;
/* 53 */     ((GetGeneralGiftRewardResponse)this.response).list = new ArrayList(welfareComponent.getGerenalGift());
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GetGeneralGiftRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */