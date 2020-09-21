/*      */ package com.linlongyx.sanguo.webgame.proto.protobuf;
/*      */ 
/*      */ import com.google.protobuf.AbstractMessage;
/*      */ import com.google.protobuf.AbstractMessageLite;
/*      */ import com.google.protobuf.CodedInputStream;
/*      */ import com.google.protobuf.Descriptors;
/*      */ import com.google.protobuf.ExtensionRegistryLite;
/*      */ import com.google.protobuf.GeneratedMessage;
/*      */ import com.google.protobuf.InvalidProtocolBufferException;
/*      */ import com.google.protobuf.Message;
/*      */ import com.google.protobuf.MessageLite;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Builder
/*      */   extends GeneratedMessage.ExtendableBuilder<Message.ResponseBase, Message.ResponseBase.Builder>
/*      */   implements Message.ResponseBaseOrBuilder
/*      */ {
/*      */   private int bitField0_;
/*      */   private int cmdId_;
/*      */   private int errorCode_;
/*      */   
/*      */   public static final Descriptors.Descriptor getDescriptor() {
/*  809 */     return Message.access$900();
/*      */   }
/*      */ 
/*      */   
/*      */   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  814 */     return Message.access$1000()
/*  815 */       .ensureFieldAccessorsInitialized(Message.ResponseBase.class, Builder.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Builder() {
/*  821 */     maybeForceBuilderInitialization();
/*      */   }
/*      */ 
/*      */   
/*      */   private Builder(GeneratedMessage.BuilderParent parent) {
/*  826 */     super(parent);
/*  827 */     maybeForceBuilderInitialization();
/*      */   }
/*      */   
/*      */   private void maybeForceBuilderInitialization() {
/*  831 */     if (Message.ResponseBase.access$1400());
/*      */   }
/*      */ 
/*      */   
/*      */   private static Builder create() {
/*  836 */     return new Builder();
/*      */   }
/*      */   
/*      */   public Builder clear() {
/*  840 */     super.clear();
/*  841 */     this.cmdId_ = 0;
/*  842 */     this.bitField0_ &= 0xFFFFFFFE;
/*  843 */     this.errorCode_ = 0;
/*  844 */     this.bitField0_ &= 0xFFFFFFFD;
/*  845 */     return this;
/*      */   }
/*      */   
/*      */   public Builder clone() {
/*  849 */     return create().mergeFrom(buildPartial());
/*      */   }
/*      */ 
/*      */   
/*      */   public Descriptors.Descriptor getDescriptorForType() {
/*  854 */     return Message.access$900();
/*      */   }
/*      */   
/*      */   public Message.ResponseBase getDefaultInstanceForType() {
/*  858 */     return Message.ResponseBase.getDefaultInstance();
/*      */   }
/*      */   
/*      */   public Message.ResponseBase build() {
/*  862 */     Message.ResponseBase result = buildPartial();
/*  863 */     if (!result.isInitialized()) {
/*  864 */       throw newUninitializedMessageException(result);
/*      */     }
/*  866 */     return result;
/*      */   }
/*      */   
/*      */   public Message.ResponseBase buildPartial() {
/*  870 */     Message.ResponseBase result = new Message.ResponseBase(this, null);
/*  871 */     int from_bitField0_ = this.bitField0_;
/*  872 */     int to_bitField0_ = 0;
/*  873 */     if ((from_bitField0_ & 0x1) == 1) {
/*  874 */       to_bitField0_ |= 0x1;
/*      */     }
/*  876 */     Message.ResponseBase.access$1602(result, this.cmdId_);
/*  877 */     if ((from_bitField0_ & 0x2) == 2) {
/*  878 */       to_bitField0_ |= 0x2;
/*      */     }
/*  880 */     Message.ResponseBase.access$1702(result, this.errorCode_);
/*  881 */     Message.ResponseBase.access$1802(result, to_bitField0_);
/*  882 */     onBuilt();
/*  883 */     return result;
/*      */   }
/*      */   
/*      */   public Builder mergeFrom(Message other) {
/*  887 */     if (other instanceof Message.ResponseBase) {
/*  888 */       return mergeFrom((Message.ResponseBase)other);
/*      */     }
/*  890 */     super.mergeFrom(other);
/*  891 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Builder mergeFrom(Message.ResponseBase other) {
/*  896 */     if (other == Message.ResponseBase.getDefaultInstance()) return this; 
/*  897 */     if (other.hasCmdId()) {
/*  898 */       setCmdId(other.getCmdId());
/*      */     }
/*  900 */     if (other.hasErrorCode()) {
/*  901 */       setErrorCode(other.getErrorCode());
/*      */     }
/*  903 */     mergeExtensionFields(other);
/*  904 */     mergeUnknownFields(other.getUnknownFields());
/*  905 */     return this;
/*      */   }
/*      */   
/*      */   public final boolean isInitialized() {
/*  909 */     if (!hasCmdId())
/*      */     {
/*  911 */       return false;
/*      */     }
/*  913 */     if (!hasErrorCode())
/*      */     {
/*  915 */       return false;
/*      */     }
/*  917 */     if (!extensionsAreInitialized())
/*      */     {
/*  919 */       return false;
/*      */     }
/*  921 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  928 */     Message.ResponseBase parsedMessage = null;
/*      */     try {
/*  930 */       parsedMessage = (Message.ResponseBase)Message.ResponseBase.PARSER.parsePartialFrom(input, extensionRegistry);
/*  931 */     } catch (InvalidProtocolBufferException e) {
/*  932 */       parsedMessage = (Message.ResponseBase)e.getUnfinishedMessage();
/*  933 */       throw e;
/*      */     } finally {
/*  935 */       if (parsedMessage != null) {
/*  936 */         mergeFrom(parsedMessage);
/*      */       }
/*      */     } 
/*  939 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCmdId() {
/*  951 */     return ((this.bitField0_ & 0x1) == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdId() {
/*  958 */     return this.cmdId_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Builder setCmdId(int value) {
/*  965 */     this.bitField0_ |= 0x1;
/*  966 */     this.cmdId_ = value;
/*  967 */     onChanged();
/*  968 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Builder clearCmdId() {
/*  975 */     this.bitField0_ &= 0xFFFFFFFE;
/*  976 */     this.cmdId_ = 0;
/*  977 */     onChanged();
/*  978 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasErrorCode() {
/*  988 */     return ((this.bitField0_ & 0x2) == 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getErrorCode() {
/*  995 */     return this.errorCode_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Builder setErrorCode(int value) {
/* 1002 */     this.bitField0_ |= 0x2;
/* 1003 */     this.errorCode_ = value;
/* 1004 */     onChanged();
/* 1005 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Builder clearErrorCode() {
/* 1012 */     this.bitField0_ &= 0xFFFFFFFD;
/* 1013 */     this.errorCode_ = 0;
/* 1014 */     onChanged();
/* 1015 */     return this;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\protobuf\Message$ResponseBase$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */