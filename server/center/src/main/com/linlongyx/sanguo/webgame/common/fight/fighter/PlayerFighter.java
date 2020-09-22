/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerFighter
/*    */   extends AbstractFighter
/*    */   implements IFighter
/*    */ {
/*    */   public PlayerFighter() {}
/*    */   
/*    */   public PlayerFighter(CommonFighterData commonFighterData, byte pos, boolean copyAttr) {
/* 21 */     this.id = commonFighterData.getId();
/* 22 */     this.type = commonFighterData.getType();
/* 23 */     this.hurtType = commonFighterData.getHurtType();
/* 24 */     this.pos = pos;
/* 25 */     this.fury = 0;
/* 26 */     this.isAuto = true;
/* 27 */     this.talisman = commonFighterData.getTalisman();
/* 28 */     this.pid = -1L;
/*    */     
/* 30 */     for (Map.Entry<Integer, Integer> kv : commonFighterData.getSkillMap().entrySet()) {
/* 31 */       addSKill(new Skill(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue()));
/*    */     }
/*    */     
/* 34 */     this.fighterData.id = this.id;
/* 35 */     this.fighterData.type = this.type;
/* 36 */     this.fighterData.fashionId = commonFighterData.getFashionId();
/* 37 */     this.fighterData.quality = commonFighterData.getQuality();
/* 38 */     this.fighterData.level = commonFighterData.getLevel();
/* 39 */     if (copyAttr) {
/* 40 */       this.baseAttrs = Arrays.copyOf(commonFighterData.getBaseAttrs(), (commonFighterData.getBaseAttrs()).length);
/* 41 */       this.attrs = Arrays.copyOf(commonFighterData.getAttrs(), (commonFighterData.getAttrs()).length);
/*    */     } else {
/* 43 */       this.baseAttrs = commonFighterData.getBaseAttrs();
/* 44 */       this.attrs = commonFighterData.getAttrs();
/*    */     } 
/* 46 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */     
/* 48 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addSKill(Skill skill) {
/* 56 */     if (skill.getType() == 1) {
/* 57 */       setCmmSkill(skill);
/* 58 */     } else if (skill.getType() == 2) {
/* 59 */       setFurySkill(skill);
/* 60 */     } else if (skill.getType() == 3) {
/* 61 */       setFitSkill(skill);
/* 62 */     } else if (skill.getType() == 5) {
/* 63 */       setCmmSkill(skill);
/* 64 */     } else if (skill.getType() == 6) {
/* 65 */       setCmmSkill(skill);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getMaxFury() {
/* 78 */     return 4;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\PlayerFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */