/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MilitaryParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int helpTimes;
/*    */   private int limitHelp;
/*    */   private int refuseTime;
/*    */   
/*    */   public MilitaryParameter() {
/* 17 */     super(51);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.itemMap = new HashMap<>();
/*    */     
/* 24 */     this.assistReward = new ArrayList<>();
/*    */   }
/*    */   private Map<Integer, Integer> itemMap; private int costCCY; private ArrayList<Reward> assistReward;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 29 */     this.helpTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 30 */     this.limitHelp = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 31 */     this.refuseTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 32 */     String[] string4 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue().split(";");
/* 33 */     for (String string : string4) {
/* 34 */       String[] string1 = string.split(":");
/* 35 */       this.itemMap.put(Integer.valueOf(Integer.parseInt(string1[0])), Integer.valueOf(Integer.parseInt(string1[1])));
/*    */     } 
/* 37 */     this.costCCY = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/*    */     
/* 39 */     String[] string8 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue().split(";");
/* 40 */     this.assistReward = new ArrayList<>();
/* 41 */     for (String rewardStr : string8) {
/* 42 */       if (!rewardStr.equals("")) {
/* 43 */         String[] tmp = rewardStr.split(",");
/* 44 */         if (tmp.length == 3) {
/* 45 */           Reward reward = new Reward();
/* 46 */           reward.type = Byte.parseByte(tmp[0]);
/* 47 */           reward.id = Integer.parseInt(tmp[1]);
/* 48 */           reward.num = Integer.parseInt(tmp[2]);
/* 49 */           this.assistReward.add(reward);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getHelpTimes() {
/* 56 */     return this.helpTimes;
/*    */   }
/*    */   
/*    */   public int getLimitHelp() {
/* 60 */     return this.limitHelp;
/*    */   }
/*    */   
/*    */   public int getRefuseTime() {
/* 64 */     return this.refuseTime;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getItemMap() {
/* 68 */     return this.itemMap;
/*    */   }
/*    */   
/*    */   public int getCostCCY() {
/* 72 */     return this.costCCY;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getAssistReward() {
/* 76 */     return this.assistReward;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\MilitaryParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */