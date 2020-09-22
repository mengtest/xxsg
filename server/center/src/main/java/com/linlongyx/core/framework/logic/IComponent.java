package com.linlongyx.core.framework.logic;

public interface IComponent {
  String getType();
  
  boolean isDBInit();
  
  boolean saveToDB();
  
  boolean saveAllToDB();
  
  boolean getFromDB();
  
  void init();
  
  void build(long paramLong);
  
  boolean reset(int paramInt);
  
  long getPlayerId();
  
  long getUserId();
  
  String setValue(String paramString1, String paramString2, String paramString3);
  
  void setNeedSaveToDB(boolean paramBoolean);
  
  void maybeSaveToDB();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\logic\IComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */