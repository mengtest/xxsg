/*     */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBoxBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBuffBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlotExpSweepData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SweepUnparalleledRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SweepUnparalleledResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SweepUnparalleledProcessor
/*     */   extends ProcessorBase<SweepUnparalleledRequest, SweepUnparalleledResponse> {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new SweepUnparalleledResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, SweepUnparalleledRequest request) {
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 18)) {
/*  43 */       return 10061;
/*     */     }
/*  45 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/*  46 */     if (unparalleledComponent.getInsMap().isEmpty()) {
/*  47 */       return 14013;
/*     */     }
/*  49 */     if (unparalleledComponent.isSweep()) {
/*  50 */       return 14018;
/*     */     }
/*  52 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  53 */     for (Iterator<Integer> iterator1 = unparalleledComponent.getInsMap().keySet().iterator(); iterator1.hasNext(); ) { int key = ((Integer)iterator1.next()).intValue();
/*  54 */       int ins = ((Integer)unparalleledComponent.getInsMap().get(Integer.valueOf(key))).intValue();
/*  55 */       WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(ins, WushuangInsBean.class);
/*  56 */       if (unparalleledComponent.getPlayedPoints().containsKey(Integer.valueOf(key))) {
/*     */         continue;
/*     */       }
/*  59 */       if (wushuangInsBean.getCheckPoint() == unparalleledComponent.getMaxPoint()) {
/*     */         break;
/*     */       }
/*  62 */       if (wushuangInsBean.getStar() != 3) {
/*     */         break;
/*     */       }
/*     */       
/*  66 */       PlotExpSweepData sweepData = new PlotExpSweepData();
/*  67 */       sweepData.insId = key;
/*  68 */       ArrayList<Reward> rewards1 = FinanceUtil.transform(wushuangInsBean.getPassReward());
/*     */       
/*  70 */       MilitaryUtil.getRewardResult(rewards1, MilitaryInsType.UnparaledIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*  71 */       sweepData.list.addAll(rewards1);
/*  72 */       rewards.addAll(rewards1);
/*  73 */       ((SweepUnparalleledResponse)this.response).reward.add(sweepData);
/*  74 */       unparalleledComponent.setSurpStar(unparalleledComponent.getSurpStar() + 3);
/*  75 */       unparalleledComponent.setCurrMaxStar(unparalleledComponent.getCurrMaxStar() + 3);
/*  76 */       unparalleledComponent.setCurPoint(wushuangInsBean.getCheckPoint());
/*  77 */       unparalleledComponent.getPlayedPoints().put(Integer.valueOf(key), Integer.valueOf(TimeUtil.currentTime()));
/*     */       
/*  79 */       if (wushuangInsBean.getCheckPoint() % 3 == 0) {
/*  80 */         if (unparalleledComponent.getAttrbuteList().isEmpty()) {
/*  81 */           unparalleledComponent.createAttrbuteList();
/*  82 */           for (Iterator<Integer> iterator3 = unparalleledComponent.getAttrbuteList().iterator(); iterator3.hasNext(); ) { int buffId = ((Integer)iterator3.next()).intValue();
/*  83 */             WushuangBuffBean wushuangBuffBean = (WushuangBuffBean)JsonTableService.getJsonData(buffId, WushuangBuffBean.class);
/*  84 */             if (wushuangBuffBean.getCostStar() == 9 && unparalleledComponent.getSurpStar() >= 9) {
/*  85 */               unparalleledComponent.getLayerAddition().add(Integer.valueOf(wushuangInsBean.getCheckPoint()));
/*  86 */               unparalleledComponent.setLayerAddition(unparalleledComponent.getLayerAddition());
/*  87 */               unparalleledComponent.getBuffs().add(Integer.valueOf(buffId));
/*  88 */               unparalleledComponent.setBuffs(unparalleledComponent.getBuffs());
/*  89 */               unparalleledComponent.setSurpStar(unparalleledComponent.getSurpStar() - wushuangBuffBean.getCostStar());
/*  90 */               unparalleledComponent.setAttrbuteList(new ArrayList());
/*     */             }  }
/*     */           
/*     */           continue;
/*     */         } 
/*  95 */         for (Iterator<Integer> iterator = unparalleledComponent.getAttrbuteList().iterator(); iterator.hasNext(); ) { int buffId = ((Integer)iterator.next()).intValue();
/*  96 */           WushuangBuffBean wushuangBuffBean = (WushuangBuffBean)JsonTableService.getJsonData(buffId, WushuangBuffBean.class);
/*  97 */           if (wushuangBuffBean.getCostStar() == 9 && unparalleledComponent.getSurpStar() >= 9) {
/*  98 */             unparalleledComponent.getLayerAddition().add(Integer.valueOf(wushuangInsBean.getCheckPoint()));
/*  99 */             unparalleledComponent.setLayerAddition(unparalleledComponent.getLayerAddition());
/* 100 */             unparalleledComponent.getBuffs().add(Integer.valueOf(buffId));
/* 101 */             unparalleledComponent.setBuffs(unparalleledComponent.getBuffs());
/* 102 */             unparalleledComponent.setSurpStar(unparalleledComponent.getSurpStar() - wushuangBuffBean.getCostStar());
/* 103 */             unparalleledComponent.setAttrbuteList(new ArrayList());
/*     */           }  }
/*     */       
/*     */       }  }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     ArrayList<Reward> boxRewards = new ArrayList<>();
/* 112 */     for (Iterator<Integer> iterator2 = request.boxList.iterator(); iterator2.hasNext(); ) { int boxStar = ((Integer)iterator2.next()).intValue();
/* 113 */       if (unparalleledComponent.getLayerBox().indexOf(Integer.valueOf(boxStar)) >= 0) {
/*     */         continue;
/*     */       }
/* 116 */       if (unparalleledComponent.getCurrMaxStar() >= boxStar) {
/* 117 */         WushuangBoxBean wushuangBoxBean = (WushuangBoxBean)JsonTableService.getJsonData(boxStar, WushuangBoxBean.class);
/* 118 */         if (wushuangBoxBean != null) {
/* 119 */           ArrayList<Reward> boxReward = FinanceUtil.transform(wushuangBoxBean.getStarReward());
/* 120 */           boxRewards.addAll(boxReward);
/* 121 */           unparalleledComponent.getLayerBox().add(Integer.valueOf(boxStar));
/*     */         } 
/*     */       }  }
/*     */     
/* 125 */     rewards.addAll(boxRewards);
/* 126 */     PlotExpSweepData sweepData2 = new PlotExpSweepData();
/*     */     
/* 128 */     sweepData2.insId = -1;
/*     */     
/* 130 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 131 */     FinanceUtil.overlyingMap(mapRewards, boxRewards);
/* 132 */     ArrayList<Reward> reward3 = FinanceUtil.overlyingReward(mapRewards);
/* 133 */     sweepData2.list.addAll(reward3);
/* 134 */     ((SweepUnparalleledResponse)this.response).reward.add(sweepData2);
/*     */     
/* 136 */     Map<Long, Long> mapReward4 = new HashMap<>();
/* 137 */     FinanceUtil.overlyingMap(mapReward4, rewards);
/* 138 */     ArrayList<Reward> reward4 = FinanceUtil.overlyingReward(mapReward4);
/*     */     
/* 140 */     ((SweepUnparalleledResponse)this.response).playedPoints.addAll(unparalleledComponent.getPlayedPoints().keySet());
/* 141 */     unparalleledComponent.setSweep(true);
/* 142 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 143 */     if (unparalleledComponent.getPartneredHpMap().isEmpty()) {
/* 144 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 145 */       long vals = playerComponent.getPlayerAttrUp().getAttrByType(AttributeType.HP.getType(), -1L);
/* 146 */       unparalleledComponent.getPartneredHpMap().put(Long.valueOf(-1L), Long.valueOf(vals));
/*     */       
/* 148 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 149 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 150 */         if (null != partnerEntity) {
/* 151 */           long val = partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.HP.getType(), partnerEntity.getPid());
/* 152 */           unparalleledComponent.getPartneredHpMap().put(Long.valueOf(partnerEntity.getPid()), Long.valueOf(val));
/* 153 */           unparalleledComponent.setPartneredHpMap(unparalleledComponent.getPartneredHpMap());
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     if (unparalleledComponent.getAttrsList().isEmpty()) {
/* 158 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 159 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 160 */         ArrayList<Long> arrayList = new ArrayList<>();
/* 161 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 162 */         if (null != partnerEntity) {
/* 163 */           partnerComponent.getPartnerAttrUp().getAttrList(arrayList, partnerEntity.getPid());
/* 164 */           unparalleledComponent.getAttrsList().put(Long.valueOf(partnerEntity.getPid()), arrayList);
/* 165 */           unparalleledComponent.getLevelMap().put(Long.valueOf(partnerEntity.getPid()), Integer.valueOf(partnerEntity.getLevel()));
/*     */         } 
/*     */       } 
/* 168 */       ArrayList<Long> arrayList2 = new ArrayList<>();
/* 169 */       playerComponent.getPlayerAttrUp().getAttrList(arrayList2, -1L);
/* 170 */       unparalleledComponent.getAttrsList().put(Long.valueOf(-1L), arrayList2);
/* 171 */       unparalleledComponent.setAttrsList(unparalleledComponent.getAttrsList());
/* 172 */       unparalleledComponent.getLevelMap().put(Long.valueOf(-1L), Integer.valueOf(playerComponent.getLevel()));
/* 173 */       unparalleledComponent.setLevelMap(unparalleledComponent.getLevelMap());
/*     */     } 
/*     */     
/* 176 */     ((SweepUnparalleledResponse)this.response).attrbuteAddition = unparalleledComponent.getBuffs();
/* 177 */     ((SweepUnparalleledResponse)this.response).layerAddition = unparalleledComponent.getLayerAddition();
/* 178 */     ((SweepUnparalleledResponse)this.response).surpStar = unparalleledComponent.getSurpStar();
/* 179 */     ((SweepUnparalleledResponse)this.response).currMaxStar = unparalleledComponent.getCurrMaxStar();
/* 180 */     ((SweepUnparalleledResponse)this.response).curPoint = unparalleledComponent.getCurPoint();
/* 181 */     ((SweepUnparalleledResponse)this.response).isSweep = unparalleledComponent.isSweep() ? 1 : 0;
/* 182 */     ((SweepUnparalleledResponse)this.response).layerBox.addAll(unparalleledComponent.getLayerBox());
/* 183 */     FinanceUtil.reward(reward4, playerSession.getPlayer(), ResourceEvent.UnparSweepReward, false);
/* 184 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 185 */     if (taskComponent != null) {
/* 186 */       taskComponent.refreshSchedule(TaskType.UnparaMaxStar, 0, 0L);
/*     */     }
/*     */     
/* 189 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\SweepUnparalleledProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */