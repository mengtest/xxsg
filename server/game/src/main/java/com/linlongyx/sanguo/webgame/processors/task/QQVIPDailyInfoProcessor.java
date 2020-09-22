/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChannelGiftBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChannelShopBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.ShopUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.QQVIPDailyInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.QQVIPDailyInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.WanbaUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QQVIPDailyInfoProcessor
/*    */   extends ProcessorBase<QQVIPDailyInfoRequest, QQVIPDailyInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new QQVIPDailyInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, QQVIPDailyInfoRequest request) {
/* 35 */     if (PlayerUtil.getActPlatform() != 4) {
/* 36 */       return 15410;
/*    */     }
/* 38 */     int SHOP_TYPE = ShopUtil.ShopType.QQVIPShop.getType();
/* 39 */     ShopComponent shopComponent = (ShopComponent)playerSession.getPlayer().getComponent(ShopComponent.class);
/* 40 */     ShopEntity shopEntity = shopComponent.getShopEntity(SHOP_TYPE);
/* 41 */     if (null == shopEntity) {
/* 42 */       shopComponent.addShop(playerSession.getPlayer().getPlayerId(), SHOP_TYPE);
/* 43 */       shopEntity = shopComponent.getShopEntity(SHOP_TYPE);
/* 44 */       shopComponent.saveToDB();
/*    */     } 
/* 46 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ChannelShopBean.class);
/* 47 */     List<Integer> list = new ArrayList<>(map.keySet());
/* 48 */     int TABLE_TYPE = ShopUtil.ShopType.QQVIPShop.getType();
/*    */     
/* 50 */     for (Integer id : list) {
/* 51 */       int count = shopEntity.getSellTimes(id.intValue());
/* 52 */       ((QQVIPDailyInfoResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), count, TABLE_TYPE, shopEntity.getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActId(), 0));
/*    */     } 
/*    */     
/* 55 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 56 */     Map<Integer, Integer> channelGift = taskComponent.getChannelGift();
/* 57 */     map = JsonTableService.getJsonTable(ChannelGiftBean.class);
/* 58 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 59 */       ChannelGiftBean channelGiftBean = (ChannelGiftBean)entry.getValue();
/* 60 */       if (channelGiftBean.getType() == 2) {
/* 61 */         int time = ((Integer)channelGift.getOrDefault(Integer.valueOf(channelGiftBean.getId()), Integer.valueOf(0))).intValue();
/* 62 */         if (time > 0 && TimeUtil.inSameDay(time)) {
/* 63 */           ((QQVIPDailyInfoResponse)this.response).list.add(Integer.valueOf(channelGiftBean.getId()));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     ((QQVIPDailyInfoResponse)this.response).vip = WanbaUtil.getVIPLevel(request.openId, request.openKey, request.pf);
/* 69 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\QQVIPDailyInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */