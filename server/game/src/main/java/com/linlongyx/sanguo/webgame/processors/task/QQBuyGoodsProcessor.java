/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChannelShopBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.ShopUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.QQBuyGoodsRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.QQBuyGoodsResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import com.linlongyx.sanguo.webgame.util.WanbaUtil;
/*    */ 
/*    */ 
/*    */ public class QQBuyGoodsProcessor
/*    */   extends ProcessorBase<QQBuyGoodsRequest, QQBuyGoodsResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new QQBuyGoodsResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, QQBuyGoodsRequest request) {
/* 33 */     if (request.num <= 0) {
/* 34 */       return 13110;
/*    */     }
/* 36 */     if (PlayerUtil.getActPlatform() != 4) {
/* 37 */       return 15410;
/*    */     }
/* 39 */     ChannelShopBean channelShopBean = (ChannelShopBean)JsonTableService.getJsonData(request.goodsId, ChannelShopBean.class);
/* 40 */     if (null == channelShopBean) {
/* 41 */       return 13104;
/*    */     }
/*    */     
/* 44 */     int platformVip = WanbaUtil.getVIPLevel(request.openId, request.openKey, request.pf);
/* 45 */     if (platformVip < channelShopBean.getVip()) {
/* 46 */       return 13107;
/*    */     }
/*    */     
/* 49 */     IPlayer player = playerSession.getPlayer();
/* 50 */     if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(channelShopBean.getCostType()), (channelShopBean.getCostNum() * request.num))) {
/* 51 */       return CostUtil.getErrorCode(CurrencyType.getCurrencyType(channelShopBean.getCostType()));
/*    */     }
/*    */     
/* 54 */     int SHOP_TYPE = ShopUtil.ShopType.QQVIPShop.getType();
/* 55 */     ShopComponent shopComponent = (ShopComponent)player.getComponent(ShopComponent.class);
/* 56 */     ShopEntity shopEntity = shopComponent.getShopEntity(SHOP_TYPE);
/* 57 */     if (null == shopEntity) {
/* 58 */       return 13101;
/*    */     }
/*    */     
/* 61 */     if (channelShopBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > channelShopBean.getSellTimes()) {
/* 62 */       return 13108;
/*    */     }
/*    */     
/* 65 */     int num = channelShopBean.getCostNum() * request.num;
/* 66 */     FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(channelShopBean.getCostType()), num, ResourceEvent.QQVIPShop);
/*    */     
/* 68 */     shopEntity.addSellTimes(request.goodsId, request.num);
/* 69 */     shopComponent.updateSellsToDB(shopEntity);
/*    */     
/* 71 */     ShopUtil.grantReward(channelShopBean.getReward(), playerSession, request.num, ResourceEvent.QQVIPShop, SHOP_TYPE, channelShopBean.getCostType(), num);
/* 72 */     LogUtils.errorLog(new Object[] { "qqbuygoods", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.type), Integer.valueOf(request.goodsId), Integer.valueOf(request.num) });
/* 73 */     ((QQBuyGoodsResponse)this.response).type = request.type;
/* 74 */     ((QQBuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 75 */     ((QQBuyGoodsResponse)this.response).num = request.num;
/* 76 */     ((QQBuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\QQBuyGoodsProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */