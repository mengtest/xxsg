/*    */ package com.linlongyx.sanguo.webgame.processors.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArtifactBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactActivateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactActivateResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArtifactActivateProcessor
/*    */   extends ProcessorBase<ArtifactActivateRequest, ArtifactActivateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 33 */     this.response = (ResponseBase)new ArtifactActivateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ArtifactActivateRequest request) {
/* 38 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 131))
/* 39 */       return 10061; 
/* 40 */     int id = request.id;
/* 41 */     ArtifactBean artifactBean = (ArtifactBean)JsonTableService.getJsonData(id, ArtifactBean.class);
/* 42 */     if (null == artifactBean) {
/* 43 */       return 12901;
/*    */     }
/* 45 */     ArtifactComponent artifactComponent = (ArtifactComponent)playerSession.getPlayer().createIfNotExist(ArtifactComponent.class);
/* 46 */     ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(id);
/* 47 */     if (null != artifactEntity) {
/* 48 */       return 12902;
/*    */     }
/* 50 */     ArrayList<Reward> rewards = FinanceUtil.transform(artifactBean.getSensitize());
/* 51 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 52 */     short code = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (code != 0) {
/* 54 */       return code;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ArtofactCost);
/* 57 */     artifactComponent.addArtifactEntity(playerSession.getPlayer().getPlayerId(), id);
/* 58 */     artifactEntity = artifactComponent.getArtifactEntity(id);
/* 59 */     Map<Integer, Integer> attrs = artifactEntity.getAttrs();
/* 60 */     ArrayList<AttrBean> initialAttr = artifactBean.getInitialAttr();
/* 61 */     for (AttrBean attrBean : initialAttr) {
/* 62 */       attrs.put(Integer.valueOf(attrBean.getId()), Integer.valueOf((int)attrBean.getNum()));
/*    */     }
/* 64 */     artifactEntity.setAttrs(attrs);
/* 65 */     artifactComponent.updateAttrsToDB(artifactEntity.getId());
/*    */ 
/*    */     
/* 68 */     AttrUpdateUtil.refreshArtifact(playerSession);
/*    */     
/* 70 */     ((ArtifactActivateResponse)this.response).data.id = id;
/*    */     
/* 72 */     for (Map.Entry<Integer, Integer> attr : attrs.entrySet()) {
/* 73 */       KeyValue keyValue = new KeyValue();
/* 74 */       keyValue.key = ((Integer)attr.getKey()).intValue();
/* 75 */       keyValue.value = ((Integer)attr.getValue()).intValue();
/* 76 */       ((ArtifactActivateResponse)this.response).data.attrs.add(keyValue);
/*    */     } 
/* 78 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\artifact\ArtifactActivateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */