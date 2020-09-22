package com.linlongyx.core.framework.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface TableField {
  String name() default "";
  
  int len() default 0;
  
  boolean isKey() default false;
  
  boolean isAutoInc() default false;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\annotation\TableField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */