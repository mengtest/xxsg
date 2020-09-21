package com.linlongyx.sanguo.webgame.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LoginOrBuilder extends MessageOrBuilder {
  boolean hasUser();
  
  String getUser();
  
  ByteString getUserBytes();
  
  boolean hasPwd();
  
  String getPwd();
  
  ByteString getPwdBytes();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\Message$LoginOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */