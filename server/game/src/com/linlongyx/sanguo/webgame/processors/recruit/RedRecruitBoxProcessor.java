/*     */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedlistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedproiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitBoxRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitBoxResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedRecruitBoxProcessor
/*     */   extends ProcessorBase<RedRecruitBoxRequest, RedRecruitBoxResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new RedRecruitBoxResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, RedRecruitBoxRequest request) {
/*  32 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/*  33 */     int type = request.type;
/*  34 */     if (type == 1) {
/*  35 */       int cub = ((Integer)recruitComponent.getToday().get(0)).intValue();
/*  36 */       RecruitRedproiBean recruitRedproiBean = (RecruitRedproiBean)JsonTableService.getJsonData(cub, RecruitRedproiBean.class);
/*  37 */       if (null == recruitRedproiBean) {
/*  38 */         return 10006;
/*     */       }
/*  40 */       RecruitRedproiBean.IdBean idBean = (RecruitRedproiBean.IdBean)recruitRedproiBean.getId().get(Integer.valueOf(request.boxId));
/*  41 */       if (null == idBean) {
/*  42 */         return 14024;
/*     */       }
/*     */       
/*  45 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/*  46 */       int actId = 2;
/*  47 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, type);
/*  48 */       if (!arrayList.isEmpty()) {
/*  49 */         actId = ((Integer)arrayList.get(0)).intValue();
/*     */       }
/*  51 */       RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/*  52 */       if (recruitRedlistBean == null) {
/*  53 */         LogUtil.errorLog(new Object[] { "RedRecruitBoxProcessor::raffleLottery get box error", Integer.valueOf(actId), Integer.valueOf(type) });
/*  54 */         return 14023;
/*     */       } 
/*     */       
/*  57 */       if (recruitComponent.getBoxList().indexOf(Integer.valueOf(request.boxId)) >= 0) {
/*  58 */         return 14022;
/*     */       }
/*  60 */       if (recruitComponent.getScore() < idBean.getPoint()) {
/*  61 */         return 14021;
/*     */       }
/*  63 */       FinanceUtil.reward(FinanceUtil.transform(idBean.getReward()), playerSession.getPlayer(), ResourceEvent.RecruitRed, true);
/*  64 */       recruitComponent.getBoxList().add(Integer.valueOf(request.boxId));
/*  65 */       recruitComponent.setBoxList(recruitComponent.getBoxList());
/*  66 */       if (recruitComponent.getBoxList().size() >= recruitParamter.getResetNum()) {
/*  67 */         recruitComponent.setBoxList(new ArrayList());
/*  68 */         int maxScore = 0;
/*  69 */         for (Iterator<Integer> iterator = recruitRedproiBean.getId().keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  70 */           int point = ((RecruitRedproiBean.IdBean)recruitRedproiBean.getId().get(Integer.valueOf(id))).getPoint();
/*  71 */           if (point > maxScore) {
/*  72 */             maxScore = point;
/*     */           } }
/*     */         
/*  75 */         recruitComponent.setScore(recruitComponent.getScore() - maxScore);
/*     */       } 
/*  77 */       ((RedRecruitBoxResponse)this.response).score = recruitComponent.getScore();
/*  78 */       ((RedRecruitBoxResponse)this.response).boxList.addAll(recruitComponent.getBoxList());
/*  79 */     } else if (type == 2) {
/*  80 */       int cub = ((Integer)recruitComponent.getGoldToday().get(0)).intValue();
/*  81 */       RecruitRedproiBean recruitRedproiBean = (RecruitRedproiBean)JsonTableService.getJsonData(cub, RecruitRedproiBean.class);
/*  82 */       if (null == recruitRedproiBean) {
/*  83 */         return 10006;
/*     */       }
/*  85 */       RecruitRedproiBean.IdBean idBean = (RecruitRedproiBean.IdBean)recruitRedproiBean.getId().get(Integer.valueOf(request.boxId));
/*  86 */       if (null == idBean) {
/*  87 */         return 14024;
/*     */       }
/*     */       
/*  90 */       RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/*  91 */       int actId = 5;
/*  92 */       ArrayList<Integer> arrayList = recruitParamter.getActId(true, true, type);
/*  93 */       if (!arrayList.isEmpty()) {
/*  94 */         actId = ((Integer)arrayList.get(0)).intValue();
/*     */       }
/*  96 */       RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/*  97 */       if (recruitRedlistBean == null) {
/*  98 */         LogUtil.errorLog(new Object[] { "RedRecruitBoxProcessor::raffleLottery get box error", Integer.valueOf(actId), Integer.valueOf(type) });
/*  99 */         return 14023;
/*     */       } 
/*     */       
/* 102 */       if (recruitComponent.getGoldBoxList().indexOf(Integer.valueOf(request.boxId)) >= 0) {
/* 103 */         return 14022;
/*     */       }
/* 105 */       if (recruitComponent.getGoldScore() < idBean.getPoint()) {
/* 106 */         return 14021;
/*     */       }
/* 108 */       FinanceUtil.reward(FinanceUtil.transform(idBean.getReward()), playerSession.getPlayer(), ResourceEvent.RecruitGoldBox, true);
/* 109 */       recruitComponent.getGoldBoxList().add(Integer.valueOf(request.boxId));
/* 110 */       recruitComponent.setGoldBoxList(recruitComponent.getGoldBoxList());
/* 111 */       if (recruitComponent.getGoldBoxList().size() >= recruitParamter.getResetNum()) {
/* 112 */         recruitComponent.setGoldBoxList(new ArrayList());
/* 113 */         int maxScore = 0;
/* 114 */         for (Iterator<Integer> iterator = recruitRedproiBean.getId().keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 115 */           int point = ((RecruitRedproiBean.IdBean)recruitRedproiBean.getId().get(Integer.valueOf(id))).getPoint();
/* 116 */           if (point > maxScore) {
/* 117 */             maxScore = point;
/*     */           } }
/*     */         
/* 120 */         recruitComponent.setGoldScore(recruitComponent.getGoldScore() - maxScore);
/*     */       } 
/* 122 */       ((RedRecruitBoxResponse)this.response).score = recruitComponent.getGoldScore();
/* 123 */       ((RedRecruitBoxResponse)this.response).boxList.addAll(recruitComponent.getGoldBoxList());
/*     */     } 
/* 125 */     ((RedRecruitBoxResponse)this.response).type = type;
/* 126 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RedRecruitBoxProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */