/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public TitleAttrUp() {
/* 23 */     super(PlayerAttrUp.AttrUpType.TITLE.getIndex(), false);
/*    */   }
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 27 */     Arrays.fill(this.attrs, 0L);
/* 28 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 29 */     Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/* 30 */     if (null != activeTitles && activeTitles.size() > 0) {
/* 31 */       activeTitles.forEach((k, v) -> {
/*    */             TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(k.intValue(), TitleBean.class);
/*    */             FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*    */             if (null != titleBean && fighterBean != null) {
/*    */               titleBean.getAttr();
/*    */             }
/*    */           });
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 45 */     Arrays.fill(this.attrs, 0L);
/* 46 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 50 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 51 */     Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/* 52 */     if (null != activeTitles && activeTitles.size() > 0)
/* 53 */       activeTitles.forEach((k, v) -> {
/*    */             TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(k.intValue(), TitleBean.class);
/*    */             PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*    */             PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*    */             if (partnerEntity == null)
/*    */               return; 
/*    */             FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*    */             if (null != titleBean && fighterBean != null)
/*    */               titleBean.getAttr();
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\TitleAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */