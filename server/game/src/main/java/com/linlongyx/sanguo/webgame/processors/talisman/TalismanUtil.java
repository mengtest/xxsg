/*    */ package com.linlongyx.sanguo.webgame.processors.talisman;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxlibBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ public class TalismanUtil
/*    */ {
/*    */   public static ArrayList<IntIntValue> refreshItems(int actId) {
/* 19 */     ArrayList<IntIntValue> resultList = new ArrayList<>();
/* 20 */     TalismanBoxBean talismanBoxBean = (TalismanBoxBean)JsonTableService.getJsonData(actId, TalismanBoxBean.class);
/* 21 */     int prob = 0;
/* 22 */     for (TalismanBoxBean.TrumpNumBean trumpNumBean : talismanBoxBean.getTrumpNum()) {
/* 23 */       prob += trumpNumBean.getPro();
/*    */     }
/* 25 */     int randNum = RandUtil.randNum(prob);
/* 26 */     int trumpNum = -1;
/* 27 */     prob = 0;
/* 28 */     for (TalismanBoxBean.TrumpNumBean trumpNumBean : talismanBoxBean.getTrumpNum()) {
/* 29 */       prob += trumpNumBean.getPro();
/* 30 */       trumpNum = trumpNumBean.getNum();
/* 31 */       if (randNum <= prob) {
/*    */         break;
/*    */       }
/*    */     } 
/* 35 */     int basicNum = talismanBoxBean.getMaxNum() - trumpNum;
/*    */     
/* 37 */     TalismanBoxlibBean basicLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getBasicLib(), TalismanBoxlibBean.class);
/* 38 */     TalismanBoxlibBean trumpLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getTrumpLib(), TalismanBoxlibBean.class);
/* 39 */     Map<Integer, Integer> basicLibMap = new TreeMap<>();
/* 40 */     Map<Integer, Integer> trumpLibMap = new TreeMap<>();
/* 41 */     int basicLibPro = 0, trumpLibPro = 0;
/* 42 */     for (Map.Entry<Integer, TalismanBoxlibBean.IdBean> kv : (Iterable<Map.Entry<Integer, TalismanBoxlibBean.IdBean>>)basicLib.getId().entrySet()) {
/* 43 */       basicLibPro += ((TalismanBoxlibBean.IdBean)kv.getValue()).getRafflePer();
/* 44 */       basicLibMap.put(kv.getKey(), Integer.valueOf(((TalismanBoxlibBean.IdBean)kv.getValue()).getRafflePer()));
/*    */     } 
/*    */     
/* 47 */     for (Map.Entry<Integer, TalismanBoxlibBean.IdBean> kv : (Iterable<Map.Entry<Integer, TalismanBoxlibBean.IdBean>>)trumpLib.getId().entrySet()) {
/* 48 */       trumpLibPro += ((TalismanBoxlibBean.IdBean)kv.getValue()).getRafflePer();
/* 49 */       trumpLibMap.put(kv.getKey(), Integer.valueOf(((TalismanBoxlibBean.IdBean)kv.getValue()).getRafflePer()));
/*    */     } 
/*    */     
/* 52 */     Set<Integer> resultSet = new HashSet<>();
/* 53 */     int count = 0, targetTrumpId = -1, targetBasicId = -1;
/* 54 */     while (count < trumpNum) {
/* 55 */       int trumpRandNum = RandUtil.randNum(trumpLibPro);
/* 56 */       int tmp = 0;
/* 57 */       for (Map.Entry<Integer, Integer> kv : trumpLibMap.entrySet()) {
/* 58 */         tmp += ((Integer)kv.getValue()).intValue();
/* 59 */         targetTrumpId = ((Integer)kv.getKey()).intValue();
/* 60 */         if (trumpRandNum <= tmp) {
/*    */           break;
/*    */         }
/*    */       } 
/* 64 */       if (!resultSet.contains(Integer.valueOf(targetTrumpId))) {
/* 65 */         resultSet.add(Integer.valueOf(targetTrumpId));
/* 66 */         count++;
/* 67 */         IntIntValue intIntValue = new IntIntValue();
/* 68 */         intIntValue.key = targetTrumpId;
/* 69 */         intIntValue.value = talismanBoxBean.getTrumpLib();
/* 70 */         resultList.add(intIntValue);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 75 */     count = 0;
/* 76 */     while (count < basicNum) {
/* 77 */       int basicRandNum = RandUtil.randNum(basicLibPro);
/* 78 */       int tmp = 0;
/* 79 */       for (Map.Entry<Integer, Integer> kv : basicLibMap.entrySet()) {
/* 80 */         tmp += ((Integer)kv.getValue()).intValue();
/* 81 */         targetBasicId = ((Integer)kv.getKey()).intValue();
/* 82 */         if (basicRandNum <= tmp) {
/*    */           break;
/*    */         }
/*    */       } 
/* 86 */       if (!resultSet.contains(Integer.valueOf(targetBasicId))) {
/* 87 */         resultSet.add(Integer.valueOf(targetBasicId));
/* 88 */         count++;
/* 89 */         IntIntValue intIntValue = new IntIntValue();
/* 90 */         intIntValue.key = targetBasicId;
/* 91 */         intIntValue.value = talismanBoxBean.getBasicLib();
/* 92 */         resultList.add(intIntValue);
/*    */       } 
/*    */     } 
/*    */     
/* 96 */     Collections.shuffle(resultList);
/* 97 */     return resultList;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\talisman\TalismanUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */