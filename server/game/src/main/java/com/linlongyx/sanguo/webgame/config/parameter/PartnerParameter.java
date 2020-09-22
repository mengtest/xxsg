/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int costItemId;
/*    */   private int destinyLevelLimit;
/* 23 */   private Map<Integer, Integer> assistPos = new HashMap<>();
/*    */   private int maxAssist;
/* 25 */   private Map<Integer, Set<Integer>> levelMap = new HashMap<>();
/*    */   
/*    */   public PartnerParameter() {
/* 28 */     super(33);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 34 */     this.costItemId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 35 */     this.destinyLevelLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 36 */     this.assistPos.clear();
/* 37 */     String[] strings3 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(";");
/* 38 */     for (String str2 : strings3) {
/* 39 */       String[] str = str2.split(":");
/* 40 */       this.assistPos.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*    */     } 
/* 42 */     this.maxAssist = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 43 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FighterReincarnBean.class);
/* 44 */     for (Object o : map.values()) {
/* 45 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)o;
/* 46 */       int openLevel = fighterReincarnBean.getOpenLevel();
/* 47 */       if (!this.levelMap.containsKey(Integer.valueOf(openLevel))) {
/* 48 */         this.levelMap.put(Integer.valueOf(openLevel), new HashSet<>());
/*    */       }
/* 50 */       ((Set<Integer>)this.levelMap.get(Integer.valueOf(openLevel))).add(Integer.valueOf(fighterReincarnBean.getId()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getCostItemId() {
/* 55 */     return this.costItemId;
/*    */   }
/*    */   
/*    */   public int getDestinyLevelLimit() {
/* 59 */     return this.destinyLevelLimit;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getAssistPos() {
/* 63 */     return this.assistPos;
/*    */   }
/*    */   
/*    */   public int getMaxAssist() {
/* 67 */     return this.maxAssist;
/*    */   }
/*    */   
/*    */   public Map<Integer, Set<Integer>> getLevelMap() {
/* 71 */     return this.levelMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\PartnerParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */