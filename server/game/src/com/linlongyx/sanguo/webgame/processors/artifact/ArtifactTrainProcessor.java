/*     */ package com.linlongyx.sanguo.webgame.processors.artifact;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArtifactBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactTrainRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactTrainResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TrainData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ArtifactTrainProcessor
/*     */   extends ProcessorBase<ArtifactTrainRequest, ArtifactTrainResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  33 */     this.response = (ResponseBase)new ArtifactTrainResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ArtifactTrainRequest request) {
/*     */     ArrayList<RewardBean> cost;
/*  38 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 131))
/*  39 */       return 10061; 
/*  40 */     int id = request.id;
/*  41 */     ArtifactBean artifactBean = (ArtifactBean)JsonTableService.getJsonData(id, ArtifactBean.class);
/*  42 */     if (null == artifactBean) {
/*  43 */       return 12901;
/*     */     }
/*     */     
/*  46 */     ArtifactComponent artifactComponent = (ArtifactComponent)playerSession.getPlayer().createIfNotExist(ArtifactComponent.class);
/*  47 */     ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(id);
/*  48 */     if (null == artifactEntity) {
/*  49 */       return 12904;
/*     */     }
/*  51 */     int type = request.type;
/*  52 */     int num = request.num;
/*  53 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  54 */     if (num < 0) {
/*  55 */       return 12905;
/*     */     }
/*  57 */     int maxNum = 0;
/*  58 */     for (AttrBean attrBean : artifactBean.getMaxAttr()) {
/*  59 */       int attrValue = ((Integer)artifactEntity.getAttrs().getOrDefault(Integer.valueOf(attrBean.getId()), Integer.valueOf(0))).intValue();
/*  60 */       if (attrValue >= attrBean.getNum()) {
/*  61 */         maxNum++;
/*     */       }
/*     */     } 
/*  64 */     if (maxNum == artifactBean.getMaxAttr().size()) {
/*  65 */       return 12909;
/*     */     }
/*     */     
/*  68 */     Map<Integer, Integer> rollLowAttr = new HashMap<>();
/*  69 */     Map<Integer, Integer> rollHighAttr = new HashMap<>();
/*  70 */     if (1 == type) {
/*  71 */       cost = artifactBean.getPrimaryCost();
/*  72 */       for (ArtifactBean.PrimaryRollBean primaryRollBean : artifactBean.getPrimaryRoll()) {
/*  73 */         rollLowAttr.put(Integer.valueOf(primaryRollBean.getId()), Integer.valueOf(primaryRollBean.getLow()));
/*  74 */         rollHighAttr.put(Integer.valueOf(primaryRollBean.getId()), Integer.valueOf(primaryRollBean.getHigh()));
/*     */       } 
/*  76 */     } else if (2 == type) {
/*  77 */       cost = artifactBean.getMiddleCost();
/*  78 */       for (ArtifactBean.MiddleRollBean middleRollBean : artifactBean.getMiddleRoll()) {
/*  79 */         rollLowAttr.put(Integer.valueOf(middleRollBean.getId()), Integer.valueOf(middleRollBean.getLow()));
/*  80 */         rollHighAttr.put(Integer.valueOf(middleRollBean.getId()), Integer.valueOf(middleRollBean.getHigh()));
/*     */       } 
/*  82 */     } else if (3 == type) {
/*  83 */       cost = artifactBean.getAdvancedCost();
/*  84 */       for (ArtifactBean.AdvancedRollBean advancedRollBean : artifactBean.getAdvancedRoll()) {
/*  85 */         rollLowAttr.put(Integer.valueOf(advancedRollBean.getId()), Integer.valueOf(advancedRollBean.getLow()));
/*  86 */         rollHighAttr.put(Integer.valueOf(advancedRollBean.getId()), Integer.valueOf(advancedRollBean.getHigh()));
/*     */       } 
/*     */     } else {
/*  89 */       return 12906;
/*     */     } 
/*  91 */     short code = CostUtil.check(cost, num, playerSession, bagComponent);
/*  92 */     if (0 != code) {
/*  93 */       return code;
/*     */     }
/*  95 */     CostUtil.cost(cost, num, playerSession, bagComponent, ResourceEvent.ArtifactTrain);
/*  96 */     Map<Integer, TrainData> trainDataMap = artifactEntity.getTrainDataMap();
/*  97 */     trainDataMap.clear();
/*  98 */     train(artifactEntity, artifactBean, rollLowAttr, rollHighAttr, num, trainDataMap);
/*     */     
/* 100 */     artifactComponent.updateTrainDataMapToDB(id);
/* 101 */     artifactEntity.setTrianType(type);
/* 102 */     artifactComponent.updateTrianTypeToDB(id);
/*     */     
/* 104 */     ((ArtifactTrainResponse)this.response).id = id;
/* 105 */     ((ArtifactTrainResponse)this.response).type = type;
/* 106 */     ((ArtifactTrainResponse)this.response).num = num;
/*     */     
/* 108 */     Map<Integer, Integer> tempA = new HashMap<>();
/* 109 */     for (Map.Entry<Integer, TrainData> entry : trainDataMap.entrySet()) {
/* 110 */       TrainData data = entry.getValue();
/* 111 */       for (KeyValue keyValue : data.attrs) {
/* 112 */         tempA.put(Integer.valueOf((int)keyValue.key), Integer.valueOf(((Integer)tempA.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue() + (int)keyValue.value));
/*     */       }
/*     */     } 
/* 115 */     for (Iterator<Integer> iterator = tempA.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 116 */       KeyValue keyValue = new KeyValue();
/* 117 */       keyValue.key = key;
/* 118 */       keyValue.value = ((Integer)tempA.get(Integer.valueOf(key))).intValue();
/* 119 */       ((ArtifactTrainResponse)this.response).tempAttrs.add(keyValue); }
/*     */ 
/*     */     
/* 122 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void train(ArtifactEntity artifactEntity, ArtifactBean artifactBean, Map<Integer, Integer> rollLowAttr, Map<Integer, Integer> rollHighAttr, int num, Map<Integer, TrainData> trainDataMap) {
/* 136 */     ArrayList<AttrBean> maxAttr = artifactBean.getMaxAttr();
/* 137 */     int size = maxAttr.size();
/* 138 */     Map<Integer, Integer> attrs = new HashMap<>(artifactEntity.getAttrs());
/*     */     
/* 140 */     Map<Integer, Integer> attrMax = new HashMap<>();
/* 141 */     for (AttrBean attrBean : maxAttr) {
/* 142 */       attrMax.put(Integer.valueOf(attrBean.getId()), Integer.valueOf((int)attrBean.getNum()));
/*     */     }
/*     */     
/* 145 */     List<Integer> randomAttr = new ArrayList<>();
/* 146 */     for (int i = 1; i <= num; i++) {
/* 147 */       int[] randomArray = RandUtil.randomArray(0, size - 1, artifactBean.getRollNum());
/* 148 */       if (null != randomArray) {
/*     */ 
/*     */ 
/*     */         
/* 152 */         randomAttr.clear();
/* 153 */         for (int j : randomArray) {
/* 154 */           AttrBean attrBean = maxAttr.get(j);
/* 155 */           randomAttr.add(Integer.valueOf(attrBean.getId()));
/*     */         } 
/* 157 */         TrainData finalTrainData = new TrainData();
/* 158 */         finalTrainData.id = i;
/* 159 */         for (Integer attr : randomAttr) {
/* 160 */           KeyValue finalKeyValue = new KeyValue();
/* 161 */           int tempValue = ((Integer)attrs.getOrDefault(attr, Integer.valueOf(0))).intValue();
/* 162 */           for (Map.Entry<Integer, TrainData> trainDataEntry : trainDataMap.entrySet()) {
/* 163 */             TrainData trainData = trainDataEntry.getValue();
/* 164 */             for (KeyValue trainDataKeyValue : trainData.attrs) {
/* 165 */               if ((int)trainDataKeyValue.key == attr.intValue()) {
/* 166 */                 tempValue = (int)(tempValue + trainDataKeyValue.value);
/*     */               }
/*     */             } 
/*     */           } 
/* 170 */           int max = ((Integer)attrMax.getOrDefault(attr, Integer.valueOf(0))).intValue();
/* 171 */           int minRoll = ((Integer)rollLowAttr.getOrDefault(attr, Integer.valueOf(0))).intValue();
/* 172 */           int maxRoll = ((Integer)rollHighAttr.getOrDefault(attr, Integer.valueOf(0))).intValue();
/* 173 */           int minValue = getMinValue(minRoll, tempValue, max);
/* 174 */           int maxValue = getMaxValue(maxRoll, max, tempValue);
/* 175 */           int randValue = RandUtil.randNum(minValue, maxValue);
/*     */           
/* 177 */           finalKeyValue.key = attr.intValue();
/* 178 */           finalKeyValue.value = randValue;
/* 179 */           finalTrainData.attrs.add(finalKeyValue);
/*     */         } 
/* 181 */         finalTrainData.recommend = isRecommend(finalTrainData.attrs);
/* 182 */         trainDataMap.put(Integer.valueOf(i), finalTrainData);
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     List<Integer> removes = new ArrayList<>();
/* 187 */     for (Map.Entry<Integer, TrainData> entry : trainDataMap.entrySet()) {
/* 188 */       TrainData trainData = entry.getValue();
/* 189 */       if (trainData.recommend) {
/* 190 */         Map<Integer, Integer> temp = new HashMap<>(attrs);
/* 191 */         for (KeyValue keyValue : trainData.attrs) {
/* 192 */           int value = ((Integer)temp.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue() + (int)keyValue.value;
/* 193 */           int max = ((Integer)attrMax.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue();
/* 194 */           temp.put(Integer.valueOf((int)keyValue.key), Integer.valueOf(Math.min(value, max)));
/*     */         } 
/* 196 */         long oldPower = getPower(attrs);
/* 197 */         long newPower = getPower(temp);
/* 198 */         if (newPower <= oldPower) {
/* 199 */           removes.add(entry.getKey()); continue;
/*     */         } 
/* 201 */         for (KeyValue keyValue : trainData.attrs) {
/* 202 */           int value = ((Integer)attrs.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue() + (int)keyValue.value;
/* 203 */           int max = ((Integer)attrMax.getOrDefault(Integer.valueOf((int)keyValue.key), Integer.valueOf(0))).intValue();
/* 204 */           attrs.put(Integer.valueOf((int)keyValue.key), Integer.valueOf(Math.min(value, max)));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     for (Integer trainId : removes) {
/* 210 */       TrainData trainData = trainDataMap.get(trainId);
/* 211 */       trainData.recommend = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMinValue(int roll, int tempValue, int max) {
/* 223 */     if (tempValue >= max) {
/* 224 */       return 0;
/*     */     }
/* 226 */     if (tempValue <= 0) {
/* 227 */       return 0;
/*     */     }
/* 229 */     if (tempValue >= roll) {
/* 230 */       return -roll;
/*     */     }
/* 232 */     return -Math.min(roll, tempValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMaxValue(int roll, int max, int tempValue) {
/* 244 */     if (tempValue >= max) {
/* 245 */       return 0;
/*     */     }
/* 247 */     if (tempValue + roll <= max) {
/* 248 */       return roll;
/*     */     }
/*     */     
/* 251 */     return roll;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isRecommend(ArrayList<KeyValue> attrs) {
/* 262 */     long sum = 0L;
/* 263 */     for (KeyValue keyValue : attrs) {
/* 264 */       sum += keyValue.value * FightConstant.getWeight((int)keyValue.key);
/*     */     }
/* 266 */     return (sum > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getPower(Map<Integer, Integer> attrs) {
/* 276 */     long sum = 0L;
/* 277 */     for (Map.Entry<Integer, Integer> entry : attrs.entrySet()) {
/* 278 */       sum += ((Integer)entry.getValue()).intValue() * FightConstant.getWeight(((Integer)entry.getKey()).intValue());
/*     */     }
/* 280 */     return sum;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\artifact\ArtifactTrainProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */