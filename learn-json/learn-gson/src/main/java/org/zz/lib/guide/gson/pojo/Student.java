package org.zz.lib.guide.gson.pojo;

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
