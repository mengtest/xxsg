/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MilitaryBean {
/*     */   private int id;
/*     */   private String name;
/*     */   private int limit;
/*     */   private Map<Integer, LevelBean> level;
/*     */   
/*     */   public int getId() {
/*  11 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  17 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLimit() {
/*  23 */     return this.limit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, LevelBean> getLevel() {
/*  29 */     return this.level;
/*     */   }
/*     */   
/*     */   public class LevelBean { private int typeS;
/*     */     private ArrayList<PreconditionBean> precondition;
/*     */     private ArrayList<RewardBean> item;
/*     */     private int time;
/*     */     
/*     */     public int getTypeS() {
/*  38 */       return this.typeS;
/*     */     }
/*     */     private ArrayList<AttrScientificresearchBean> attrScientificresearch;
/*     */     private String type;
/*     */     
/*     */     public ArrayList<PreconditionBean> getPrecondition() {
/*  44 */       return this.precondition;
/*     */     }
/*     */     private ArrayList<InteriorSpecialBean> interiorSpecial; private ArrayList<CrusadeSpecialBean> crusadeSpecial;
/*     */     
/*     */     public class PreconditionBean { private int id;
/*     */       private int num;
/*     */       
/*     */       public int getId() {
/*  52 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getNum() {
/*  57 */         return this.num;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  62 */         String retVal = "id= " + this.id + ", num= " + this.num;
/*  63 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<RewardBean> getItem() {
/*  70 */       return this.item;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getTime() {
/*  76 */       return this.time;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrScientificresearchBean> getAttrScientificresearch() {
/*  82 */       return this.attrScientificresearch;
/*     */     }
/*     */     
/*     */     public class AttrScientificresearchBean { private int attrId;
/*     */       private int num;
/*     */       
/*     */       public int getAttrId() {
/*  89 */         return this.attrId;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getNum() {
/*  94 */         return this.num;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  99 */         String retVal = "attrId= " + this.attrId + ", num= " + this.num;
/* 100 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getType() {
/* 107 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<InteriorSpecialBean> getInteriorSpecial() {
/* 113 */       return this.interiorSpecial;
/*     */     }
/*     */     
/*     */     public class InteriorSpecialBean { private int type;
/*     */       private int attrId;
/*     */       private int num;
/*     */       
/*     */       public int getType() {
/* 121 */         return this.type;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getAttrId() {
/* 126 */         return this.attrId;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getNum() {
/* 131 */         return this.num;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 136 */         String retVal = "type= " + this.type + ", attrId= " + this.attrId + ", num= " + this.num;
/* 137 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<CrusadeSpecialBean> getCrusadeSpecial() {
/* 144 */       return this.crusadeSpecial;
/*     */     }
/*     */     
/*     */     public class CrusadeSpecialBean { private int type;
/*     */       private int attrId;
/*     */       private int num;
/*     */       
/*     */       public int getType() {
/* 152 */         return this.type;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getAttrId() {
/* 157 */         return this.attrId;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getNum() {
/* 162 */         return this.num;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 167 */         String retVal = "type= " + this.type + ", attrId= " + this.attrId + ", num= " + this.num;
/* 168 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 174 */       String retVal = "typeS= " + this.typeS + ", precondition= " + this.precondition + ", item= " + this.item + ", time= " + this.time + ", attrScientificresearch= " + this.attrScientificresearch + ", type= " + this.type + ", interiorSpecial= " + this.interiorSpecial + ", crusadeSpecial= " + this.crusadeSpecial;
/* 175 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 181 */     String retVal = "id= " + this.id + ", name= " + this.name + ", limit= " + this.limit + ", level= " + this.level;
/* 182 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MilitaryBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */