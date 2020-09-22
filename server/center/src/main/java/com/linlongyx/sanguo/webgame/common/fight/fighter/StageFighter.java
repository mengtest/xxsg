/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MonsterStageBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageFighter
/*    */   extends PlayerFighter
/*    */ {
/*    */   public StageFighter(CommonFighterData commonFighterData, boolean copyAttr) {
/* 18 */     super(commonFighterData, (byte)10, copyAttr);
/* 19 */     this.type = 4;
/* 20 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public StageFighter(CommonFighterData commonFighterData, byte pos, boolean copyAttr) {
/* 24 */     super(commonFighterData, pos, copyAttr);
/* 25 */     this.type = 4;
/* 26 */     this.fighterData.type = this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public StageFighter(int stageId) {
/* 31 */     MonsterStageBean monsterStageBean = (MonsterStageBean)JsonTableService.getJsonData(stageId, MonsterStageBean.class);
/* 32 */     if (monsterStageBean != null) {
/* 33 */       this.id = stageId;
/* 34 */       this.type = 4;
/* 35 */       this.hurtType = (byte)monsterStageBean.getHurtType();
/* 36 */       this.pos = 10;
/* 37 */       this.isAuto = true;
/* 38 */       this.pid = -1L;
/*    */       
/* 40 */       addSKill(new Skill(monsterStageBean.getPetSkill(), 1));
/*    */ 
/*    */       
/* 43 */       this.fighterData.id = this.id;
/* 44 */       this.fighterData.fashionId = 0;
/* 45 */       this.fighterData.type = this.type;
/* 46 */       this.fighterData.level = (short)monsterStageBean.getStar();
/* 47 */       this.fighterData.quality = monsterStageBean.getQuality();
/* 48 */       for (AttrBean attr : monsterStageBean.getAttr()) {
/* 49 */         if (attr.getId() < AttributeType.BASE_ATTR_END.getType())
/* 50 */           this.baseAttrs[attr.getId()] = attr.getNum(); 
/* 51 */         if (attr.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/* 52 */           this.attrs[attr.getId()] = attr.getNum();
/*    */         }
/*    */       } 
/* 55 */       this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */       
/* 57 */       this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initSkill(Skill stageSkill) {
/* 63 */     setCmmSkill(stageSkill);
/* 64 */     setFurySkill(null);
/* 65 */     setFitSkill(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(long id) {
/* 70 */     this.id = id;
/* 71 */     this.fighterData.id = id;
/*    */   }
/*    */   
/*    */   public void setLevel(short level) {
/* 75 */     this.level = level;
/* 76 */     this.fighterData.level = level;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\StageFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */