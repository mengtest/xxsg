/*    */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.PetTurntableInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.PetTurntableInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PetTurntableInfoProcessor
/*    */   extends ProcessorBase<PetTurntableInfoRequest, PetTurntableInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new PetTurntableInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PetTurntableInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 43)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(request.actId, LuckylistBean.class);
/* 34 */     if (luckylistBean == null) {
/* 35 */       return 13502;
/*    */     }
/* 37 */     if (!LimitUtil.isActOpen(luckylistBean.getServerTypeb(), luckylistBean.getBeginTimeb(), luckylistBean.getEndTimeb())) {
/* 38 */       return 13501;
/*    */     }
/* 40 */     LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 41 */     LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(request.actId);
/* 42 */     if (luckyTurntableEntity == null) {
/* 43 */       luckyTurntableEntity = new LuckyTurntableEntity(playerSession.getPlayer().getPlayerId(), request.actId);
/* 44 */       luckyTurntableEntity.setFreeTimes(luckylistBean.getFreeTimes());
/* 45 */       luckyTurntableComponent.addEntity((IEntity)luckyTurntableEntity);
/*    */     } 
/* 47 */     ((PetTurntableInfoResponse)this.response).point = luckyTurntableEntity.getLuckyPoint();
/* 48 */     ((PetTurntableInfoResponse)this.response)
/* 49 */       .time = (luckylistBean.getServerTypeb() == 0) ? TimeUtil.getTimeFromDate(luckylistBean.getEndTimeb()) : TimeUtil.getTimeDiffToNow(Integer.parseInt(luckylistBean.getEndTimeb()) - TimeUtil.getDayDiffToOpen(MContext.getOpenTime()) + 1);
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\PetTurntableInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */