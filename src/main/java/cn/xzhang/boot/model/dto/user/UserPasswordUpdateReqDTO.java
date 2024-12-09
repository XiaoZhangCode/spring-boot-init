package cn.xzhang.boot.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author code_zhang
 * @Date 2024/12/10 1:24
 */
@Data
@Schema(description = "用户修改密码请求")
public class UserPasswordUpdateReqDTO {

    @Schema(description = "旧密码")
    private String oldPassword;

    @Schema(description = "新密码")
    private String newPassword;

    @Schema(description = "确认密码")
    private String checkPassword;


}
