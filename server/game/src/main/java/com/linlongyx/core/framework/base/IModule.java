package com.linlongyx.core.framework.base;

public interface IModule {
  IProcessor getProcessor(int paramInt);
  
  void addProcessor(int paramInt, IProcessor paramIProcessor);
  
  boolean isOpen();
  
  void setOpen(boolean paramBoolean);
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\base\IModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */