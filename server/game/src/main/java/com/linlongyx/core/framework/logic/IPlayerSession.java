package com.linlongyx.core.framework.logic;

import com.linlongyx.core.framework.protocol.ResponseBase;
import com.linlongyx.core.framework.protocol.mobile.ResponseBase;

public interface IPlayerSession extends ISession {
  IPlayer getPlayer();
  
  void setPlayer(IPlayer paramIPlayer);
  
  void sendMessage(Object paramObject);
  
  void sendMessage(ResponseBase paramResponseBase);
  
  void sendErrorCode(short paramShort, ResponseBase paramResponseBase);
  
  void sendMessage(ResponseBase paramResponseBase);
  
  void sendErrorCode(short paramShort, ResponseBase paramResponseBase);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\IPlayerSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */