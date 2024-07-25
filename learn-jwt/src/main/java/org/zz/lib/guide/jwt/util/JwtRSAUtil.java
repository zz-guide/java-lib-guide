package org.zz.lib.guide.jwt.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JwtRSAUtil {
    public static final String PUBLIC_KEY = "PUBLIC KEY";

    public static final String PRIVATE_KEY = "PRIVATE KEY";

    public static final String RSA_PRIVATE_KEY = "RSA PRIVATE KEY";

    public static final String CERTIFICATE = "CERTIFICATE";

    public static final Pattern PEM_DATA = Pattern.compile("-----BEGIN (.*)-----(.*)-----END (.*)-----", Pattern.DOTALL);

    public static final String ALGORITHM = "RSA";

    public static final String SHA_256 = "SHA-256";

    public static final String SHA_1 = "SHA-1";

    public static final String MGF1 = "MGF1";

    public static final String CERTIFICATE_TYPE_X509 = "X509";

    public static final String PKCS12 = "PKCS12";

    public static final String DEFAULT_ALGORITHM_WITH_MODE_PADDING
            = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    public static final String SIGN_ALGORITHM_SHA256_WITH_RSA = "SHA256withRSA";

    public static byte[] utf8Encode(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] validateKeyAndDecodeContent(String fileContent, String type) {
        Matcher m = PEM_DATA.matcher(fileContent.trim());
        if (!m.matches()) {
            throw new IllegalArgumentException("秘钥格式不正确");
        }
        String title = m.group(1);
        System.out.println("title:"+title);

        if (!title.equals(type)) {
            throw new IllegalArgumentException(title + " 不是正确的秘钥格式");
        }

        // Base64.getMimeDecoder() 可以处理换行符
        // Base64.getDecoder() 适用于没有换行符的内容, 因此要提前处理换行符
        return Base64.getMimeDecoder().decode(utf8Encode(m.group(2)));
    }
}
