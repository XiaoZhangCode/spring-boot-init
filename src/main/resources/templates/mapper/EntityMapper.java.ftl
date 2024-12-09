package ${basePackage}.mapper;
<#-- 转换为小驼峰 -->
<#assign words = entityName?split("_")>
<#assign camelCaseEntityName = words[0]?lower_case + (words[1..]?map(w -> w?capitalize))?join("")>
<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>

import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.core.mapper.BaseMapperPlus;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}PageReqDTO;
import ${basePackage}.model.entity.${pascalCaseEntityName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【${camelCaseEntityName}(${description}表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface ${pascalCaseEntityName}Mapper extends BaseMapperPlus<${pascalCaseEntityName}> {

    default PageResult<${pascalCaseEntityName}> selectPage(${pascalCaseEntityName}PageReqDTO ${camelCaseEntityName}PageReqDTO) {
        return selectPage(${camelCaseEntityName}PageReqDTO, new LambdaQueryWrapper<${pascalCaseEntityName}>()
                <#list columns as column>
                <#if (
                    column.columnName == "createTime" || column.columnName == "updateTime"|| column.columnName == "deleted" ||
                    column.columnName == "creator" || column.columnName == "updater"
                )>
                <#else >
                <#if (column.javaType == 'String')>
                .eq(Objects.nonNull(${camelCaseEntityName}PageReqDTO.get${column.columnName?cap_first}()), ${pascalCaseEntityName}::get${column.columnName?cap_first}, ${camelCaseEntityName}PageReqDTO.get${column.columnName?cap_first}())
                <#elseif (column.javaType == "java.util.Date") ||(column.columnName == "deleted")>
                </#if>
                </#if>
                </#list>
                .orderByDesc(${pascalCaseEntityName}::getCreateTime)

        );
    }

}




