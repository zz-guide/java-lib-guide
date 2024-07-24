package org.zz.lib.guide.jackson.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    private Long id;
    @JsonProperty(value = "name_alias")
    private String name;
    private Integer age;
    private Boolean isSenior;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonIgnore
    private String d;
}