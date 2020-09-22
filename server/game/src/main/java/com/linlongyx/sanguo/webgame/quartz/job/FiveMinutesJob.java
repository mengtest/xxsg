/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.processors.cardbook.CardBookUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.chat.ChatUtils;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.divine.DivineUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.FunctionTimeNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiveMinutesJob
/*    */   implements IJob
/*    */ {
/*    */   public short process() {
/* 74 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 75 */     groupService.sort();
/* 76 */     TeamUtil.sendDoubletimeNotice();
/* 77 */     ChatUtils.randomRebootChat();
/* 78 */     ChatUtils.groupAdd(true);
/* 79 */     ChatUtils.groupAdd(false);
/* 80 */     DivineUtil.balanceDivineRank();
/* 81 */     CardBookUtil.checkAsk();
/* 82 */     CrossUtil.heartBeat(MContext.getServerIdInt(), RankingLevel.getWorldLevel());
/* 83 */     return 10001;
/*    */   }
/*    */   
/*    */   private void functionTimeNotice(int cmd, int status) {
/* 87 */     FunctionTimeNoticeResponse functionTimeNoticeResponse = new FunctionTimeNoticeResponse();
/* 88 */     functionTimeNoticeResponse.functionId = cmd;
/* 89 */     functionTimeNoticeResponse.status = status;
/* 90 */     LookUpService.broadcast((ResponseBase)functionTimeNoticeResponse);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\FiveMinutesJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */