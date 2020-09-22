/*     */ package com.linlongyx.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.log4j.FileAppender;
/*     */ import org.apache.log4j.Layout;
/*     */ import org.apache.log4j.helpers.LogLog;
/*     */ import org.apache.log4j.spi.LoggingEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DailyMaxRollingFileAppender
/*     */   extends FileAppender
/*     */ {
/*     */   static final int TOP_OF_TROUBLE = -1;
/*     */   static final int TOP_OF_MINUTE = 0;
/*     */   static final int TOP_OF_HOUR = 1;
/*     */   static final int HALF_DAY = 2;
/*     */   static final int TOP_OF_DAY = 3;
/*     */   static final int TOP_OF_WEEK = 4;
/*     */   static final int TOP_OF_MONTH = 5;
/* 152 */   private String datePattern = "'.'yyyy-MM-dd";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   private int maxBackupIndex = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String scheduledFilename;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   private long nextCheck = System.currentTimeMillis() - 1L;
/*     */   
/* 176 */   Date now = new Date();
/*     */   
/*     */   SimpleDateFormat sdf;
/*     */   
/* 180 */   RollingPastCalendar rpc = new RollingPastCalendar();
/*     */   
/* 182 */   int checkPeriod = -1;
/*     */ 
/*     */   
/* 185 */   static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DailyMaxRollingFileAppender() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DailyMaxRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
/* 201 */     super(layout, filename, true);
/* 202 */     this.datePattern = datePattern;
/* 203 */     activateOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDatePattern(String pattern) {
/* 212 */     this.datePattern = pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDatePattern() {
/* 219 */     return this.datePattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxBackupIndex(int maxBackups) {
/* 232 */     this.maxBackupIndex = maxBackups;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxBackupIndex() {
/* 239 */     return this.maxBackupIndex;
/*     */   }
/*     */   
/*     */   public void activateOptions() {
/* 243 */     super.activateOptions();
/*     */     
/* 245 */     LogLog.debug("Max backup file kept: " + this.maxBackupIndex + ".");
/*     */     
/* 247 */     if (this.datePattern != null && this.fileName != null) {
/* 248 */       this.now.setTime(System.currentTimeMillis());
/* 249 */       this.sdf = new SimpleDateFormat(this.datePattern);
/* 250 */       int type = computeCheckPeriod();
/* 251 */       printPeriodicity(type);
/* 252 */       this.rpc.setType(type);
/*     */       
/* 254 */       File file = new File(this.fileName);
/* 255 */       this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));
/*     */     } else {
/* 257 */       LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void printPeriodicity(int type) {
/* 263 */     switch (type) {
/*     */       case 0:
/* 265 */         LogLog.debug("Appender [[+name+]] to be rolled every minute.");
/*     */         return;
/*     */       case 1:
/* 268 */         LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
/*     */         return;
/*     */       
/*     */       case 2:
/* 272 */         LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
/*     */         return;
/*     */       
/*     */       case 3:
/* 276 */         LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
/*     */         return;
/*     */       
/*     */       case 4:
/* 280 */         LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
/*     */         return;
/*     */       
/*     */       case 5:
/* 284 */         LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
/*     */         return;
/*     */     } 
/*     */     
/* 288 */     LogLog.warn("Unknown periodicity for appender [[+name+]].");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int computeCheckPeriod() {
/* 302 */     RollingPastCalendar rollingPastCalendar = new RollingPastCalendar(gmtTimeZone, Locale.ENGLISH);
/*     */     
/* 304 */     Date epoch = new Date(0L);
/* 305 */     if (this.datePattern != null) {
/* 306 */       for (int i = 0; i <= 5; i++) {
/* 307 */         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
/* 308 */         simpleDateFormat.setTimeZone(gmtTimeZone);
/* 309 */         String r0 = simpleDateFormat.format(epoch);
/* 310 */         rollingPastCalendar.setType(i);
/* 311 */         Date next = new Date(rollingPastCalendar.getNextCheckMillis(epoch));
/* 312 */         String r1 = simpleDateFormat.format(next);
/*     */ 
/*     */         
/* 315 */         if (r0 != null && r1 != null && !r0.equals(r1)) {
/* 316 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 321 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void rollOver() throws IOException {
/* 329 */     if (this.datePattern == null) {
/* 330 */       this.errorHandler.error("Missing DatePattern option in rollOver().");
/*     */       
/*     */       return;
/*     */     } 
/* 334 */     String datedFilename = this.fileName + this.sdf.format(this.now);
/*     */ 
/*     */ 
/*     */     
/* 338 */     if (this.scheduledFilename.equals(datedFilename)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 343 */     closeFile();
/*     */     
/* 345 */     File target = new File(this.scheduledFilename);
/* 346 */     if (target.exists()) {
/* 347 */       target.delete();
/*     */     }
/*     */     
/* 350 */     File file = new File(this.fileName);
/* 351 */     boolean result = file.renameTo(target);
/* 352 */     if (result) {
/* 353 */       LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
/*     */ 
/*     */       
/* 356 */       if (this.maxBackupIndex > 0) {
/*     */         
/* 358 */         file = new File(this.fileName + dateBefore());
/*     */         
/* 360 */         if (file.exists())
/* 361 */           file.delete(); 
/*     */       } 
/*     */     } else {
/* 364 */       LogLog.error("Failed to rename [[+fileName+]] to [[+scheduledFilename+]].");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 370 */       setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
/* 371 */     } catch (IOException e) {
/* 372 */       this.errorHandler.error("setFile(" + this.fileName + ", false) call failed.");
/*     */     } 
/* 374 */     this.scheduledFilename = datedFilename;
/*     */   }
/*     */   
/*     */   private String dateBefore() {
/* 378 */     String dataAnte = "";
/*     */ 
/*     */     
/* 381 */     if (this.datePattern != null) {
/* 382 */       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
/*     */       
/* 384 */       dataAnte = simpleDateFormat.format(new Date(this.rpc.getPastCheckMillis(new Date(), this.maxBackupIndex)));
/*     */     } 
/*     */     
/* 387 */     return dataAnte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void subAppend(LoggingEvent event) {
/* 399 */     long n = System.currentTimeMillis();
/*     */     
/* 401 */     if (n >= this.nextCheck) {
/* 402 */       this.now.setTime(n);
/* 403 */       this.nextCheck = this.rpc.getNextCheckMillis(this.now);
/*     */       
/*     */       try {
/* 406 */         rollOver();
/* 407 */       } catch (IOException ioe) {
/* 408 */         LogLog.error("rollOver() failed.", ioe);
/*     */       } 
/*     */     } 
/*     */     
/* 412 */     super.subAppend(event);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongy\\util\DailyMaxRollingFileAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */