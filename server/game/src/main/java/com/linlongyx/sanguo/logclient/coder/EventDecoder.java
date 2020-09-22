package com.linlongyx.sanguo.logclient.coder;

import java.io.IOException;

public interface EventDecoder {
  <T> T read(byte[] paramArrayOfbyte, Class<T> paramClass) throws IOException;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\coder\EventDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */