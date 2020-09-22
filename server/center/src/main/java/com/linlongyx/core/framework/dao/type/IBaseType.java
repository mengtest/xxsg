package com.linlongyx.core.framework.dao.type;

public interface IBaseType {
  Class<?> getType();
  
  String serial();
  
  void unserial(String paramString);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\type\IBaseType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */