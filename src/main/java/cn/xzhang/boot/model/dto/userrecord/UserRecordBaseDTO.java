package cn.xzhang.boot.model.dto.userrecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 提现记录基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "提现记录基础信息类", description = "提现记录基础信息")
public class UserRecordBaseDTO implements Serializable {

    @NotNull(message = "用户id不能为空")
    @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @NotNull(message = "总额不能为空")
    @Schema(description = "总额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal totalPrice;

    @NotNull(message = "扣除前余额不能为空")
    @Schema(description = "扣除前余额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal beforeDeduction;

    @NotNull(message = "扣除后余额不能为空")
    @Schema(description = "扣除后余额",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.math.BigDecimal afterDeduction;

    @NotNull(message = "备注不能为空")
    @Schema(description = "备注",requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;


}
