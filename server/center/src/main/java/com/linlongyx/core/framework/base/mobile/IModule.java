package com.linlongyx.core.framework.base.mobile;

public interface IModule {
  IProcessor getProcessor(int paramInt);
  
  void addProcessor(int paramInt, IProcessor paramIProcessor);
  
  boolean isOpen();
  
  void setOpen(boolean paramBoolean);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\mobile\IModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */