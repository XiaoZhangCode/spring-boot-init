package cn.xzhang.boot.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 用户登录
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "用户登录")
public class UserLoginReqDTO {

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userAccount;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userPassword;

}
