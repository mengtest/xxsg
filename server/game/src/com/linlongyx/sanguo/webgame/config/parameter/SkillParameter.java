/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ModelParaBean;
/*     */ import com.linlongyx.sanguo.webgame.processors.skill.SkillOpenParam;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SkillParameter extends AbstractParameter {
/*     */   private static final int TYPE_1 = 1;
/*     */   private static final int TYPE_2 = 2;
/*     */   private static final int TYPE_3 = 3;
/*     */   private Map<Integer, SkillOpenParam> skillOpens;
/*     */   private int skippInitLevel;
/*     */   private Map<Integer, SkillOpenParam> skillOpens2;
/*     */   private Map<String, Integer> actTimes_1;
/*     */   private Map<String, Integer> actTimes_2;
/*     */   
/*     */   public Map<Integer, SkillOpenParam> getSkillOpens() {
/*     */     return this.skillOpens;
/*     */   }
/*     */   
/*  21 */   public SkillParameter() { super(9);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     this.skillOpens = new HashMap<>();
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.skillOpens2 = new HashMap<>();
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
/*  66 */     this.actTimes_1 = new HashMap<>();
/*  67 */     this.actTimes_2 = new HashMap<>(); }
/*     */   public int getSkippInitLevel() { return this.skippInitLevel; } public void initModelParaData() {
/*  69 */     this.actTimes_1.clear();
/*  70 */     this.actTimes_2.clear();
/*  71 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ModelParaBean.class);
/*  72 */     map.values().forEach(obj -> {
/*     */           ModelParaBean modelParaBean = (ModelParaBean)obj;
/*     */           if (modelParaBean.getActTime() != null && !modelParaBean.getActTime().isEmpty())
/*     */             for (ModelParaBean.ActTimeBean actTimeBean : modelParaBean.getActTime()) {
/*     */               if (actTimeBean.getActType() == 1) {
/*     */                 this.actTimes_1.put(modelParaBean.getRid(), Integer.valueOf(actTimeBean.getTime()));
/*     */                 continue;
/*     */               } 
/*     */               if (actTimeBean.getActType() == 2)
/*     */                 this.actTimes_2.put(modelParaBean.getRid(), Integer.valueOf(actTimeBean.getTime())); 
/*     */             }  
/*     */         });
/*     */   } public Map<Integer, SkillOpenParam> getSkillOpens2() {
/*     */     return this.skillOpens2;
/*     */   }
/*     */   void init(ParameterBean bean) {
/*  88 */     this.skillOpens.clear();
/*  89 */     this.skillOpens2.clear();
/*  90 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/*  91 */     for (String string : strings) {
/*  92 */       String[] strings2 = string.split(":");
/*  93 */       this.skillOpens.put(Integer.valueOf(Integer.parseInt(strings2[0])), new SkillOpenParam(Integer.parseInt(strings2[0]), Integer.parseInt(strings2[1]), Integer.parseInt(strings2[2])));
/*     */     } 
/*  95 */     this.skippInitLevel = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*  96 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(";");
/*  97 */     for (String string : strings) {
/*  98 */       String[] strings2 = string.split(":");
/*  99 */       this.skillOpens2.put(Integer.valueOf(Integer.parseInt(strings2[0])), new SkillOpenParam(Integer.parseInt(strings2[0]), Integer.parseInt(strings2[1]), Integer.parseInt(strings2[2])));
/*     */     } 
/*     */ 
/*     */     
/* 103 */     initModelParaData();
/*     */   }
/*     */   
/*     */   public int getActTime(String rid, int actType) {
/*     */     if (actType == 1) {
/*     */       if (this.actTimes_1.containsKey(rid))
/*     */         return ((Integer)this.actTimes_1.get(rid)).intValue(); 
/*     */     } else if (actType == 2 && this.actTimes_2.containsKey(rid)) {
/*     */       return ((Integer)this.actTimes_2.get(rid)).intValue();
/*     */     } 
/*     */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\SkillParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */