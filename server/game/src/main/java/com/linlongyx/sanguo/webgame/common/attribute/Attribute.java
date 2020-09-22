package com.linlongyx.sanguo.webgame.common.attribute;

import com.linlongyx.core.framework.logic.IPlayer;
import java.util.Set;

public interface Attribute {
  public static final int DIFFERENCE_BASE_ATTR_TO_PER = 17;
  
  void refresh(IPlayer paramIPlayer, Set<Integer> paramSet);
  
  void refreshPartner(IPlayer paramIPlayer, Set<Integer> paramSet, long paramLong);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\Attribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */