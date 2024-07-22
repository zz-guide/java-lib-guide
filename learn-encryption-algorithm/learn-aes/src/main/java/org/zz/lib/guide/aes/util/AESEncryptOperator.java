package org.zz.lib.guide.aes.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

import lombok.SneakyThrows;
import org.zz.lib.guide.utils.util.StringUtil;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESEncryptOperator {
    private AESOperator aesOperator;

    public void setAesOperator(AESOperator aesOperator) {
        if (aesOperator == null) {
            throw new InvalidParameterException("aesOperator 不存在");
        }

        this.aesOperator = aesOperator;
    }

    public AESEncryptOperator(AESOperator aesOperator) {
        setAesOperator(aesOperator);
    }

    public String encrypt(final String src) {
        return encrypt(StringUtil.getUTF8Bytes(src));
    }

    public String encrypt(final byte[] src) {
        byte[] encrypted = encryptRaw(src);
        return new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public byte[] encryptRaw(final byte[] src) {
        aesOperator.checkSrc(src);
        Cipher cipher = aesOperator.getCipher(Cipher.ENCRYPT_MODE);
        return cipher.doFinal(src);
    }
}
