/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerEquipAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public PartnerEquipAttrUp() {
/* 24 */     super(PlayerAttrUp.AttrUpType.PARTNER_EQUIP.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 29 */     Arrays.fill(this.attrs, 0L);
/* 30 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 31 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/* 32 */     Map<Integer, Integer> suitNum = new HashMap<>();
/*    */     
/* 34 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 35 */     if (null == partnerEntity) {
/*    */       return;
/*    */     }
/* 38 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 39 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 42 */     suitNum.clear();
/* 43 */     Map<Integer, Long> equips = partnerEntity.getEquips();
/* 44 */     for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/* 45 */       EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/* 46 */       if (null == equipEntity) {
/*    */         continue;
/*    */       }
/* 49 */       AttrUpdateUtil.updateNormal(fighterBean, equipEntity, this.attrs, updates, suitNum);
/*    */     } 
/* 51 */     AttrUpdateUtil.updateEquipSuit(fighterBean, this.attrs, updates, suitNum);
/* 52 */     AttrUpdateUtil.updateTalisman(player, fighterBean, this.attrs, updates, pid);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\PartnerEquipAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */