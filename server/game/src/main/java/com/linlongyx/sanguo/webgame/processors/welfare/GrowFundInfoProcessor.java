/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GrowFundInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GrowFundInfoResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class GrowFundInfoProcessor
/*    */   extends ProcessorBase<GrowFundInfoRequest, GrowFundInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new GrowFundInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GrowFundInfoRequest request) {
/* 22 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 23 */     ((GrowFundInfoResponse)this.response).isBuy = (welfareComponent.isBuyFund() == true) ? 1 : 0;
/* 24 */     for (Iterator<Integer> iterator = welfareComponent.getFundReward().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 25 */       ((GrowFundInfoResponse)this.response).fundLevel.add(Integer.valueOf(key)); }
/*    */     
/* 27 */     ((GrowFundInfoResponse)this.response).groupBuyNum = WelfareUtil.growfundNum.get();
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GrowFundInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */