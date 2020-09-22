package com.linlongyx.core.framework.dao.proxy;

public interface IProxy {
  String getEntityKeyId();
  
  void setEntityKeyId(String paramString);
  
  void reset();
  
  boolean saveAll();
  
  boolean save();
  
  boolean get();
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\proxy\IProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */