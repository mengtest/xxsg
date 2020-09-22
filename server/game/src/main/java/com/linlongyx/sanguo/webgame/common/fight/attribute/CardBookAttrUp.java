/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CardsPairBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public class CardBookAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public CardBookAttrUp() {
/* 23 */     super(PlayerAttrUp.AttrUpType.CARDBOOK.getIndex(), false);
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
/* 35 */     getAttrs(cardBookComponent, fighterBean, this.attrs, updates, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 40 */     Arrays.fill(this.attrs, 0L);
/* 41 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 42 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 43 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 46 */     CardBookComponent cardBookComponent = (CardBookComponent)player.createIfNotExist(CardBookComponent.class);
/* 47 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 48 */     getAttrs(cardBookComponent, fighterBean, this.attrs, updates, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void getAttrs(CardBookComponent cardBookComponent, FighterBean fighterBean, long[] attrs, Set<Integer> updates, boolean isAdd) {
/* 53 */     for (Iterator<Integer> iterator = cardBookComponent.getCardPair().keySet().iterator(); iterator.hasNext(); ) { int pairId = ((Integer)iterator.next()).intValue();
/* 54 */       CardsPairBean cardsPairBean = (CardsPairBean)JsonTableService.getJsonData(pairId, CardsPairBean.class);
/* 55 */       if (null == cardsPairBean) {
/*    */         continue;
/*    */       }
/* 58 */       int level = ((Integer)cardBookComponent.getCardPair().get(Integer.valueOf(pairId))).intValue();
/* 59 */       if (level == 0) {
/* 60 */         for (AttrBean attrBean : cardsPairBean.getAttr0())
/* 61 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 63 */       if (level == 1) {
/* 64 */         for (AttrBean attrBean : cardsPairBean.getAttr1())
/* 65 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 67 */       if (level == 2) {
/* 68 */         for (AttrBean attrBean : cardsPairBean.getAttr2())
/* 69 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 71 */       if (level == 3) {
/* 72 */         for (AttrBean attrBean : cardsPairBean.getAttr3())
/* 73 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 75 */       if (level == 4) {
/* 76 */         for (AttrBean attrBean : cardsPairBean.getAttr4())
/* 77 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 79 */       if (level == 5) {
/* 80 */         for (AttrBean attrBean : cardsPairBean.getAttr5())
/* 81 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);  continue;
/*    */       } 
/* 83 */       if (level == 6)
/* 84 */         for (AttrBean attrBean : cardsPairBean.getAttr6())
/* 85 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), isAdd);   }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\CardBookAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */