package com.linlongyx.core.framework.logic;

public interface IPlayer extends ICharacter {
  long getUserId();
  
  void setUserId(long paramLong);
  
  long getPlayerId();
  
  void setPlayerId(long paramLong);
  
  String getPlayerName();
  
  void setPlayerName(String paramString);
  
  void setSession(IPlayerSession paramIPlayerSession);
  
  IPlayerSession getSession();
  
  void login();
  
  void logout();
  
  void logout(boolean paramBoolean);
  
  void enterLogout();
  
  void saveAll();
  
  void addComponent(IComponent paramIComponent);
  
  IComponent getComponent(Class<?> paramClass);
  
  IComponent createIfNotExist(Class<? extends IComponent> paramClass);
  
  void reset(int paramInt);
  
  default void resetOne(int time, String type) {}
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\IPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */