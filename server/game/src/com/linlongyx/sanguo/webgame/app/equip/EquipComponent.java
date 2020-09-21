/*     */ package com.linlongyx.sanguo.webgame.app.equip;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.EquipFightValue;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipArtificeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CrossRankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagRecoveryProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRecoveryRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.AddEquipNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ 
/*     */ public class EquipComponent
/*     */   extends AbstractMapComponent<EquipEntity>
/*     */ {
/*  39 */   private Pair<Integer, AtomicInteger> statisticRequest = new Pair(Integer.valueOf(TimeUtil.currentTime()), new AtomicInteger(0));
/*     */   
/*     */   public Pair<Integer, AtomicInteger> getStatisticRequest() {
/*  42 */     return this.statisticRequest;
/*     */   }
/*     */   
/*  45 */   private ArrayList<EquipData> equipDatas = new ArrayList<>();
/*     */   
/*     */   public EquipComponent(long playerId) {
/*  48 */     super(EquipEntity.class, playerId);
/*     */   }
/*     */   private static final int BAG_MAX_SIZE = 500;
/*     */   public EquipEntity getEquipEntity(long mid) {
/*  52 */     return (EquipEntity)getEntity(String.valueOf(mid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  60 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  65 */     if (time == 0) {
/*  66 */       for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  67 */         EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*  68 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  69 */         if (null != itemBean) {
/*  70 */           EquipArtificeBean equipArtificeBean = (EquipArtificeBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipArtificeBean.class);
/*  71 */           if (null != equipArtificeBean && equipArtificeBean.getCleanUp() == 1) {
/*  72 */             equipEntity.setArtificeLucky(0);
/*  73 */             updateArtificeLuckyToDB(equipEntity);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEquipSize() {
/*  87 */     int num = 0;
/*  88 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  89 */       EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*  90 */       if (equipEntity.getItemId() != 0) {
/*  91 */         num++;
/*     */       }
/*     */     } 
/*  94 */     return num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEquip(IPlayer player, int itemId, ResourceEvent resourceEvent) {
/* 104 */     addEquip(player, itemId, resourceEvent, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEquip(IPlayer player, int tableId, ResourceEvent resourceEvent, boolean sendFlag) {
/* 113 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(tableId, ItemBean.class);
/* 114 */     if (null == itemBean) {
/* 115 */       LogUtil.errorLog(new Object[] { "addEquip", Integer.valueOf(tableId) });
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     ExtendComponent extendComponent = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/*     */     
/* 121 */     extendComponent.setEquipId(extendComponent.getEquipId() + 1L);
/* 122 */     long mid = extendComponent.getEquipId();
/* 123 */     EquipEntity equipEntity = (EquipEntity)createEntity(Long.valueOf(mid));
/*     */     
/* 125 */     equipEntity.setItemId(tableId);
/* 126 */     updateMidToDB(equipEntity);
/* 127 */     equipEntity.setQuaity(itemBean.getItemQuality());
/* 128 */     equipEntity.setTalismanRank(1);
/* 129 */     updateTalismanRankToDB(equipEntity);
/* 130 */     addEntity((IEntity)equipEntity);
/* 131 */     if (getEntityMap().size() > 500) {
/* 132 */       BagRecoveryRequest request = new BagRecoveryRequest();
/* 133 */       request.ids.add(Long.valueOf(equipEntity.getMid()));
/* 134 */       request.type = 1;
/* 135 */       (new BagRecoveryProcessor()).handle(player.getSession(), (RequestBase)request);
/*     */       return;
/*     */     } 
/* 138 */     if (itemBean.getRecord() == 0) {
/* 139 */       LogUtil.gameLog(LogType.EQUIP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(tableId), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(1) });
/*     */     }
/* 141 */     updateEquipFightValue(equipEntity);
/* 142 */     EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 143 */     updateStoneToDB(equipEntity);
/* 144 */     this.equipDatas.add(equipData);
/* 145 */     if (sendFlag) {
/* 146 */       notice();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void notice() {
/* 152 */     if (this.equipDatas.isEmpty())
/*     */       return; 
/* 154 */     if (LookUpService.getByPlayerId(getPlayerId()) != null) {
/* 155 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/* 156 */       AddEquipNoticeResponse response = new AddEquipNoticeResponse();
/* 157 */       response.equipDatas.addAll(this.equipDatas);
/* 158 */       playerSession.sendMessage((ResponseBase)response);
/* 159 */       this.equipDatas.clear();
/*     */     } 
/* 161 */     saveAllToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EquipEntity checkEquip(long equipId) {
/* 171 */     EquipEntity equipEntity = getEquipEntity(equipId);
/* 172 */     if (null == equipEntity) {
/* 173 */       return null;
/*     */     }
/* 175 */     return equipEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<EquipEntity> getList(int itemId) {
/* 185 */     List<EquipEntity> list = new ArrayList<>();
/* 186 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 187 */       EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 188 */       if (equipEntity.getItemId() == itemId && !equipEntity.wear() && equipEntity.getRefineLv() == 0 && equipEntity.getStrengthLv() == 0) {
/* 189 */         list.add(equipEntity);
/*     */       }
/*     */     } 
/* 192 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteEquip(EquipEntity equipEntity, ResourceEvent resourceEvent) {
/* 202 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 203 */     if (itemBean != null && itemBean.getRecord() == 0) {
/* 204 */       LogUtil.gameLog(LogType.EQUIP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(equipEntity.getItemId()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/* 205 */       LogUtil.errorLog(new Object[] { "deleteEquip", Long.valueOf(getPlayerId()), Integer.valueOf(equipEntity.getItemId()), Long.valueOf(equipEntity.getMid()) });
/*     */     } 
/* 207 */     this.proxy.delEntity(equipEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEquipFightValue(EquipEntity equipEntity) {
/* 216 */     EquipFightValue equipFightValue = new EquipFightValue(0, false);
/* 217 */     long total = equipFightValue.getEquipFightValue(equipEntity);
/* 218 */     equipEntity.setFightValue(total);
/* 219 */     updateFightValueToDB(equipEntity);
/* 220 */     if (!EquipUtil.isTreasure(equipEntity.getItemId()) && RankActUtil.isOldServer()) {
/* 221 */       RankActUtil.refreshRankValue(RankActType.Equip2.getType(), total, this.playerId);
/*     */     } else {
/* 223 */       RankActUtil.refreshRankValue(RankActType.Equip.getType(), total, this.playerId);
/*     */     } 
/* 225 */     CrossRankActUtil.refreshRankValue(CrossRankActType.Equip.getType(), total, this.playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 231 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 236 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateMidToDB(EquipEntity equipEntity) {
/* 240 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "itemId");
/*     */   }
/*     */   
/*     */   public void updateStrengthToDB(EquipEntity equipEntity) {
/* 244 */     updateEquipFightValue(equipEntity);
/* 245 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "strengthLv");
/*     */   }
/*     */   
/*     */   public void updateSoulToDB(EquipEntity equipEntity) {
/* 249 */     updateEquipFightValue(equipEntity);
/* 250 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "soulLv");
/*     */   }
/*     */   
/*     */   public void updateRefineToDB(EquipEntity equipEntity) {
/* 254 */     updateEquipFightValue(equipEntity);
/* 255 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "refineLv");
/*     */   }
/*     */   
/*     */   public void updateStoneToDB(EquipEntity equipEntity) {
/* 259 */     updateEquipFightValue(equipEntity);
/* 260 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "stones");
/*     */   }
/*     */   
/*     */   public void updateIsWearToDB(EquipEntity equipEntity) {
/* 264 */     if (equipEntity.wear()) {
/* 265 */       updateEquipFightValue(equipEntity);
/*     */     }
/* 267 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "isWear");
/*     */   }
/*     */   
/*     */   public void updateZhuLvToDB(EquipEntity equipEntity) {
/* 271 */     updateEquipFightValue(equipEntity);
/* 272 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "zhuLv");
/*     */   }
/*     */   
/*     */   public void updateFightValueToDB(EquipEntity equipEntity) {
/* 276 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "fightValue");
/*     */   }
/*     */   
/*     */   public void updateArtificeProcessToDB(EquipEntity equipEntity) {
/* 280 */     updateEquipFightValue(equipEntity);
/* 281 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "artificeProcess");
/*     */   }
/*     */   public void updateArtificeLevelToDB(EquipEntity equipEntity) {
/* 284 */     updateEquipFightValue(equipEntity);
/* 285 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "artificeLevel");
/*     */   }
/*     */   public void updateArtificeLuckyToDB(EquipEntity equipEntity) {
/* 288 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "artificeLucky");
/*     */   }
/*     */   
/*     */   public void updateTalismanRankToDB(EquipEntity equipEntity) {
/* 292 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "talismanRank");
/*     */   }
/*     */   
/*     */   public void updateBelondToToDB(EquipEntity equipEntity) {
/* 296 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "belondTo");
/*     */   }
/*     */   
/*     */   public void updateStarToDB(EquipEntity equipEntity) {
/* 300 */     getProxy().setUpdate(String.valueOf(equipEntity.getMid()), "star");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\equip\EquipComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */