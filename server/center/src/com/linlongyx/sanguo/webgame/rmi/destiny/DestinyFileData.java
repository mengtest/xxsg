/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.file.FileChecker;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
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
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DestinyFileData {
/*  21 */   private static Logger LOG = LoggerFactory.getLogger(DestinyFileData.class);
/*     */   
/*     */   private String dataPath;
/*     */   private String name;
/*     */   private int writetime;
/*     */   private int dataLen;
/*     */   private FileChecker fileChecker;
/*     */   private RandomAccessFile readFile;
/*     */   private RandomAccessFile writeFile;
/*     */   
/*     */   public DestinyFileData(String dataPath, String name) {
/*  32 */     this.dataPath = dataPath;
/*  33 */     this.name = name;
/*  34 */     this.fileChecker = new DestinyFileChecker();
/*     */   }
/*     */   
/*     */   private String metaDataFileName() {
/*  38 */     return this.dataPath + File.separator + this.name + ".destiny.meta.dat";
/*     */   }
/*     */   
/*     */   private String fileName() {
/*  42 */     return this.dataPath + File.separator + this.name + "." + this.writetime + ".destiny.data.dat";
/*     */   }
/*     */   
/*     */   public byte[] readFile() {
/*  46 */     retrieveMetaData();
/*  47 */     String fileName = fileName();
/*     */     try {
/*  49 */       Paths.get(this.dataPath, new String[0]).toFile().mkdirs();
/*  50 */       Path file = Paths.get(fileName, new String[0]);
/*  51 */       if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/*  52 */         return null;
/*     */       }
/*  54 */       this.readFile = new RandomAccessFile(file.toFile(), "r");
/*  55 */       if (this.dataLen > 0) {
/*  56 */         byte[] bytes = new byte[this.dataLen];
/*  57 */         this.readFile.read(bytes);
/*     */ 
/*     */         
/*  60 */         this.dataLen = 0;
/*  61 */         return bytes;
/*     */       } 
/*  63 */       LOG.info("destiny({}): readFile() opened {}", this.name, fileName);
/*  64 */     } catch (IOException e) {
/*  65 */       LOG.error("open file: {} file fail, {}", fileName, e.getMessage());
/*     */     } finally {
/*  67 */       if (this.readFile != null) {
/*     */         try {
/*  69 */           this.readFile.close();
/*  70 */           this.readFile = null;
/*  71 */         } catch (IOException e) {
/*  72 */           LOG.error("close readFile: {} file fail, {}", fileName, e.getMessage());
/*     */         } 
/*     */       }
/*     */     } 
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   public void writeFile(byte[] bytes) {
/*  80 */     this.writetime = TimeUtil.currentTime();
/*  81 */     String fileName = fileName();
/*     */     try {
/*  83 */       Paths.get(this.dataPath, new String[0]).toFile().mkdirs();
/*  84 */       Path file = Paths.get(fileName, new String[0]);
/*     */       
/*  86 */       if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/*  87 */         Files.createFile(file, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/*  89 */       this.writeFile = new RandomAccessFile(file.toFile(), "rw");
/*  90 */       this.writeFile.seek(0L);
/*  91 */       this.dataLen = bytes.length;
/*  92 */       this.writeFile.write(bytes);
/*  93 */       persistMetaData();
/*  94 */       LOG.info("destiny({}): writeFile() opened {}", this.name, fileName);
/*     */     }
/*  96 */     catch (IOException e) {
/*  97 */       if (this.writeFile != null) {
/*     */         try {
/*  99 */           this.writeFile.close();
/* 100 */         } catch (IOException e1) {
/* 101 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 104 */       this.writeFile = null;
/* 105 */       LOG.error("open file: {} file fail, {}", fileName, e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] loadRecord(String fileName) {
/*     */     try {
/* 111 */       Paths.get(this.dataPath, new String[0]).toFile().mkdirs();
/* 112 */       Path file = Paths.get(this.dataPath + File.separator + fileName, new String[0]);
/* 113 */       if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 114 */         return null;
/*     */       }
/* 116 */       this.readFile = new RandomAccessFile(file.toFile(), "r");
/* 117 */       long length = this.readFile.length();
/* 118 */       if (length >= 2147483647L) {
/* 119 */         LOG.error("loadRecord: {} file fail, {}", fileName, "file is too big");
/* 120 */         return null;
/*     */       } 
/* 122 */       byte[] bytes = new byte[(int)length];
/* 123 */       this.readFile.read(bytes);
/* 124 */       return bytes;
/* 125 */     } catch (IOException e) {
/* 126 */       LOG.error("open file: {} file fail, {}", fileName, e.getMessage());
/*     */     } finally {
/* 128 */       if (this.readFile != null) {
/*     */         try {
/* 130 */           this.readFile.close();
/* 131 */         } catch (IOException e) {
/* 132 */           LOG.error("close readFile: {} file fail, {}", fileName, e.getMessage());
/*     */         } 
/* 134 */         this.readFile = null;
/*     */       } 
/*     */     } 
/* 137 */     return null;
/*     */   }
/*     */   
/*     */   public void saveRecord(byte[] bytes, String fileName) {
/*     */     try {
/* 142 */       Paths.get(this.dataPath, new String[0]).toFile().mkdirs();
/* 143 */       Path file = Paths.get(this.dataPath + File.separator + fileName, new String[0]);
/*     */       
/* 145 */       if (!Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 146 */         Files.createFile(file, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/* 148 */       this.writeFile = new RandomAccessFile(file.toFile(), "rw");
/* 149 */       this.writeFile.seek(0L);
/* 150 */       this.writeFile.write(bytes);
/* 151 */       LOG.info("destiny({}): saveRecord() opened {}", this.name, fileName);
/*     */     }
/* 153 */     catch (IOException e) {
/* 154 */       if (this.writeFile != null) {
/*     */         try {
/* 156 */           this.writeFile.close();
/* 157 */         } catch (IOException e1) {
/* 158 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 161 */       this.writeFile = null;
/* 162 */       LOG.error("open file: {} file fail, {}", fileName, e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void retrieveMetaData() {
/* 168 */     String fileName = metaDataFileName();
/*     */     
/*     */     try {
/* 171 */       Path file = Paths.get(fileName, new String[0]);
/* 172 */       this.writetime = TimeUtil.currentTime();
/* 173 */       this.dataLen = 0;
/* 174 */       if (Files.exists(file, new java.nio.file.LinkOption[0])) {
/* 175 */         DataInputStream dis = new DataInputStream(new FileInputStream(file.toFile()));
/* 176 */         int writetime = dis.readInt();
/* 177 */         int dataLen = dis.readInt();
/* 178 */         if (!this.fileChecker.isExpired(writetime)) {
/* 179 */           this.writetime = writetime;
/* 180 */           this.dataLen = dataLen;
/*     */         } 
/* 182 */         dis.close();
/*     */       } 
/* 184 */     } catch (IOException e) {
/* 185 */       LOG.error("destiny({}) open metaData file {} failed, ", new Object[] { this.name, fileName, e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void persistMetaData() {
/* 191 */     String fileName = metaDataFileName();
/*     */     try {
/* 193 */       Path source = Paths.get(fileName + "_" + System.nanoTime() + ".tmp", new String[0]);
/* 194 */       if (!Files.exists(source, new java.nio.file.LinkOption[0])) {
/* 195 */         Files.createFile(source, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/* 197 */       File file = source.toFile();
/* 198 */       DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
/* 199 */       dos.writeInt(this.writetime);
/* 200 */       dos.writeInt(this.dataLen);
/* 201 */       dos.flush();
/*     */       
/* 203 */       dos.close();
/* 204 */       Files.move(source, Paths.get(fileName, new String[0]), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 205 */     } catch (IOException e) {
/* 206 */       LOG.error("destiny({}), failed to persist metaDataFile {}: {}", new Object[] { this.name, fileName, e.getMessage() });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\DestinyFileData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */