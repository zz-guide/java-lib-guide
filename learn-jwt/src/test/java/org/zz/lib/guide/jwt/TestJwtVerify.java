package org.zz.lib.guide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jwt.config.WebJwtProperties;
import org.zz.lib.guide.jwt.util.JwtUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 一个token是不是有效的，取决于 iat,exp,nbf 这三个参数，issuer等，还有算法以及自定义的一些规则等
 *
 */

public class TestJwtVerify {
    public static final Logger logger = Logger.getLogger("TestJwtVerify");

    @Test
    public void testIat(){
        // 签发时间 IssuedAt, 简称iat, 必须小于当前时间
        // The token was issued in a past date "iat" < NOW
        WebJwtProperties webJwtProperties = new WebJwtProperties();
        // 失败
        LocalDateTime futureLocalDateTime = LocalDateTime.parse("2025-12-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 正常
        LocalDateTime pastLocalDateTime = LocalDateTime.parse("2023-12-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Instant issuedAt = futureLocalDateTime.toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(60).toInstant(JwtUtil.ZONE_OFFSET);
        Algorithm algorithm = Algorithm.none();
        String issuer = webJwtProperties.getIssuer();

        String jwtToken = JWT.create()
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        JWTVerifier build = JWT.require(algorithm).build();

        Assertions.assertThrows(IncorrectClaimException.class, () -> {
            System.out.println("当 iat是将来时间的时候，直接报错，校验失败");
            DecodedJWT decodedJWT = build.verify(jwtToken);
        });
    }

    @Test
    public void testExp(){
        // 过期时间 ExpireAt, 简称exp, 必须大于当前时间
        // The token hasn't expired yet "exp" > NOW
        WebJwtProperties webJwtProperties = new WebJwtProperties();

        Algorithm algorithm = Algorithm.none();
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(-60).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        JWTVerifier build = JWT.require(algorithm).build();

        Assertions.assertThrows(TokenExpiredException.class, () -> {
            System.out.println("当 exp 是过去时间，抛异常，校验失败");
            DecodedJWT decodedJWT = build.verify(jwtToken);
        });
    }

    @Test
    public void testNbf() throws InterruptedException {
        // 生效时间 Not Before, 简称nbf, 必须小于当前时间
        // The token can already be used. "nbf" < NOW
        WebJwtProperties webJwtProperties = new WebJwtProperties();

        Algorithm algorithm = Algorithm.none();
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(60).toInstant(JwtUtil.ZONE_OFFSET);
        Instant notBeforeAt = LocalDateTime.now().plusSeconds(2).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withNotBefore(notBeforeAt) // 生效时间
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        Thread.sleep(3000);
        JWTVerifier build = JWT.require(algorithm).build();

        Assertions.assertThrows(IncorrectClaimException.class, () -> {
            System.out.println("当 nbf 是将来时间，抛异常，校验失败");
            DecodedJWT decodedJWT = build.verify(jwtToken);
        });
    }

    @Test
    public void testIatAndNbf() {
        WebJwtProperties webJwtProperties = new WebJwtProperties();

        Algorithm algorithm = Algorithm.none();
        String issuer = webJwtProperties.getIssuer();
        Instant expireAt = LocalDateTime.now().plusSeconds(60).toInstant(JwtUtil.ZONE_OFFSET);

        // 手动构造一个 签发时间在生效时间后的数据，理论上来讲应该先签发后生效，签发 < 生效才对。
        Instant issuedAt = LocalDateTime.parse("2024-07-25 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(JwtUtil.ZONE_OFFSET);;
        Instant notBeforeAt = LocalDateTime.parse("2024-07-25 15:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withNotBefore(notBeforeAt) // 生效时间
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        // JWTVerifier 对象中的withxxx等方法都是额外的校验条件，比如issuer，claim等，添加了的话必须匹配才行
        // JWTVerifier build = JWT.require(algorithm).withClaim("xx", 123).build();

        JWTVerifier build = JWT.require(algorithm).build();

        // 经过测试发现 iat, nbf 之间谁大谁小没关系， 只要都小于当前时间就行
        DecodedJWT decodedJWT = build.verify(jwtToken);
    }
}
