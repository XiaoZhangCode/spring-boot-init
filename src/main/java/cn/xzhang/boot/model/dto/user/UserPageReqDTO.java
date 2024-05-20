package cn.xzhang.boot.model.dto.user;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "用户分页请求")
@EqualsAndHashCode(callSuper = true)
public class UserPageReqDTO extends PageParam implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "用户角色")
    private String userRole;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "开放平台id")
    private String unionId;

    @Schema(description = "公众号openId")
    private String mpOpenId;

}
