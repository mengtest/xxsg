/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SingleInsParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NormalInsInfoProcessor
/*    */   extends ProcessorBase<NormalInsInfoRequest, NormalInsInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new NormalInsInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, NormalInsInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 13)) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     int type = request.type;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     SingleInsParameter singleInsParameter = (SingleInsParameter)ParameterConstant.getParameter(13);
/* 41 */     Map<Integer, Integer> list = singleInsParameter.getInsListByType(type);
/* 42 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 43 */     Map<Integer, Integer> times = singleInsComponent.getTimes();
/* 44 */     Map<Integer, Integer> maxPoints = singleInsComponent.getMaxPoints();
/* 45 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 46 */     int dailyTime = VipUtil.getNum(playerComponent.getVip(), 8);
/* 47 */     for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
/* 48 */       PersonalInsBean personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(((Integer)entry.getValue()).intValue(), PersonalInsBean.class);
/* 49 */       if (null != personalInsBean) {
/* 50 */         if (!SingleInsUtil.isDayValue(personalInsBean.getDay())) {
/* 51 */           return 13005;
/*    */         }
/* 53 */         ((NormalInsInfoResponse)this.response).maxCount = dailyTime;
/* 54 */         ((NormalInsInfoResponse)this.response).leftCount = dailyTime - ((Integer)times.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 59 */     ((NormalInsInfoResponse)this.response).checkPoint = ((Integer)maxPoints.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 60 */     ((NormalInsInfoResponse)this.response).type = type;
/* 61 */     int vipSweepTime = VipUtil.getNum(playerComponent.getVip(), 17);
/* 62 */     ((NormalInsInfoResponse)this.response).leftSweep = vipSweepTime - ((Integer)singleInsComponent.getVipSweepTimes().getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 63 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\NormalInsInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */