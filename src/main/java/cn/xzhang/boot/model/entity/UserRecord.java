package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
* 提现记录
* @TableName UserRecord
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="user_record")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRecord extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}