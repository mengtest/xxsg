/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetUnparalleledInfoProcessor
/*    */   extends ProcessorBase<GetUnparalleledInfoRequest, GetUnparalleledInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GetUnparalleledInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetUnparalleledInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 18)) {
/* 28 */       return 10061;
/*    */     }
/* 30 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 31 */     ((GetUnparalleledInfoResponse)this.response).resetTimes = unparalleledComponent.getResetTimes();
/* 32 */     ((GetUnparalleledInfoResponse)this.response).attrbuteAddition.addAll(unparalleledComponent.getBuffs());
/* 33 */     ((GetUnparalleledInfoResponse)this.response).curPoint = unparalleledComponent.getCurPoint();
/* 34 */     ((GetUnparalleledInfoResponse)this.response).currMaxStar = unparalleledComponent.getCurrMaxStar();
/* 35 */     ((GetUnparalleledInfoResponse)this.response).layerAddition = unparalleledComponent.getLayerAddition();
/* 36 */     ((GetUnparalleledInfoResponse)this.response).layerBox = unparalleledComponent.getLayerBox();
/* 37 */     for (Iterator<Integer> iterator = unparalleledComponent.getInsMap().keySet().iterator(); iterator.hasNext(); ) { int ins = ((Integer)iterator.next()).intValue();
/* 38 */       KeyValue keyValue = new KeyValue();
/* 39 */       keyValue.key = ins;
/* 40 */       WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(((Integer)unparalleledComponent.getInsMap().get(Integer.valueOf(ins))).intValue(), WushuangInsBean.class);
/* 41 */       keyValue.value = wushuangInsBean.getStar();
/* 42 */       ((GetUnparalleledInfoResponse)this.response).layerStar.add(keyValue); }
/*    */     
/* 44 */     unparalleledComponent.getPlayedPoints().keySet().forEach(guanka -> ((GetUnparalleledInfoResponse)this.response).playedPoints.add(guanka));
/*    */ 
/*    */     
/* 47 */     ((GetUnparalleledInfoResponse)this.response).surpStar = unparalleledComponent.getSurpStar();
/* 48 */     unparalleledComponent.getPartneredHpMap().keySet().forEach(partner -> {
/*    */           KeyValue keyValue = new KeyValue();
/*    */           keyValue.key = partner.longValue();
/*    */           keyValue.value = ((Long)unparalleledComponent.getPartneredHpMap().get(partner)).longValue();
/*    */           ((GetUnparalleledInfoResponse)this.response).partnerHP.add(keyValue);
/*    */         });
/* 54 */     ((GetUnparalleledInfoResponse)this.response).isSweep = unparalleledComponent.isSweep() ? 1 : 0;
/* 55 */     ((GetUnparalleledInfoResponse)this.response).lastMaxStar = unparalleledComponent.getLastMaxStar();
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\GetUnparalleledInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */