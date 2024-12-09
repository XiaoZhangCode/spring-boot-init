package ${basePackage}.service;
<#-- 转换为小驼峰 -->
<#assign words = entityName?split("_")>
<#assign camelCaseEntityName = words[0]?lower_case + (words[1..]?map(w -> w?capitalize))?join("")>
<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>

import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}SaveReqDTO;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}PageReqDTO;
import ${basePackage}.model.entity.${pascalCaseEntityName};
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}SimpleVo;
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}Vo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【${camelCaseEntityName}(${description}表)】的数据库操作Service
 */
public interface ${pascalCaseEntityName}Service extends IService<${pascalCaseEntityName}> {

    /**
     * 添加${description}信息。
     *
     * @param ${camelCaseEntityName}ReqDTO ${description}添加请求数据传输对象，包含要添加的${description}的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long add${pascalCaseEntityName}(${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO);

    /**
     * 更新${description}信息。
     *
     * @param ${camelCaseEntityName}ReqDTO 包含${description}更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的${description}属性。
     * @return boolean 返回true如果${description}信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean update${pascalCaseEntityName}(${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO);

    /**
     * 删除${description}
     *
     * @param id ${description}的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean delete${pascalCaseEntityName}(Long id);

    /**
     * 根据${pascalCaseEntityName}对象获取对应的${pascalCaseEntityName}Vo对象。
     *
     * @param ${camelCaseEntityName} 一个包含${description}信息的${pascalCaseEntityName}对象。
     * @return 返回一个包含${description}信息的${pascalCaseEntityName}Vo对象。
     */
    ${pascalCaseEntityName}SimpleVo getSimple${pascalCaseEntityName}VO(${pascalCaseEntityName} ${camelCaseEntityName});

    /**
     * 获取${description}页面信息
     *
     * @param ${camelCaseEntityName}PageReqDTO 包含分页和筛选条件的${description}请求数据传输对象
     * @return 返回${description}页面的结果，包括${description}列表和分页信息
     */
    PageResult<${pascalCaseEntityName}Vo> get${pascalCaseEntityName}Page(${pascalCaseEntityName}PageReqDTO ${camelCaseEntityName}PageReqDTO);

    /**
     * 根据${pascalCaseEntityName}对象获取对应的${pascalCaseEntityName}Vo对象。
     *
     * @param ${camelCaseEntityName} 一个包含${description}信息的${pascalCaseEntityName}对象。
     * @return 返回一个包含${description}信息的${pascalCaseEntityName}Vo对象。
     */
    ${pascalCaseEntityName}Vo get${pascalCaseEntityName}VO(${pascalCaseEntityName} ${camelCaseEntityName});
}
