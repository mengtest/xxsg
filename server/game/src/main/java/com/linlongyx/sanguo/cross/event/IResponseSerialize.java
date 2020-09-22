package com.linlongyx.sanguo.cross.event;

public interface IResponseSerialize {
  byte[] serial() throws Exception;
  
  IResponseEvent unserial(byte[] paramArrayOfbyte) throws Exception;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\IResponseSerialize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */