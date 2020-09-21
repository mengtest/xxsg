package com.linlongyx.core.framework.communication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public interface MessageSender {
  Object sendMessage(Object paramObject);
  
  void sendMessage(ByteBuf paramByteBuf);
  
  void close();
  
  Channel getChannel();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\communication\MessageSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */