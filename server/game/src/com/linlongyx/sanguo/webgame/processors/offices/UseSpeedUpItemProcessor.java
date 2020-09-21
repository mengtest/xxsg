/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.MilitaryParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.UseSpeedUpItemRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.UseSpeedUpItemResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class UseSpeedUpItemProcessor
/*    */   extends ProcessorBase<UseSpeedUpItemRequest, UseSpeedUpItemResponse> {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new UseSpeedUpItemResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, UseSpeedUpItemRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80)) {
/* 32 */       return 10061;
/*    */     }
/* 34 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 35 */     if (militaryOfficeComponent.getCurrJobId() == 0) {
/* 36 */       return 22802;
/*    */     }
/* 38 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 39 */     int freeTime = VipUtil.getNum(playerComponent.getVip(), 19);
/* 40 */     if (request.type == 0) {
/* 41 */       if (request.itemNum <= 0) {
/* 42 */         return 10090;
/*    */       }
/* 44 */       if (TimeUtil.currentTime() > militaryOfficeComponent.getEndTime() - freeTime) {
/* 45 */         return 22803;
/*    */       }
/* 47 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(request.itemId, ItemBean.class);
/* 48 */       if (null == itemBean) {
/* 49 */         return 10701;
/*    */       }
/* 51 */       MilitaryParameter militaryParameter = (MilitaryParameter)ParameterConstant.getParameter(51);
/* 52 */       if (!militaryParameter.getItemMap().containsKey(Integer.valueOf(request.itemId))) {
/* 53 */         return 10701;
/*    */       }
/*    */       
/* 56 */       if (request.itemId == CurrencyType.CCY.getType()) {
/* 57 */         int cost = request.itemNum * militaryParameter.getCostCCY();
/* 58 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 59 */           return 10052;
/*    */         }
/* 61 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.MilitarySpeed);
/*    */       } else {
/* 63 */         BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 64 */         if (!bagComponent.check(request.itemId, request.itemNum)) {
/* 65 */           return 10050;
/*    */         }
/* 67 */         bagComponent.deleteItem(request.itemId, request.itemNum, ResourceEvent.MilitarySpeed, true);
/*    */       } 
/*    */       
/* 70 */       int refuseTime = ((Integer)militaryParameter.getItemMap().get(Integer.valueOf(request.itemId))).intValue();
/* 71 */       militaryOfficeComponent.setEndTime(militaryOfficeComponent.getEndTime() - refuseTime * request.itemNum);
/* 72 */     } else if (request.type == 1) {
/* 73 */       if (TimeUtil.currentTime() < militaryOfficeComponent.getEndTime() - freeTime) {
/* 74 */         return 22801;
/*    */       }
/* 76 */       militaryOfficeComponent.setEndTime(TimeUtil.currentTime() - 1);
/*    */     } 
/* 78 */     ((UseSpeedUpItemResponse)this.response).endTime = militaryOfficeComponent.getEndTime();
/* 79 */     ((UseSpeedUpItemResponse)this.response).itemId = request.itemId;
/* 80 */     ((UseSpeedUpItemResponse)this.response).itemNum = request.itemNum;
/* 81 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\UseSpeedUpItemProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */