package com.linlongyx.core.framework.protocol;

import io.netty.buffer.ByteBuf;

public interface ISerialize {
  void serial(ByteBuf paramByteBuf);
  
  void unserial(ByteBuf paramByteBuf);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\protocol\ISerialize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */