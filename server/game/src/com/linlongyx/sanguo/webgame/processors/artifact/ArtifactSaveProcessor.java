/*     */ package com.linlongyx.sanguo.webgame.processors.artifact;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArtifactBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactSaveRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactSaveResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TrainData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArtifactSaveProcessor
/*     */   extends ProcessorBase<ArtifactSaveRequest, ArtifactSaveResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new ArtifactSaveResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ArtifactSaveRequest request) {
/*  36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 131)) {
/*  37 */       return 10061;
/*     */     }
/*  39 */     int id = request.id;
/*  40 */     ArtifactBean artifactBean = (ArtifactBean)JsonTableService.getJsonData(id, ArtifactBean.class);
/*  41 */     if (null == artifactBean) {
/*  42 */       return 12901;
/*     */     }
/*     */     
/*  45 */     ArtifactComponent artifactComponent = (ArtifactComponent)playerSession.getPlayer().createIfNotExist(ArtifactComponent.class);
/*  46 */     ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(id);
/*  47 */     if (null == artifactEntity) {
/*  48 */       return 12904;
/*     */     }
/*     */     
/*  51 */     Map<Integer, TrainData> trainDataMap = artifactEntity.getTrainDataMap();
/*  52 */     Map<Integer, Integer> attrs = artifactEntity.getAttrs();
/*  53 */     if (trainDataMap.isEmpty()) {
/*  54 */       return 12907;
/*     */     }
/*     */     
/*  57 */     for (TrainData trainData : trainDataMap.values()) {
/*  58 */       for (KeyValue keyValue : trainData.attrs) {
/*  59 */         attrs.put(Integer.valueOf((int)keyValue.key), Integer.valueOf(((Integer)attrs.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue() + (int)keyValue.value));
/*     */       }
/*     */     } 
/*     */     
/*  63 */     Map<Integer, Integer> randomAttrMax = new HashMap<>();
/*  64 */     ArrayList<AttrBean> maxAttr = artifactBean.getMaxAttr();
/*  65 */     for (AttrBean attrBean : maxAttr) {
/*  66 */       randomAttrMax.put(Integer.valueOf(attrBean.getId()), Integer.valueOf((int)attrBean.getNum()));
/*     */     }
/*     */     
/*  69 */     List<Integer> minList = new ArrayList<>();
/*  70 */     List<Integer> maxList = new ArrayList<>();
/*  71 */     for (Map.Entry<Integer, Integer> entry : attrs.entrySet()) {
/*  72 */       if (((Integer)entry.getValue()).intValue() < 0) {
/*  73 */         minList.add(entry.getKey()); continue;
/*  74 */       }  if (((Integer)entry.getValue()).intValue() > ((Integer)randomAttrMax.getOrDefault(entry.getKey(), (V)Integer.valueOf(0))).intValue()) {
/*  75 */         maxList.add(entry.getKey());
/*     */       }
/*     */     } 
/*  78 */     for (Integer attr : minList) {
/*  79 */       attrs.put(attr, Integer.valueOf(0));
/*     */     }
/*  81 */     for (Integer attr : maxList) {
/*  82 */       attrs.put(attr, randomAttrMax.getOrDefault(attr, Integer.valueOf(0)));
/*     */     }
/*  84 */     artifactEntity.setAttrs(attrs);
/*  85 */     artifactComponent.updateAttrsToDB(id);
/*  86 */     trainDataMap.clear();
/*  87 */     artifactEntity.setTrainDataMap(trainDataMap);
/*  88 */     artifactComponent.updateTrainDataMapToDB(id);
/*  89 */     artifactEntity.setTrianType(0);
/*  90 */     artifactComponent.updateTrianTypeToDB(id);
/*     */ 
/*     */     
/*  93 */     AttrUpdateUtil.refreshArtifact(playerSession);
/*     */     
/*  95 */     ((ArtifactSaveResponse)this.response).data.id = id;
/*  96 */     ((ArtifactSaveResponse)this.response).data.type = artifactEntity.getTrianType();
/*  97 */     ((ArtifactSaveResponse)this.response).data.num = 0;
/*     */     
/*  99 */     for (AttrBean attrBean : artifactBean.getMaxAttr()) {
/* 100 */       KeyValue keyValue = new KeyValue();
/* 101 */       keyValue.key = attrBean.getId();
/* 102 */       keyValue.value = ((Integer)attrs.getOrDefault(Integer.valueOf(attrBean.getId()), Integer.valueOf(0))).intValue();
/* 103 */       ((ArtifactSaveResponse)this.response).data.attrs.add(keyValue);
/*     */     } 
/* 105 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\artifact\ArtifactSaveProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */