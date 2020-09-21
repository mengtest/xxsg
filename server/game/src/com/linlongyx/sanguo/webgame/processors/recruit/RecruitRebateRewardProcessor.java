/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRewardlistBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecruitRebateRewardProcessor
/*    */   extends ProcessorBase<RecruitRebateRewardRequest, RecruitRebateRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new RecruitRebateRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RecruitRebateRewardRequest request) {
/* 31 */     RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 32 */     List<Integer> list = recruitParamter.getActId(true);
/* 33 */     if (list.isEmpty()) {
/* 34 */       return 12702;
/*    */     }
/* 36 */     RecruitRewardlistBean recruitRewardlistBean = (RecruitRewardlistBean)JsonTableService.getJsonData(request.id, RecruitRewardlistBean.class);
/* 37 */     if (null == recruitRewardlistBean) {
/* 38 */       return 10006;
/*    */     }
/* 40 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 41 */     if (recruitComponent.getRebateScore() < recruitRewardlistBean.getPoints()) {
/* 42 */       return 15003;
/*    */     }
/* 44 */     if (recruitComponent.getRebateReward().containsKey(Integer.valueOf(request.id))) {
/* 45 */       return 12004;
/*    */     }
/* 47 */     recruitComponent.getRebateReward().put(Integer.valueOf(request.id), Integer.valueOf(TimeUtil.currentTime()));
/* 48 */     recruitComponent.setRebateReward(recruitComponent.getRebateReward());
/* 49 */     FinanceUtil.reward(FinanceUtil.transform(recruitRewardlistBean.getReward()), playerSession.getPlayer(), ResourceEvent.RecruitRebateReward, true);
/* 50 */     ((RecruitRebateRewardResponse)this.response).id = request.id;
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RecruitRebateRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */