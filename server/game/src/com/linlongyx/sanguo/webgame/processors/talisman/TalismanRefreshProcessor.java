/*    */ package com.linlongyx.sanguo.webgame.processors.talisman;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanRefreshRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanRefreshResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TalismanRefreshProcessor
/*    */   extends ProcessorBase<TalismanRefreshRequest, TalismanRefreshResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new TalismanRefreshResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TalismanRefreshRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 29)) {
/* 30 */       return 10061;
/*    */     }
/* 32 */     TalismanBoxBean talismanBoxBean = (TalismanBoxBean)JsonTableService.getJsonData(request.actId, TalismanBoxBean.class);
/* 33 */     if (talismanBoxBean == null) {
/* 34 */       return 11808;
/*    */     }
/* 36 */     TalismanComponent talismanComponent = (TalismanComponent)playerSession.getPlayer().createIfNotExist(TalismanComponent.class);
/* 37 */     TalismanEntity talismanEntity = (TalismanEntity)talismanComponent.getEntity(request.actId + "");
/* 38 */     if (talismanEntity.getFreeRefresh() > 0) {
/* 39 */       talismanEntity.setFreeRefresh(talismanEntity.getFreeRefresh() - 1);
/*    */     } else {
/* 41 */       int index = (talismanEntity.getRefreshTimes() >= talismanBoxBean.getFresh().size()) ? (talismanBoxBean.getFresh().size() - 1) : talismanEntity.getRefreshTimes();
/* 42 */       int cost = ((Integer)talismanBoxBean.getFresh().get(index)).intValue();
/* 43 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 44 */         return 10052;
/*    */       }
/* 46 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.TalismanRefresh);
/* 47 */       talismanEntity.setRefreshTimes(talismanEntity.getRefreshTimes() + 1);
/*    */     } 
/* 49 */     ArrayList<IntIntValue> itemList = TalismanUtil.refreshItems(request.actId);
/* 50 */     talismanEntity.setItems(itemList);
/* 51 */     ((TalismanRefreshResponse)this.response).itemList = itemList;
/* 52 */     ((TalismanRefreshResponse)this.response).freeRefresh = (byte)talismanEntity.getFreeRefresh();
/* 53 */     ((TalismanRefreshResponse)this.response).refreshTimes = talismanEntity.getRefreshTimes();
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\talisman\TalismanRefreshProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */