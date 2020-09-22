package com.linlongyx.core.framework.base;

import com.linlongyx.core.framework.logic.IPlayerSession;
import com.linlongyx.core.framework.protocol.RequestBase;

public interface IProcessor {
  void handle(IPlayerSession paramIPlayerSession, RequestBase paramRequestBase);
  
  void handleException(IPlayerSession paramIPlayerSession, short paramShort);
  
  boolean isOpen();
  
  void setEntrance(Entrance paramEntrance);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\IProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */