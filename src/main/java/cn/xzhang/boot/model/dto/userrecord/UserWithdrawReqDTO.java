package cn.xzhang.boot.model.dto.userrecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author code_zhang
 * @Date 2024/11/6 16:02
 */
@Data
@Schema(description = "提现请求参数")
public class UserWithdrawReqDTO {

    @Schema(description = "提现金额")
    private BigDecimal price;

}
