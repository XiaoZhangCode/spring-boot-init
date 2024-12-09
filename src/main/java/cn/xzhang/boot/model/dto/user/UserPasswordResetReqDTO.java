package cn.xzhang.boot.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author code_zhang
 * @Date 2024/12/10 1:19
 */
@Data
@Schema(description = "重置用户密码请求")
public class UserPasswordResetReqDTO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "新密码")
    private String newPassword;
}
