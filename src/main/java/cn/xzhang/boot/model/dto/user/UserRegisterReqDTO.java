package cn.xzhang.boot.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户注册请求")
public class UserRegisterReqDTO implements Serializable {

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userAccount;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userPassword;

    @Schema(description = "确认密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String checkPassword;

}
