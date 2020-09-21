/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 17 */     if (strings[2].equals("refresh")) {
/*    */       
/* 19 */       int rankingType = Integer.parseInt(strings[3]);
/* 20 */       RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 21 */       rankBaseService.refresh(rankingType);
/* 22 */     } else if (strings[2].equals("refreshAll")) {
/*    */       
/* 24 */       RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 25 */       for (RankingType rankingType : RankingType.values())
/* 26 */         rankBaseService.refresh(rankingType.getType()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\RankingGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */