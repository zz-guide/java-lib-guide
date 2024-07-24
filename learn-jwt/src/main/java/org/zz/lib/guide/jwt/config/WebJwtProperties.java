package org.zz.lib.guide.jwt.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Long effectiveTimeSeconds = 0L;

    /**
     * JWT签发者
     */
    private String issuer = "https://www.zz.org";

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;
}
