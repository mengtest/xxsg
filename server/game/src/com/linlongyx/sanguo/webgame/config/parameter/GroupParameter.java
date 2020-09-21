/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocSkillBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GroupParameter extends AbstractParameter {
/*     */   private Map<Integer, Integer> createCost;
/*     */   private int maxApplyNum;
/*     */   private List<Reward> sacrificeCost;
/*     */   private List<Reward> sacrificeReward;
/*     */   
/*     */   public GroupParameter() {
/*  16 */     super(11);
/*     */ 
/*     */     
/*  19 */     this.createCost = new HashMap<>();
/*     */     
/*  21 */     this.sacrificeCost = new ArrayList<>();
/*  22 */     this.sacrificeReward = new ArrayList<>();
/*  23 */     this.sacrificeExp = new ArrayList<>();
/*  24 */     this.sacrificePoint = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.skillIndex = new ArrayList<>();
/*     */   }
/*     */   private List<Integer> sacrificeExp; private List<Integer> sacrificePoint; private int showSize; private int viceSize; private int skillCurrency; private int autoDay;
/*     */   private int noticeLength;
/*     */   private String defaultNotice;
/*     */   private List<Integer> skillIndex;
/*     */   private int openLevel;
/*     */   private int timeLimit;
/*     */   private int changeNotice;
/*     */   private int changeNameItemId;
/*     */   private int groupRecruitCD;
/*     */   
/*     */   public int getCreateCost(int vip) {
/*  44 */     int maxVip = 0;
/*  45 */     int cost = 0;
/*  46 */     for (Map.Entry<Integer, Integer> entry : this.createCost.entrySet()) {
/*  47 */       if (((Integer)entry.getKey()).intValue() <= vip && maxVip <= ((Integer)entry.getKey()).intValue()) {
/*  48 */         maxVip = ((Integer)entry.getKey()).intValue();
/*  49 */         cost = ((Integer)entry.getValue()).intValue();
/*     */       } 
/*     */     } 
/*  52 */     return cost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxApplyNum() {
/*  60 */     return this.maxApplyNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getSacrificeCost(int index) {
/*  69 */     return this.sacrificeCost.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getSacrificeReward(int index) {
/*  78 */     return this.sacrificeReward.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSacrificeExp(int index) {
/*  87 */     return ((Integer)this.sacrificeExp.get(index)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSacrificePoint(int index) {
/*  96 */     return ((Integer)this.sacrificePoint.get(index)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShowSize() {
/* 104 */     return this.showSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getViceSize() {
/* 112 */     return this.viceSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkillCurrency() {
/* 120 */     return this.skillCurrency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAutoDay() {
/* 128 */     return this.autoDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNoticeLength() {
/* 136 */     return this.noticeLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultNotice() {
/* 144 */     return this.defaultNotice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getSkillIndex() {
/* 153 */     return this.skillIndex;
/*     */   }
/*     */   
/*     */   public int getChangeNotice() {
/* 157 */     return this.changeNotice;
/*     */   }
/*     */   
/*     */   public int getChangeNameItemId() {
/* 161 */     return this.changeNameItemId;
/*     */   }
/*     */   
/*     */   private void initSkill() {
/* 165 */     this.skillIndex.clear();
/* 166 */     Map<Integer, Object> map = JsonTableService.getJsonTable(BlocSkillBean.class);
/* 167 */     map.values().forEach(obj -> {
/*     */           BlocSkillBean blocSkillBean = (BlocSkillBean)obj;
/*     */           if (!this.skillIndex.contains(Integer.valueOf(blocSkillBean.getIndex()))) {
/*     */             this.skillIndex.add(Integer.valueOf(blocSkillBean.getIndex()));
/*     */           }
/*     */         });
/* 173 */     Collections.sort(this.skillIndex);
/*     */   }
/*     */   
/*     */   public int getOpenLevel() {
/* 177 */     return this.openLevel;
/*     */   }
/*     */   
/*     */   public int getTimeLimit() {
/* 181 */     return this.timeLimit;
/*     */   }
/*     */   
/*     */   public int getGroupRecruitCD() {
/* 185 */     return this.groupRecruitCD;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 190 */     this.createCost.clear();
/* 191 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 192 */     for (String string : strings) {
/* 193 */       String[] string1 = string.split(":");
/* 194 */       this.createCost.put(Integer.valueOf(string1[0]), Integer.valueOf(string1[1]));
/*     */     } 
/*     */     
/* 197 */     this.maxApplyNum = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/*     */     
/* 199 */     this.sacrificeCost.clear();
/* 200 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(",");
/* 201 */     this.sacrificeCost.add(getReward(strings));
/* 202 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue().split(",");
/* 203 */     this.sacrificeCost.add(getReward(strings));
/* 204 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue().split(",");
/* 205 */     this.sacrificeCost.add(getReward(strings));
/*     */     
/* 207 */     this.sacrificeReward.clear();
/* 208 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue().split(";");
/* 209 */     for (String string : strings) {
/* 210 */       String[] string1 = string.split(",");
/* 211 */       this.sacrificeReward.add(getReward(string1));
/*     */     } 
/*     */     
/* 214 */     this.sacrificeExp.clear();
/* 215 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue().split(";");
/* 216 */     for (String string : strings) {
/* 217 */       this.sacrificeExp.add(Integer.valueOf(string));
/*     */     }
/*     */     
/* 220 */     this.sacrificePoint.clear();
/* 221 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue().split(";");
/* 222 */     for (String string : strings) {
/* 223 */       this.sacrificePoint.add(Integer.valueOf(string));
/*     */     }
/*     */     
/* 226 */     this.showSize = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue()).intValue();
/* 227 */     this.viceSize = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(10))).getValue()).intValue();
/* 228 */     this.skillCurrency = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue()).intValue();
/* 229 */     this.autoDay = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue()).intValue();
/* 230 */     this.noticeLength = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(13))).getValue()).intValue();
/* 231 */     this.defaultNotice = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(14))).getValue();
/* 232 */     this.timeLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(15))).getValue());
/* 233 */     this.openLevel = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(16))).getValue());
/* 234 */     this.changeNotice = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(17))).getValue());
/* 235 */     this.changeNameItemId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(18))).getValue());
/* 236 */     this.groupRecruitCD = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(19))).getValue());
/* 237 */     initSkill();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\GroupParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */