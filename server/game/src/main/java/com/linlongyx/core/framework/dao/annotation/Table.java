package com.linlongyx.core.framework.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
  String tableName();
  
  String prefix();
  
  String keyField() default "";
  
  boolean isPlayerIdKey() default false;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\annotation\Table.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */