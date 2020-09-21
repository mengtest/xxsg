/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.BuyGoodsProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.MysteryShopListProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.ShopUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.shop.BuyGoodsRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryShopListRequest;
/*    */ 
/*    */ 
/*    */ public class ShopGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 19 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 20 */     if (playerComponent == null)
/* 21 */       return;  if (strings[2].equals("getMysteryShop")) {
/* 22 */       MysteryShopListRequest request = new MysteryShopListRequest();
/* 23 */       (new MysteryShopListProcessor()).handle(playerSession, (RequestBase)request);
/* 24 */     }  if (strings[2].equals("resetRefreshNum")) {
/* 25 */       ShopComponent shopComponent = (ShopComponent)playerSession.getPlayer().createIfNotExist(ShopComponent.class);
/* 26 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/* 27 */       shopEntity.setRefreshNum(0);
/* 28 */     } else if (strings[2].equals("buy")) {
/* 29 */       BuyGoodsRequest request = new BuyGoodsRequest();
/* 30 */       request.type = Integer.valueOf(strings[3]).intValue();
/* 31 */       request.goodsId = Integer.valueOf(strings[4]).intValue();
/* 32 */       request.num = Integer.valueOf(strings[5]).intValue();
/* 33 */       (new BuyGoodsProcessor()).handle(playerSession, (RequestBase)request);
/* 34 */     } else if (strings[2].equals("level")) {
/* 35 */       ShopUtil.setShopLevel(Integer.valueOf(strings[3]).intValue());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\ShopGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */