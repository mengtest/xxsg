/*     */ package com.linlongyx.sanguo.webgame.app.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBreakBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterDestinyBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AddPartnerNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartnerComponent
/*     */   extends AbstractMapComponent<PartnerEntity>
/*     */ {
/*  41 */   PartnerAttrUpdate partnerAttrUp = new PartnerAttrUpdate();
/*  42 */   private ArrayList<PartnerInfo> partnerInfos = new ArrayList<>(); private final int CHANGETYPE_ADD = 1;
/*     */   private final int CHANGETYPE_DEC = 2;
/*     */   
/*  45 */   public PartnerComponent(long playerId) { super(PartnerEntity.class, playerId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.CHANGETYPE_ADD = 1;
/*  81 */     this.CHANGETYPE_DEC = 2; }
/*     */    public PartnerEntity getEntity(long id) {
/*     */     return (PartnerEntity)getEntity(String.valueOf(id));
/*     */   }
/*     */   public void init() {
/*  86 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*     */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*     */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void deletePartner(PartnerEntity partnerEntity, ResourceEvent resourceEvent) {
/* 100 */     LogUtil.gameLog(LogType.PARTNER, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(partnerEntity.getTableId()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/* 101 */     LogUtil.errorLog(new Object[] { "deletePartner", Long.valueOf(getPlayerId()), Integer.valueOf(partnerEntity.getTableId()), Long.valueOf(partnerEntity.getPid()) });
/* 102 */     this.proxy.delEntity(partnerEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPartner(IPlayer player, int tableId, ResourceEvent resourceEvent) {
/* 114 */     addPartner(player, tableId, resourceEvent, false);
/*     */   } public boolean reset(int time) { if (time == 0) {
/*     */       for (IMapEntity iMapEntity : getEntityMap().values()) {
/*     */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*     */         FighterDestinyBean fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(partnerEntity.getDesLv(), FighterDestinyBean.class);
/*     */         if (fighterDestinyBean.getCleanUp() == 1)
/*     */           partnerEntity.setProgress(0); 
/*     */       } 
/*     */     } else if (time == 18000) {
/*     */     
/*     */     } 
/* 125 */     return true; } public void addPartner(IPlayer player, int tableId, ResourceEvent resourceEvent, boolean sendFlag) { FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(tableId, FighterBean.class);
/* 126 */     if (fighterBean == null) {
/*     */       return;
/*     */     }
/* 129 */     ExtendComponent extendComponent = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/* 130 */     extendComponent.setPartnerId(extendComponent.getPartnerId() + 1L);
/* 131 */     long mid = extendComponent.getPartnerId();
/*     */     
/* 133 */     PartnerEntity partnerEntity = (PartnerEntity)createEntity(Long.valueOf(mid));
/*     */     
/* 135 */     partnerEntity.setTableId(tableId);
/* 136 */     partnerEntity.setLevel(1);
/* 137 */     for (EquipPart equipPart : EquipPart.values()) {
/* 138 */       if (null == partnerEntity.getEquips().get(Integer.valueOf(equipPart.getPart()))) {
/* 139 */         partnerEntity.getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/*     */       }
/*     */     } 
/* 142 */     addEntity((IEntity)partnerEntity);
/* 143 */     LogUtil.gameLog(LogType.PARTNER, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(partnerEntity.getTableId()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/* 144 */     this.partnerAttrUp.initBase(null, partnerEntity.getPid());
/* 145 */     this.partnerAttrUp.initAll(player, partnerEntity.getPid());
/*     */     
/* 147 */     logNotice(partnerEntity, 1);
/* 148 */     tranformPartner(partnerEntity);
/* 149 */     TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(player.getPlayerId(), TaskComponent.class);
/* 150 */     if (null != taskComponent) {
/* 151 */       taskComponent.refreshSchedule(TaskType.QualityFighter, fighterBean.getQuality(), 0L);
/* 152 */       taskComponent.refreshSchedule(TaskType.GetOnePartner, fighterBean.getId(), 0L);
/*     */     } 
/*     */     
/* 155 */     if (sendFlag) {
/* 156 */       notice();
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPartner2(IPlayer player, int tableId, int stars, ResourceEvent resourceEvent, boolean sendFlag) {
/* 167 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(tableId, FighterBean.class);
/* 168 */     if (fighterBean == null) {
/*     */       return;
/*     */     }
/* 171 */     DBIncrementService service = (DBIncrementService)AppContext.getBean("dbIncrementService");
/* 172 */     long mid = service.generate(this.proxy.getTableName(), AppContext.getPlayerInitId()).longValue();
/*     */     
/* 174 */     PartnerEntity partnerEntity = (PartnerEntity)createEntity(Long.valueOf(mid));
/*     */     
/* 176 */     partnerEntity.setTableId(tableId);
/* 177 */     partnerEntity.setStars(stars);
/* 178 */     partnerEntity.setLevel(1);
/* 179 */     for (EquipPart equipPart : EquipPart.values()) {
/* 180 */       if (null == partnerEntity.getEquips().get(Integer.valueOf(equipPart.getPart()))) {
/* 181 */         partnerEntity.getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/*     */       }
/*     */     } 
/* 184 */     addEntity((IEntity)partnerEntity);
/* 185 */     this.partnerAttrUp.initBase(null, partnerEntity.getPid());
/* 186 */     this.partnerAttrUp.initAll(player, partnerEntity.getPid());
/*     */     
/* 188 */     logNotice(partnerEntity, 1);
/* 189 */     saveAllToDB();
/* 190 */     tranformPartner(partnerEntity);
/* 191 */     if (sendFlag) {
/* 192 */       notice();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkPartner(int tId) {
/* 201 */     boolean isHash = false;
/* 202 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 203 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 204 */       if (partnerEntity.getTableId() == tId) {
/* 205 */         isHash = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 209 */     return isHash;
/*     */   }
/*     */   
/*     */   public void notice() {
/* 213 */     if (this.partnerInfos.isEmpty())
/*     */       return; 
/* 215 */     if (LookUpService.getByPlayerId(getPlayerId()) != null) {
/* 216 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/* 217 */       AddPartnerNoticeResponse response = new AddPartnerNoticeResponse();
/* 218 */       response.partnerInfos.addAll(this.partnerInfos);
/* 219 */       playerSession.sendMessage((ResponseBase)response);
/* 220 */       this.partnerInfos.clear();
/*     */       
/* 222 */       AttrUpdateUtil.refreshPartner(playerSession);
/*     */     } 
/* 224 */     saveAllToDB();
/*     */   }
/*     */   
/*     */   private void tranformPartner(PartnerEntity partnerEntity) {
/* 228 */     PartnerInfo partnerInfo = new PartnerInfo();
/* 229 */     partnerInfo.pid = partnerEntity.getPid();
/* 230 */     partnerInfo.tid = partnerEntity.getTableId();
/* 231 */     partnerInfo.level = partnerEntity.getLevel();
/* 232 */     partnerInfo.exp = partnerEntity.getExp();
/* 233 */     partnerInfo.wearSkin = partnerEntity.getWearSkin();
/* 234 */     partnerInfo.activeSkins = new ArrayList<>(partnerEntity.getActiveSkins());
/* 235 */     partnerInfo.desLv = partnerEntity.getDesLv();
/* 236 */     partnerInfo.progress = partnerEntity.getProgress();
/* 237 */     partnerInfo.primLevel = partnerEntity.getPrimLv();
/* 238 */     partnerInfo.stars = partnerEntity.getStars();
/* 239 */     partnerInfo.status = partnerEntity.getStatus();
/* 240 */     partnerInfo.breachLevel = partnerEntity.getBreakthroughs();
/* 241 */     partnerInfo.fightValue = partnerEntity.getFightValue();
/* 242 */     partnerEntity.getEquips().keySet().forEach(part -> {
/*     */           KeyValue keyValue = new KeyValue();
/*     */           keyValue.key = part.intValue();
/*     */           keyValue.value = ((Long)partnerEntity.getEquips().get(part)).longValue();
/*     */           partnerInfo.equips.add(keyValue);
/*     */         });
/* 248 */     KeyValue keyValue = new KeyValue();
/* 249 */     keyValue.key = 7L;
/* 250 */     keyValue.value = partnerEntity.getTalisman();
/* 251 */     partnerInfo.equips.add(keyValue);
/* 252 */     this.partnerAttrUp.getAttrList(partnerInfo.attributes, partnerEntity.getPid());
/* 253 */     this.partnerInfos.add(partnerInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTalentsSkill(PartnerEntity partnerEntity) {
/* 263 */     FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBreakBean.class);
/* 264 */     int skillId = 0;
/* 265 */     for (int i = 0; i <= partnerEntity.getBreakthroughs(); i++) {
/* 266 */       for (FighterBreakBean.LevelBean.TalentBean talentBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(i))).getTalent()) {
/* 267 */         if (talentBean.getId() == 100) {
/* 268 */           skillId = talentBean.getNum();
/*     */         }
/*     */       } 
/*     */     } 
/* 272 */     return skillId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<PartnerEntity> getBattlePartner() {
/* 281 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(this.playerId, PlayerComponent.class);
/* 282 */     ArrayList<PartnerEntity> battlePartner = new ArrayList<>();
/* 283 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 284 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 285 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/* 286 */         battlePartner.add(partnerEntity);
/*     */       }
/*     */     } 
/* 289 */     return battlePartner;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartnerEntity getMaxFightValue() {
/* 297 */     PartnerEntity targetPartnerEntity = null;
/* 298 */     long fightValue = 0L;
/* 299 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 300 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 301 */       if (fightValue < partnerEntity.getFightValue()) {
/* 302 */         fightValue = partnerEntity.getFightValue();
/* 303 */         targetPartnerEntity = partnerEntity;
/*     */       } 
/*     */     } 
/* 306 */     return targetPartnerEntity;
/*     */   }
/*     */   
/*     */   public PartnerAttrUpdate getPartnerAttrUp() {
/* 310 */     return this.partnerAttrUp;
/*     */   }
/*     */   
/*     */   public void setPartnerAttrUp(PartnerAttrUpdate partnerAttrUp) {
/* 314 */     this.partnerAttrUp = partnerAttrUp;
/*     */   }
/*     */   
/*     */   public void logNotice(PartnerEntity partnerEntity, int type) {
/* 318 */     int num = (type == 1) ? 1 : -1;
/*     */   }
/*     */   
/*     */   public void updateDesLvDB(long id) {
/* 322 */     getProxy().setUpdate(String.valueOf(id), "desLv");
/*     */   }
/*     */   
/*     */   public void updateProgressDB(long id) {
/* 326 */     getProxy().setUpdate(String.valueOf(id), "progress");
/*     */   }
/*     */   
/*     */   public void updatePrimLvDB(long id) {
/* 330 */     getProxy().setUpdate(String.valueOf(id), "primLv");
/*     */   }
/*     */   
/*     */   public void updateLevelDB(long id) {
/* 334 */     getProxy().setUpdate(String.valueOf(id), "level");
/*     */   }
/*     */   
/*     */   public void updateExpDB(long id) {
/* 338 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*     */   }
/*     */   
/*     */   public void updateStarsDB(long id) {
/* 342 */     getProxy().setUpdate(String.valueOf(id), "stars");
/*     */   }
/*     */   
/*     */   public void updateBreakthroughsDB(long id) {
/* 346 */     getProxy().setUpdate(String.valueOf(id), "breakthroughs");
/*     */   }
/*     */   
/*     */   public void updateEquipsDB(long id) {
/* 350 */     getProxy().setUpdate(String.valueOf(id), "equips");
/*     */   }
/*     */   
/*     */   public void updateStatusDB(long id) {
/* 354 */     getProxy().setUpdate(String.valueOf(id), "status");
/*     */   }
/*     */   
/*     */   public void updateFightValueDB(long id) {
/* 358 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*     */   }
/*     */   
/*     */   public void updateSoulLevelDB(long id) {
/* 362 */     getProxy().setUpdate(String.valueOf(id), "soulLevel");
/*     */   }
/*     */   
/*     */   public void updateTalismanDB(long id) {
/* 366 */     getProxy().setUpdate(String.valueOf(id), "talisman");
/*     */   }
/*     */   
/*     */   public void updateRuneMapDB(long id) {
/* 370 */     getProxy().setUpdate(String.valueOf(id), "runeMap");
/*     */   }
/*     */   
/*     */   public void updateReincarnationMapDB(long id) {
/* 374 */     getProxy().setUpdate(String.valueOf(id), "reincarnationMap");
/*     */   }
/*     */   
/*     */   public void updateReincarnationIdsDB(long id) {
/* 378 */     getProxy().setUpdate(String.valueOf(id), "reincarnationIds");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\partner\PartnerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */