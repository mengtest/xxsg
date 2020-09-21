/*    */ package com.linlongyx.sanguo.webgame.processors.rechargeAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TiringrebateBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RechargeActNoticeProcessor
/*    */   extends ProcessorBase<RechargeActNoticeRequest, RechargeActNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new RechargeActNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RechargeActNoticeRequest request) {
/* 26 */     Map<Integer, Object> objectMap = JsonTableService.getJsonTable(TiringrebateBean.class);
/* 27 */     for (Iterator<Integer> iterator = objectMap.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 28 */       if (RechargeActivityUtil.isOpen(id)) {
/* 29 */         ((RechargeActNoticeResponse)this.response).list.add(Integer.valueOf(id));
/*    */       } }
/*    */     
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rechargeAct\RechargeActNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */