<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>
package ${basePackage}.model.dto.${pascalCaseEntityName};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * ${description}添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "${description}创建/更新请求")
@Valid
public class ${pascalCaseEntityName}SaveReqDTO {

    @Schema(description = "id" )
    @NotBlank(message = "id不能为空")
    private Long id;

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
