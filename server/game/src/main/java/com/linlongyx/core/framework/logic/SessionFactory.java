package com.linlongyx.core.framework.logic;

public interface SessionFactory {
  ISession newSession();
  
  IPlayerSession newPlayerSession(IPlayer paramIPlayer);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\SessionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */