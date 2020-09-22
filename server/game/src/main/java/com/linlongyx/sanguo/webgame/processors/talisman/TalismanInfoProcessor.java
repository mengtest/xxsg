/*    */ package com.linlongyx.sanguo.webgame.processors.talisman;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TalismanInfoProcessor
/*    */   extends ProcessorBase<TalismanInfoRequest, TalismanInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new TalismanInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TalismanInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 29)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     TalismanBoxBean talismanBoxBean = (TalismanBoxBean)JsonTableService.getJsonData(request.actId, TalismanBoxBean.class);
/* 34 */     if (talismanBoxBean == null) {
/* 35 */       return 11808;
/*    */     }
/* 37 */     TalismanComponent talismanComponent = (TalismanComponent)playerSession.getPlayer().createIfNotExist(TalismanComponent.class);
/* 38 */     TalismanEntity talismanEntity = (TalismanEntity)talismanComponent.getEntity(request.actId + "");
/* 39 */     if (talismanEntity == null) {
/* 40 */       talismanEntity = new TalismanEntity(playerSession.getPlayer().getPlayerId(), request.actId);
/* 41 */       talismanEntity.setFreeTimes(1);
/* 42 */       talismanEntity.setFreeRefresh(1);
/* 43 */       ArrayList<IntIntValue> itemList = TalismanUtil.refreshItems(request.actId);
/* 44 */       talismanEntity.setItems(itemList);
/* 45 */       talismanComponent.addEntity((IEntity)talismanEntity);
/*    */     } 
/* 47 */     ((TalismanInfoResponse)this.response).freeTimes = (byte)talismanEntity.getFreeTimes();
/* 48 */     ((TalismanInfoResponse)this.response).refreshTimes = talismanEntity.getRefreshTimes();
/* 49 */     ((TalismanInfoResponse)this.response).freeRefresh = (byte)talismanEntity.getFreeRefresh();
/* 50 */     ((TalismanInfoResponse)this.response).times = talismanBoxBean.getHitNum() + talismanEntity.getHitTimes() - talismanEntity.getTotalTimes();
/* 51 */     ((TalismanInfoResponse)this.response).itemList = new ArrayList(talismanEntity.getItems());
/* 52 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\talisman\TalismanInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */