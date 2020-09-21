/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardInfoResponse;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonthCardInfoProcessor
/*    */   extends ProcessorBase<MonthCardInfoRequest, MonthCardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new MonthCardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MonthCardInfoRequest request) {
/* 24 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 25 */     Map<Integer, Integer> monthEndTime = welfareComponent.getMonthEndTime();
/* 26 */     Map<Integer, Integer> monthGetTime = welfareComponent.getMonthGetTime();
/*    */     
/* 28 */     int normalEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 29 */     int specialEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/* 30 */     int weekEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*    */     
/* 32 */     int normalGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 33 */     int specialGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/* 34 */     int weekGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*    */     
/* 36 */     ((MonthCardInfoResponse)this.response).list.add(MonthCardUtil.getNormalKeyValue(normalEndTime, normalGetTime));
/* 37 */     ((MonthCardInfoResponse)this.response).list.add(MonthCardUtil.getSpecialKeyValue(specialEndTime, specialGetTime));
/* 38 */     ((MonthCardInfoResponse)this.response).list.add(MonthCardUtil.getWeekKeyValue(weekEndTime, weekGetTime));
/* 39 */     ((MonthCardInfoResponse)this.response).day = MonthCardUtil.getNormalCardDay(normalEndTime);
/* 40 */     ((MonthCardInfoResponse)this.response).weekDay = MonthCardUtil.getNormalCardDay(weekEndTime);
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\MonthCardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */