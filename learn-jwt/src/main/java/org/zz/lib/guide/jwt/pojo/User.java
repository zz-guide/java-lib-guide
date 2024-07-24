package org.zz.lib.guide.jwt.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
}
