/*    */ package linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyCache;
/*    */ import com.linlongyx.sanguo.webgame.rmi.runewar.RunewarUtil;
/*    */ import java.util.ArrayList;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
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
/*    */ public class MondayZeroJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 25 */     DestinyCache.clearAll();
/* 26 */     ArrayList<Pair<Integer, Integer>> serverWorldLevels = LogicRmiManager.getServerWorldLevel();
/* 27 */     DestinyCache.serverReZone(serverWorldLevels);
/*    */ 
/*    */     
/* 30 */     RunewarUtil.balanceRank();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\MondayZeroJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */