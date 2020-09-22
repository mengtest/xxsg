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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LuckyTurntableRecord;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckyTurntableInfoProcessor
/*    */   extends ProcessorBase<LuckyTurntableInfoRequest, LuckyTurntableInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new LuckyTurntableInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyTurntableInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 43)) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(request.actId, LuckylistBean.class);
/* 36 */     if (luckylistBean == null) {
/* 37 */       return 13502;
/*    */     }
/* 39 */     if (!LimitUtil.isActOpen(luckylistBean.getServerTypeb(), luckylistBean.getBeginTimeb(), luckylistBean.getEndTimeb())) {
/* 40 */       return 13501;
/*    */     }
/* 42 */     LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 43 */     LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(request.actId);
/* 44 */     if (luckyTurntableEntity == null) {
/* 45 */       luckyTurntableEntity = new LuckyTurntableEntity(playerSession.getPlayer().getPlayerId(), request.actId);
/* 46 */       luckyTurntableEntity.setFreeTimes(luckylistBean.getFreeTimes());
/* 47 */       luckyTurntableComponent.addEntity((IEntity)luckyTurntableEntity);
/*    */     } 
/* 49 */     ((LuckyTurntableInfoResponse)this.response).isFirst = luckyTurntableEntity.getIsFirstDraw();
/* 50 */     ((LuckyTurntableInfoResponse)this.response).luckyPoint = luckyTurntableEntity.getLuckyPoint();
/* 51 */     ((LuckyTurntableInfoResponse)this.response).recordList = new ArrayList<>(LuckyTurntableUtil.getRecordList());
/* 52 */     ((LuckyTurntableInfoResponse)this.response).freeTimes = luckyTurntableEntity.getFreeTimes();
/* 53 */     ((LuckyTurntableInfoResponse)this.response)
/* 54 */       .time = (luckylistBean.getServerTypeb() == 0) ? TimeUtil.getTimeFromDate(luckylistBean.getEndTimeb()) : TimeUtil.getTimeDiffToNow(Integer.parseInt(luckylistBean.getEndTimeb()) - TimeUtil.getDayDiffToOpen(MContext.getOpenTime()) + 1);
/*    */     
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */