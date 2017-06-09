package me.hao0.luna.common.protocol;

/**
 * The Language enum
 */
public enum Language {

    JAVA((byte) 0),

    CPP((byte) 1),

    DOTNET((byte) 2),

    PYTHON((byte) 3),

    DELPHI((byte) 4),

    ERLANG((byte) 5),

    RUBY((byte) 6),

    GOLANG((byte) 7),

    HTTP((byte) 8),

    OTHER((byte) 255);

    private byte value;

    Language(byte value) {
        this.value = value;
    }

    public static Language from(byte code) {

        for (Language language : Language.values()) {
            if (language.value == code) {
                return language;
            }
        }

        throw new IllegalArgumentException("Not support language: " + code);
    }

    public byte getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Language{" +
                "value=" + value +
                '}';
    }
}
