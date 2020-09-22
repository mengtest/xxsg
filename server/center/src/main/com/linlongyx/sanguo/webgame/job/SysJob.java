package com.linlongyx.sanguo.webgame.job;

public interface SysJob extends Runnable {
  long getInitialDelay();
  
  long getPeriod();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\job\SysJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */