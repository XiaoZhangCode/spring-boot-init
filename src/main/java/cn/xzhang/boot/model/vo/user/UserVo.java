package cn.xzhang.boot.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "用户VO")
public class UserVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "账号")
    private String userAccount;

    @Schema(description = "密码")
    private String userPassword;

    @Schema(description = "微信开放平台id")
    private String unionId;

    @Schema(description = "公众号openId")
    private String mpOpenId;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "用户简介")
    private String userProfile;

    @Schema(description = "用户角色：user/admin")
    private String userRole;

    @Schema(description = "用户状态（0正常 1停用）")
    private Integer userStatus;
}
