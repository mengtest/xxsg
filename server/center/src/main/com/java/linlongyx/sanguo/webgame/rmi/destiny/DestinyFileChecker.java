/*    */ package linlongyx.sanguo.webgame.rmi.destiny;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.file.FileChecker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyFileChecker
/*    */   implements FileChecker
/*    */ {
/*    */   public boolean isExpired(int time) {
/* 14 */     return TimeUtil.isTimeLeapDay(time, 1);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\DestinyFileChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */