package org.zz.lib.guide.jwt.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class WebJwtProperties {
    /**
     * 公钥路径
     */
    private String publicKeyPath;

    /**
     * 私钥路径
     */
    private String privateKeyPath;

    /**
     * 有效时间
     */
    private Long effectiveTimeSeconds = 3600L;

    /**
     * JWT签发者
     */
    private String issuer = "https://www.my-website.com";

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        publicKey = (RSAPublicKey) RSAUtils.loadRSAX509PublicKey(publicKeyPath);
//        privateKey = (RSAPrivateKey) RSAUtils.loadRSAPKCS8PrivateKey(privateKeyPath);
//    }
}
