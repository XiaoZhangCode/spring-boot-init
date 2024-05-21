package ${basePackage}.model.dto.${entityName?lower_case};

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * ${description}基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "${description}基础信息类", description = "${description}基础信息")
public class ${entityName}BaseDTO implements Serializable {

<#list columns as column>
<#if column.columnName == 'id'>
<#else>
    <#if (
        column.columnName == "createTime" || column.columnName == "updateTime"|| column.columnName == "deleted" ||
        column.columnName == "creator" || column.columnName == "updater"
    )>
    <#else >
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
</#if>
</#list>

}
