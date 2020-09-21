/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.commons.codec.DecoderException;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ public class Rijndael
/*     */ {
/*  35 */   private static final Logger LOGGER = LoggerFactory.getLogger(Rijndael.class);
/*     */   
/*  37 */   private static final Map<String, Rijndael> AES_CACHES = new ConcurrentHashMap<>();
/*     */   
/*     */   public static final String ALGORITHM = "AES";
/*     */   
/*     */   public static final int KEY_LENGTH = 16;
/*     */   
/*     */   public static final int IV_LENGTH = 16;
/*     */   
/*     */   private final AESMode mode;
/*     */   
/*     */   private final SecretKeySpec keySpec;
/*     */   
/*     */   private final IvParameterSpec ivSpec;
/*     */   
/*     */   private Cipher enc;
/*     */   private Cipher dec;
/*     */   
/*     */   public static Rijndael getInstance(byte[] key) {
/*  55 */     return getInstance(key, AESMode.ECB_PKCS5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Rijndael getInstance(byte[] key, AESMode mode) {
/*  66 */     String hexKey = Hex.encodeHexString(key);
/*  67 */     String cacheKey = StringUtils.join(new Object[] { hexKey, 
/*  68 */           StringUtils.replace(mode.getName(), "/", "_") }, "_");
/*     */     
/*  70 */     if (AES_CACHES.containsKey(cacheKey)) {
/*  71 */       return AES_CACHES.get(cacheKey);
/*     */     }
/*  73 */     Rijndael o = new Rijndael(key, mode);
/*  74 */     AES_CACHES.put(cacheKey, o);
/*  75 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Rijndael getInstance(byte[] key, byte[] iv) {
/*  86 */     return getInstance(key, iv, AESMode.CBC_PKCS5);
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
/*     */   public static Rijndael getInstance(byte[] key, byte[] iv, AESMode mode) {
/*  98 */     String hexKey = Hex.encodeHexString(key);
/*  99 */     String hexIV = Hex.encodeHexString(iv);
/* 100 */     String cacheKey = StringUtils.join(new Object[] { hexKey, hexIV, 
/* 101 */           StringUtils.replace(mode.getName(), "/", "_") }, "_");
/*     */     
/* 103 */     if (AES_CACHES.containsKey(cacheKey)) {
/* 104 */       return AES_CACHES.get(cacheKey);
/*     */     }
/* 106 */     Rijndael o = new Rijndael(key, iv, mode);
/* 107 */     AES_CACHES.put(cacheKey, o);
/* 108 */     return o;
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
/*     */   public Rijndael(byte[] key) {
/* 124 */     this(key, AESMode.ECB_PKCS5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rijndael(byte[] key, AESMode mode) {
/* 134 */     validateKey(key);
/*     */     
/* 136 */     byte[] aesKey = ArrayUtils.subarray(key, 0, 16);
/* 137 */     this.keySpec = new SecretKeySpec(aesKey, "AES");
/* 138 */     this.ivSpec = null;
/* 139 */     this.mode = mode;
/*     */     
/* 141 */     initEncryptionCipher();
/* 142 */     initDecryptionCipher();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rijndael(byte[] key, byte[] iv) {
/* 152 */     this(key, iv, AESMode.CBC_PKCS5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rijndael(byte[] key, byte[] iv, AESMode mode) {
/* 163 */     validateKey(key);
/* 164 */     validateIV(iv);
/*     */     
/* 166 */     byte[] aesKey = ArrayUtils.subarray(key, 0, 16);
/* 167 */     byte[] aesIV = ArrayUtils.subarray(iv, 0, 16);
/* 168 */     this.keySpec = new SecretKeySpec(aesKey, "AES");
/* 169 */     this.ivSpec = new IvParameterSpec(aesIV);
/* 170 */     this.mode = mode;
/*     */     
/* 172 */     initEncryptionCipher();
/* 173 */     initDecryptionCipher();
/*     */   }
/*     */ 
/*     */   
/*     */   private void validateKey(byte[] key) {
/* 178 */     if (key == null || key.length < 16) {
/* 179 */       throw new IllegalArgumentException(String.format("Invalid AES key length: %s bytes", new Object[] {
/* 180 */               Integer.valueOf((key == null) ? 0 : key.length)
/*     */             }));
/*     */     }
/*     */   }
/*     */   
/*     */   private void validateIV(byte[] iv) {
/* 186 */     if (iv == null || iv.length < 16) {
/* 187 */       throw new IllegalArgumentException(String.format("Wrong IV length: must be %s bytes long", new Object[] {
/* 188 */               Integer.valueOf(16)
/*     */             }));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCihperLength(int sourceLen) {
/* 199 */     int base = 0;
/* 200 */     if (!StringUtils.endsWith(this.mode.getName(), "NoPadding")) {
/* 201 */       base = 16;
/*     */     }
/* 203 */     int pad = sourceLen % 16;
/* 204 */     if (pad == 0) {
/* 205 */       return sourceLen + base;
/*     */     }
/* 207 */     return sourceLen - pad + base;
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
/*     */   public byte[] encrypt(byte[] data) {
/*     */     byte[] input;
/* 223 */     if (ArrayUtils.isEmpty(data)) {
/* 224 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     }
/*     */     
/* 227 */     if (StringUtils.endsWith(this.mode.getName(), "NoPadding")) {
/* 228 */       input = padding(data);
/*     */     } else {
/* 230 */       input = data;
/*     */     } 
/*     */     try {
/* 233 */       return this.enc.doFinal(input);
/* 234 */     } catch (Exception ex) {
/* 235 */       writeEncryptLogger(ex);
/*     */       
/* 237 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
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
/*     */ 
/*     */   
/*     */   public byte[] decrypt(byte[] data) {
/*     */     try {
/* 254 */       byte[] result = this.dec.doFinal(data);
/* 255 */       if (StringUtils.endsWith(this.mode.getName(), "NoPadding")) {
/* 256 */         int idx = result.length;
/* 257 */         for (int i = result.length; --i >= 0 && 
/* 258 */           result[i] == 0;) {
/* 259 */           idx = i;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 264 */         ArrayUtils.subarray(result, 0, idx);
/*     */       } 
/* 266 */       return result;
/* 267 */     } catch (Exception ex) {
/* 268 */       writeDecryptLogger(ex);
/*     */       
/* 270 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encrypt(byte[] data, int offset, int length) {
/*     */     try {
/* 289 */       return this.enc.doFinal(data, offset, length);
/* 290 */     } catch (Exception ex) {
/* 291 */       writeEncryptLogger(ex);
/*     */       
/* 293 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] decrypt(byte[] data, int offset, int length) {
/*     */     try {
/* 312 */       return this.dec.doFinal(data, offset, length);
/* 313 */     } catch (Exception ex) {
/* 314 */       writeDecryptLogger(ex);
/*     */       
/* 316 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encrypt(byte[] inputData, int inputOffset, int inputLen, byte[] outputData, int outputOffset) {
/*     */     try {
/* 337 */       return this.enc.doFinal(inputData, inputOffset, inputLen, outputData, outputOffset);
/* 338 */     } catch (Exception ex) {
/* 339 */       writeEncryptLogger(ex);
/*     */       
/* 341 */       return 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int decrypt(byte[] inputData, int inputOffset, int inputLen, byte[] outputData, int outputOffset) {
/*     */     try {
/* 362 */       return this.dec.doFinal(inputData, inputOffset, inputLen, outputData, outputOffset);
/* 363 */     } catch (Exception ex) {
/* 364 */       writeDecryptLogger(ex);
/*     */       
/* 366 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String encryptToHex(byte[] data) {
/* 376 */     byte[] bytes = encrypt(data);
/* 377 */     if (bytes.length == 0) {
/* 378 */       return null;
/*     */     }
/* 380 */     return Hex.encodeHexString(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] decryptHex(String hexStr) {
/*     */     byte[] bytes;
/* 392 */     if (StringUtils.isBlank(hexStr)) {
/* 393 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     }
/*     */     
/*     */     try {
/* 397 */       bytes = Hex.decodeHex(hexStr.toCharArray());
/* 398 */     } catch (DecoderException ex) {
/* 399 */       writeDecryptLogger((Throwable)ex);
/* 400 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     } 
/* 402 */     return decrypt(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encryptString(String input, String encoding) {
/*     */     byte[] data;
/* 413 */     if (StringUtils.isBlank(input)) {
/* 414 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     }
/* 416 */     if (StringUtils.isBlank(encoding)) {
/* 417 */       encoding = "ISO-8859-1";
/*     */     }
/*     */     
/*     */     try {
/* 421 */       data = input.getBytes(encoding);
/* 422 */     } catch (UnsupportedEncodingException ex) {
/* 423 */       writeEncryptLogger(ex);
/* 424 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     } 
/* 426 */     return encrypt(data);
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
/*     */   public String decryptToString(byte[] data, String encoding) {
/* 441 */     if (ArrayUtils.isEmpty(data)) {
/* 442 */       return null;
/*     */     }
/* 444 */     if (StringUtils.isBlank(encoding)) {
/* 445 */       encoding = "ISO-8859-1";
/*     */     }
/* 447 */     byte[] result = decrypt(data);
/* 448 */     if (result.length == 0) {
/* 449 */       return null;
/*     */     }
/*     */     try {
/* 452 */       return new String(result, encoding);
/* 453 */     } catch (UnsupportedEncodingException ex) {
/* 454 */       return null;
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
/*     */   public String encryptStringToHex(String input, String encoding) {
/* 468 */     byte[] bytes = encryptString(input, encoding);
/* 469 */     if (bytes.length == 0) {
/* 470 */       return null;
/*     */     }
/* 472 */     return Hex.encodeHexString(bytes);
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
/*     */   public String decryptHexToString(String hexStr, String encoding) {
/* 485 */     byte[] bytes = decryptHex(hexStr);
/* 486 */     if (bytes.length == 0) {
/* 487 */       return null;
/*     */     }
/* 489 */     if (StringUtils.isBlank(encoding)) {
/* 490 */       encoding = "ISO-8859-1";
/*     */     }
/*     */     try {
/* 493 */       return new String(bytes, encoding);
/* 494 */     } catch (UnsupportedEncodingException ex) {
/* 495 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String encryptToBase64(byte[] data) {
/* 506 */     byte[] bytes = encrypt(data);
/* 507 */     if (bytes.length == 0) {
/* 508 */       return null;
/*     */     }
/* 510 */     return Base64.encodeBase64String(bytes);
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
/*     */   public byte[] decryptBase64(String base64Str) {
/* 523 */     byte[] base64Bytes = Base64.decodeBase64(base64Str);
/* 524 */     if (ArrayUtils.isEmpty(base64Bytes)) {
/* 525 */       return ArrayUtils.EMPTY_BYTE_ARRAY;
/*     */     }
/* 527 */     return decrypt(base64Bytes);
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
/*     */   public String encryptStringToBase64(String input, String encoding) {
/* 541 */     byte[] bytes = encryptString(input, encoding);
/* 542 */     if (bytes.length == 0) {
/* 543 */       return null;
/*     */     }
/* 545 */     return Base64.encodeBase64String(bytes);
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
/*     */ 
/*     */   
/*     */   public String decryptBase64ToString(String base64Str, String encoding) {
/* 568 */     byte[] bytes = decryptBase64(base64Str);
/* 569 */     if (bytes.length == 0) {
/* 570 */       return null;
/*     */     }
/* 572 */     if (StringUtils.isBlank(encoding)) {
/* 573 */       encoding = "ISO-8859-1";
/*     */     }
/*     */     try {
/* 576 */       return new String(bytes, encoding);
/* 577 */     } catch (UnsupportedEncodingException ex) {
/* 578 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeEncryptLogger(Throwable cause) {
/* 583 */     LOGGER.error(String.format("AES decrypt failed. Cause: %s", new Object[] { cause.getMessage() }));
/*     */   }
/*     */   
/*     */   private void writeDecryptLogger(Throwable cause) {
/* 587 */     LOGGER.error(String.format("AES decrypt failed. Cause: %s", new Object[] { cause.getMessage() }));
/*     */   }
/*     */   
/*     */   private byte[] padding(byte[] bytes) {
/* 591 */     int len = bytes.length;
/* 592 */     if (len == 0)
/* 593 */       return bytes; 
/* 594 */     int pad = 0;
/* 595 */     if (len < 16) {
/* 596 */       pad = 16 - len;
/* 597 */       byte[] pads = createSequence((byte)0, pad, (byte)0);
/* 598 */       return ArrayUtils.addAll(bytes, pads);
/*     */     } 
/* 600 */     pad = len % 16;
/* 601 */     if (pad != 0) {
/* 602 */       byte[] pads = createSequence((byte)0, 16 - pad, (byte)0);
/* 603 */       return ArrayUtils.addAll(bytes, pads);
/*     */     } 
/* 605 */     return bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initEncryptionCipher() {
/* 610 */     if (this.enc == null) {
/*     */       try {
/* 612 */         this.enc = Cipher.getInstance(this.mode.getName());
/* 613 */         if (this.ivSpec == null) {
/* 614 */           this.enc.init(1, this.keySpec);
/*     */         } else {
/* 616 */           this.enc.init(1, this.keySpec, this.ivSpec);
/*     */         } 
/* 618 */       } catch (NoSuchAlgorithmException ex) {
/* 619 */         LOGGER.error(String.format("The current platform no such algorithm - %s, Cause: %s", new Object[] { "AES", ex
/* 620 */                 .getMessage() }));
/* 621 */       } catch (NoSuchPaddingException ex) {
/* 622 */         LOGGER.error(String.format("The current platform no such padding - %s, Cause: %s", new Object[] { this.mode
/* 623 */                 .getName(), ex.getMessage() }));
/* 624 */       } catch (InvalidKeyException ex) {
/* 625 */         LOGGER.error(StringUtils.join(new Object[] { "Invalid key - ", (this.keySpec != null) ? this.keySpec
/* 626 */                 .toString() : "encrypt key. ", " Cause: ", ex
/* 627 */                 .getMessage() }));
/*     */       }
/* 629 */       catch (InvalidAlgorithmParameterException ex) {
/* 630 */         LOGGER.error(StringUtils.join(new Object[] { "Invalid algorithm parameter - ", (this.ivSpec != null) ? this.ivSpec
/*     */                 
/* 632 */                 .toString() : "encrypt iv parameter. ", "Cause: ", ex
/* 633 */                 .getMessage() }));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initDecryptionCipher() {
/* 640 */     if (this.dec == null) {
/*     */       try {
/* 642 */         this.dec = Cipher.getInstance(this.mode.getName());
/* 643 */         if (this.ivSpec == null) {
/* 644 */           this.dec.init(2, this.keySpec);
/*     */         } else {
/* 646 */           this.dec.init(2, this.keySpec, this.ivSpec);
/*     */         } 
/* 648 */       } catch (NoSuchAlgorithmException ex) {
/* 649 */         LOGGER.error(String.format("The current platform no such algorithm - %s, Cause: %s", new Object[] { "AES", ex
/* 650 */                 .getMessage() }));
/* 651 */       } catch (NoSuchPaddingException ex) {
/* 652 */         LOGGER.error(String.format("The current platform no such padding - %s, Cause: %s", new Object[] { this.mode
/* 653 */                 .getName(), ex.getMessage() }));
/* 654 */       } catch (InvalidKeyException ex) {
/* 655 */         LOGGER.error(StringUtils.join(new Object[] { "Invalid key - ", (this.keySpec != null) ? this.keySpec
/* 656 */                 .toString() : "decrypt key. ", " Cause: ", ex
/* 657 */                 .getMessage() }));
/*     */       }
/* 659 */       catch (InvalidAlgorithmParameterException ex) {
/* 660 */         LOGGER.error(StringUtils.join(new Object[] { "Invalid algorithm parameter - ", (this.ivSpec != null) ? this.ivSpec
/*     */                 
/* 662 */                 .toString() : "decrypt iv parameter. ", "Cause: ", ex
/* 663 */                 .getMessage() }));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] createSequence(byte start, int length, byte step) {
/* 670 */     byte[] result = new byte[length];
/* 671 */     if (step == 0) {
/* 672 */       for (int j = 0; j < length; j++) {
/* 673 */         result[j] = start;
/*     */       }
/* 675 */       return result;
/*     */     } 
/* 677 */     for (int i = 0; i < length; i++) {
/* 678 */       result[i] = start;
/* 679 */       int next = start + step;
/* 680 */       if (next < -128 || next > 127) {
/*     */         
/* 682 */         if (step > 0) {
/* 683 */           start = Byte.MAX_VALUE;
/* 684 */           step = 0;
/*     */         } else {
/* 686 */           start = Byte.MIN_VALUE;
/* 687 */           step = 0;
/*     */         } 
/*     */       } else {
/* 690 */         start = Integer.valueOf(next).byteValue();
/*     */       } 
/*     */     } 
/* 693 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum AESMode
/*     */   {
/* 702 */     CFB_NO("AES/CFB/NoPadding"),
/*     */ 
/*     */     
/* 705 */     CFB_PKCS5("AES/CFB/PKCS5Padding"),
/*     */ 
/*     */     
/* 708 */     CFB_ISO10126("AES/CFB/ISO10126Padding"),
/*     */ 
/*     */     
/* 711 */     CBC_NO("AES/CBC/NoPadding"),
/*     */ 
/*     */     
/* 714 */     CBC_PKCS5("AES/CBC/PKCS5Padding"),
/*     */ 
/*     */     
/* 717 */     CBC_ISO10126("AES/CBC/ISO10126Padding"),
/*     */ 
/*     */     
/* 720 */     ECB_NO("AES/ECB/NoPadding"),
/*     */ 
/*     */     
/* 723 */     ECB_PKCS5("AES/ECB/PKCS5Padding"),
/*     */ 
/*     */     
/* 726 */     ECB_ISO10126("AES/ECB/ISO10126Padding");
/*     */     
/*     */     final String name;
/*     */     
/*     */     AESMode(String name) {
/* 731 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 736 */       return this.name;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 741 */     String encoding = "UTF-8";
/* 742 */     String plain = "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人";
/* 743 */     byte[] plainBytes = "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人".getBytes("UTF-8");
/* 744 */     System.out.println(String.format("原文字节数组长度：%s", new Object[] { Integer.valueOf(plainBytes.length) }));
/* 745 */     byte[] key = "0123456789ABCDEF".getBytes();
/* 746 */     byte[] iv = "FEDCBA9876543210".getBytes();
/* 747 */     Rijndael ecb_aes = new Rijndael(key);
/* 748 */     System.out
/* 749 */       .println("ECB+++++++++++++++++++++++++++++++++ Base64 ++++++++++++++++++++++++++++++++++++");
/* 750 */     String b64Str = ecb_aes.encryptStringToBase64("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 751 */     String b64Plain = ecb_aes.decryptBase64ToString(b64Str, "UTF-8");
/* 752 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 753 */     System.out.println(String.format("密文：%s", new Object[] { b64Str }));
/* 754 */     System.out.println(String.format("解密：%s", new Object[] { b64Plain }));
/* 755 */     System.out.println("");
/* 756 */     System.out
/* 757 */       .println("ECB+++++++++++++++++++++++++++++++++ Hex(16) +++++++++++++++++++++++++++++++++++");
/* 758 */     String hexStr = ecb_aes.encryptStringToHex("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 759 */     String hexPlain = ecb_aes.decryptHexToString(hexStr, "UTF-8");
/* 760 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 761 */     System.out.println(String.format("密文：%s", new Object[] { hexStr }));
/* 762 */     System.out.println(String.format("解密：%s", new Object[] { hexPlain }));
/* 763 */     System.out.println("");
/* 764 */     Rijndael cbc_aes = new Rijndael(key, iv, AESMode.CBC_NO);
/* 765 */     System.out
/* 766 */       .println("CBC+++++++++++++++++++++++++++++++++ Base64 ++++++++++++++++++++++++++++++++++++");
/* 767 */     String b64Str2 = cbc_aes.encryptStringToBase64("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 768 */     String b64Plain2 = cbc_aes.decryptBase64ToString(b64Str2, "UTF-8");
/* 769 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 770 */     System.out.println(String.format("密文：%s", new Object[] { b64Str2 }));
/* 771 */     System.out.println(String.format("解密：%s", new Object[] { b64Plain2 }));
/* 772 */     System.out.println("");
/* 773 */     System.out
/* 774 */       .println("CBC+++++++++++++++++++++++++++++++++ Hex(16) +++++++++++++++++++++++++++++++++++");
/* 775 */     String hexStr2 = cbc_aes.encryptStringToHex("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 776 */     String hexPlain2 = cbc_aes.decryptHexToString(hexStr2, "UTF-8");
/* 777 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 778 */     System.out.println(String.format("密文：%s", new Object[] { hexStr2 }));
/* 779 */     System.out.println(String.format("解密：%s", new Object[] { hexPlain2 }));
/* 780 */     System.out.println("");
/*     */     
/* 782 */     Rijndael cbc_aes_pkcs5 = new Rijndael(key, iv, AESMode.CBC_PKCS5);
/* 783 */     System.out
/* 784 */       .println("CBC+CBC_PKCS5+++++++++++++++++++++++ Base64 ++++++++++++++++++++++++++++++++++++");
/* 785 */     String b64Str3 = cbc_aes_pkcs5.encryptStringToBase64("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 786 */     String b64Plain3 = cbc_aes_pkcs5.decryptBase64ToString(b64Str3, "UTF-8");
/* 787 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 788 */     System.out.println(String.format("密文：%s", new Object[] { b64Str3 }));
/* 789 */     System.out.println(String.format("解密：%s", new Object[] { b64Plain3 }));
/* 790 */     System.out.println("");
/* 791 */     System.out
/* 792 */       .println("CBC+CBC_PKCS5+++++++++++++++++++++++ Hex(16) +++++++++++++++++++++++++++++++++++");
/* 793 */     String hexStr3 = cbc_aes_pkcs5.encryptStringToHex("`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人", "UTF-8");
/* 794 */     String hexPlain3 = cbc_aes_pkcs5.decryptHexToString(hexStr3, "UTF-8");
/* 795 */     System.out.println(String.format("原文：%s", new Object[] { "`1234567890~!@#$%^&*()_+|{}:\"<>?[];',./'\\\n\r\t中国人" }));
/* 796 */     System.out.println(String.format("密文：%s", new Object[] { hexStr3 }));
/* 797 */     System.out.println(String.format("解密：%s", new Object[] { hexPlain3 }));
/* 798 */     System.out.println("");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\Rijndael.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */