<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>
package ${basePackage}.model.vo.${pascalCaseEntityName};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * ${description}信息Vo
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "${description}简要信息VO")
public class ${pascalCaseEntityName}SimpleVo implements Serializable {

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
