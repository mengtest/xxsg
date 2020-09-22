/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerFighter
/*    */   extends AbstractFighter
/*    */   implements IFighter
/*    */ {
/*    */   public PartnerFighter(int id, PartnerComponent partnerComponent) {
/* 19 */     this.id = id;
/* 20 */     this.type = 1;
/*    */ 
/*    */     
/* 23 */     this.isAuto = false;
/*    */     
/* 25 */     FighterBean bean = (FighterBean)JsonTableService.getJsonData(id, FighterBean.class);
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
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     this.fighterData.id = id;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     if (this.fighterData.maxHp <= 0L) {
/* 48 */       this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerFighter(int id, PartnerComponent partnerComponent, byte pos, byte side) {
/* 54 */     this(id, partnerComponent);
/* 55 */     setPos(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getMaxFury() {
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\PartnerFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */