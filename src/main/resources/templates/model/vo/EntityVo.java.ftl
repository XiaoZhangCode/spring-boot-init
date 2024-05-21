package ${basePackage}.model.dto.${entityName?lower_case};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;

/**
 * ${description}VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "${description}VO")
public class ${entityName}Vo implements Serializable {

<#list columns as column>
    <#if (
    column.columnName == "createTime" || column.columnName == "updateTime"|| column.columnName == "deleted" ||
    column.columnName == "creator" || column.columnName == "updater"
    )>
    <#else>
    <#if column.columnName == 'id'>
    @Schema(description = "id")
    private Long id;

    <#else>
    <#if (column.javaType == "java.util.Date")>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    @Schema(description = "${column.columnComment}"<#if column.nullable>,requiredMode = Schema.RequiredMode.REQUIRED</#if>)
    private ${column.javaType} ${column.columnName};

    </#if>
    </#if>
</#list>
}
