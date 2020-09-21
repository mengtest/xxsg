/*    */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaBuyTreasureRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaBuyTreasureResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaBuyTreasureProcessor
/*    */   extends ProcessorBase<BaguaBuyTreasureRequest, BaguaBuyTreasureResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new BaguaBuyTreasureResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BaguaBuyTreasureRequest request) {
/* 28 */     BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/* 29 */     if (!baguaComponent.getInsIdMap().containsKey(Integer.valueOf(request.insId))) {
/* 30 */       return 14101;
/*    */     }
/* 32 */     if (((Integer)baguaComponent.getInsIdMap().get(Integer.valueOf(request.insId))).intValue() == 2) {
/* 33 */       return 14102;
/*    */     }
/*    */     
/* 36 */     BaguaInsBean baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(request.insId, BaguaInsBean.class);
/* 37 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, baguaInsBean.getMoney())) {
/* 38 */       return 10052;
/*    */     }
/* 40 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, baguaInsBean.getMoney(), ResourceEvent.BaguaBuy);
/* 41 */     FinanceUtil.reward(FinanceUtil.transform(baguaInsBean.getChest()), playerSession.getPlayer(), ResourceEvent.BaguaBuy, true);
/*    */     
/* 43 */     baguaComponent.getInsIdMap().put(Integer.valueOf(request.insId), Integer.valueOf(2));
/* 44 */     ((BaguaBuyTreasureResponse)this.response).insId = request.insId;
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaBuyTreasureProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */