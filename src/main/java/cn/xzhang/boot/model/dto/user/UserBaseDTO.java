package cn.xzhang.boot.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "用户基础信息类", description = "用户基础信息")
public class UserBaseDTO implements Serializable {

    @Schema(description = "账号")
    private String userAccount;

    @Schema(description = "密码")
    private String userPassword;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "用户简介")
    private String userProfile;

    @Schema(description = "用户角色",example = "user")
    private String userRole;

}
