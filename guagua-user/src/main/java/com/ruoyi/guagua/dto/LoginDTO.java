package com.ruoyi.guagua.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data

public class LoginDTO {

    private String username;

    /** 加密后的密码 */
    private String password;

    private String code;

    private String uuid;

}
