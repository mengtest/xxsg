/*     */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class RuneAttrUp
/*     */   extends AttrUpBase
/*     */ {
/*     */   public RuneAttrUp() {
/*  30 */     super(PlayerAttrUp.AttrUpType.RUNE.getIndex(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh(IPlayer player, Set<Integer> updates) {
/*  35 */     Arrays.fill(this.attrs, 0L);
/*  36 */     updateRune(player, updates, this.attrs, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshPartner(IPlayer player, Set<Integer> updates, long updatePid) {
/*  41 */     Arrays.fill(this.attrs, 0L);
/*  42 */     updateRune(player, updates, this.attrs, updatePid);
/*     */   }
/*     */   
/*     */   public void updateRune(IPlayer player, Set<Integer> updates, long[] attrs, long updatePid) {
/*  46 */     RuneComponent runeComponent = (RuneComponent)player.createIfNotExist(RuneComponent.class);
/*  47 */     RuneBagComponent runeBagComponent = (RuneBagComponent)player.createIfNotExist(RuneBagComponent.class);
/*  48 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*     */     
/*  50 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  51 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { FighterBean fighterBean; long pid = ((Long)iterator.next()).longValue();
/*  52 */       Map<Integer, Long> rune = new HashMap<>();
/*  53 */       Map<Integer, HashSet> suits = new HashMap<>();
/*  54 */       Map<Integer, Integer> ranks = new HashMap<>();
/*     */       
/*  56 */       if (pid == 0L) {
/*     */         continue;
/*     */       }
/*  59 */       if (pid == -1L) {
/*  60 */         rune = runeComponent.getRuneMap();
/*  61 */         fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*     */       } else {
/*  63 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  64 */         if (null == partnerEntity) {
/*     */           continue;
/*     */         }
/*  67 */         rune = partnerEntity.getRuneMap();
/*  68 */         fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*     */       } 
/*  70 */       if (null == fighterBean) {
/*     */         continue;
/*     */       }
/*     */       
/*  74 */       for (null = rune.values().iterator(); null.hasNext(); ) { long mid = ((Long)null.next()).longValue();
/*  75 */         RuneBagEntity runeBagEntity = runeBagComponent.getEntity(mid);
/*  76 */         if (null != runeBagEntity) {
/*  77 */           RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/*  78 */           if (null == runeBean) {
/*     */             continue;
/*     */           }
/*  81 */           int level = runeBagEntity.getLevel();
/*  82 */           RuneLevelBean runeLevelBean = (RuneLevelBean)JsonTableService.getJsonData(level, RuneLevelBean.class);
/*  83 */           if (null == runeLevelBean) {
/*     */             continue;
/*     */           }
/*  86 */           if (runeBean.getSuitId() > 0) {
/*  87 */             if (!suits.containsKey(Integer.valueOf(runeBean.getSuitId()))) {
/*  88 */               suits.put(Integer.valueOf(runeBean.getSuitId()), new HashSet());
/*     */             }
/*  90 */             ((HashSet<Integer>)suits.get(Integer.valueOf(runeBean.getSuitId()))).add(Integer.valueOf(runeBean.getItemId()));
/*     */             
/*  92 */             if (!ranks.containsKey(Integer.valueOf(runeBean.getSuitId())) || ((Integer)ranks.get(Integer.valueOf(runeBean.getSuitId()))).intValue() > runeBean.getQuality()) {
/*  93 */               ranks.put(Integer.valueOf(runeBean.getSuitId()), Integer.valueOf(runeBean.getQuality()));
/*     */             }
/*     */           } 
/*     */           
/*  97 */           for (AttrBean attrBean : runeBean.getStrengthAttr()) {
/*  98 */             if (pid == updatePid) {
/*  99 */               AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), (long)(attrBean.getNum() * runeLevelBean.getAttr() / 10000.0D), true); continue;
/*     */             } 
/* 101 */             AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), (long)(attrBean.getNum() * runeLevelBean.getAttr() / 10000.0D), false);
/*     */           } 
/*     */         }  }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       for (Map.Entry<Integer, HashSet> entry : suits.entrySet()) {
/* 109 */         RuneSuitBean runeSuitBean = (RuneSuitBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), RuneSuitBean.class);
/* 110 */         if (null == runeSuitBean) {
/*     */           continue;
/*     */         }
/*     */         
/* 114 */         int count = 0;
/* 115 */         for (Integer itemId : runeSuitBean.getRune()) {
/* 116 */           if (((HashSet)entry.getValue()).contains(itemId)) {
/* 117 */             count++;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 122 */         if (count >= runeSuitBean.getNum() && ranks.containsKey(entry.getKey())) {
/* 123 */           int rank = ((Integer)ranks.get(entry.getKey())).intValue();
/* 124 */           RuneSuitBean.RankBean rankBean = (RuneSuitBean.RankBean)runeSuitBean.getRank().get(Integer.valueOf(rank));
/* 125 */           if (null != rankBean && !rankBean.getAttr().isEmpty())
/* 126 */             rankBean.getAttr().forEach(attrBean -> {
/*     */                   if (pid == updatePid) {
/*     */                     AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */                   } else {
/*     */                     AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*     */                   } 
/*     */                 }); 
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\RuneAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */