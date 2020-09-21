/*     */ package com.linlongyx.sanguo.webgame.processors.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RuneParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysReplaceRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysReplaceResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuneSysReplaceProcessor
/*     */   extends ProcessorBase<RuneSysReplaceRequest, RuneSysReplaceResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new RuneSysReplaceResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, RuneSysReplaceRequest request) {
/*  35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 69)) {
/*  36 */       return 10061;
/*     */     }
/*  38 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/*     */     
/*  40 */     RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/*  41 */     RuneBagEntity runeBagEntity = runeBagComponent.getEntity(request.mid);
/*  42 */     if (null == runeBagEntity) {
/*  43 */       return 17806;
/*     */     }
/*  45 */     if (runeBagEntity.getHole() > 0) {
/*  46 */       return 17809;
/*     */     }
/*     */     
/*  49 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  50 */     if (playerComponent.getFighters().indexOf(Long.valueOf(request.pid)) < 0) {
/*  51 */       return 13302;
/*     */     }
/*  53 */     RuneParameter runeParameter = (RuneParameter)ParameterConstant.getParameter(69);
/*  54 */     if (request.hole <= 0 || request.hole > runeParameter.getHoleLimit()) {
/*  55 */       return 17811;
/*     */     }
/*  57 */     if (playerComponent.getLevel() < runeParameter.getHoleLevel(request.hole)) {
/*  58 */       return 17812;
/*     */     }
/*  60 */     RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/*  61 */     if (null == runeBean) {
/*  62 */       return 10095;
/*     */     }
/*  64 */     if (request.pid == -1L) {
/*  65 */       long mid = ((Long)runeComponent.getRuneMap().getOrDefault(Integer.valueOf(request.hole), Long.valueOf(0L))).longValue();
/*  66 */       boolean hash = false;
/*  67 */       for (Iterator<Long> iterator = runeComponent.getRuneMap().values().iterator(); iterator.hasNext(); ) { long rid = ((Long)iterator.next()).longValue();
/*  68 */         if (rid == mid) {
/*     */           continue;
/*     */         }
/*  71 */         RuneBagEntity runeBagEntity2 = runeBagComponent.getEntity(rid);
/*  72 */         RuneBean runeBean1 = (RuneBean)JsonTableService.getJsonData(runeBagEntity2.getItemId(), RuneBean.class);
/*  73 */         if (null != runeBagEntity2 && runeBean.getType() == runeBean1.getType()) {
/*  74 */           hash = true;
/*     */           break;
/*     */         }  }
/*     */       
/*  78 */       if (hash) {
/*  79 */         return 17810;
/*     */       }
/*     */       
/*  82 */       if (mid != 0L) {
/*  83 */         RuneBagEntity runeBagEntity1 = runeBagComponent.getEntity(mid);
/*  84 */         if (null != runeBagEntity1) {
/*  85 */           runeBagComponent.upload(runeBagEntity1);
/*     */         }
/*     */       } 
/*  88 */       runeComponent.getRuneMap().put(Integer.valueOf(request.hole), Long.valueOf(request.mid));
/*  89 */       runeComponent.setRuneMap(runeComponent.getRuneMap());
/*     */     } else {
/*  91 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  92 */       PartnerEntity partnerEntity = partnerComponent.getEntity(request.pid);
/*  93 */       if (null == partnerEntity) {
/*  94 */         return 13303;
/*     */       }
/*  96 */       long mid = ((Long)partnerEntity.getRuneMap().getOrDefault(Integer.valueOf(request.hole), Long.valueOf(0L))).longValue();
/*  97 */       boolean hash = false;
/*  98 */       for (Iterator<Long> iterator = partnerEntity.getRuneMap().values().iterator(); iterator.hasNext(); ) { long rid = ((Long)iterator.next()).longValue();
/*  99 */         if (rid == mid) {
/*     */           continue;
/*     */         }
/* 102 */         RuneBagEntity runeBagEntity2 = runeBagComponent.getEntity(rid);
/* 103 */         RuneBean runeBean1 = (RuneBean)JsonTableService.getJsonData(runeBagEntity2.getItemId(), RuneBean.class);
/* 104 */         if (null != runeBagEntity2 && runeBean.getType() == runeBean1.getType()) {
/* 105 */           hash = true;
/*     */           break;
/*     */         }  }
/*     */       
/* 109 */       if (hash) {
/* 110 */         return 17810;
/*     */       }
/*     */       
/* 113 */       if (mid != 0L) {
/* 114 */         RuneBagEntity runeBagEntity1 = runeBagComponent.getEntity(mid);
/* 115 */         if (null != runeBagEntity1) {
/* 116 */           runeBagComponent.upload(runeBagEntity1);
/*     */         }
/*     */       } 
/* 119 */       partnerEntity.getRuneMap().put(Integer.valueOf(request.hole), Long.valueOf(request.mid));
/* 120 */       partnerComponent.updateRuneMapDB(partnerEntity.getPid());
/*     */     } 
/* 122 */     runeBagEntity.setHole(request.hole);
/* 123 */     runeBagComponent.updateHoleToDB(runeBagEntity);
/* 124 */     AttrUpdateUtil.refreshRune(playerSession);
/* 125 */     ((RuneSysReplaceResponse)this.response).pid = request.pid;
/* 126 */     ((RuneSysReplaceResponse)this.response).rune = RuneUtil.tranform(runeBagEntity);
/* 127 */     runeBagComponent.updateHole(runeBagEntity);
/* 128 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void process(IPlayerSession playerSession, RuneSysReplaceRequest request) throws Exception {
/* 133 */     short retCode = handleRequest(playerSession, request);
/* 134 */     ((RuneSysReplaceResponse)this.response).setRetCode(retCode);
/* 135 */     playerSession.sendMessage(this.response);
/*     */     
/* 137 */     if (retCode != 0)
/*     */       return; 
/* 139 */     RuneUtil.sysList(playerSession);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneSysReplaceProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */