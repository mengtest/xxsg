package com.linlongyx.sanguo.cross.event;

public interface IRequestSerialize {
  byte[] serial() throws Exception;
  
  IRequestEvent unserial(byte[] paramArrayOfbyte) throws Exception;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\IRequestSerialize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */