package cn.xzhang.boot.model.vo.userrecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 提现记录VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "提现记录VO")
public class UserRecordVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "总额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal totalPrice;

    @Schema(description = "扣除前余额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal beforeDeduction;

    @Schema(description = "扣除后余额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal afterDeduction;

    @Schema(description = "备注",requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;

}
