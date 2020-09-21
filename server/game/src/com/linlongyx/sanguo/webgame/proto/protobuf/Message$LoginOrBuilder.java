package com.linlongyx.sanguo.webgame.proto.protobuf;

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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\protobuf\Message$LoginOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */