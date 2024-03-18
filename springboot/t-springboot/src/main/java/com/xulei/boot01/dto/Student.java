package com.xulei.boot01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @NotNull(message = "ID不能为空")
    private String id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 12, message = "用户名的长度在4~12之间!")
    private String username;

    @Valid
    private Person person;
}
