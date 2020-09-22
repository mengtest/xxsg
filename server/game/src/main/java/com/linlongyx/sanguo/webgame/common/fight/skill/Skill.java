/*    */ package com.linlongyx.sanguo.webgame.common.fight.skill;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.NewSkillBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SkillData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Skill
/*    */ {
/*    */   private final int skillId;
/*    */   private final int level;
/*    */   private final NewSkillBean bean;
/* 18 */   private final SkillData skillData = new SkillData();
/*    */   
/* 20 */   private final List<Effect> effects = new ArrayList<>();
/*    */   
/*    */   public Skill(int skillId, int level) {
/* 23 */     this.skillId = skillId;
/* 24 */     this.level = level;
/* 25 */     this.bean = (NewSkillBean)JsonTableService.getJsonData(skillId, NewSkillBean.class);
/*    */     
/* 27 */     for (Iterator<Integer> iterator = this.bean.getEffect().iterator(); iterator.hasNext(); ) { int effectId = ((Integer)iterator.next()).intValue();
/* 28 */       Effect effect1 = new Effect(effectId, level);
/* 29 */       this.effects.add(effect1); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSkillId() {
/* 35 */     return this.skillId;
/*    */   }
/*    */   
/*    */   public NewSkillBean getBean() {
/* 39 */     return this.bean;
/*    */   }
/*    */   
/*    */   public List<Effect> getEffects() {
/* 43 */     return this.effects;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 47 */     return this.level;
/*    */   }
/*    */   
/*    */   public SkillData getSkillData() {
/* 51 */     this.skillData.skillId = this.skillId;
/* 52 */     this.skillData.level = (short)this.level;
/* 53 */     return this.skillData;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 57 */     return this.bean.getType();
/*    */   }
/*    */   
/*    */   private static boolean hasEffect(List<Integer> effects, int effectId) {
/* 61 */     for (Iterator<Integer> iterator = effects.iterator(); iterator.hasNext(); ) { int effect = ((Integer)iterator.next()).intValue();
/* 62 */       if (effect == effectId)
/* 63 */         return true;  }
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\skill\Skill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */