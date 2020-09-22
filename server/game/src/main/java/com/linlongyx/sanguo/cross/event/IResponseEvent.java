package com.linlongyx.sanguo.cross.event;

public interface IResponseEvent extends IEvent {
  long getPlayerId();
  
  void setPlayerId(long paramLong);
  
  byte getMark();
  
  void setMark(byte paramByte);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\IResponseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */