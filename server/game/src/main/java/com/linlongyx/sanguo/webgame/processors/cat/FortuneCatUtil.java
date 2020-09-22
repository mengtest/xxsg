/*     */ package com.linlongyx.sanguo.webgame.processors.cat;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneCatBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneTimeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatPushResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FortuneCatUtil
/*     */ {
/*  26 */   private static List<KeyValue> rewardList = new LinkedList<>();
/*  27 */   private static List<KeyValue> rewardList2 = new LinkedList<>();
/*  28 */   private static final Object LOCK = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FortuneCatBean.TimesBean getFortuneCatBean(int count, int act) {
/*  37 */     FortuneCatBean fortuneCatBean = (FortuneCatBean)JsonTableService.getJsonData(act, FortuneCatBean.class);
/*  38 */     if (null != fortuneCatBean) {
/*  39 */       FortuneCatBean.TimesBean timesBean = (FortuneCatBean.TimesBean)fortuneCatBean.getTimes().get(Integer.valueOf(count + 1));
/*  40 */       if (null != timesBean) {
/*  41 */         return timesBean;
/*     */       }
/*  43 */       Map<Integer, Object> map = JsonTableService.getJsonTable(FortuneCatBean.class);
/*  44 */       int max = 0;
/*  45 */       for (Map.Entry<Integer, FortuneCatBean.TimesBean> entry : (Iterable<Map.Entry<Integer, FortuneCatBean.TimesBean>>)fortuneCatBean.getTimes().entrySet()) {
/*  46 */         if (((Integer)entry.getKey()).intValue() > max) {
/*  47 */           max = ((Integer)entry.getKey()).intValue();
/*     */         }
/*     */       } 
/*  50 */       timesBean = (FortuneCatBean.TimesBean)fortuneCatBean.getTimes().get(Integer.valueOf(max));
/*  51 */       return timesBean;
/*     */     } 
/*     */     
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRewardList(int num, String name, int type) {
/*  65 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/*  66 */     KeyValue keyValue = new KeyValue();
/*  67 */     keyValue.value = num;
/*  68 */     keyValue.valueStr = name;
/*  69 */     synchronized (LOCK) {
/*  70 */       if (type == 1) {
/*  71 */         rewardList.add(keyValue);
/*  72 */         if (rewardList.size() > fortuneCatParameter.getMaxLength()) {
/*  73 */           rewardList.remove(0);
/*     */         }
/*  75 */       } else if (type == 2) {
/*  76 */         rewardList2.add(keyValue);
/*  77 */         if (rewardList2.size() > fortuneCatParameter.getMaxLength()) {
/*  78 */           rewardList2.remove(0);
/*     */         }
/*     */       } 
/*     */     } 
/*  82 */     FortuneCatPushResponse fortuneCatPushResponse = new FortuneCatPushResponse();
/*  83 */     fortuneCatPushResponse.data.value = num;
/*  84 */     fortuneCatPushResponse.data.valueStr = name;
/*  85 */     LookUpService.broadcast((ResponseBase)fortuneCatPushResponse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getRewardList(List<KeyValue> list, int type) {
/*  94 */     synchronized (LOCK) {
/*  95 */       if (type == 1) {
/*  96 */         list.addAll(rewardList);
/*  97 */       } else if (type == 2) {
/*  98 */         list.addAll(rewardList2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getFortuneCatCCY(FortuneCatBean.TimesBean fortuneCatBean, FortuneCatEntity fortuneCatEntity) {
/* 109 */     if (null == fortuneCatBean) {
/* 110 */       LogUtil.errorLog(new Object[] { "FortuneCatUtil", Long.valueOf(fortuneCatEntity.getPlayerId()), Integer.valueOf(fortuneCatEntity.getCount()) });
/*     */       return;
/*     */     } 
/* 113 */     int weightAll = fortuneCatBean.getProF() + fortuneCatBean.getProS() + fortuneCatBean.getProT();
/* 114 */     int rand = RandUtil.randNum(0, weightAll);
/* 115 */     if (rand <= fortuneCatBean.getProF()) {
/* 116 */       fortuneCatEntity.setMaxCCY(fortuneCatBean.getFirstL());
/* 117 */       fortuneCatEntity.setMinCCY(fortuneCatBean.getFirstR());
/* 118 */     } else if (rand <= fortuneCatBean.getProS() + fortuneCatBean.getProF()) {
/* 119 */       fortuneCatEntity.setMaxCCY(fortuneCatBean.getSecondL());
/* 120 */       fortuneCatEntity.setMinCCY(fortuneCatBean.getSecondR());
/* 121 */     } else if (rand <= fortuneCatBean.getProT() + fortuneCatBean.getProS() + fortuneCatBean.getProF()) {
/* 122 */       fortuneCatEntity.setMaxCCY(fortuneCatBean.getThirdL());
/* 123 */       fortuneCatEntity.setMinCCY(fortuneCatBean.getThirdR());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateActCharge(IPlayer player, long value) {
/* 134 */     FortuneCatComponent fortuneCatComponent = (FortuneCatComponent)player.createIfNotExist(FortuneCatComponent.class);
/* 135 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/* 136 */     List<Integer> list = fortuneCatParameter.getActId(true);
/* 137 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 138 */       FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)JsonTableService.getJsonData(id, FortuneTimeBean.class);
/* 139 */       if (null != fortuneTimeBean && fortuneTimeBean.getType() == 2) {
/* 140 */         FortuneCatEntity fortuneCatEntity = fortuneCatComponent.getEntity(id);
/* 141 */         fortuneCatEntity.setActCharge(fortuneCatEntity.getActCharge() + value);
/* 142 */         fortuneCatComponent.updateActChargeDB(id);
/*     */       }  }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cat\FortuneCatUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */