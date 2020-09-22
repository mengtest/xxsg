/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetAttrbuteInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetAttrbuteInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetAttrbuteInfoProcessor
/*    */   extends ProcessorBase<GetAttrbuteInfoRequest, GetAttrbuteInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new GetAttrbuteInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetAttrbuteInfoRequest request) {
/* 24 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 25 */     WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(request.insId, WushuangInsBean.class);
/*    */     
/* 27 */     if (unparalleledComponent.getLayerAddition().indexOf(Integer.valueOf(wushuangInsBean.getCheckPoint())) >= 0) {
/* 28 */       return 14011;
/*    */     }
/*    */     
/* 31 */     if (wushuangInsBean.getCheckPoint() % 3 != 0) {
/* 32 */       return 14012;
/*    */     }
/* 34 */     if (unparalleledComponent.getAttrbuteList().isEmpty()) {
/* 35 */       unparalleledComponent.createAttrbuteList();
/* 36 */       ((GetAttrbuteInfoResponse)this.response).attrbuteAddition.addAll(unparalleledComponent.getAttrbuteList());
/*    */     } else {
/* 38 */       ((GetAttrbuteInfoResponse)this.response).attrbuteAddition.addAll(unparalleledComponent.getAttrbuteList());
/*    */     } 
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\GetAttrbuteInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */