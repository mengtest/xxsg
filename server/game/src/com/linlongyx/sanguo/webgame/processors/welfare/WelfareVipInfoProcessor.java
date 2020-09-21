/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipGiftBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WelfareVipInfoProcessor
/*    */   extends ProcessorBase<WelfareVipInfoRequest, WelfareVipInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new WelfareVipInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WelfareVipInfoRequest request) {
/* 33 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 34 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 35 */     welfareComponent.checkVipWeekLevel();
/* 36 */     ((WelfareVipInfoResponse)this.response).list = new ArrayList(welfareComponent.getVipDailySet());
/* 37 */     Map<Integer, Integer> vipWeekGoods = welfareComponent.getVipWeekGoods();
/* 38 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/* 39 */     int level = welfareParameter.getVipWeekLevel(playerComponent.getLevel());
/* 40 */     List<Integer> list = welfareParameter.getVipWeekList(level);
/* 41 */     if (null != list)
/*    */     {
/* 43 */       for (Integer id : list) {
/* 44 */         IntIntValue intIntValue = new IntIntValue();
/* 45 */         intIntValue.key = id.intValue();
/* 46 */         intIntValue.value = ((Integer)vipWeekGoods.getOrDefault(id, Integer.valueOf(0))).intValue();
/* 47 */         ((WelfareVipInfoResponse)this.response).goods.add(intIntValue);
/*    */       } 
/*    */     }
/* 50 */     Map<Integer, Integer> vipGiftGoods = welfareComponent.getVipGiftGoods();
/* 51 */     Map<Integer, Object> map = JsonTableService.getJsonTable(VipGiftBean.class);
/*    */     
/* 53 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 54 */       VipGiftBean vipGiftBean = (VipGiftBean)entry.getValue();
/* 55 */       Integer id = Integer.valueOf(vipGiftBean.getPackageType());
/* 56 */       IntIntValue intIntValue = new IntIntValue();
/* 57 */       intIntValue.key = id.intValue();
/* 58 */       intIntValue.value = ((Integer)vipGiftGoods.getOrDefault(id, Integer.valueOf(0))).intValue();
/* 59 */       ((WelfareVipInfoResponse)this.response).vipGoods.add(intIntValue);
/*    */     } 
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\WelfareVipInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */