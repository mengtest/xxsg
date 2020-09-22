package linlongyx.core.framework.base.mobile;

import linlongyx.core.framework.base.Entrance;
import linlongyx.core.framework.logic.IPlayerSession;
import linlongyx.core.framework.protocol.mobile.RequestBase;

public interface IProcessor {
  void handle(IPlayerSession paramIPlayerSession, RequestBase paramRequestBase);
  
  void handleException(IPlayerSession paramIPlayerSession, short paramShort);
  
  boolean isOpen();
  
  void setEntrance(Entrance paramEntrance);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\mobile\IProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */