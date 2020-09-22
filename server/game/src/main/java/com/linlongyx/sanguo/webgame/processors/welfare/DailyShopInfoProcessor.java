/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailyShopInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailyShopInfoResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class DailyShopInfoProcessor
/*    */   extends ProcessorBase<DailyShopInfoRequest, DailyShopInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new DailyShopInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DailyShopInfoRequest request) {
/* 22 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 23 */     for (Iterator<Integer> iterator = welfareComponent.getDailyBuyReward().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 24 */       ((DailyShopInfoResponse)this.response).dailyOrderId.add(Integer.valueOf(key)); }
/*    */     
/* 26 */     ((DailyShopInfoResponse)this.response).worldLevel = WelfareUtil.getDailyWorldLevel();
/* 27 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\DailyShopInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */