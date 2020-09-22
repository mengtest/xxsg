/*     */ package com.linlongyx.sanguo.logclient;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ 
/*     */ public class DiskQueue {
/*  21 */   private static final Logger LOG = LoggerFactory.getLogger(DiskQueue.class);
/*     */   
/*     */   private static final int MAX_QUEUE_SIZE = 1000;
/*     */   
/*     */   private static final long DEFAULT_SYNC_EVERY = 120000L;
/*     */   private long readPos;
/*     */   private long writePos;
/*     */   private long readFileNum;
/*     */   private long writeFileNum;
/*  30 */   private AtomicLong depth = new AtomicLong(0L);
/*     */   
/*  32 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String dataPath;
/*     */   
/*     */   private final long maxBytesPerFile;
/*     */   
/*     */   private final long syncEvery;
/*     */   
/*     */   private volatile boolean needSync;
/*     */   
/*     */   private volatile boolean exit;
/*     */   
/*     */   private long lastWriteTime;
/*     */   
/*     */   private long nextReadPos;
/*     */   
/*     */   private long nextReadFileNum;
/*     */   
/*     */   private RandomAccessFile readFile;
/*     */   
/*     */   private RandomAccessFile writeFile;
/*     */   
/*     */   private ArrayBlockingQueue<byte[]> writeQueue;
/*     */ 
/*     */   
/*     */   public DiskQueue(String name, String dataPath, int maxBytesPerFile) {
/*  61 */     this(name, dataPath, maxBytesPerFile, 120000L);
/*     */   }
/*     */   
/*     */   public DiskQueue(String name, String dataPath, int maxBytesPerFile, long syncEvery) {
/*  65 */     this.name = name;
/*  66 */     this.dataPath = dataPath;
/*  67 */     this.maxBytesPerFile = maxBytesPerFile;
/*  68 */     this.syncEvery = syncEvery;
/*  69 */     this.needSync = true;
/*  70 */     this.exit = false;
/*  71 */     this.writeQueue = (ArrayBlockingQueue)new ArrayBlockingQueue<>(1000);
/*  72 */     this.lastWriteTime = System.currentTimeMillis();
/*     */ 
/*     */     
/*  75 */     retrieveMetaData();
/*     */   }
/*     */   
/*     */   public void put(byte[] bytes) {
/*  79 */     if (this.exit)
/*     */       return; 
/*  81 */     long curTime = System.currentTimeMillis();
/*  82 */     if ((curTime - this.lastWriteTime >= this.syncEvery && !this.writeQueue.isEmpty()) || this.writeQueue.size() >= 1000) {
/*  83 */       this.lastWriteTime = curTime;
/*  84 */       writeToFile();
/*     */     } 
/*  86 */     if (this.needSync) {
/*  87 */       sync();
/*     */     }
/*     */     try {
/*  90 */       this.writeQueue.put(bytes);
/*  91 */     } catch (InterruptedException e) {
/*  92 */       LOG.error("diskqueue({}), put element to writeQueue failed, {}", this.name, e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/*  98 */       this.lock.lock();
/*  99 */       this.exit = true;
/* 100 */       writeToFile();
/* 101 */       if (this.readFile != null) {
/* 102 */         this.readFile.close();
/* 103 */         this.readFile = null;
/*     */       } 
/* 105 */       if (this.writeFile != null) {
/* 106 */         this.writeFile.close();
/* 107 */         this.writeFile = null;
/*     */       } 
/* 109 */       sync();
/*     */     }
/* 111 */     catch (IOException e) {
/* 112 */       LOG.error("diskqueue({}) close failed : {}", this.name, e.getMessage());
/*     */     } finally {
/* 114 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void deleteAllFiles() {
/* 119 */     skipToNextRWFile();
/* 120 */     File file = new File(metaDataFileName());
/* 121 */     if (file.isFile() && file.exists() && 
/* 122 */       !file.delete()) {
/* 123 */       LOG.error("diskqueue({}), delete metaFile failed, {}", this.name, file.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void skipToNextRWFile() {
/* 129 */     this.lock.lock();
/*     */     try {
/* 131 */       if (this.readFile != null) {
/*     */         try {
/* 133 */           this.readFile.close();
/* 134 */         } catch (IOException e) {
/* 135 */           LOG.error("diskqueue({}), failed to close readFile, {}", this.name, e.getMessage());
/*     */         } 
/* 137 */         this.readFile = null;
/*     */       } 
/* 139 */       if (this.writeFile != null) {
/*     */         try {
/* 141 */           this.writeFile.close();
/* 142 */         } catch (IOException e) {
/* 143 */           LOG.error("diskqueue({}), failed to close writeFile, {}", this.name, e.getMessage());
/*     */         } 
/* 145 */         this.writeFile = null;
/*     */       } 
/* 147 */       for (long i = this.readFileNum; i <= this.writeFileNum; i++) {
/* 148 */         String fileName = fileName(i);
/* 149 */         File file = new File(fileName);
/* 150 */         if (file.isFile() && file.exists() && 
/* 151 */           !file.delete()) {
/* 152 */           LOG.error("diskqueue({}), delete file failed, {}", this.name, fileName);
/*     */         }
/*     */       } 
/*     */       
/* 156 */       this.writeFileNum++;
/* 157 */       this.writePos = 0L;
/* 158 */       this.readFileNum = this.writeFileNum;
/* 159 */       this.readPos = 0L;
/* 160 */       this.nextReadFileNum = this.writeFileNum;
/* 161 */       this.nextReadPos = 0L;
/* 162 */       this.depth.set(0L);
/*     */     } finally {
/* 164 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] readOne() {
/* 169 */     if (this.readFile == null) {
/* 170 */       String curFileName = fileName(this.readFileNum);
/*     */       try {
/* 172 */         Path file = Paths.get(curFileName, new String[0]);
/* 173 */         if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 174 */           Files.createFile(file, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         }
/* 176 */         this.readFile = new RandomAccessFile(file.toFile(), "r");
/* 177 */         LOG.info("disqueue({}): readOne() opened {}", this.name, curFileName);
/* 178 */         if (this.readPos > 0L) {
/* 179 */           this.readFile.seek(this.readPos);
/*     */         }
/* 181 */       } catch (IOException e) {
/* 182 */         if (this.readFile != null) {
/*     */           try {
/* 184 */             this.readFile.close();
/* 185 */           } catch (IOException e1) {
/* 186 */             e1.printStackTrace();
/*     */           } 
/*     */         }
/* 189 */         this.readFile = null;
/* 190 */         LOG.error("open file: {} file fail", curFileName);
/* 191 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 196 */       int dataLen = this.readFile.readInt();
/* 197 */       byte[] result = new byte[dataLen];
/* 198 */       this.readFile.read(result);
/* 199 */       this.nextReadPos = this.readPos + (4 + dataLen);
/* 200 */       this.nextReadFileNum = this.readFileNum;
/* 201 */       if (this.nextReadPos > this.maxBytesPerFile) {
/* 202 */         if (this.readFile != null) {
/* 203 */           this.readFile.close();
/* 204 */           this.readFile = null;
/*     */         } 
/* 206 */         this.nextReadFileNum++;
/* 207 */         this.readPos = 0L;
/*     */       } 
/* 209 */       return result;
/* 210 */     } catch (IOException e) {
/* 211 */       if (this.readFile != null) {
/*     */         try {
/* 213 */           this.readFile.close();
/* 214 */         } catch (IOException e1) {
/* 215 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 218 */       this.readFile = null;
/*     */       
/* 220 */       return null;
/*     */     } 
/*     */   }
/*     */   private void writeOne(byte[] data) {
/* 224 */     if (this.writeFile == null) {
/* 225 */       String curFileName = fileName(this.writeFileNum);
/*     */       try {
/* 227 */         Path file = Paths.get(curFileName, new String[0]);
/*     */         
/* 229 */         if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 230 */           Files.createFile(file, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         }
/* 232 */         this.writeFile = new RandomAccessFile(file.toFile(), "rw");
/* 233 */         LOG.info("disqueue({}): writeOne() opened {}", this.name, curFileName);
/* 234 */         if (this.writePos > 0L) {
/* 235 */           this.writeFile.seek(this.writePos);
/*     */         }
/* 237 */       } catch (IOException e) {
/* 238 */         if (this.writeFile != null) {
/*     */           try {
/* 240 */             this.writeFile.close();
/* 241 */           } catch (IOException e1) {
/* 242 */             e1.printStackTrace();
/*     */           } 
/*     */         }
/* 245 */         this.writeFile = null;
/* 246 */         LOG.error("open file: {} file fail", curFileName);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     try {
/* 251 */       int dataLen = data.length;
/* 252 */       this.writeFile.writeInt(dataLen);
/* 253 */       this.writeFile.write(data);
/* 254 */       this.writePos += (4 + data.length);
/* 255 */       this.depth.incrementAndGet();
/*     */       
/* 257 */       if (this.writePos > this.maxBytesPerFile) {
/* 258 */         this.writeFileNum++;
/* 259 */         this.writePos = 0L;
/* 260 */         sync();
/* 261 */         if (this.writeFile != null) {
/* 262 */           this.writeFile.close();
/* 263 */           this.writeFile = null;
/*     */         } 
/*     */       } 
/* 266 */     } catch (IOException e) {
/* 267 */       if (this.writeFile != null) {
/*     */         try {
/* 269 */           this.writeFile.close();
/* 270 */         } catch (IOException e1) {
/* 271 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 274 */       this.writeFile = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sync() {
/*     */     try {
/* 280 */       this.lock.lock();
/* 281 */       if (this.writeFile != null) {
/* 282 */         FileDescriptor fd = this.writeFile.getFD();
/* 283 */         fd.sync();
/*     */       } 
/* 285 */       persistMetaData();
/* 286 */       this.needSync = false;
/* 287 */     } catch (IOException e) {
/*     */       
/* 289 */       LOG.error("diskqueue({}), failed to sync, {}", this.name, e.getMessage());
/*     */     } finally {
/* 291 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkTailCorruption(long depth) {
/* 296 */     if (this.readFileNum < this.writeFileNum || this.readPos < this.writePos) {
/*     */       return;
/*     */     }
/* 299 */     if (this.depth.get() != 0L) {
/* 300 */       if (depth < 0L) {
/* 301 */         LOG.error("diskqueue {} negative depth at tail {}, meta data corruption, resetting 0...", this.name, Long.valueOf(depth));
/* 302 */       } else if (depth > 0L) {
/* 303 */         LOG.error("diskqueue {} positive depth at tail {}, data loss, resetting 0...", this.name, Long.valueOf(depth));
/*     */       } 
/* 305 */       this.depth.set(0L);
/* 306 */       this.needSync = true;
/*     */     } 
/*     */     
/* 309 */     if (this.readFileNum != this.writeFileNum || this.readPos != this.writePos) {
/* 310 */       if (this.readFileNum > this.writeFileNum) {
/* 311 */         LOG.error("disqueue({}) readFileNum > writeFileNum ({} > {}), corruption, skipping to next writeFileNum and resetting 0...", new Object[] { this.name, Long.valueOf(this.readFileNum), Long.valueOf(this.writeFileNum) });
/*     */       }
/* 313 */       if (this.readPos > this.writePos) {
/* 314 */         LOG.error("diskqueue({}) readPos > writePos ({} > {}), corruption, skipping to next writeFileNum and resetting 0...", new Object[] { this.name, Long.valueOf(this.readPos), Long.valueOf(this.writePos) });
/*     */       }
/* 316 */       skipToNextRWFile();
/* 317 */       this.needSync = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveForward() {
/* 323 */     long oldReadFileNum = this.readFileNum;
/* 324 */     this.readFileNum = this.nextReadFileNum;
/* 325 */     this.readPos = this.nextReadPos;
/* 326 */     long depth = this.depth.decrementAndGet();
/*     */     
/* 328 */     if (oldReadFileNum != this.nextReadFileNum) {
/* 329 */       this.needSync = true;
/* 330 */       String fileName = fileName(oldReadFileNum);
/* 331 */       File file = new File(fileName);
/* 332 */       if (!file.delete()) {
/* 333 */         LOG.error("diskqueue({}) failed to Remove({})", this.name, fileName);
/*     */       }
/*     */     } 
/* 336 */     checkTailCorruption(depth);
/*     */   }
/*     */   
/*     */   private void handleReadError() {
/*     */     try {
/* 341 */       if (this.readFileNum == this.writeFileNum) {
/* 342 */         if (this.writeFile != null) {
/* 343 */           this.writeFile.close();
/* 344 */           this.writeFile = null;
/*     */         } 
/* 346 */         this.writeFileNum++;
/* 347 */         this.writePos = 0L;
/*     */       } 
/* 349 */       String fileName = fileName(this.readFileNum);
/* 350 */       String badFileName = fileName + ".bad";
/* 351 */       LOG.warn("diskqueue({}) jump to next file and saving bad file as {}", this.name, badFileName);
/* 352 */       Path file = Paths.get(fileName, new String[0]);
/* 353 */       Path targetFile = Paths.get(badFileName, new String[0]);
/* 354 */       Files.move(file, targetFile, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 355 */       this.readFileNum++;
/* 356 */       this.readPos = 0L;
/* 357 */       this.nextReadFileNum = this.readFileNum;
/* 358 */       this.nextReadPos = 0L;
/* 359 */       this.needSync = true;
/*     */     }
/* 361 */     catch (IOException e) {
/* 362 */       LOG.error("diskqueue({}) failed to rename bad diskqueue file {} to {}", new Object[] { this.name, fileName(this.readFileNum), fileName(this.readFileNum) + ".bad" });
/*     */     } 
/*     */   }
/*     */   
/*     */   private String metaDataFileName() {
/* 367 */     return this.dataPath + File.separator + this.name + ".diskqueue.meta.dat";
/*     */   }
/*     */   
/*     */   private String fileName(long fileNum) {
/* 371 */     return this.dataPath + File.separator + this.name + ".diskqueue." + fileNum + ".dat";
/*     */   }
/*     */ 
/*     */   
/*     */   private void retrieveMetaData() {
/* 376 */     String fileName = metaDataFileName();
/*     */     
/*     */     try {
/* 379 */       Path file = Paths.get(fileName, new String[0]);
/* 380 */       if (Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 381 */         DataInputStream dis = new DataInputStream(new FileInputStream(file.toFile()));
/* 382 */         this.depth.set(dis.readLong());
/* 383 */         this.readFileNum = dis.readLong();
/* 384 */         this.readPos = dis.readLong();
/* 385 */         this.writeFileNum = dis.readLong();
/* 386 */         this.writePos = dis.readLong();
/* 387 */         this.nextReadFileNum = this.readFileNum;
/* 388 */         this.nextReadPos = this.readPos;
/* 389 */         dis.close();
/*     */       } 
/* 391 */     } catch (IOException e) {
/* 392 */       LOG.error("diskqueue({}) open metaData file {} failed, ", new Object[] { this.name, fileName, e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void persistMetaData() {
/* 399 */     String fileName = metaDataFileName();
/*     */     try {
/* 401 */       Path source = Paths.get(fileName + "_" + System.nanoTime() + ".tmp", new String[0]);
/*     */       
/* 403 */       if (!Files.exists(source, new java.nio.file.LinkOption[0])) {
/* 404 */         Files.createFile(source, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/* 406 */       File file = source.toFile();
/* 407 */       DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
/*     */       
/* 409 */       dos.writeLong(this.depth.get());
/* 410 */       dos.writeLong(this.readFileNum);
/* 411 */       dos.writeLong(this.readPos);
/* 412 */       dos.writeLong(this.writeFileNum);
/* 413 */       dos.writeLong(this.writePos);
/* 414 */       dos.flush();
/*     */       
/* 416 */       dos.close();
/* 417 */       Files.move(source, Paths.get(fileName, new String[0]), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 418 */     } catch (IOException e) {
/* 419 */       LOG.error("diskqueue({}), failed to persist metaDataFile {}: {}", new Object[] { this.name, fileName, e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<byte[]> readAllBytesFromFile() {
/* 426 */     List<byte[]> bytesList = (List)new ArrayList<>();
/*     */ 
/*     */     
/*     */     try {
/* 430 */       while (!this.writeQueue.isEmpty()) {
/* 431 */         bytesList.add(this.writeQueue.take());
/*     */       }
/* 433 */     } catch (InterruptedException e) {
/* 434 */       LOG.error("diskqueue({}), take element form writeQueue failed, {}", this.name, e.getMessage());
/*     */     } 
/*     */ 
/*     */     
/* 438 */     while (this.readFileNum < this.writeFileNum || this.readPos < this.writePos) {
/* 439 */       if (this.nextReadPos == this.readPos) {
/* 440 */         byte[] bytes = readOne();
/* 441 */         if (bytes == null) {
/* 442 */           handleReadError(); continue;
/*     */         } 
/* 444 */         bytesList.add(bytes);
/* 445 */         moveForward();
/*     */       } 
/*     */     } 
/*     */     
/* 449 */     deleteAllFiles();
/* 450 */     sync();
/* 451 */     return bytesList;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeToFile() {
/*     */     try {
/* 457 */       this.lock.lock();
/* 458 */       while (!this.writeQueue.isEmpty()) {
/* 459 */         writeOne(this.writeQueue.take());
/*     */       }
/* 461 */       sync();
/* 462 */     } catch (InterruptedException e) {
/* 463 */       LOG.error("diskqueue({}) writeQueue take failed : {}", this.name, e.getMessage());
/*     */     } finally {
/* 465 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\DiskQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */