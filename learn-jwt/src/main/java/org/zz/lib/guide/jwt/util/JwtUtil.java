package org.zz.lib.guide.jwt.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class JwtUtil {
    public static final ZoneOffset ZONE_OFFSET = ZoneId.systemDefault().getRules().getOffset(Instant.now());
}
