package me.hao0.luna.common.protocol;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The abstract command
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public abstract class AbstractCommand<T> implements Command<T> {

    /**
     * The request id generator
     */
    private static AtomicInteger requestId = new AtomicInteger(0);

    /**
     * The request id，corresponding to the response
     */
    private int opaque = requestId.getAndIncrement();

    /**
     * The command type code
     */
    private int code;

    /**
     * The language type
     */
    private byte language;

    /**
     * The version
     */
    private int version = 0;

    /**
     * The flag
     */
    private int flag = 0;

    /**
     * The remark
     */
    private String remark;

    /**
     * The serialize type
     */
    private byte serializeType;

    /**
     * The extend fields
     */
    private Map<String, String> extFields;

    /**
     * The body data
     */
    private byte[] body;

    public int getOpaque() {
        return opaque;
    }

    public void setOpaque(int opaque) {
        this.opaque = opaque;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte getSerializeType() {
        return serializeType;
    }

    public void setSerializeType(byte serializeType) {
        this.serializeType = serializeType;
    }

    public Map<String, String> getExtFields() {
        return extFields;
    }

    public void setExtFields(Map<String, String> extFields) {
        this.extFields = extFields;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "opaque=" + opaque +
                ", code=" + code +
                ", language=" + language +
                ", version=" + version +
                ", flag=" + flag +
                ", remark='" + remark + '\'' +
                ", serializeType=" + serializeType +
                ", extFields=" + extFields +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
