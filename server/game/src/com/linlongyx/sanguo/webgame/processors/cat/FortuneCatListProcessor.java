/*    */ package com.linlongyx.sanguo.webgame.processors.cat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FortuneTimeBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FortuneCatListProcessor
/*    */   extends ProcessorBase<FortuneCatListRequest, FortuneCatListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new FortuneCatListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FortuneCatListRequest request) {
/* 23 */     int id = request.actId;
/* 24 */     FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)JsonTableService.getJsonData(id, FortuneTimeBean.class);
/* 25 */     if (null == fortuneTimeBean) {
/* 26 */       return 18001;
/*    */     }
/* 28 */     FortuneCatUtil.getRewardList(((FortuneCatListResponse)this.response).list, fortuneTimeBean.getType());
/* 29 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cat\FortuneCatListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */