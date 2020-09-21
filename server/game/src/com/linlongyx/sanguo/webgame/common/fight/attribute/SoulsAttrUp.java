/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsLevelBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsTalentBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.souls.SoulsUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class SoulsAttrUp
/*    */   extends AttrUpBase {
/*    */   public SoulsAttrUp() {
/* 30 */     super(PlayerAttrUp.AttrUpType.SOULS.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 35 */     Arrays.fill(this.attrs, 0L);
/* 36 */     updateSouls(player, this.attrs, updates, -1L, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 41 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/* 44 */     Arrays.fill(this.attrs, 0L);
/* 45 */     updateSouls(player, this.attrs, updates, -1L, false);
/*    */   }
/*    */   
/*    */   public void updateSouls(IPlayer player, long[] attrs, Set<Integer> updates, long pid, boolean add) {
/* 49 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 50 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 51 */     FighterBean fighterBean = null;
/* 52 */     if (pid != -1L) {
/* 53 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*    */     } else {
/* 55 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*    */     } 
/* 57 */     if (null == fighterBean) {
/* 58 */       LogUtils.errorLog(new Object[] { "updateSouls", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 63 */     SoulsComponent soulsComponent = (SoulsComponent)player.createIfNotExist(SoulsComponent.class);
/* 64 */     for (IMapEntity iMapEntity : soulsComponent.getEntityMap().values()) {
/* 65 */       SoulsEntity soulsEntity = (SoulsEntity)iMapEntity;
/* 66 */       int star = soulsEntity.getStar();
/* 67 */       int level = soulsEntity.getLevel();
/* 68 */       SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(soulsEntity.getId(), SoulsStarBean.class);
/* 69 */       if (null == soulsStarBean) {
/*    */         continue;
/*    */       }
/* 72 */       int levelPar = ((SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(star))).getLevelPar();
/* 73 */       SoulsLevelBean soulsLevelBean = (SoulsLevelBean)JsonTableService.getJsonData(soulsEntity.getLevel(), SoulsLevelBean.class);
/* 74 */       if (null != soulsLevelBean) {
/* 75 */         for (AttrBean attrBean : soulsLevelBean.getAttr()) {
/* 76 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * 1L * levelPar / 10000L, add);
/*    */         }
/*    */       }
/*    */       
/* 80 */       SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/* 81 */       int breaks = level / soulsParameter.getRankLevel();
/* 82 */       int readBreak = (breaks == 0) ? 0 : (breaks - 1);
/* 83 */       SoulsTalentBean soulsTalentBean = (SoulsTalentBean)JsonTableService.getJsonData(soulsEntity.getId(), SoulsTalentBean.class);
/* 84 */       if (null != soulsTalentBean) {
/* 85 */         for (AttrBean attrBean : ((SoulsTalentBean.LevelClassBean)soulsTalentBean.getLevelClass().get(Integer.valueOf(readBreak))).getBeAttr()) {
/* 86 */           AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * 1L * levelPar / 10000L, add);
/*    */         }
/*    */       }
/*    */     } 
/*    */     
/* 91 */     ArrayList<Integer> arrayList = SoulsUtil.getSoulsHandBook(soulsComponent);
/* 92 */     for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) { int handBookId = ((Integer)iterator.next()).intValue();
/* 93 */       SoulsBean soulsBean = (SoulsBean)JsonTableService.getJsonData(handBookId, SoulsBean.class);
/* 94 */       for (AttrBean attrBean : soulsBean.getAttr())
/* 95 */         AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), add);  }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\SoulsAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */