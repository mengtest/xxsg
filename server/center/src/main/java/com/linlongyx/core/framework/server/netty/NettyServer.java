package com.linlongyx.core.framework.server.netty;

import com.linlongyx.core.framework.server.Server;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public interface NettyServer extends Server {
  ChannelInitializer<? extends Channel> getChannelInitializer();
  
  void setChannelInitializer(ChannelInitializer<? extends Channel> paramChannelInitializer);
  
  NettyConfig getNettyConfig();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\server\netty\NettyServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */