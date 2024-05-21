package ${basePackage}.mapper;

import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.core.mapper.BaseMapperPlus;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}PageReqDTO;
import ${basePackage}.model.entity.${entityName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【${entityName?lower_case}(${description}表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface ${entityName}Mapper extends BaseMapperPlus<${entityName}> {

    default PageResult<${entityName}> selectPage(${entityName}PageReqDTO ${entityName?lower_case}PageReqDTO) {
        return selectPage(${entityName?lower_case}PageReqDTO, new LambdaQueryWrapper<${entityName}>()
                <#list columns as column>
                <#if (
                    column.columnName == "createTime" || column.columnName == "updateTime"|| column.columnName == "deleted" ||
                    column.columnName == "creator" || column.columnName == "updater"
                )>
                <#else >
                <#if (column.javaType == 'String')>
                .eq(Objects.nonNull(${entityName?lower_case}PageReqDTO.get${column.columnName?cap_first}()), ${entityName}::get${column.columnName?cap_first}, ${entityName?lower_case}PageReqDTO.get${column.columnName?cap_first}())
                <#elseif (column.javaType == "java.util.Date") ||(column.columnName == "deleted")>
                </#if>
                </#if>
                </#list>
                .orderByDesc(${entityName}::getCreateTime)

        );
    }

}




