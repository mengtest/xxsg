/*    */ package com.linlongyx.sanguo.webgame.processors.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DivineRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DivineParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineDrawOneRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineDrawOneResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineDrawOneProcessor
/*    */   extends ProcessorBase<DivineDrawOneRequest, DivineDrawOneResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 32 */     this.response = (ResponseBase)new DivineDrawOneResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DivineDrawOneRequest request) {
/* 37 */     int curActId = DivineUtil.getCurDivineActId();
/* 38 */     if (curActId == -1) {
/* 39 */       return 14301;
/*    */     }
/* 41 */     DivineParameter divineParameter = (DivineParameter)ParameterConstant.getParameter(57);
/* 42 */     DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/* 43 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 44 */     if (divineEntity.getTimes() <= 0) {
/* 45 */       return 14302;
/*    */     }
/* 47 */     divineEntity.setTimes(divineEntity.getTimes() - 1);
/* 48 */     String luckNum = DivineUtil.fmtDivineNum(((IntIntValue)ConstantService.divineMap.get(Integer.valueOf(curActId))).key);
/* 49 */     char[] charNum = luckNum.toCharArray();
/* 50 */     int num = DivineUtil.drawOneTime(charNum);
/* 51 */     String fmtNum = DivineUtil.fmtDivineNum(num);
/* 52 */     char[] targetNum = fmtNum.toCharArray();
/* 53 */     int sameCount = DivineUtil.sameDigitCount(charNum, targetNum);
/* 54 */     if (num > divineEntity.getMaxDivineNum()) {
/* 55 */       divineEntity.setMaxDivineNum(num);
/*    */     }
/* 57 */     DivineUtil.updateMaxDivineNum(curActId, playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName(), num);
/*    */     
/* 59 */     IntIntValue kv = new IntIntValue();
/* 60 */     kv.key = num;
/* 61 */     kv.value = TimeUtil.currentTime();
/* 62 */     divineEntity.getRecordList().add(kv);
/* 63 */     divineEntity.setRecordList(divineEntity.getRecordList());
/*    */     
/* 65 */     byte hit = (byte)(((Map)divineParameter.getSameNumMap().get(Integer.valueOf(curActId))).containsKey(Integer.valueOf(sameCount)) ? 1 : 0);
/* 66 */     if (hit == 1) {
/* 67 */       DivineRewardBean bean = (DivineRewardBean)JsonTableService.getJsonData(((Integer)((Map)divineParameter.getSameNumMap().get(Integer.valueOf(curActId))).get(Integer.valueOf(sameCount))).intValue(), DivineRewardBean.class);
/* 68 */       ArrayList<Reward> rewardList = FinanceUtil.transform(bean.getReward());
/* 69 */       FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.DivineOneDraw, false);
/* 70 */       divineComponent.setRewardList(rewardList);
/* 71 */       if (sameCount == luckNum.length()) {
/* 72 */         PlayerUtil.sendNotice(5703, playerSession.getPlayer(), ((RewardBean)bean.getReward().get(0)).getId(), null);
/*    */       }
/*    */     } 
/*    */     
/* 76 */     ((DivineDrawOneResponse)this.response).hit = hit;
/* 77 */     ((DivineDrawOneResponse)this.response).divineNum = fmtNum;
/* 78 */     ((DivineDrawOneResponse)this.response).divineTimes = divineEntity.getTimes();
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineDrawOneProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */