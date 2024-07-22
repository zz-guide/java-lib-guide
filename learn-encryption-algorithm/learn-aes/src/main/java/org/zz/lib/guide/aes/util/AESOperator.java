package org.zz.lib.guide.aes.util;

import lombok.Getter;
import lombok.SneakyThrows;
import org.zz.lib.guide.utils.util.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Getter
public class AESOperator {
    private final static String ALGORITHM = "AES";

    private final byte[] key;
    private final Integer keyLength;
    private final byte[] ivParameter;
    private String transformation;

    public AESOperator(byte[] key, Integer keyLength, byte[] iv) {
        this.keyLength = keyLength;

        checkKey(key);
        this.key = key;

        checkIv(iv);
        this.ivParameter = iv;
    }

    public AESOperator(String transformation, String key, Integer keyLength, String iv) {
        this(StringUtil.getBytes(key), keyLength, StringUtil.getBytes(iv));

        checkTransformation(transformation);
        this.transformation = transformation;
    }

    public void checkTransformation(String transformation) {
        if (transformation == null || transformation.isBlank()) {
            throw new IllegalArgumentException("transformation cannot be empty.");
        }
    }

    public void checkIv(byte[] iv) {
        if (iv == null || iv.length != this.keyLength) {
            throw new IllegalArgumentException(String.format("iv length must be %d.", this.keyLength));
        }
    }

    public void checkKey(byte[] key) {
        if (key == null || key.length != this.keyLength) {
            throw new IllegalArgumentException(String.format("key length must be %d.", this.keyLength));
        }
    }

    public void checkSrc(byte[] src) {
        if (src == null) {
            throw new IllegalArgumentException("source to be encrypted or decrypted can not be null.");
        }
    }

    @SneakyThrows
    public Cipher getCipher(final int mode) {
        Cipher cipher = Cipher.getInstance(this.transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.key, ALGORITHM);
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec ivParameterSpec = new IvParameterSpec(this.ivParameter);
        cipher.init(mode, secretKeySpec, ivParameterSpec);
        return cipher;
    }
}
