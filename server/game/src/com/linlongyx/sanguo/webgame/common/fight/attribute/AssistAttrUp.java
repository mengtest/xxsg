/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterStarBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AssistAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public AssistAttrUp() {
/* 26 */     super(PlayerAttrUp.AttrUpType.ASSIST.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 31 */     Arrays.fill(this.attrs, 0L);
/* 32 */     updateAssist(player, this.attrs, updates, -1L, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 37 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/* 40 */     Arrays.fill(this.attrs, 0L);
/* 41 */     updateAssist(player, this.attrs, updates, -1L, false);
/*    */   }
/*    */   
/*    */   public void updateAssist(IPlayer player, long[] attrs, Set<Integer> updates, long pid, boolean add) {
/* 45 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 46 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 47 */     FighterBean fighterBean = null;
/* 48 */     if (pid != -1L) {
/* 49 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*    */     } else {
/* 51 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*    */     } 
/* 53 */     if (null == fighterBean) {
/* 54 */       LogUtils.errorLog(new Object[] { "updateSouls", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*    */       return;
/*    */     } 
/* 57 */     GeneralComponent generalComponent = (GeneralComponent)player.createIfNotExist(GeneralComponent.class);
/* 58 */     int totalAddition = 0;
/* 59 */     FighterStarBean.LevelBean levelBean = null;
/* 60 */     for (Iterator<Long> iterator = generalComponent.getAssistInBattle().iterator(); iterator.hasNext(); ) { long assistId = ((Long)iterator.next()).longValue();
/* 61 */       if (assistId == 0L) {
/*    */         continue;
/*    */       }
/* 64 */       PartnerEntity partnerEntity = partnerComponent.getEntity(assistId);
/* 65 */       if (null == partnerEntity) {
/*    */         continue;
/*    */       }
/*    */       
/* 69 */       FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterStarBean.class);
/* 70 */       levelBean = (FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerEntity.getStars()));
/* 71 */       if (null != levelBean) {
/* 72 */         totalAddition += levelBean.getCheerPro();
/* 73 */         for (FighterStarBean.LevelBean.CheerAttrBean attrBean : levelBean.getCheerAttr()) {
/* 74 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), add);
/*    */         }
/*    */       }  }
/*    */ 
/*    */     
/* 79 */     if (null != levelBean)
/* 80 */       for (int i = 1; i < AttributeType.ATTRIB_TYPE_END.getType(); i++) {
/* 81 */         if (attrs[i] != 0L)
/* 82 */           attrs[i] = attrs[i] + attrs[i] * 1L * totalAddition / 10000L; 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\AssistAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */