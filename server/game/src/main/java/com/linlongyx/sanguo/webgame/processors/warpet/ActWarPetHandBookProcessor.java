/*     */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.KungfuHandbookBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PetHandbookBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StageHandbookBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActWarPetHandBookRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActWarPetHandBookResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public class ActWarPetHandBookProcessor
/*     */   extends ProcessorBase<ActWarPetHandBookRequest, ActWarPetHandBookResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new ActWarPetHandBookResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ActWarPetHandBookRequest request) {
/*  33 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  34 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  35 */     if (request.handType == 0) {
/*  36 */       PetHandbookBean petHandbookBean = (PetHandbookBean)JsonTableService.getJsonData(request.handbookId, PetHandbookBean.class);
/*  37 */       WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/*  38 */       if (null == petHandbookBean) {
/*  39 */         return 10006;
/*     */       }
/*  41 */       if (request.type == 0) {
/*     */         
/*  43 */         if (extendComponent.getWpHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/*  44 */           return 16006;
/*     */         }
/*  46 */         int num = 0;
/*  47 */         for (Iterator<Integer> iterator = petHandbookBean.getPet().iterator(); iterator.hasNext(); ) { int warpetId = ((Integer)iterator.next()).intValue();
/*  48 */           if (warPetComponent.getEntity(warpetId) != null) {
/*  49 */             num++;
/*     */           } }
/*     */         
/*  52 */         if (num != petHandbookBean.getPet().size()) {
/*  53 */           return 16007;
/*     */         }
/*  55 */         extendComponent.getWpHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(0));
/*  56 */         extendComponent.setWpHandbook(extendComponent.getWpHandbook());
/*  57 */       } else if (request.type == 1) {
/*  58 */         if (!extendComponent.getWpHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/*  59 */           return 16008;
/*     */         }
/*  61 */         int star = ((Integer)extendComponent.getWpHandbook().get(Integer.valueOf(request.handbookId))).intValue() + 1;
/*  62 */         int num = 0;
/*  63 */         for (Iterator<Integer> iterator = petHandbookBean.getPet().iterator(); iterator.hasNext(); ) { int warpetId = ((Integer)iterator.next()).intValue();
/*  64 */           if (warPetComponent.getEntity(warpetId).getStar() >= star) {
/*  65 */             num++;
/*     */           } }
/*     */         
/*  68 */         if (num != petHandbookBean.getPet().size()) {
/*  69 */           return 16007;
/*     */         }
/*  71 */         extendComponent.getWpHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(star));
/*  72 */         extendComponent.setWpHandbook(extendComponent.getWpHandbook());
/*     */       } 
/*  74 */       ((ActWarPetHandBookResponse)this.response).handbookId = request.handbookId;
/*  75 */       ((ActWarPetHandBookResponse)this.response).star = ((Integer)extendComponent.getWpHandbook().get(Integer.valueOf(request.handbookId))).intValue();
/*  76 */       extendComponent.getWpHandbook().keySet().forEach(id -> {
/*     */             KeyValue keyValue = new KeyValue();
/*     */             keyValue.key = id.intValue();
/*     */             keyValue.value = ((Integer)extendComponent.getWpHandbook().get(id)).intValue();
/*     */             ((ActWarPetHandBookResponse)this.response).infos.add(keyValue);
/*     */           });
/*  82 */       AttrUpdateUtil.refreshWarPet(playerSession);
/*  83 */     } else if (request.handType == 1) {
/*     */       
/*  85 */       KungfuHandbookBean kungfuHandbookBean = (KungfuHandbookBean)JsonTableService.getJsonData(request.handbookId, KungfuHandbookBean.class);
/*  86 */       KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/*  87 */       if (null == kungfuHandbookBean) {
/*  88 */         return 10006;
/*     */       }
/*  90 */       if (request.type == 0) {
/*  91 */         if (singleInsComponent.getKfHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/*  92 */           return 16006;
/*     */         }
/*  94 */         int num = 0;
/*  95 */         for (Iterator<Integer> iterator = kungfuHandbookBean.getKungfu().iterator(); iterator.hasNext(); ) { int kungfu = ((Integer)iterator.next()).intValue();
/*  96 */           if (kungFuComponent.getEntity(kungfu) != null) {
/*  97 */             num++;
/*     */           } }
/*     */         
/* 100 */         if (num != kungfuHandbookBean.getKungfu().size()) {
/* 101 */           return 16007;
/*     */         }
/* 103 */         singleInsComponent.getKfHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(0));
/* 104 */         singleInsComponent.setKfHandbook(singleInsComponent.getKfHandbook());
/* 105 */       } else if (request.type == 1) {
/* 106 */         if (!singleInsComponent.getKfHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/* 107 */           return 16008;
/*     */         }
/* 109 */         int star = ((Integer)singleInsComponent.getKfHandbook().get(Integer.valueOf(request.handbookId))).intValue() + 1;
/* 110 */         int num = 0;
/* 111 */         for (Iterator<Integer> iterator = kungfuHandbookBean.getKungfu().iterator(); iterator.hasNext(); ) { int kungfu = ((Integer)iterator.next()).intValue();
/* 112 */           if (kungFuComponent.getEntity(kungfu).getStar() >= star) {
/* 113 */             num++;
/*     */           } }
/*     */         
/* 116 */         if (num != kungfuHandbookBean.getKungfu().size()) {
/* 117 */           return 16007;
/*     */         }
/* 119 */         singleInsComponent.getKfHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(star));
/* 120 */         singleInsComponent.setKfHandbook(singleInsComponent.getKfHandbook());
/*     */       } 
/* 122 */       ((ActWarPetHandBookResponse)this.response).handbookId = request.handbookId;
/* 123 */       ((ActWarPetHandBookResponse)this.response).star = ((Integer)singleInsComponent.getKfHandbook().get(Integer.valueOf(request.handbookId))).intValue();
/* 124 */       singleInsComponent.getKfHandbook().keySet().forEach(id -> {
/*     */             KeyValue keyValue = new KeyValue();
/*     */             keyValue.key = id.intValue();
/*     */             keyValue.value = ((Integer)singleInsComponent.getKfHandbook().get(id)).intValue();
/*     */             ((ActWarPetHandBookResponse)this.response).infos.add(keyValue);
/*     */           });
/* 130 */       AttrUpdateUtil.refreshKungFu(playerSession);
/*     */     }
/* 132 */     else if (request.handType == 2) {
/*     */       
/* 134 */       StageHandbookBean stageHandbookBean = (StageHandbookBean)JsonTableService.getJsonData(request.handbookId, StageHandbookBean.class);
/* 135 */       StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 136 */       if (null == stageHandbookBean) {
/* 137 */         return 10006;
/*     */       }
/* 139 */       if (request.type == 0) {
/* 140 */         if (singleInsComponent.getStageHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/* 141 */           return 16006;
/*     */         }
/* 143 */         int num = 0;
/* 144 */         for (Iterator<Integer> iterator = stageHandbookBean.getStage().iterator(); iterator.hasNext(); ) { int stage = ((Integer)iterator.next()).intValue();
/* 145 */           if (stageComponent.getEntity(stage) != null) {
/* 146 */             num++;
/*     */           } }
/*     */         
/* 149 */         if (num != stageHandbookBean.getStage().size()) {
/* 150 */           return 16007;
/*     */         }
/* 152 */         singleInsComponent.getStageHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(0));
/* 153 */         singleInsComponent.setStageHandbook(singleInsComponent.getStageHandbook());
/* 154 */       } else if (request.type == 1) {
/* 155 */         if (!singleInsComponent.getStageHandbook().containsKey(Integer.valueOf(request.handbookId))) {
/* 156 */           return 16008;
/*     */         }
/* 158 */         int star = ((Integer)singleInsComponent.getStageHandbook().get(Integer.valueOf(request.handbookId))).intValue() + 1;
/* 159 */         int num = 0;
/* 160 */         for (Iterator<Integer> iterator = stageHandbookBean.getStage().iterator(); iterator.hasNext(); ) { int stage = ((Integer)iterator.next()).intValue();
/* 161 */           if (stageComponent.getEntity(stage).getStar() >= star) {
/* 162 */             num++;
/*     */           } }
/*     */         
/* 165 */         if (num != stageHandbookBean.getStage().size()) {
/* 166 */           return 16007;
/*     */         }
/* 168 */         singleInsComponent.getStageHandbook().put(Integer.valueOf(request.handbookId), Integer.valueOf(star));
/* 169 */         singleInsComponent.setStageHandbook(singleInsComponent.getStageHandbook());
/*     */       } 
/* 171 */       ((ActWarPetHandBookResponse)this.response).handbookId = request.handbookId;
/* 172 */       ((ActWarPetHandBookResponse)this.response).star = ((Integer)singleInsComponent.getStageHandbook().get(Integer.valueOf(request.handbookId))).intValue();
/* 173 */       singleInsComponent.getStageHandbook().keySet().forEach(id -> {
/*     */             KeyValue keyValue = new KeyValue();
/*     */             keyValue.key = id.intValue();
/*     */             keyValue.value = ((Integer)singleInsComponent.getStageHandbook().get(id)).intValue();
/*     */             ((ActWarPetHandBookResponse)this.response).infos.add(keyValue);
/*     */           });
/* 179 */       AttrUpdateUtil.refreshStage(playerSession);
/* 180 */     } else if (request.handType == 3) {
/*     */     
/*     */     } 
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
/* 229 */     ((ActWarPetHandBookResponse)this.response).handType = request.handType;
/* 230 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\ActWarPetHandBookProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */