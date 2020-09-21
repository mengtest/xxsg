package com.linlongyx.sanguo.logclient.coder;

import java.io.IOException;

public interface EventEncoder {
  <T> byte[] write(T paramT) throws IOException;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\coder\EventEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */