/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.HashMap;
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
/*    */ public class PartnerBattleParameter
/*    */   extends AbstractParameter
/*    */ {
/* 20 */   private Map<Integer, Integer> map = new HashMap<>();
/* 21 */   private Map<Integer, Integer> vipMap = new HashMap<>();
/*    */   
/*    */   public PartnerBattleParameter() {
/* 24 */     super(34);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 31 */     this.map.clear();
/* 32 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 33 */     for (String string : strings) {
/* 34 */       String[] strings2 = string.split(":");
/* 35 */       this.map.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/* 37 */     this.vipMap.clear();
/* 38 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(";");
/* 39 */     for (String string : strings) {
/* 40 */       String[] strings2 = string.split(":");
/* 41 */       this.vipMap.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getMap() {
/* 46 */     return this.map;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getVipMap() {
/* 50 */     return this.vipMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\PartnerBattleParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */