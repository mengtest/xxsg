/*    */ package com.linlongyx.sanguo.webgame.processors.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TurnplateInfoProcessor
/*    */   extends ProcessorBase<TurnplateInfoRequest, TurnplateInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new TurnplateInfoResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TurnplateInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 24)) {
/* 31 */       return 10061;
/*    */     }
/*    */     
/* 34 */     TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 35 */     if (turnplateComponent.getCurActId() == 0 || !turnplateComponent.isActOpen(turnplateComponent.getCurActId())) {
/* 36 */       turnplateComponent.setCurActId(0);
/* 37 */       return 12402;
/*    */     } 
/*    */     
/* 40 */     TurnplateUtil.addPlayer(playerSession.getPlayer().getPlayerId());
/* 41 */     TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/* 42 */     TurnplateBean turnplateBean = (TurnplateBean)JsonTableService.getJsonData(turnplateComponent.getCurActId(), TurnplateBean.class);
/*    */     
/* 44 */     ((TurnplateInfoResponse)this.response).firstDraw = (byte)((turnplateEntity.getDrawCount() > 0) ? 1 : 0);
/* 45 */     ((TurnplateInfoResponse)this.response).point = turnplateEntity.getPoint();
/* 46 */     ((TurnplateInfoResponse)this.response).yuanbao = TurnplateUtil.getGoldPool();
/* 47 */     ((TurnplateInfoResponse)this.response)
/* 48 */       .endtime = (turnplateBean.getServerType() == 0) ? TimeUtil.getTimeFromDate(turnplateBean.getEndTime()) : TimeUtil.getTimeDiffToNow(Integer.parseInt(turnplateBean.getEndTime()) - TimeUtil.getDayDiffToOpen(MContext.getOpenTime()) + 1);
/* 49 */     ((TurnplateInfoResponse)this.response).recordList = TurnplateUtil.getRecordList();
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\turnplate\TurnplateInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */