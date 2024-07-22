package org.zz.lib.guide.aes.util;

import lombok.SneakyThrows;
import org.zz.lib.guide.utils.util.StringUtil;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Base64;

public class AESDecryptOperator {
    private AESOperator aesOperator;

    public void setAesOperator(AESOperator aesOperator) {
        if (aesOperator == null) {
            throw new InvalidParameterException("aesOperator 不存在");
        }

        this.aesOperator = aesOperator;
    }

    public AESDecryptOperator(AESOperator aesOperator) {
        setAesOperator(aesOperator);
    }

    public String decrypt(final String src) {
        return new String(decryptAsBytes(StringUtil.getUTF8Bytes(src)), StandardCharsets.UTF_8);
    }

    public byte[] decryptAsBytes(final byte[] src) {
        aesOperator.checkSrc(src);
        // 先用base64解密
        byte[] decrypted = Base64.getDecoder().decode(src);
        return decryptRaw(decrypted);
    }

    @SneakyThrows
    public byte[] decryptRaw(final byte[] src) {
        aesOperator.checkSrc(src);
        Cipher cipher = aesOperator.getCipher(Cipher.DECRYPT_MODE);
        //不进行base64解码
        return cipher.doFinal(src);
    }
}
