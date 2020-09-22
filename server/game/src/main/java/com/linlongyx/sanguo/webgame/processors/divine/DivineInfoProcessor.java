/*    */ package com.linlongyx.sanguo.webgame.processors.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DivineBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineInfoProcessor
/*    */   extends ProcessorBase<DivineInfoRequest, DivineInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new DivineInfoResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DivineInfoRequest request) {
/* 31 */     int curActId = DivineUtil.getCurDivineActId();
/* 32 */     if (curActId == -1) {
/* 33 */       ((DivineInfoResponse)this.response).actId = -1;
/* 34 */       return 0;
/*    */     } 
/* 36 */     DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/*    */     
/* 38 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 39 */     if (divineEntity == null) {
/* 40 */       divineEntity = new DivineEntity(playerSession.getPlayer().getPlayerId(), curActId);
/* 41 */       divineComponent.addEntity((IEntity)divineEntity);
/*    */     } 
/* 43 */     DivineBean divineBean = (DivineBean)JsonTableService.getJsonData(curActId, DivineBean.class);
/* 44 */     ((DivineInfoResponse)this.response).actId = curActId;
/* 45 */     ((DivineInfoResponse)this.response).divineNum = DivineUtil.fmtDivineNum(((IntIntValue)ConstantService.divineMap.get(Integer.valueOf(curActId))).key);
/* 46 */     ((DivineInfoResponse)this.response).divineTimes = divineEntity.getTimes();
/* 47 */     ((DivineInfoResponse)this.response)
/* 48 */       .time = (divineBean.getServerType() == 0) ? TimeUtil.getTimeFromDate(divineBean.getEndTime()) : TimeUtil.getTimeDiffToNow(Integer.parseInt(divineBean.getEndTime()) - TimeUtil.getDayDiffToOpen(MContext.getOpenTime()) + 1);
/*    */     
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */