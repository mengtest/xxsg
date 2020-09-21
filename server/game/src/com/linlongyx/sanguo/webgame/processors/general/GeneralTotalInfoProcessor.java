/*     */ package com.linlongyx.sanguo.webgame.processors.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralTotalInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralTotalInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeneralTotalInfoProcessor
/*     */   extends ProcessorBase<GeneralTotalInfoRequest, GeneralTotalInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  26 */     this.response = (ResponseBase)new GeneralTotalInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GeneralTotalInfoRequest request) {
/*  31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 12)) {
/*  32 */       return 10061;
/*     */     }
/*  34 */     int level = request.level;
/*  35 */     if (level != 1 && level != 2 && level != 3) {
/*  36 */       return 11808;
/*     */     }
/*  38 */     if (level == 2) {
/*  39 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1201)) {
/*  40 */         return 10061;
/*     */       }
/*  42 */     } else if (level == 3 && 
/*  43 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 1202)) {
/*  44 */       return 10061;
/*     */     } 
/*     */     
/*  47 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  48 */     Map<Integer, Integer> stars = generalComponent.getStars();
/*  49 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/*  50 */     Map<Integer, Map<Integer, Integer>> configs = generalParameter.getConfigs();
/*  51 */     Set<Integer> notClearList = new HashSet<>();
/*  52 */     Set<Integer> diffClearList = new HashSet<>();
/*  53 */     Map<Integer, Map<Integer, Set<Integer>>> levelConfigs = generalParameter.getLevelConfig();
/*     */     
/*  55 */     for (Map.Entry<Integer, Map<Integer, Integer>> entry : configs.entrySet()) {
/*  56 */       Map<Integer, Integer> points = entry.getValue();
/*  57 */       int sum = 0;
/*  58 */       boolean hadAdd = false;
/*  59 */       boolean diffAdd = false;
/*  60 */       for (Map.Entry<Integer, Integer> entry1 : points.entrySet()) {
/*  61 */         int star = ((Integer)stars.getOrDefault(entry1.getValue(), Integer.valueOf(0))).intValue();
/*  62 */         GeneralInsBean generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(((Integer)entry1.getValue()).intValue(), GeneralInsBean.class);
/*  63 */         if (generalInsBean.getDifficult() == level) {
/*  64 */           sum += star;
/*  65 */           if (!hadAdd && 0 == star) {
/*  66 */             hadAdd = true;
/*  67 */             notClearList.add(entry.getKey());
/*     */           } 
/*     */         } 
/*  70 */         if (generalInsBean.getDifficult() == 2 && level == 1 && 
/*  71 */           !diffAdd && 0 == star) {
/*  72 */           diffAdd = true;
/*  73 */           diffClearList.add(entry.getKey());
/*     */         } 
/*     */       } 
/*     */       
/*  77 */       IntIntValue intIntValue = new IntIntValue();
/*  78 */       intIntValue.key = ((Integer)entry.getKey()).intValue();
/*  79 */       intIntValue.value = sum;
/*  80 */       ((GeneralTotalInfoResponse)this.response).list.add(intIntValue);
/*     */     } 
/*  82 */     int temp = ((Integer)generalParameter.getMaxChapter().getOrDefault(Integer.valueOf(level), Integer.valueOf(20))).intValue();
/*  83 */     for (Integer chapter : notClearList) {
/*  84 */       if (chapter.intValue() < temp) {
/*  85 */         temp = chapter.intValue();
/*     */       }
/*     */     } 
/*  88 */     int curChapter = temp;
/*  89 */     generalComponent.setCurChapter(curChapter);
/*     */     
/*  91 */     int tempDiff = ((Integer)generalParameter.getMaxChapter().getOrDefault(Integer.valueOf(2), Integer.valueOf(20))).intValue();
/*  92 */     for (Integer chapter : diffClearList) {
/*  93 */       if (chapter.intValue() < tempDiff) {
/*  94 */         tempDiff = chapter.intValue();
/*     */       }
/*     */     } 
/*  97 */     if (level == 1) {
/*  98 */       if (diffClearList.isEmpty()) {
/*  99 */         ((GeneralTotalInfoResponse)this.response).diffChapter = ((Integer)generalParameter.getMaxChapter().getOrDefault(Integer.valueOf(2), Integer.valueOf(20))).intValue() + 1;
/*     */       } else {
/* 101 */         ((GeneralTotalInfoResponse)this.response).diffChapter = tempDiff;
/*     */       } 
/*     */     }
/* 104 */     ((GeneralTotalInfoResponse)this.response).list.removeIf(intIntValue -> (intIntValue.key > curChapter));
/* 105 */     if (notClearList.isEmpty()) {
/* 106 */       ((GeneralTotalInfoResponse)this.response).curChapter = ((Integer)generalParameter.getMaxChapter().getOrDefault(Integer.valueOf(level), Integer.valueOf(20))).intValue() + 1;
/*     */     } else {
/* 108 */       ((GeneralTotalInfoResponse)this.response).curChapter = curChapter;
/*     */     } 
/* 110 */     ((GeneralTotalInfoResponse)this.response).orderNum = generalComponent.getBuyTime();
/* 111 */     ((GeneralTotalInfoResponse)this.response).level = level;
/* 112 */     ((GeneralTotalInfoResponse)this.response).currOrder = GeneralUtil.getLeftOrder(generalComponent);
/* 113 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralTotalInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */