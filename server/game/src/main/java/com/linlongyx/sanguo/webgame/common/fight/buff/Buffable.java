package com.linlongyx.sanguo.webgame.common.fight.buff;

import com.linlongyx.sanguo.webgame.common.fight.IFight;
import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;

public interface Buffable {
  long effect(IFight paramIFight, boolean paramBoolean);
  
  long effect(IFight paramIFight, boolean paramBoolean, long paramLong);
  
  long calHurt();
  
  long immediate(ResultPlayData paramResultPlayData);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\Buffable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */