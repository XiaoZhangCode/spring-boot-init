package ${basePackage}.model.entity;

import ${basePackage}.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* ${description}
* @TableName ${entityName}
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="${entityName?lower_case}")
@Data
@EqualsAndHashCode(callSuper = true)
public class ${entityName} extends BaseDO implements Serializable {


<#list columns as column>
<#if column.columnName == 'id'>
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;
<#else>
    <#if (!column.nullable) && (column.javaType == 'String')>
    @NotEmpty(message = "${column.columnComment}不能为空")
    <#elseif (column.nullable)>
    @NotNull(message = "${column.columnComment}不能为空")
    </#if>
    <#if (column.javaType == "java.util.Date")>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    @Schema(description = "${column.columnComment}"<#if column.nullable>,requiredMode = Schema.RequiredMode.REQUIRED</#if>)
    private ${column.javaType} ${column.columnName};

</#if>
</#list>

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}