/*     */ package com.linlongyx.sanguo.webgame.app.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rune.RuneUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RuneBagComponent
/*     */   extends AbstractMapComponent<RuneBagEntity>
/*     */ {
/*     */   private final int CHANGETYPE_ADD = 1;
/*     */   private final int CHANGETYPE_DEC = 2;
/*     */   
/*     */   public RuneBagComponent(long playerId) {
/*  27 */     super(RuneBagEntity.class, playerId);
/*     */ 
/*     */     
/*  30 */     this.CHANGETYPE_ADD = 1;
/*  31 */     this.CHANGETYPE_DEC = 2;
/*     */   }
/*     */   
/*     */   public RuneBagEntity createEntity(Object key) {
/*  35 */     return (RuneBagEntity)super.createEntity(key);
/*     */   }
/*     */   
/*     */   public RuneBagEntity getEntity(long mid) {
/*  39 */     return (RuneBagEntity)getEntity(String.valueOf(mid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuneBagEntity addRune(IPlayer player, int itemId, ResourceEvent resourceEvent) {
/*  48 */     RuneComponent runeComponent = (RuneComponent)player.createIfNotExist(RuneComponent.class);
/*  49 */     long mid = runeComponent.genRuneMidId();
/*  50 */     RuneBagEntity runeBagEntity = createEntity(Long.valueOf(mid));
/*  51 */     runeBagEntity.setItemId(itemId);
/*  52 */     runeBagEntity.setLevel(1);
/*  53 */     runeBagEntity.setHole(0);
/*  54 */     addEntity((IEntity)runeBagEntity);
/*  55 */     notice(runeBagEntity, 1);
/*  56 */     runeBagLog(runeBagEntity, 1, resourceEvent);
/*     */     
/*  58 */     return runeBagEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notice(RuneBagEntity runeBagEntity, int type) {
/*  69 */     if (LookUpService.getByPlayerId(getPlayerId()) != null) {
/*  70 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/*  71 */       RuneBagNoticeResponse response = new RuneBagNoticeResponse();
/*  72 */       response.info.add(RuneUtil.tranform(runeBagEntity));
/*     */       
/*  74 */       response.changeType = (type == 1) ? 0 : 1;
/*  75 */       playerSession.sendMessage((ResponseBase)response);
/*  76 */       RuneUtil.sysList(playerSession);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHole(RuneBagEntity runeBagEntity) {
/*  85 */     if (LookUpService.getByPlayerId(getPlayerId()) != null) {
/*  86 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/*  87 */       RuneBagNoticeResponse response = new RuneBagNoticeResponse();
/*  88 */       response.info.add(RuneUtil.tranform(runeBagEntity));
/*  89 */       response.changeType = 2;
/*  90 */       playerSession.sendMessage((ResponseBase)response);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteItem(RuneBagEntity runeBagEntity, ResourceEvent resourceEvent) {
/*  99 */     runeBagLog(runeBagEntity, 2, resourceEvent);
/* 100 */     notice(runeBagEntity, 2);
/* 101 */     this.proxy.delEntity(runeBagEntity);
/*     */   }
/*     */   
/*     */   public void runeBagLog(RuneBagEntity runeBagEntity, int type, ResourceEvent resourceEvent) {
/* 105 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(getPlayerId(), PlayerComponent.class);
/* 106 */     LogUtil.errorLog(new Object[] { LogType.RUNE, Long.valueOf(getPlayerId()), Long.valueOf(playerComponent.getUserId()), Integer.valueOf(playerComponent.getChannel()), Long.valueOf(runeBagEntity.getMid()), Integer.valueOf(runeBagEntity.getItemId()), Integer.valueOf(runeBagEntity.getLevel()), Integer.valueOf(runeBagEntity.getHole()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(type) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upload(RuneBagEntity runeBagEntity) {
/* 114 */     runeBagEntity.setHole(0);
/* 115 */     updateHoleToDB(runeBagEntity);
/* 116 */     updateHole(runeBagEntity);
/*     */   }
/*     */   
/*     */   public ArrayList<RuneBagEntity> getNotInstall() {
/* 120 */     ArrayList<RuneBagEntity> list = new ArrayList<>();
/* 121 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/* 122 */       RuneBagEntity runeBagEntity = (RuneBagEntity)entry.getValue();
/* 123 */       if (runeBagEntity.getHole() == 0) {
/* 124 */         list.add(runeBagEntity);
/*     */       }
/*     */     } 
/* 127 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 133 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 138 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateLevelToDB(RuneBagEntity runeBagEntity) {
/* 142 */     getProxy().setUpdate(String.valueOf(runeBagEntity.getMid()), "level");
/*     */   }
/*     */   
/*     */   public void updateHoleToDB(RuneBagEntity runeBagEntity) {
/* 146 */     getProxy().setUpdate(String.valueOf(runeBagEntity.getMid()), "hole");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rune\RuneBagComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */