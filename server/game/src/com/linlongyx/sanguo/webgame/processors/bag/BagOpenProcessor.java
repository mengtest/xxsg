/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BagParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagOpenResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BagItemInfo;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagOpenProcessor
/*    */   extends ProcessorBase<BagOpenRequest, BagOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new BagOpenResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagOpenRequest request) {
/* 33 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 34 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)bagComponent.getEntityMap().entrySet()) {
/* 35 */       BagEntity bagEntity = (BagEntity)entry.getValue();
/* 36 */       BagItemInfo bagItemInfo = new BagItemInfo();
/* 37 */       bagItemInfo.itemId = bagEntity.getItemId();
/* 38 */       bagItemInfo.num = bagEntity.getNum();
/* 39 */       ((BagOpenResponse)this.response).bagItemInfos.add(bagItemInfo);
/*    */     } 
/* 41 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 42 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 43 */     BagParameter bagParameter = (BagParameter)ParameterConstant.getParameter(7);
/* 44 */     if (extendComponent.getBagSize() < bagParameter.getDefaultSize()) {
/* 45 */       extendComponent.setBagSize(extendComponent.getBagSize() + bagParameter.getDefaultSize());
/*    */     }
/* 47 */     int maxCount = VipUtil.getNum(playerComponent.getVip(), 12) + extendComponent.getBagSize();
/* 48 */     ((BagOpenResponse)this.response).bagSize = maxCount;
/* 49 */     ((BagOpenResponse)this.response).buyGoldTimes = extendComponent.getBuyGoldTimes();
/* 50 */     ((BagOpenResponse)this.response).buyGoldCost = extendComponent.getBuyGoldCost();
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */