package org.zz.lib.guide.fastjson2.pojo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Student {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
