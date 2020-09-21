/*    */ package com.linlongyx.sanguo.webgame.processors.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArtifactBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArtifactData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TrainData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArtifactListProcessor
/*    */   extends ProcessorBase<ArtifactListRequest, ArtifactListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new ArtifactListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ArtifactListRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 131)) {
/* 36 */       return 10061;
/*    */     }
/* 38 */     ArtifactComponent artifactComponent = (ArtifactComponent)playerSession.getPlayer().createIfNotExist(ArtifactComponent.class);
/* 39 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 40 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArtifactBean.class);
/*    */ 
/*    */     
/* 43 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 44 */       ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(((Integer)entry.getKey()).intValue());
/* 45 */       if (null != artifactEntity) {
/* 46 */         ArtifactBean artifactBean = (ArtifactBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), ArtifactBean.class);
/* 47 */         if (null == artifactBean) {
/*    */           continue;
/*    */         }
/* 50 */         ArtifactData artifactData = new ArtifactData();
/* 51 */         artifactData.id = ((Integer)entry.getKey()).intValue();
/* 52 */         Map<Integer, Integer> attrs = artifactEntity.getAttrs();
/* 53 */         for (AttrBean attrBean : artifactBean.getMaxAttr()) {
/* 54 */           KeyValue keyValue = new KeyValue();
/* 55 */           keyValue.key = attrBean.getId();
/* 56 */           keyValue.value = ((Integer)attrs.getOrDefault(Integer.valueOf(attrBean.getId()), Integer.valueOf(0))).intValue();
/* 57 */           artifactData.attrs.add(keyValue);
/*    */         } 
/*    */         
/* 60 */         Map<Integer, Integer> tempA = new HashMap<>();
/* 61 */         for (Map.Entry<Integer, TrainData> entry1 : (Iterable<Map.Entry<Integer, TrainData>>)artifactEntity.getTrainDataMap().entrySet()) {
/* 62 */           TrainData data = entry1.getValue();
/* 63 */           for (KeyValue keyValue1 : data.attrs) {
/* 64 */             tempA.put(Integer.valueOf((int)keyValue1.key), Integer.valueOf(((Integer)tempA.getOrDefault(Integer.valueOf((int)keyValue1.key), Integer.valueOf(0))).intValue() + (int)keyValue1.value));
/*    */           }
/*    */         } 
/* 67 */         for (Iterator<Integer> iterator = tempA.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 68 */           KeyValue keyValue1 = new KeyValue();
/* 69 */           keyValue1.key = key;
/* 70 */           keyValue1.value = ((Integer)tempA.get(Integer.valueOf(key))).intValue();
/* 71 */           artifactData.tempAttrs.add(keyValue1); }
/*    */ 
/*    */         
/* 74 */         if (!artifactEntity.getTrainDataMap().isEmpty() && artifactEntity.getTrianType() == 0) {
/* 75 */           artifactData.type = 1;
/*    */         } else {
/* 77 */           artifactData.type = artifactEntity.getTrianType();
/*    */         } 
/* 79 */         if (artifactEntity.getTrainDataMap().isEmpty()) {
/* 80 */           artifactData.num = 0;
/*    */         } else {
/* 82 */           artifactData.num = (artifactEntity.getTrainDataMap().size() == 10) ? 2 : 1;
/*    */         } 
/*    */         
/* 85 */         ((ArtifactListResponse)this.response).list.add(artifactData);
/*    */       } 
/*    */     } 
/* 88 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\artifact\ArtifactListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */