/*    */ package com.linlongyx.sanguo.webgame.processors.title;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleDelayRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleDelayResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleDelayProcessor
/*    */   extends ProcessorBase<TitleDelayRequest, TitleDelayResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TitleDelayResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TitleDelayRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 38)) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     int title = request.title;
/* 36 */     TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(title, TitleBean.class);
/* 37 */     if (null == titleBean) {
/* 38 */       return 13801;
/*    */     }
/* 40 */     if (titleBean.getEndTime() == 0) {
/* 41 */       return 13805;
/*    */     }
/* 43 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 44 */     Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/* 45 */     if (!activeTitles.containsKey(Integer.valueOf(title))) {
/* 46 */       return 13802;
/*    */     }
/* 48 */     short errorCode = CostUtil.handleCost(titleBean.getItem(), playerSession, ResourceEvent.TitleDelay);
/* 49 */     if (0 != errorCode) {
/* 50 */       return errorCode;
/*    */     }
/* 52 */     int time = ((Integer)activeTitles.get(Integer.valueOf(title))).intValue();
/* 53 */     if (0 == time) {
/* 54 */       time = TimeUtil.currentTime();
/*    */     }
/* 56 */     time += titleBean.getEndTime();
/* 57 */     activeTitles.put(Integer.valueOf(title), Integer.valueOf(time));
/* 58 */     playerComponent.setActiveTitles(activeTitles);
/*    */     
/* 60 */     ((TitleDelayResponse)this.response).data.key = title;
/* 61 */     ((TitleDelayResponse)this.response).data.value = time;
/*    */     
/* 63 */     LogUtil.errorLog(new Object[] { "TitleDelay", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(title), Integer.valueOf(time) });
/*    */     
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\title\TitleDelayProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */