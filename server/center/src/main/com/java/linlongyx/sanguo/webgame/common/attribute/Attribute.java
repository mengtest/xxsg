package linlongyx.sanguo.webgame.common.attribute;

import com.linlongyx.core.framework.logic.IPlayer;
import java.util.Set;

public interface Attribute {
  void refresh(IPlayer paramIPlayer, Set<Integer> paramSet);
  
  void refreshPartner(IPlayer paramIPlayer, Set<Integer> paramSet, long paramLong);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\Attribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */