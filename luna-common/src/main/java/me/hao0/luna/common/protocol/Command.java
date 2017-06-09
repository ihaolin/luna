package me.hao0.luna.common.protocol;

import java.util.Map;

/**
 * The client-server communication protocol command
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public interface Command<T> {

    int getOpaque();

    void setOpaque(int opaque);

    int getCode();

    void setCode(int code);

    byte getLanguage();

    void setLanguage(byte language);

    int getVersion();

    void setVersion(int version);

    int getFlag();

    void setFlag(int flag);

    String getRemark();

    void setRemark(String remark);

    byte getSerializeType();

    void setSerializeType(byte serializeType);

    Map<String, String> getExtFields();

    void setExtFields(Map<String, String> extFields);

    byte[] getBody();

    void setBody(byte[] body);

    /**
     * Encode the command object to bytes data
     * @return the byte array
     */
    byte[] encode();

    /**
     * Decode the bytes data to the command object
     * @param data the bytes data
     * @return the command object
     */
    T decode(byte[] data);
}
