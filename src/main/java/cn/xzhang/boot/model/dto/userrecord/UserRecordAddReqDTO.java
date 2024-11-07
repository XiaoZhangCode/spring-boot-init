package cn.xzhang.boot.model.dto.userrecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提现记录添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "提现记录添加请求")
public class UserRecordAddReqDTO extends UserRecordBaseDTO{


}
