/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
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
/*    */ 
/*    */ public class StageFighter
/*    */   extends PlayerFighter
/*    */ {
/*    */   public StageFighter(PlayerComponent component, byte pos) {
/* 20 */     super(component, pos);
/* 21 */     this.type = 4;
/* 22 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public StageFighter(CommonFighterData commonFighterData) {
/* 26 */     super(commonFighterData, (byte)10);
/* 27 */     this.type = 4;
/* 28 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public StageFighter(CommonFighterData commonFighterData, byte pos) {
/* 32 */     super(commonFighterData, pos);
/* 33 */     this.type = 4;
/* 34 */     this.fighterData.type = this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public StageFighter(int stageId) {
/* 39 */     MonsterStageBean monsterStageBean = (MonsterStageBean)JsonTableService.getJsonData(stageId, MonsterStageBean.class);
/* 40 */     if (monsterStageBean != null) {
/* 41 */       this.id = stageId;
/* 42 */       this.type = 4;
/* 43 */       this.hurtType = (byte)monsterStageBean.getHurtType();
/* 44 */       this.pos = 10;
/* 45 */       this.isAuto = true;
/* 46 */       this.pid = -1L;
/*    */       
/* 48 */       addSKill(new Skill(monsterStageBean.getPetSkill(), 1));
/*    */ 
/*    */       
/* 51 */       this.fighterData.id = this.id;
/* 52 */       this.fighterData.fashionId = 0;
/* 53 */       this.fighterData.type = this.type;
/* 54 */       this.fighterData.level = (short)monsterStageBean.getStar();
/* 55 */       this.fighterData.quality = monsterStageBean.getQuality();
/* 56 */       for (AttrBean attr : monsterStageBean.getAttr()) {
/* 57 */         if (attr.getId() < AttributeType.BASE_ATTR_END.getType())
/* 58 */           this.baseAttrs[attr.getId()] = attr.getNum(); 
/* 59 */         if (attr.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/* 60 */           this.attrs[attr.getId()] = attr.getNum();
/*    */         }
/*    */       } 
/* 63 */       this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */       
/* 65 */       this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initSkill(Skill stageSkill) {
/* 71 */     setCmmSkill(stageSkill);
/* 72 */     setFurySkill(null);
/* 73 */     setFitSkill(null);
/*    */   }
/*    */   
/*    */   public void setStageId(int id) {
/* 77 */     this.id = id;
/* 78 */     this.fighterData.id = id;
/*    */   }
/*    */   
/*    */   public void setLevel(short level) {
/* 82 */     this.level = level;
/* 83 */     this.fighterData.level = level;
/*    */   }
/*    */   
/*    */   public void setSide(byte side) {
/* 87 */     this.side = side;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\StageFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */