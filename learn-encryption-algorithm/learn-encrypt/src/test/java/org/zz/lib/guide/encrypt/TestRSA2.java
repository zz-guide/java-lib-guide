package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;

/**
 * MD2withRSA
 * MD5withRSA
 * SHA1withRSA
 * SHA224withRSA
 * SHA256withRSA
 * SHA384withRSA
 * SHA512withRSA
 *
 * RIPEMD128withRSA
 * RIPEMD160withRSA
 */

public class TestRSA2 {
    public static final Logger logger = Logger.getLogger("TestRSA2");
    static final String T_PLAINTEXT = "xiaobai security dsa";
    static final String RSA_ALGORITHM = "RSA";
    static final String SIGNATURE_ALGORITHM = "MD5withRSA"; // 自行查看源码看支持哪些算法

    @Test
    public void testECDSA() throws Exception {
        byte[] plaintextBytes = T_PLAINTEXT.getBytes(StandardCharsets.UTF_8);
        int keyLength = 512;

        /* 一、初始化密钥 */
        // 1.获得RSA的KeyPairGenerator对象示例
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        // 2.修改key的长度
        keyPairGenerator.initialize(keyLength);
        // 3.得到KeyPair对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 4.获取公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        // 5.获取私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        /* 二、执行签名 */
        // 1.通过私钥进行签名，获取PKCS8EncodedKeySpec对象示例
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        // 2.通过RSA算法构建KeyFactory实例对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        // 3.声明privateKey
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"MD5withRSA"
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.执行签名的初始化方法
        signature.initSign(privateKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.执行签名
        byte[] result = signature.sign();

        System.out.println("jdk rsa sign:"+ Hex.encodeHexString(result));

        /* 三、验证签名 */
        // 1.通过公钥进行签名，获取X509EncodedKeySpec对象示例
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        // 2.通过RSA算法构建KeyFactory实例对象  (此处是为了完整性，所以新创建了一个对象)
        keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        // 3.获得PublicKey对象
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"MD5withRSA"
        signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.验证签名的初始化方法
        signature.initVerify(publicKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.验证签名
        boolean resultBool = signature.verify(result);
        System.out.println("jdk rsa verify:" + resultBool);
    }
}
