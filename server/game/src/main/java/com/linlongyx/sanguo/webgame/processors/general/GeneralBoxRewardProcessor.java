/*     */ package com.linlongyx.sanguo.webgame.processors.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.GeneralChapterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBoxRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBoxRewardResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GeneralBoxRewardProcessor
/*     */   extends ProcessorBase<GeneralBoxRewardRequest, GeneralBoxRewardResponse> {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new GeneralBoxRewardResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GeneralBoxRewardRequest request) {
/*  32 */     int chapter = request.chapter;
/*  33 */     int box = request.box;
/*  34 */     int level = request.level;
/*  35 */     GeneralChapterBean generalChapterBean = (GeneralChapterBean)JsonTableService.getJsonData(chapter, GeneralChapterBean.class);
/*  36 */     if (null == generalChapterBean) {
/*  37 */       return 11201;
/*     */     }
/*  39 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/*  40 */     Map<Integer, Integer> pointIdMap = generalParameter.getPointIdMap(chapter);
/*  41 */     Map<Integer, Set<Integer>> levelMap = (Map<Integer, Set<Integer>>)generalParameter.getLevelConfig().get(Integer.valueOf(chapter));
/*  42 */     if (null == pointIdMap || null == levelMap) {
/*  43 */       return 11203;
/*     */     }
/*  45 */     GeneralChapterBean.DifficultyBean difficultyBean = (GeneralChapterBean.DifficultyBean)generalChapterBean.getDifficulty().get(Integer.valueOf(level));
/*  46 */     if (null == difficultyBean) {
/*  47 */       return 11203;
/*     */     }
/*  49 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  50 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  51 */     Map<Integer, List<Integer>> boxReward = new HashMap<>();
/*  52 */     if (level == 1) {
/*  53 */       if (4 == box) {
/*  54 */         rewards = FinanceUtil.transform(difficultyBean.getFReward());
/*  55 */       } else if (8 == box) {
/*  56 */         rewards = FinanceUtil.transform(difficultyBean.getEReward());
/*  57 */       } else if (12 == box) {
/*  58 */         rewards = FinanceUtil.transform(difficultyBean.getSReward());
/*     */       } else {
/*  60 */         return 11201;
/*     */       } 
/*  62 */       boxReward = generalComponent.getBoxReward();
/*  63 */     } else if (level == 2) {
/*  64 */       if (4 == box) {
/*  65 */         rewards = FinanceUtil.transform(difficultyBean.getFReward());
/*  66 */       } else if (8 == box) {
/*  67 */         rewards = FinanceUtil.transform(difficultyBean.getEReward());
/*  68 */       } else if (12 == box) {
/*  69 */         rewards = FinanceUtil.transform(difficultyBean.getSReward());
/*     */       } else {
/*  71 */         return 11201;
/*     */       } 
/*  73 */       boxReward = generalComponent.getDiffboxReward();
/*  74 */     } else if (level == 3) {
/*  75 */       if (4 == box) {
/*  76 */         rewards = FinanceUtil.transform(difficultyBean.getFReward());
/*  77 */       } else if (8 == box) {
/*  78 */         rewards = FinanceUtil.transform(difficultyBean.getEReward());
/*  79 */       } else if (12 == box) {
/*  80 */         rewards = FinanceUtil.transform(difficultyBean.getSReward());
/*     */       } else {
/*  82 */         return 11201;
/*     */       } 
/*  84 */       boxReward = generalComponent.getNightboxReward();
/*     */     } 
/*     */     
/*  87 */     Map<Integer, Integer> stars = generalComponent.getStars();
/*  88 */     int star = getStart(stars, pointIdMap, level, levelMap);
/*  89 */     if (star < box) {
/*  90 */       return 11202;
/*     */     }
/*     */     
/*  93 */     List<Integer> list = boxReward.getOrDefault(Integer.valueOf(chapter), new ArrayList<>());
/*  94 */     if (list.contains(Integer.valueOf(box))) {
/*  95 */       return 10091;
/*     */     }
/*  97 */     list.add(Integer.valueOf(box));
/*  98 */     boxReward.put(Integer.valueOf(chapter), list);
/*  99 */     if (level == 1) {
/* 100 */       generalComponent.setBoxReward(boxReward);
/* 101 */     } else if (level == 2) {
/* 102 */       generalComponent.setDiffboxReward(boxReward);
/* 103 */     } else if (level == 3) {
/* 104 */       generalComponent.setNightboxReward(boxReward);
/*     */     } 
/*     */     
/* 107 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.GeneralBoxReward, true);
/* 108 */     ((GeneralBoxRewardResponse)this.response).chapter = chapter;
/* 109 */     ((GeneralBoxRewardResponse)this.response).box = box;
/* 110 */     ((GeneralBoxRewardResponse)this.response).level = level;
/* 111 */     LogUtils.errorLog(new Object[] { "GeneralBoxReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(chapter), Integer.valueOf(box) });
/* 112 */     return 0;
/*     */   }
/*     */   
/*     */   private int getStart(Map<Integer, Integer> stars, Map<Integer, Integer> pointIdMap, int level, Map<Integer, Set<Integer>> levelMap) {
/* 116 */     int star = 0;
/* 117 */     for (Map.Entry<Integer, Integer> entry : pointIdMap.entrySet()) {
/* 118 */       Set<Integer> set = levelMap.get(Integer.valueOf(level));
/* 119 */       if (null != set && !set.contains(entry.getValue())) {
/*     */         continue;
/*     */       }
/* 122 */       star += ((Integer)stars.getOrDefault(entry.getValue(), Integer.valueOf(0))).intValue();
/*     */     } 
/* 124 */     return star;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralBoxRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */