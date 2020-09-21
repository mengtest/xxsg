/*    */ package com.linlongyx.sanguo.webgame.processors.yearbeast;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class YearBeastBossInfoProcessor
/*    */   extends ProcessorBase<YearBeastBossInfoRequest, YearBeastBossInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new YearBeastBossInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, YearBeastBossInfoRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 62))
/* 32 */       return 10061; 
/* 33 */     YearBeastComponent yearBeastComponent = (YearBeastComponent)playerSession.getPlayer().createIfNotExist(YearBeastComponent.class);
/* 34 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 35 */     if (!bossHomeParameter.isActOpen(request.actId)) {
/* 36 */       return 12702;
/*    */     }
/* 38 */     YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(request.actId, YearBeastBean.class);
/* 39 */     if (null == yearBeastBean) {
/* 40 */       return 11808;
/*    */     }
/*    */     
/* 43 */     FestivalTime festivalTime = bossHomeParameter.getActTime(request.actId);
/* 44 */     int day = TimeUtil.getDayDisTime(festivalTime.startTime);
/* 45 */     YearBeastEntity yearBeastEntity = yearBeastComponent.getEntity(request.actId);
/* 46 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 47 */     ((YearBeastBossInfoResponse)this.response).actId = request.actId;
/* 48 */     ((YearBeastBossInfoResponse)this.response).bossId = yearBeastEntity.getCurrBossId();
/* 49 */     ((YearBeastBossInfoResponse)this.response).buyTimes = yearBeastEntity.getBuyTimes();
/* 50 */     ((YearBeastBossInfoResponse)this.response).changeTime = yearBeastBean.getChallLimit() + yearBeastEntity.getBuyTimes() - yearBeastEntity.getYearBeastTimes();
/* 51 */     ((YearBeastBossInfoResponse)this.response).currHp = yearBeastComponent.getCurTotalHp();
/* 52 */     ((YearBeastBossInfoResponse)this.response).maxHp = yearBeastEntity.getMaxHp();
/* 53 */     ((YearBeastBossInfoResponse)this.response).currDay = day;
/* 54 */     ((YearBeastBossInfoResponse)this.response).worldLevel = rankService.getLevel(request.actId);
/* 55 */     ((YearBeastBossInfoResponse)this.response).deathList = new ArrayList(yearBeastEntity.getDeathList());
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\yearbeast\YearBeastBossInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */