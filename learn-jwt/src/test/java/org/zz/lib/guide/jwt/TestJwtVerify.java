package org.zz.lib.guide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jwt.config.WebJwtProperties;
import org.zz.lib.guide.jwt.util.JwtUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */

public class TestJwtVerify {
    public static final Logger logger = Logger.getLogger("TestJwtVerify");

    @Test
    public void testWithPayload(){
        Algorithm algorithm = Algorithm.none();

        Map<String, String> payload = new HashMap<>();
        payload.put("id", "123");
        payload.put("name", "仔仔");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now()
                .plusSeconds(webJwtProperties.getEffectiveTimeSeconds())
                .toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withPayload(payload) // payload
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        // 测试 jwt 过期问题
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(jwtToken);



        String claimId = decodedJWT.getClaim("id").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim id: {0}", new Object[]{claimId});

        String claimName = decodedJWT.getClaim("name").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim name: {0}", new Object[]{claimName});
    }
}
