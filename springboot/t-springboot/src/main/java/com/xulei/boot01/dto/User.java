package com.xulei.boot01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @NotNull(message = "ID不能为空")
    private String id;

    @Size(min = 4, max = 12, message = "用户名的长度在4~12之间!")
    private String username;

    @Email(message = "非法邮箱")
    private String email;

    @NotBlank(message = "密码不能为空!")
    private String password;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Min(value = 1, message = "年龄最小为1岁")
    @Max(value = 120, message = "年龄最大为120岁")
    private Integer age;
}
