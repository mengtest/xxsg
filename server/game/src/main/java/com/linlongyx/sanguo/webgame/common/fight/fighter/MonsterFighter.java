/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MonsterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ public class MonsterFighter
/*    */   extends AbstractFighter
/*    */   implements IFighter
/*    */ {
/*    */   private MonsterBean bean;
/*    */   
/*    */   public MonsterFighter(int monsterId, byte pos) {
/* 17 */     this.type = 2;
/* 18 */     this.bean = (MonsterBean)JsonTableService.getJsonData(monsterId, MonsterBean.class);
/* 19 */     assert null != this.bean;
/* 20 */     if (this.bean == null) {
/*    */       return;
/*    */     }
/* 23 */     this.id = monsterId;
/* 24 */     this.pos = pos;
/* 25 */     this.hurtType = (byte)this.bean.getHurtType();
/* 26 */     this.cmmSkill = new Skill(this.bean.getSkill(), 1);
/* 27 */     this.furySkill = new Skill(this.bean.getHotSkill(), 1);
/* 28 */     if (this.bean.getFitSkill() != 0) {
/* 29 */       this.fitSkill = new Skill(this.bean.getFitSkill(), 1);
/*    */     }
/*    */     
/* 32 */     this.level = (short)this.bean.getLv();
/*    */     
/* 34 */     this.fighterData.type = this.type;
/*    */     
/* 36 */     this.isAuto = true;
/*    */     
/* 38 */     this.bean.getAttr().forEach(attr -> this.attrs[attr.getId()] = attr.getNum());
/*    */     
/* 40 */     System.arraycopy(this.attrs, 1, this.baseAttrs, 1, AttributeType.BASE_ATTR_END.getType() - 1);
/*    */     
/* 42 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/* 43 */     this.fighterData.id = this.bean.getId();
/* 44 */     this.fighterData.type = this.type;
/* 45 */     this.fighterData.pos = pos;
/* 46 */     this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */   }
/*    */   
/*    */   public final String getName() {
/* 50 */     return this.bean.getName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getMaxFury() {
/* 60 */     return (byte)this.bean.getFury();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\MonsterFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */