/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import org.msgpack.MessagePack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ProtocolUtil
/*     */ {
/*     */   private static final int BYTE_MAX = 255;
/*     */   private static final int SHORT_MAX = 65535;
/*     */   private static final byte SIZE_TYPE_BYTE = 0;
/*     */   private static final byte SIZE_TYPE_SHORT = 1;
/*     */   
/*     */   public static int readSize(ByteBuf bytes) {
/*  17 */     int size = bytes.readShort();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  24 */     return size;
/*     */   }
/*     */   
/*     */   public static void writeSize(int size, ByteBuf bytes) {
/*  28 */     bytes.writeShort(size);
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
/*     */   public static String readUTF(ByteBuf in) {
/*  46 */     int utflen = in.readUnsignedByte();
/*  47 */     if (utflen < 0)
/*  48 */       throw new IndexOutOfBoundsException(); 
/*  49 */     if (utflen >= 255) {
/*  50 */       utflen = in.readUnsignedShort();
/*     */     }
/*  52 */     byte[] bytearr = new byte[utflen];
/*  53 */     in.readBytes(bytearr);
/*     */     try {
/*  55 */       return new String(bytearr, "utf-8");
/*  56 */     } catch (Exception exception) {
/*     */ 
/*     */       
/*  59 */       return "";
/*     */     } 
/*     */   }
/*     */   public static String readUTFStr(ByteBuf in) {
/*  63 */     int utflen = readStrArraySize(in);
/*  64 */     if (utflen < 0)
/*  65 */       throw new IndexOutOfBoundsException(); 
/*  66 */     if (utflen >= 255);
/*     */ 
/*     */     
/*  69 */     byte[] bytearr = new byte[utflen];
/*  70 */     in.readBytes(bytearr);
/*     */     try {
/*  72 */       return new String(bytearr, "utf-8");
/*  73 */     } catch (Exception exception) {
/*     */ 
/*     */       
/*  76 */       return "";
/*     */     } 
/*     */   }
/*     */   public static void writeUTF(String str, ByteBuf out) {
/*  80 */     if (null == str)
/*  81 */       str = ""; 
/*  82 */     byte[] bytearr = null;
/*     */     try {
/*  84 */       bytearr = str.getBytes("utf-8");
/*  85 */     } catch (Exception exception) {}
/*     */     
/*  87 */     if (bytearr != null) {
/*  88 */       int utflen = bytearr.length;
/*  89 */       if (utflen >= 255) {
/*  90 */         out.writeByte(255);
/*     */       } else {
/*     */         
/*  93 */         out.writeByte(utflen);
/*     */       } 
/*  95 */       out.writeBytes(bytearr);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeUTFBinary(ByteBuf out, String str) {
/* 100 */     if (null == str) str = ""; 
/* 101 */     byte[] bytearr = null;
/*     */     try {
/* 103 */       bytearr = str.getBytes("utf-8");
/* 104 */     } catch (Exception exception) {}
/*     */     
/* 106 */     if (bytearr != null) {
/* 107 */       writeStrArraySize(out, bytearr.length);
/* 108 */       out.writeBytes(bytearr);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String readUTFCompress(ByteBuf in) {
/* 113 */     int utflen = in.readUnsignedByte();
/* 114 */     if (utflen < 0)
/* 115 */       throw new IndexOutOfBoundsException(); 
/* 116 */     if (utflen >= 255) {
/* 117 */       utflen = in.readUnsignedShort();
/*     */     }
/* 119 */     byte[] bytearr = new byte[utflen];
/* 120 */     in.readBytes(bytearr);
/*     */     try {
/* 122 */       return CompressUtil.decompress(bytearr);
/* 123 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 126 */       return "";
/*     */     } 
/*     */   }
/*     */   public static Object readUTFJSON(MessagePack pack, ByteBuf in, Class clazz) {
/* 130 */     int utflen = in.readUnsignedByte();
/* 131 */     if (utflen < 0)
/* 132 */       throw new IndexOutOfBoundsException(); 
/* 133 */     if (utflen >= 255) {
/* 134 */       utflen = in.readUnsignedShort();
/*     */     }
/* 136 */     byte[] bytearr = new byte[utflen];
/* 137 */     in.readBytes(bytearr);
/*     */     try {
/* 139 */       return pack.read(bytearr, clazz);
/* 140 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 143 */       return "";
/*     */     } 
/*     */   }
/*     */   public static void writeUTFJSON(byte[] bytearr, ByteBuf out) {
/* 147 */     if (null == bytearr || bytearr.length <= 0)
/* 148 */       return;  int utflen = bytearr.length;
/* 149 */     if (utflen >= 255) {
/* 150 */       out.writeByte(255);
/* 151 */       out.writeShort(utflen);
/*     */     } else {
/* 153 */       out.writeByte(utflen);
/*     */     } 
/* 155 */     out.writeBytes(bytearr);
/*     */   }
/*     */   
/*     */   public static byte readUTFBinByte(ByteBuf in) {
/* 159 */     byte type = in.readByte();
/* 160 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 161 */     if (type == 122) return in.readByte(); 
/* 162 */     return in.readByte();
/*     */   }
/*     */   
/*     */   public static short readUTFBinShort(ByteBuf in) {
/* 166 */     byte type = in.readByte();
/* 167 */     if (type < 122 && type > Byte.MIN_VALUE) return (short)type; 
/* 168 */     if (type == 122) return (short)in.readByte(); 
/* 169 */     return in.readShort();
/*     */   }
/*     */   
/*     */   public static int readUTFBinInt(ByteBuf in) {
/* 173 */     byte type = in.readByte();
/* 174 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 175 */     if (type == 122) return in.readByte(); 
/* 176 */     if (type == 123) return in.readShort(); 
/* 177 */     return in.readInt();
/*     */   }
/*     */   
/*     */   public static long readUTFBinLong(ByteBuf in) {
/* 181 */     byte type = in.readByte();
/* 182 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 183 */     if (type == 122) return in.readByte(); 
/* 184 */     if (type == 123) return in.readShort(); 
/* 185 */     if (type == 124) return in.readInt(); 
/* 186 */     return in.readLong();
/*     */   }
/*     */   
/*     */   public static float readUTFBinFloat(ByteBuf in) {
/* 190 */     byte type = in.readByte();
/* 191 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 192 */     if (type == 122) return in.readByte(); 
/* 193 */     if (type == 123) return in.readShort(); 
/* 194 */     if (type == 124) return in.readInt(); 
/* 195 */     if (type == 125) return (float)in.readLong(); 
/* 196 */     return in.readFloat();
/*     */   }
/*     */   
/*     */   public static double readUTFBinDouble(ByteBuf in) {
/* 200 */     byte type = in.readByte();
/* 201 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 202 */     if (type == 122) return in.readByte(); 
/* 203 */     if (type == 123) return in.readShort(); 
/* 204 */     if (type == 124) return in.readInt(); 
/* 205 */     if (type == 125) return in.readLong(); 
/* 206 */     if (type == 126) return Double.parseDouble(String.valueOf(in.readFloat())); 
/* 207 */     return in.readDouble();
/*     */   }
/*     */   
/*     */   public static void writeByte(ByteBuf out, byte val) {
/* 211 */     if (val < 122 && val > Byte.MIN_VALUE) {
/* 212 */       out.writeByte(val);
/*     */     } else {
/* 214 */       out.writeByte(122);
/* 215 */       out.writeByte(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeShort(ByteBuf out, short val) {
/* 220 */     if (val < 122 && val > -128) {
/* 221 */       out.writeByte((byte)val);
/* 222 */     } else if (val < 128 && val > 122) {
/* 223 */       out.writeByte(122);
/* 224 */       out.writeByte((byte)val);
/*     */     } else {
/* 226 */       out.writeByte(123);
/* 227 */       out.writeShort(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeInt(ByteBuf out, int val) {
/* 232 */     if (val < 122 && val > -128) {
/* 233 */       out.writeByte((byte)val);
/* 234 */     } else if (val < 128 && val > 122) {
/* 235 */       out.writeByte(122);
/* 236 */       out.writeByte((byte)val);
/* 237 */     } else if (val < 32767 && val > -32768) {
/* 238 */       out.writeByte(123);
/* 239 */       out.writeShort((short)val);
/*     */     } else {
/* 241 */       out.writeByte(124);
/* 242 */       out.writeInt(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeLong(ByteBuf out, long val) {
/* 247 */     if (val < 122L && val > -128L) {
/* 248 */       out.writeByte((byte)(int)val);
/* 249 */     } else if (val < 128L && val > 122L) {
/* 250 */       out.writeByte(122);
/* 251 */       out.writeByte((byte)(int)val);
/* 252 */     } else if (val < 32767L && val > -32768L) {
/* 253 */       out.writeByte(123);
/* 254 */       out.writeShort((short)(int)val);
/* 255 */     } else if (val < 2147483647L && val > -2147483648L) {
/* 256 */       out.writeByte(124);
/* 257 */       out.writeInt((int)val);
/*     */     } else {
/* 259 */       out.writeByte(125);
/* 260 */       out.writeLong(val);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeFloat(ByteBuf out, float val) {
/* 266 */     out.writeByte(126);
/* 267 */     out.writeFloat(val);
/*     */   }
/*     */   
/*     */   public static void writeDouble(ByteBuf out, double val) {
/* 271 */     out.writeByte(127);
/* 272 */     out.writeDouble(val);
/*     */   }
/*     */   
/*     */   public static int readStrArraySize(ByteBuf in) {
/* 276 */     byte type = in.readByte();
/* 277 */     if (type < 122) return type; 
/* 278 */     if (type == 122) return in.readByte(); 
/* 279 */     if (type == 123) return in.readShort(); 
/* 280 */     if (type == 124) return in.readInt(); 
/* 281 */     if (type == 125) return (int)in.readLong(); 
/* 282 */     if (type == 126) return (int)in.readFloat(); 
/* 283 */     if (type == Byte.MAX_VALUE) return (int)in.readDouble(); 
/* 284 */     return 0;
/*     */   }
/*     */   
/*     */   public static void writeStrArraySize(ByteBuf out, int val) {
/* 288 */     if (val < 122) {
/* 289 */       out.writeByte((byte)val);
/* 290 */     } else if (val < 128) {
/* 291 */       out.writeByte(122);
/* 292 */       out.writeByte((byte)val);
/* 293 */     } else if (val < 32767) {
/* 294 */       out.writeByte(123);
/* 295 */       out.writeShort((short)val);
/* 296 */     } else if (val < Integer.MAX_VALUE) {
/* 297 */       out.writeByte(124);
/* 298 */       out.writeInt(val);
/*     */     } else {
/* 300 */       out.writeByte(125);
/* 301 */       out.writeLong(val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\cor\\utils\ProtocolUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */