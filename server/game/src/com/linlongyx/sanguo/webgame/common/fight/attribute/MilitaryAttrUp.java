/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MilitaryAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public MilitaryAttrUp() {
/* 23 */     super(PlayerAttrUp.AttrUpType.MILITARY.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 28 */     Arrays.fill(this.attrs, 0L);
/* 29 */     CardBookComponent cardBookComponent = (CardBookComponent)player.createIfNotExist(CardBookComponent.class);
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 31 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 32 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 35 */     Map<Integer, Integer> map = MilitaryUtil.getMilitaryByType(player.getPlayerId(), 1);
/* 36 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int military = ((Integer)iterator.next()).intValue();
/* 37 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(military, MilitaryBean.class);
/* 38 */       if (null != militaryBean) {
/* 39 */         MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(map.get(Integer.valueOf(military)));
/* 40 */         for (MilitaryBean.LevelBean.AttrScientificresearchBean attrBean : levelBean.getAttrScientificresearch()) {
/* 41 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getAttrId(), attrBean.getNum(), true);
/*    */         }
/*    */       }  }
/*    */   
/*    */   }
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 48 */     Arrays.fill(this.attrs, 0L);
/* 49 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 50 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 51 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 54 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 55 */     Map<Integer, Integer> map = MilitaryUtil.getMilitaryByType(player.getPlayerId(), 1);
/* 56 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int military = ((Integer)iterator.next()).intValue();
/* 57 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(military, MilitaryBean.class);
/* 58 */       if (null != militaryBean) {
/* 59 */         MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(map.get(Integer.valueOf(military)));
/* 60 */         for (MilitaryBean.LevelBean.AttrScientificresearchBean attrBean : levelBean.getAttrScientificresearch())
/* 61 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getAttrId(), attrBean.getNum(), false); 
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\MilitaryAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */