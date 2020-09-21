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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineDrawTenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineDrawTenResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineDrawTenProcessor
/*    */   extends ProcessorBase<DivineDrawTenRequest, DivineDrawTenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 33 */     this.response = (ResponseBase)new DivineDrawTenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DivineDrawTenRequest request) {
/* 38 */     int curActId = DivineUtil.getCurDivineActId();
/* 39 */     if (curActId == -1) {
/* 40 */       return 14301;
/*    */     }
/* 42 */     DivineParameter divineParameter = (DivineParameter)ParameterConstant.getParameter(57);
/* 43 */     DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/* 44 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/*    */     
/* 46 */     if (divineEntity.getTimes() < 10) {
/* 47 */       return 14302;
/*    */     }
/* 49 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 50 */     divineEntity.setTimes(divineEntity.getTimes() - 10);
/* 51 */     String luckNum = DivineUtil.fmtDivineNum(((IntIntValue)ConstantService.divineMap.get(Integer.valueOf(curActId))).key);
/* 52 */     char[] charNum = luckNum.toCharArray();
/*    */     
/* 54 */     int[] nums = DivineUtil.drawTenTime(charNum);
/* 55 */     for (int num : nums) {
/* 56 */       String fmtNum = DivineUtil.fmtDivineNum(num);
/* 57 */       char[] targetNum = fmtNum.toCharArray();
/* 58 */       int sameCount = DivineUtil.sameDigitCount(charNum, targetNum);
/* 59 */       if (num > divineEntity.getMaxDivineNum()) {
/* 60 */         divineEntity.setMaxDivineNum(num);
/*    */       }
/* 62 */       DivineUtil.updateMaxDivineNum(curActId, playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName(), num);
/* 63 */       IntIntValue kv = new IntIntValue();
/* 64 */       kv.key = num;
/* 65 */       kv.value = TimeUtil.currentTime();
/* 66 */       divineEntity.getRecordList().add(kv);
/*    */       
/* 68 */       if (((Map)divineParameter.getSameNumMap().get(Integer.valueOf(curActId))).containsKey(Integer.valueOf(sameCount))) {
/* 69 */         DivineRewardBean bean = (DivineRewardBean)JsonTableService.getJsonData(((Integer)((Map)divineParameter.getSameNumMap().get(Integer.valueOf(curActId))).get(Integer.valueOf(sameCount))).intValue(), DivineRewardBean.class);
/* 70 */         rewardList.addAll(FinanceUtil.transform(bean.getReward()));
/* 71 */         if (sameCount == luckNum.length()) {
/* 72 */           PlayerUtil.sendNotice(5703, playerSession.getPlayer(), ((RewardBean)bean.getReward().get(0)).getId(), null);
/*    */         }
/*    */       } 
/* 75 */       ((DivineDrawTenResponse)this.response).divineNumList.add(fmtNum);
/*    */     } 
/* 77 */     divineEntity.setRecordList(divineEntity.getRecordList());
/* 78 */     FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.DivineTenDraw, true);
/* 79 */     ((DivineDrawTenResponse)this.response).list = rewardList;
/* 80 */     ((DivineDrawTenResponse)this.response).divineTimes = divineEntity.getTimes();
/* 81 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineDrawTenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */