/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.UnparalleledParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.ResetUnparalleledRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.ResetUnparalleledResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class ResetUnparalleledProcessor
/*    */   extends ProcessorBase<ResetUnparalleledRequest, ResetUnparalleledResponse> {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new ResetUnparalleledResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ResetUnparalleledRequest request) {
/* 32 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 33 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 34 */     UnparalleledParameter unparalleledParameter = (UnparalleledParameter)ParameterConstant.getParameter(18);
/* 35 */     int maxCount = VipUtil.getNum(playerComponent.getVip(), 9);
/* 36 */     if (unparalleledComponent.getResetTimes() >= maxCount) {
/* 37 */       return 14014;
/*    */     }
/* 39 */     int num = unparalleledParameter.getCost(unparalleledComponent.getResetTimes());
/* 40 */     boolean enough = FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, num);
/* 41 */     if (!enough) {
/* 42 */       return 10052;
/*    */     }
/* 44 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, num, ResourceEvent.UnparReset);
/* 45 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 46 */     if (unparalleledComponent.getResetTimes() == 0) {
/* 47 */       singleInsComponent.saveFindTimes(true, FindRewardType.UNPARM.getType(), 1);
/*    */     }
/* 49 */     unparalleledComponent.setResetTimes(unparalleledComponent.getResetTimes() + 1);
/* 50 */     unparalleledComponent.setTotalResetTimes(unparalleledComponent.getTotalResetTimes() + 1);
/* 51 */     unparalleledComponent.setBuffs(new ArrayList());
/* 52 */     unparalleledComponent.setAttrbuteList(new ArrayList());
/* 53 */     unparalleledComponent.setCurPoint(0);
/* 54 */     unparalleledComponent.setSurpStar(0);
/* 55 */     unparalleledComponent.setPartneredHpMap(new HashMap<>());
/* 56 */     unparalleledComponent.setPartneredHpMap(new HashMap<>());
/* 57 */     unparalleledComponent.setSweep(false);
/* 58 */     unparalleledComponent.setLayerAddition(new ArrayList());
/* 59 */     unparalleledComponent.setPlayedPoints(new HashMap<>());
/* 60 */     unparalleledComponent.setLayerBox(new ArrayList());
/* 61 */     unparalleledComponent.setCurrMaxStar(0);
/* 62 */     unparalleledComponent.setBattlePartner(new ArrayList(playerComponent.getFighters()));
/* 63 */     unparalleledComponent.setAttrsList(new HashMap<>());
/* 64 */     unparalleledComponent.setLevelMap(new HashMap<>());
/* 65 */     ((ResetUnparalleledResponse)this.response).curPoint = unparalleledComponent.getCurPoint();
/* 66 */     ((ResetUnparalleledResponse)this.response).surpStar = unparalleledComponent.getSurpStar();
/* 67 */     ((ResetUnparalleledResponse)this.response).resetTimes = unparalleledComponent.getResetTimes();
/* 68 */     ((ResetUnparalleledResponse)this.response).isSweep = unparalleledComponent.isSweep() ? 1 : 0;
/* 69 */     ((ResetUnparalleledResponse)this.response).currMaxStar = unparalleledComponent.getCurrMaxStar();
/* 70 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 71 */     if (taskComponent != null) {
/* 72 */       taskComponent.refreshSchedule(TaskType.unparaReset, 0, 1L);
/* 73 */       taskComponent.refreshSchedule(TaskType.TotalResetUnpra, 0, 1L);
/*    */     } 
/*    */ 
/*    */     
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\ResetUnparalleledProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */