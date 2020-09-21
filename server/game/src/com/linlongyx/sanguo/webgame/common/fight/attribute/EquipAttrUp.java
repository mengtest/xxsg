/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
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
/*    */ 
/*    */ 
/*    */ public class EquipAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public EquipAttrUp() {
/* 25 */     super(PlayerAttrUp.AttrUpType.EQUIP.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 30 */     Arrays.fill(this.attrs, 0L);
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 32 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/* 33 */     Map<Integer, Integer> suitNum = new HashMap<>();
/* 34 */     suitNum.clear();
/* 35 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 36 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 39 */     for (Map.Entry<Integer, Long> entry : (Iterable<Map.Entry<Integer, Long>>)playerComponent.getEquips().entrySet()) {
/* 40 */       EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/* 41 */       if (null == equipEntity) {
/*    */         continue;
/*    */       }
/* 44 */       AttrUpdateUtil.updateNormal(fighterBean, equipEntity, this.attrs, updates, suitNum);
/*    */     } 
/* 46 */     AttrUpdateUtil.updateEquipSuit(fighterBean, this.attrs, updates, suitNum);
/* 47 */     AttrUpdateUtil.updateTalisman(player, fighterBean, this.attrs, updates, -1L);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\EquipAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */