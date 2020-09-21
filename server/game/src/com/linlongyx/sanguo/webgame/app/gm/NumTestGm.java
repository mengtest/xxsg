/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitActRewardProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.rebate.ChargeRebateTurnProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.recruit.BuyRedRecruitItemProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.shop.BuyGoodsProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateTurnRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.BuyRedRecruitItemRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.shop.BuyGoodsRequest;
/*    */ 
/*    */ 
/*    */ public class NumTestGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 19 */     if (strings[2].equals("shop")) {
/* 20 */       BuyGoodsRequest request = new BuyGoodsRequest();
/* 21 */       request.type = Integer.parseInt(strings[3]);
/* 22 */       request.goodsId = Integer.parseInt(strings[4]);
/* 23 */       request.num = Integer.parseInt(strings[5]);
/* 24 */       (new BuyGoodsProcessor()).handle(playerSession, (RequestBase)request);
/* 25 */     } else if (strings[2].equals("recruit")) {
/* 26 */       BuyRedRecruitItemRequest request = new BuyRedRecruitItemRequest();
/* 27 */       request.itemId = Integer.parseInt(strings[3]);
/* 28 */       request.itemNum = Integer.parseInt(strings[4]);
/* 29 */       (new BuyRedRecruitItemProcessor()).handle(playerSession, (RequestBase)request);
/* 30 */     } else if (strings[2].equals("limit")) {
/* 31 */       LimitActRewardRequest request = new LimitActRewardRequest();
/* 32 */       request.actId = Integer.parseInt(strings[3]);
/* 33 */       request.itemId = Integer.parseInt(strings[4]);
/* 34 */       request.num = Integer.parseInt(strings[5]);
/* 35 */       (new LimitActRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 36 */     } else if (strings[2].equals("rebate")) {
/* 37 */       ChargeRebateTurnRequest request = new ChargeRebateTurnRequest();
/* 38 */       request.actId = Integer.parseInt(strings[3]);
/* 39 */       request.times = Integer.parseInt(strings[4]);
/* 40 */       (new ChargeRebateTurnProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\NumTestGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */