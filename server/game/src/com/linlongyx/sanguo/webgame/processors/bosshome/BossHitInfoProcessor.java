/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossHitInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossHitInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossHitInfoProcessor
/*    */   extends ProcessorBase<BossHitInfoRequest, BossHitInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new BossHitInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BossHitInfoRequest request) {
/* 23 */     int insId = request.insId;
/* 24 */     BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId, BossHomeBean.class);
/* 25 */     if (null == bossHomeBean) {
/* 26 */       return 10301;
/*    */     }
/* 28 */     ((BossHitInfoResponse)this.response).insId = insId;
/* 29 */     if (bossHomeBean.getType() == 1) {
/* 30 */       ((BossHitInfoResponse)this.response).list = BossUtil.getBossDamageData(insId);
/*    */     }
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\BossHitInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */