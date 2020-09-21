/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StarcraftBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String rid; private int camp; private int buildingType; private int buildingLevel; private int accommodateCap;
/*    */   private int acquisitionTime;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private ArrayList<RewardBean> acquisitionRevenue; private int points;
/*    */   private ArrayList<BuildingLocationBean> buildingLocation;
/*    */   
/*    */   public String getRid() {
/* 22 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCamp() {
/* 28 */     return this.camp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBuildingType() {
/* 34 */     return this.buildingType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBuildingLevel() {
/* 40 */     return this.buildingLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAccommodateCap() {
/* 46 */     return this.accommodateCap;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAcquisitionTime() {
/* 52 */     return this.acquisitionTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getAcquisitionRevenue() {
/* 58 */     return this.acquisitionRevenue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPoints() {
/* 64 */     return this.points;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<BuildingLocationBean> getBuildingLocation() {
/* 70 */     return this.buildingLocation;
/*    */   }
/*    */   
/*    */   public class BuildingLocationBean { private int x;
/*    */     private int y;
/*    */     
/*    */     public int getX() {
/* 77 */       return this.x;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getY() {
/* 82 */       return this.y;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 87 */       String retVal = "x= " + this.x + ", y= " + this.y;
/* 88 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", camp= " + this.camp + ", buildingType= " + this.buildingType + ", buildingLevel= " + this.buildingLevel + ", accommodateCap= " + this.accommodateCap + ", acquisitionTime= " + this.acquisitionTime + ", acquisitionRevenue= " + this.acquisitionRevenue + ", points= " + this.points + ", buildingLocation= " + this.buildingLocation;
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\StarcraftBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */