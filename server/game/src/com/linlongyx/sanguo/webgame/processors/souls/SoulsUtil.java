/*    */ package com.linlongyx.sanguo.webgame.processors.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsLevelBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulsUtil
/*    */ {
/*    */   public static ArrayList<Integer> getSoulsHandBook(SoulsComponent soulsComponent) {
/* 31 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 32 */     Map<Integer, Object> map = JsonTableService.getJsonTable(SoulsBean.class);
/* 33 */     for (Object o : map.values()) {
/* 34 */       SoulsBean soulsBean = (SoulsBean)o;
/* 35 */       int size = soulsBean.getPet().size();
/* 36 */       int num = 0;
/* 37 */       boolean hash = false;
/* 38 */       for (IMapEntity iMapEntity : soulsComponent.getEntityMap().values()) {
/* 39 */         SoulsEntity soulsEntity = (SoulsEntity)iMapEntity;
/* 40 */         for (Iterator<Integer> iterator = soulsBean.getPet().iterator(); iterator.hasNext(); ) { int soulsId = ((Integer)iterator.next()).intValue();
/* 41 */           if (soulsId == soulsEntity.getId()) {
/* 42 */             num++;
/*    */           }
/* 44 */           if (num == size) {
/* 45 */             arrayList.add(Integer.valueOf(soulsBean.getId()));
/* 46 */             hash = true;
/*    */             break;
/*    */           }  }
/*    */         
/* 50 */         if (hash) {
/*    */           break;
/*    */         }
/*    */       } 
/*    */     } 
/* 55 */     return arrayList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArrayList<Reward> soulsRecast(SoulsEntity soulsEntity, SoulsComponent soulsComponent) {
/* 65 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 66 */     int level = soulsEntity.getLevel();
/* 67 */     if (level > 1) {
/* 68 */       SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/* 69 */       SoulsLevelBean soulsLevelBean = (SoulsLevelBean)JsonTableService.getJsonData(level, SoulsLevelBean.class);
/*    */       
/* 71 */       SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(soulsEntity.getId(), SoulsStarBean.class);
/* 72 */       int breankPar = ((SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(soulsEntity.getStar()))).getBreakPar();
/*    */       
/* 74 */       ArrayList<Reward> rewards1 = FinanceUtil.transform(soulsLevelBean.getTotalConsume());
/* 75 */       for (Reward reward : rewards1) {
/* 76 */         if (!soulsParameter.getExpMap().containsKey(Integer.valueOf(reward.id))) {
/* 77 */           reward.num = (long)Math.floor((reward.num * breankPar) / 10000.0D);
/*    */         }
/* 79 */         if (reward.num == 0L) {
/*    */           continue;
/*    */         }
/* 82 */         rewards.add(reward);
/*    */       } 
/*    */       
/* 85 */       soulsEntity.setLevel(1);
/* 86 */       soulsComponent.updateLevelDB(soulsEntity.getId());
/* 87 */       soulsEntity.setExp(0);
/* 88 */       soulsComponent.updateExpDB(soulsEntity.getId());
/*    */     } 
/* 90 */     return rewards;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\souls\SoulsUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */