/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBuffBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SelectUnparalleledAttrbuteRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SelectUnparalleledAttrbuteResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectUnparalleledAttrbuteProcessor
/*    */   extends ProcessorBase<SelectUnparalleledAttrbuteRequest, SelectUnparalleledAttrbuteResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new SelectUnparalleledAttrbuteResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SelectUnparalleledAttrbuteRequest request) {
/* 28 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/*    */ 
/*    */     
/* 31 */     if (unparalleledComponent.getAttrbuteList().indexOf(Integer.valueOf(request.id)) < 0) {
/* 32 */       return 14010;
/*    */     }
/* 34 */     WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(request.insId, WushuangInsBean.class);
/* 35 */     if (unparalleledComponent.getLayerAddition().indexOf(Integer.valueOf(wushuangInsBean.getCheckPoint())) >= 0) {
/* 36 */       return 14009;
/*    */     }
/* 38 */     WushuangBuffBean wushuangBuffBean = (WushuangBuffBean)JsonTableService.getJsonData(request.id, WushuangBuffBean.class);
/* 39 */     if (wushuangBuffBean.getCostStar() > unparalleledComponent.getSurpStar()) {
/* 40 */       return 14007;
/*    */     }
/* 42 */     int res = wushuangInsBean.getCheckPoint() % 3;
/* 43 */     if (res != 0) {
/* 44 */       return 14012;
/*    */     }
/* 46 */     unparalleledComponent.setSurpStar(unparalleledComponent.getSurpStar() - wushuangBuffBean.getCostStar());
/* 47 */     unparalleledComponent.getLayerAddition().add(Integer.valueOf(wushuangInsBean.getCheckPoint()));
/* 48 */     unparalleledComponent.setLayerAddition(unparalleledComponent.getLayerAddition());
/* 49 */     unparalleledComponent.getBuffs().add(Integer.valueOf(request.id));
/* 50 */     unparalleledComponent.setBuffs(unparalleledComponent.getBuffs());
/* 51 */     unparalleledComponent.setAttrbuteList(new ArrayList());
/* 52 */     ((SelectUnparalleledAttrbuteResponse)this.response).attrbuteAddition = unparalleledComponent.getBuffs();
/* 53 */     ((SelectUnparalleledAttrbuteResponse)this.response).layerAddition = unparalleledComponent.getLayerAddition();
/* 54 */     ((SelectUnparalleledAttrbuteResponse)this.response).surpStar = unparalleledComponent.getSurpStar();
/* 55 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\SelectUnparalleledAttrbuteProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */