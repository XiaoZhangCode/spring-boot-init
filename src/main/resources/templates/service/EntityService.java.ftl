package ${basePackage}.service;

import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}AddReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}PageReqDTO;
import ${basePackage}.model.dto.${entityName?lower_case}.${entityName}UpdateReqDTO;
import ${basePackage}.model.entity.${entityName};
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}SimpleVo;
import ${basePackage}.model.vo.${entityName?lower_case}.${entityName}Vo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【${entityName?lower_case}(${description}表)】的数据库操作Service
 */
public interface ${entityName}Service extends IService<${entityName}> {

    /**
     * 添加${description}信息。
     *
     * @param ${entityName?lower_case}ReqDTO ${description}添加请求数据传输对象，包含要添加的${description}的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long add${entityName}(${entityName}AddReqDTO ${entityName?lower_case}ReqDTO);

    /**
     * 更新${description}信息。
     *
     * @param ${entityName?lower_case}ReqDTO 包含${description}更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的${description}属性。
     * @return boolean 返回true如果${description}信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean update${entityName}(${entityName}UpdateReqDTO ${entityName?lower_case}ReqDTO);

    /**
     * 删除${description}
     *
     * @param id ${description}的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean delete${entityName}(Long id);

    /**
     * 根据${entityName}对象获取对应的${entityName}Vo对象。
     *
     * @param ${entityName?lower_case} 一个包含${description}信息的${entityName}对象。
     * @return 返回一个包含${description}信息的${entityName}Vo对象。
     */
    ${entityName}SimpleVo getSimple${entityName}VO(${entityName} ${entityName?lower_case});

    /**
     * 获取${description}页面信息
     *
     * @param ${entityName?lower_case}PageReqDTO 包含分页和筛选条件的${description}请求数据传输对象
     * @return 返回${description}页面的结果，包括${description}列表和分页信息
     */
    PageResult<${entityName}Vo> get${entityName}Page(${entityName}PageReqDTO ${entityName?lower_case}PageReqDTO);

    /**
     * 根据${entityName}对象获取对应的${entityName}Vo对象。
     *
     * @param ${entityName?lower_case} 一个包含${description}信息的${entityName}对象。
     * @return 返回一个包含${description}信息的${entityName}Vo对象。
     */
    ${entityName}Vo get${entityName}VO(${entityName} ${entityName?lower_case});
}
