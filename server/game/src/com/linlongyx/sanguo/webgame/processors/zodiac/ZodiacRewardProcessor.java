/*    */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZodiacRewardProcessor
/*    */   extends ProcessorBase<ZodiacRewardRequest, ZodiacRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new ZodiacRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ZodiacRewardRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 75))
/* 32 */       return 10061; 
/* 33 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 34 */     ZodiacComponent zodiacComponent = (ZodiacComponent)playerSession.getPlayer().createIfNotExist(ZodiacComponent.class);
/* 35 */     if (!zodiacParameter.isZodiacAct(request.actId)) {
/* 36 */       return 14301;
/*    */     }
/*    */     
/* 39 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*    */     
/* 41 */     ZodiacRewardBean ZodiacRewardBean = (ZodiacRewardBean)JsonTableService.getJsonData(request.type, ZodiacRewardBean.class);
/* 42 */     ArrayList<Integer> condition = ((ZodiacRewardBean.IdBean)ZodiacRewardBean.getId().get(Integer.valueOf(request.boxId))).getCondition();
/* 43 */     Map<Integer, Integer> zodiacGoods = zodiacComponent.getZodiacGoods(request.actId);
/* 44 */     for (Integer i : condition) {
/* 45 */       if (i.intValue() != 0 && (
/* 46 */         !zodiacGoods.containsKey(i) || ((Integer)zodiacGoods.getOrDefault(i, Integer.valueOf(0))).intValue() == 0)) {
/* 47 */         return 17504;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     if (!bagComponent.check(zodiacParameter.getItemId(), ((ZodiacRewardBean.IdBean)ZodiacRewardBean.getId().get(Integer.valueOf(request.boxId))).getNumber())) {
/* 52 */       return 10050;
/*    */     }
/* 54 */     bagComponent.deleteItem(zodiacParameter.getItemId(), ((ZodiacRewardBean.IdBean)ZodiacRewardBean.getId().get(Integer.valueOf(request.boxId))).getNumber(), ResourceEvent.ZodiacReward, true);
/* 55 */     if (request.rewardType == 1) {
/* 56 */       ArrayList<RewardBean> costRward = ((ZodiacRewardBean.IdBean)ZodiacRewardBean.getId().get(Integer.valueOf(request.boxId))).getReward1();
/* 57 */       FinanceUtil.reward(FinanceUtil.transform(costRward), playerSession.getPlayer(), ResourceEvent.ZodiacReward, true);
/*    */     }
/* 59 */     else if (request.rewardType == 2) {
/* 60 */       FinanceUtil.reward(FinanceUtil.transform(((ZodiacRewardBean.IdBean)ZodiacRewardBean.getId().get(Integer.valueOf(request.boxId))).getReward2()), playerSession.getPlayer(), ResourceEvent.ZodiacReward, true);
/*    */     } 
/* 62 */     int num = ((Integer)zodiacGoods.getOrDefault(Integer.valueOf(request.boxId), Integer.valueOf(0))).intValue();
/* 63 */     num++;
/* 64 */     zodiacGoods.put(Integer.valueOf(request.boxId), Integer.valueOf(num));
/* 65 */     zodiacComponent.setZodiacGoods(request.actId, zodiacGoods);
/*    */     
/* 67 */     ((ZodiacRewardResponse)this.response).type = request.type;
/* 68 */     ((ZodiacRewardResponse)this.response).actId = request.actId;
/* 69 */     ((ZodiacRewardResponse)this.response).boxId = request.boxId;
/* 70 */     ((ZodiacRewardResponse)this.response).num = ((Integer)zodiacComponent.getZodiacGoods(request.actId).get(Integer.valueOf(request.boxId))).intValue();
/* 71 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */