/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
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
/*     */ public class TimeUtil
/*     */ {
/*     */   public static final String FORMAT_DATE = "yyyy-MM-dd";
/*     */   public static final String FORMAT_TIME = "HH:mm";
/*     */   public static final String FORMAT_DATE_TIME2 = "yyyy-MM-dd HH:mm:ss";
/*     */   public static final String FORMAT_MONTH_DAY_TIME = "MM月dd日 HH:mm";
/*     */   private static final int YEAR = 31536000;
/*     */   private static final int MONTH = 2592000;
/*     */   public static final int DAY = 86400;
/*     */   public static final int DAY_MILL_SEC = 86400000;
/*     */   public static final int HOUR = 3600;
/*     */   public static final int MINUTE = 60;
/*     */   public static final int RESET_ZERO = 0;
/*     */   public static final int RESET_FIVE_HOUR = 5;
/*     */   public static final int RESET_TWELVE_HOUR = 12;
/*     */   public static final int RESET_TWENTY_HOUR = 20;
/*     */   public static final int RESET_WEEK = 50;
/*     */   public static final int RESET_FIVE = 18000;
/*     */   public static final int RESET_TWELVE = 43200;
/*     */   public static final int RESET_TWENTY = 72000;
/*  41 */   public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */   
/*     */   public static long currentTimeMillis() {
/*  44 */     return System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public static int currentTime() {
/*  48 */     return (int)(System.currentTimeMillis() / 1000L);
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
/*     */   public static int getNowSecond() {
/*  60 */     Calendar calendar = Calendar.getInstance();
/*  61 */     long curMillTime = calendar.getTimeInMillis();
/*  62 */     calendar.set(11, 0);
/*  63 */     calendar.set(12, 0);
/*  64 */     calendar.set(13, 0);
/*  65 */     long zeroMillTime = calendar.getTimeInMillis();
/*  66 */     return (int)((curMillTime - zeroMillTime) / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNowMinutes() {
/*  74 */     return getNowSecond() / 60;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTodayHourMillis(int hour) {
/*  83 */     Calendar calendar = Calendar.getInstance();
/*  84 */     calendar.set(11, hour);
/*  85 */     calendar.set(12, 0);
/*  86 */     calendar.set(13, 0);
/*  87 */     calendar.set(14, 0);
/*  88 */     return calendar.getTimeInMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTodayRefreshTime(int refreshHour) {
/*  97 */     return getTodayHourMillis(refreshHour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getZeroTime(int day) {
/* 106 */     Calendar calendar = Calendar.getInstance();
/* 107 */     calendar.set(11, 0);
/* 108 */     calendar.set(12, 0);
/* 109 */     calendar.set(13, 0);
/* 110 */     calendar.add(5, day);
/* 111 */     return calendar.getTimeInMillis();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameDate(Date date) {
/* 132 */     Date now = new Date();
/* 133 */     return (now.getDay() == date.getDay());
/*     */   }
/*     */   
/*     */   public static boolean inSameDay(int timestamp) {
/* 137 */     Calendar calendar = Calendar.getInstance();
/* 138 */     int curDay = calendar.get(6);
/*     */     
/* 140 */     calendar.setTimeInMillis(1000L * timestamp);
/* 141 */     int timestampDay = calendar.get(6);
/*     */     
/* 143 */     return (curDay == timestampDay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameDay(int timestamp, int clearingTime) {
/* 150 */     Calendar calendar = Calendar.getInstance();
/* 151 */     calendar.set(11, 0);
/* 152 */     calendar.set(12, 0);
/* 153 */     calendar.set(13, 0);
/*     */     
/* 155 */     int curDay = (int)((calendar.getTimeInMillis() - 1000L * clearingTime) / 86400000L);
/*     */     
/* 157 */     calendar.setTimeInMillis(1000L * timestamp);
/* 158 */     calendar.set(11, 0);
/* 159 */     calendar.set(12, 0);
/* 160 */     calendar.set(13, 0);
/*     */     
/* 162 */     int timestampDay = (int)((calendar.getTimeInMillis() - 1000L * clearingTime) / 86400000L);
/*     */     
/* 164 */     return (curDay == timestampDay);
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
/*     */   public static int getDayDiff(int timestamp) {
/* 178 */     Calendar calendar = Calendar.getInstance();
/* 179 */     calendar.set(11, 0);
/* 180 */     calendar.set(12, 0);
/* 181 */     calendar.set(13, 0);
/* 182 */     int nowDay = (int)(calendar.getTimeInMillis() / 86400000L);
/*     */     
/* 184 */     long time = 1000L * timestamp;
/* 185 */     calendar.setTimeInMillis(time);
/* 186 */     calendar.set(11, 0);
/* 187 */     calendar.set(12, 0);
/* 188 */     calendar.set(13, 0);
/* 189 */     int timestampDay = (int)(calendar.getTimeInMillis() / 86400000L);
/*     */     
/* 191 */     return nowDay - timestampDay;
/*     */   }
/*     */   
/*     */   public static int getTimeZoneOffset() {
/* 195 */     Calendar calendar = new GregorianCalendar();
/* 196 */     return calendar.getTimeZone().getOffset(System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayDiffToOpen(String openTime) {
/* 201 */     Calendar calendar = Calendar.getInstance();
/* 202 */     calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
/* 203 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try {
/* 205 */       Date date = sdf.parse(openTime);
/* 206 */       calendar.setTime(date);
/* 207 */     } catch (ParseException e) {
/* 208 */       e.printStackTrace();
/*     */     } 
/* 210 */     calendar.set(11, 0);
/* 211 */     calendar.set(12, 0);
/* 212 */     calendar.set(13, 0);
/* 213 */     int day1 = (int)(calendar.getTimeInMillis() / 86400000L);
/* 214 */     calendar.setTime(new Date());
/* 215 */     calendar.set(11, 0);
/* 216 */     calendar.set(12, 0);
/* 217 */     calendar.set(13, 0);
/* 218 */     int day2 = (int)(calendar.getTimeInMillis() / 86400000L);
/* 219 */     return day2 - day1 + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimeDiffToNow(int day) {
/* 228 */     Calendar calendar = Calendar.getInstance();
/* 229 */     calendar.set(11, 0);
/* 230 */     calendar.set(12, 0);
/* 231 */     calendar.set(13, 0);
/* 232 */     calendar.set(14, 0);
/* 233 */     calendar.add(5, day);
/* 234 */     return (int)(calendar.getTimeInMillis() / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDayDiffToOpen(int openTime, int timeStamp) {
/* 244 */     long time = 1000L * openTime;
/* 245 */     Calendar calendar = Calendar.getInstance();
/* 246 */     calendar.setTimeInMillis(time);
/* 247 */     calendar.set(11, 0);
/* 248 */     calendar.set(12, 0);
/* 249 */     calendar.set(13, 0);
/* 250 */     int openDay = (int)(calendar.getTimeInMillis() / 86400000L);
/*     */     
/* 252 */     time = 1000L * timeStamp;
/* 253 */     calendar.setTimeInMillis(time);
/* 254 */     calendar.set(11, 0);
/* 255 */     calendar.set(12, 0);
/* 256 */     calendar.set(13, 0);
/* 257 */     int timestampDay = (int)(calendar.getTimeInMillis() / 86400000L);
/*     */     
/* 259 */     return timestampDay - openDay + 1;
/*     */   }
/*     */   
/*     */   public static long getSecondDiffToOpen(String openTime) {
/*     */     try {
/* 264 */       Date date = dateFormat.parse(openTime);
/* 265 */       return (System.currentTimeMillis() - date.getTime()) / 1000L;
/* 266 */     } catch (ParseException e) {
/* 267 */       e.printStackTrace();
/*     */       
/* 269 */       return 0L;
/*     */     } 
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
/*     */   public static int timingTime(int hour, int minutes) {
/* 283 */     Calendar calendar = Calendar.getInstance();
/* 284 */     calendar.set(11, 0);
/* 285 */     calendar.set(13, 0);
/* 286 */     calendar.set(12, 0);
/*     */     
/* 288 */     long time = calendar.getTimeInMillis() / 1000L;
/* 289 */     return (int)(time + (hour * 3600) + (minutes * 60));
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
/*     */   public static int timingTime(int hour) {
/* 302 */     Calendar calendar = Calendar.getInstance();
/* 303 */     calendar.set(11, 0);
/* 304 */     calendar.set(13, 0);
/* 305 */     calendar.set(12, 0);
/*     */     
/* 307 */     long time = calendar.getTimeInMillis() / 1000L;
/* 308 */     return (int)(time + hour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int leftNextZeroTime() {
/* 317 */     int now = currentTime();
/* 318 */     int zero = timingTime(0);
/* 319 */     return zero + 86400 - now;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCardLeftDay(int deadLineTime) {
/* 328 */     int nowTime = currentTime();
/* 329 */     int day = 0;
/* 330 */     while (deadLineTime > nowTime) {
/* 331 */       day++;
/* 332 */       deadLineTime -= 86400;
/*     */     } 
/* 334 */     return day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int leftTimeFromFive() {
/* 344 */     int now = currentTime();
/* 345 */     int five = timingTime(18000);
/* 346 */     if (now > five) return five + 86400 - now; 
/* 347 */     return now - five;
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
/*     */   public static int nowWeek() {
/* 359 */     Calendar calendar = Calendar.getInstance();
/* 360 */     return calendar.get(7) - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNowHourOfDay() {
/* 368 */     Calendar calendar = Calendar.getInstance();
/* 369 */     return calendar.get(11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWeek() {
/* 377 */     int week = nowWeek();
/* 378 */     if (0 == week) {
/* 379 */       return 7;
/*     */     }
/* 381 */     return week;
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
/*     */   public static int time2Week(int curTime) {
/* 394 */     Calendar calendar = Calendar.getInstance();
/* 395 */     calendar.setTimeInMillis(1000L * curTime);
/* 396 */     return calendar.get(7) - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTimeLeapDay(int timestamp, int day) {
/* 406 */     if (timestamp <= 0) {
/* 407 */       return false;
/*     */     }
/* 409 */     if (day < 1 || day > 7) {
/* 410 */       return false;
/*     */     }
/* 412 */     int curTime = currentTime();
/* 413 */     if (curTime - timestamp < 86400)
/* 414 */       return false; 
/* 415 */     Calendar calendar1 = Calendar.getInstance();
/* 416 */     calendar1.setTimeInMillis(1000L * timestamp);
/*     */     
/* 418 */     Calendar calendar2 = Calendar.getInstance();
/* 419 */     calendar2.setTimeInMillis(1000L * curTime);
/* 420 */     while (calendar1.getTimeInMillis() < calendar2.getTimeInMillis()) {
/* 421 */       int week = calendar1.get(7);
/* 422 */       if (week == day) {
/* 423 */         return true;
/*     */       }
/* 425 */       calendar1.add(5, 1);
/*     */     } 
/*     */     
/* 428 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPrevTargetWeekDay(int weekDay) {
/* 437 */     Calendar calendar = Calendar.getInstance();
/* 438 */     while (calendar.get(7) != weekDay) {
/* 439 */       calendar.add(5, -1);
/*     */     }
/* 441 */     int year = calendar.get(1);
/* 442 */     int month = calendar.get(2) + 1;
/* 443 */     int day = calendar.get(5);
/* 444 */     return year * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNextTargetWeekDay(int weekDay) {
/* 453 */     Calendar calendar = Calendar.getInstance();
/* 454 */     while (calendar.get(7) != weekDay) {
/* 455 */       calendar.add(5, 1);
/*     */     }
/* 457 */     int year = calendar.get(1);
/* 458 */     int month = calendar.get(2) + 1;
/* 459 */     int day = calendar.get(5);
/* 460 */     return year * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNextTargetWeekDayTimestamp(int weekDay) {
/* 469 */     weekDay %= 7;
/* 470 */     if (weekDay < 0) weekDay += 7; 
/* 471 */     Calendar calendar = Calendar.getInstance();
/*     */     while (true) {
/* 473 */       calendar.add(5, 1);
/* 474 */       if (calendar.get(7) == weekDay) {
/* 475 */         calendar.set(11, 0);
/* 476 */         calendar.set(12, 0);
/* 477 */         calendar.set(13, 0);
/* 478 */         calendar.set(14, 0);
/* 479 */         return (int)(calendar.getTimeInMillis() / 1000L);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimestampHour(int timestamp) {
/* 488 */     Calendar calendar = Calendar.getInstance();
/* 489 */     calendar.setTimeInMillis(1000L * timestamp);
/* 490 */     return calendar.get(11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int leftTimeHour() {
/* 499 */     return timingTime((hourSeconds() + 1) * 3600) - currentTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hourSeconds() {
/* 508 */     return (currentTime() - timingTime(0)) / 3600;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurrentYearMonthDay() {
/* 517 */     Calendar calendar = Calendar.getInstance();
/* 518 */     int year = calendar.get(1);
/* 519 */     int month = calendar.get(2) + 1;
/* 520 */     int day = calendar.get(5);
/* 521 */     return year * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getYesterdayYearMonthDay() {
/* 529 */     Calendar calendar = Calendar.getInstance();
/* 530 */     calendar.setTimeInMillis(currentTimeMillis() - 86400000L);
/* 531 */     int year = calendar.get(1);
/* 532 */     int month = calendar.get(2) + 1;
/* 533 */     int day = calendar.get(5);
/* 534 */     return year * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getYearMonthDay(int curTime) {
/* 542 */     Calendar calendar = Calendar.getInstance();
/* 543 */     calendar.setTimeInMillis(curTime * 1000L);
/* 544 */     int year = calendar.get(1);
/* 545 */     int month = calendar.get(2) + 1;
/* 546 */     int day = calendar.get(5);
/* 547 */     return year * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSimpleYearMonthDay() {
/* 555 */     Calendar calendar = Calendar.getInstance();
/* 556 */     calendar.setTimeInMillis(currentTimeMillis());
/* 557 */     int year = calendar.get(1);
/* 558 */     int month = calendar.get(2) + 1;
/* 559 */     int day = calendar.get(5);
/* 560 */     return year % 2000 * 10000 + month * 100 + day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurrentYearMonth() {
/* 569 */     Calendar calendar = Calendar.getInstance();
/* 570 */     int year = calendar.get(1);
/* 571 */     int month = calendar.get(2) + 1;
/* 572 */     return year * 100 + month;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPrevYearMonth(int num) {
/* 581 */     Calendar calendar = Calendar.getInstance();
/* 582 */     calendar.add(2, num);
/* 583 */     int year = calendar.get(1);
/* 584 */     int month = calendar.get(2) + 1;
/* 585 */     return year * 100 + month;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimeStampZero(int year, int month, int day) {
/* 596 */     Calendar calendar = Calendar.getInstance();
/* 597 */     calendar.set(11, 0);
/* 598 */     calendar.set(13, 0);
/* 599 */     calendar.set(12, 0);
/*     */     
/* 601 */     calendar.set(1, year);
/* 602 */     calendar.set(2, month - 1);
/* 603 */     calendar.set(5, day);
/*     */     
/* 605 */     int time = (int)(calendar.getTimeInMillis() / 1000L);
/* 606 */     return time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimeStampZero(int yearMonthDay) {
/* 615 */     String string = String.valueOf(yearMonthDay);
/* 616 */     if (string.length() != 8) {
/* 617 */       return 0;
/*     */     }
/* 619 */     int year = Integer.valueOf(string.substring(0, 4)).intValue();
/* 620 */     int month = Integer.valueOf(string.substring(4, 6)).intValue();
/* 621 */     int day = Integer.valueOf(string.substring(6, 8)).intValue();
/* 622 */     return getTimeStampZero(year, month, day);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxDay() {
/* 631 */     Calendar calendar = Calendar.getInstance();
/* 632 */     return calendar.getActualMaximum(5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getToday() {
/* 641 */     Calendar calendar = Calendar.getInstance();
/* 642 */     return calendar.get(5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFormatDate() {
/* 651 */     Date date = new Date();
/* 652 */     return dateFormat.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFormatDate(int time) {
/* 660 */     Date date = new Date(time * 1000L);
/* 661 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String getFormatDate(Date date) {
/* 665 */     return dateFormat.format(date);
/*     */   }
/*     */   
/*     */   public static String getFormatDate2() {
/* 669 */     Date date = new Date();
/* 670 */     return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDayDisTime(int timestamp) {
/* 680 */     int curTime = timingTime(0);
/* 681 */     int day = 1;
/* 682 */     while (timestamp < curTime) {
/* 683 */       curTime -= 86400;
/* 684 */       day++;
/*     */     } 
/* 686 */     return day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimeFromDate(String strDate) {
/*     */     try {
/* 694 */       Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(strDate);
/* 695 */       return (int)(date.getTime() / 1000L);
/* 696 */     } catch (ParseException e) {
/* 697 */       e.printStackTrace();
/*     */       
/* 699 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getZeroTimeStamp(int timeStamp) {
/* 708 */     long time = 1000L * timeStamp;
/* 709 */     Calendar calendar = Calendar.getInstance();
/* 710 */     calendar.setTimeInMillis(time);
/* 711 */     calendar.set(11, 0);
/* 712 */     calendar.set(12, 0);
/* 713 */     calendar.set(13, 0);
/* 714 */     return (int)(calendar.getTimeInMillis() / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameWeek(Date d1, Date d2) {
/* 724 */     return isSameWeek((int)(d1.getTime() / 1000L), (int)(d2.getTime() / 1000L));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameWeek(int t1, int t2) {
/* 735 */     int diffDay = Math.abs(t1 - t2) / 86400;
/* 736 */     if (diffDay >= 7) return false;
/*     */     
/* 738 */     Calendar c1 = Calendar.getInstance();
/* 739 */     c1.setTimeInMillis((t1 * 1000));
/* 740 */     Calendar c2 = Calendar.getInstance();
/* 741 */     c2.setTimeInMillis((t2 * 1000));
/*     */     
/* 743 */     int year1 = c1.get(1);
/* 744 */     int year2 = c2.get(1);
/*     */     
/* 746 */     int week1 = c1.getWeeksInWeekYear();
/* 747 */     int week2 = c2.getWeeksInWeekYear();
/* 748 */     if (c1.get(7) == 1) week1--; 
/* 749 */     if (c2.get(7) == 1) week2--; 
/* 750 */     if (year1 == year2) {
/* 751 */       return (week1 == week2);
/*     */     }
/* 753 */     return (week1 == 0 || week2 == 0);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */