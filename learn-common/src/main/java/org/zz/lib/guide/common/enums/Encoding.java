package org.zz.lib.guide.common.enums;

import lombok.Getter;

@Getter
public enum Encoding {
    UTF_8("UTF-8"),
    GBK("GBK"),
    ISO_8859_1("ISO-8859-1"),;
    private final String encoding;

    Encoding(String encoding) {
        this.encoding = encoding;
    }
}
