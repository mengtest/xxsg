/*    */ package com.linlongyx.sanguo.webgame.processors.title;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleActivateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleActivateResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TitleActivateProcessor
/*    */   extends ProcessorBase<TitleActivateRequest, TitleActivateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new TitleActivateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TitleActivateRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 38)) {
/* 34 */       return 10061;
/*    */     }
/* 36 */     int title = request.title;
/* 37 */     TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(title, TitleBean.class);
/* 38 */     if (null == titleBean) {
/* 39 */       return 13801;
/*    */     }
/*    */     
/* 42 */     TitleUtil.checkTitleValid(playerSession);
/*    */     
/* 44 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 45 */     if (playerComponent.getActiveTitles().containsKey(Integer.valueOf(title))) {
/* 46 */       return 13804;
/*    */     }
/*    */ 
/*    */     
/* 50 */     ArrayList<RewardBean> cost = titleBean.getItem();
/* 51 */     if (cost != null && cost.size() > 0) {
/* 52 */       short errorCode = CostUtil.handleCost(cost, playerSession, ResourceEvent.TitleActivate);
/* 53 */       if (0 != errorCode) {
/* 54 */         return errorCode;
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     int time = 0;
/* 67 */     if (titleBean.getEndTime() > 0) {
/* 68 */       time = TimeUtil.currentTime() + titleBean.getEndTime();
/*    */     }
/* 70 */     playerComponent.getActiveTitles().put(Integer.valueOf(titleBean.getTitleId()), Integer.valueOf(time));
/* 71 */     playerComponent.getActiveTitles().forEach((k, v) -> {
/*    */           KeyValue keyValue = new KeyValue();
/*    */           
/*    */           keyValue.key = k.intValue();
/*    */           keyValue.value = v.intValue();
/*    */           ((TitleActivateResponse)this.response).time.add(keyValue);
/*    */           ((TitleActivateResponse)this.response).list.add(k);
/*    */         });
/* 79 */     LogUtil.errorLog(new Object[] { "TitleActivate", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(title) });
/*    */ 
/*    */     
/* 82 */     AttrUpdateUtil.refreshTitle(playerSession);
/*    */     
/* 84 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 85 */     if (taskComponent != null) {
/* 86 */       taskComponent.refreshSchedule(TaskType.ActiveTitle, 0, 0L);
/*    */     }
/* 88 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\title\TitleActivateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */