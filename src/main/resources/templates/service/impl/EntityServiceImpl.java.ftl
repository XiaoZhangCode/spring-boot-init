package ${basePackage}.service.impl;
<#-- 转换为小驼峰 -->
<#assign words = entityName?split("_")>
<#assign camelCaseEntityName = words[0]?lower_case + (words[1..]?map(w -> w?capitalize))?join("")>
<#-- 转换为大驼峰 -->
<#assign words = entityName?split("_")>
<#assign pascalCaseEntityName = words?map(w -> w?capitalize)?join("")>

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.common.exception.ServiceException;
import ${basePackage}.common.pojo.PageResult;
import ${basePackage}.mapper.${pascalCaseEntityName}Mapper;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}PageReqDTO;
import ${basePackage}.model.dto.${camelCaseEntityName}.${pascalCaseEntityName}SaveReqDTO;
import ${basePackage}.model.entity.${pascalCaseEntityName};
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}SimpleVo;
import ${basePackage}.model.vo.${camelCaseEntityName}.${pascalCaseEntityName}Vo;
import ${basePackage}.service.${pascalCaseEntityName}Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ${basePackage}.common.exception.enums.GlobalErrorCodeConstants.*;
import static ${basePackage}.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【${camelCaseEntityName}(${description}表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class ${pascalCaseEntityName}ServiceImpl extends ServiceImpl<${pascalCaseEntityName}Mapper, ${pascalCaseEntityName}> implements ${pascalCaseEntityName}Service {

    @Resource
    private ${pascalCaseEntityName}Mapper ${camelCaseEntityName}Mapper;

    /**
     * 添加新${description}
     *
     * @param ${camelCaseEntityName}ReqDTO ${description}信息请求DTO
     * @return 添加成功返回${description}id
     */
    @Override
    public long add${pascalCaseEntityName}(${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO) {
        ${pascalCaseEntityName} ${camelCaseEntityName} = new ${pascalCaseEntityName}();
        BeanUtil.copyProperties(${camelCaseEntityName}ReqDTO, ${camelCaseEntityName});
        if (!this.save(${camelCaseEntityName})) {
            throw exception(ADD_FAIL);
        }
        return ${camelCaseEntityName}.getId();
    }

    /**
     * 更新${description}信息
     *
     * @param ${camelCaseEntityName}ReqDTO ${description}信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean update${pascalCaseEntityName}(${pascalCaseEntityName}SaveReqDTO ${camelCaseEntityName}ReqDTO) {
        if (${camelCaseEntityName}ReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        ${pascalCaseEntityName} ${camelCaseEntityName} = new ${pascalCaseEntityName}();
        BeanUtil.copyProperties(${camelCaseEntityName}ReqDTO, ${camelCaseEntityName});
        boolean b = this.updateById(${camelCaseEntityName});
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除${description}
     *
     * @param id ${description}id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean delete${pascalCaseEntityName}(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
    }

    /**
     * 将${description}对象转换为${description}VO对象
     *
     * @param ${camelCaseEntityName} ${description}对象
     * @return 返回${description}VO对象
     */
    @Override
    public ${pascalCaseEntityName}SimpleVo getSimple${pascalCaseEntityName}VO(${pascalCaseEntityName} ${camelCaseEntityName}) {
        if (${camelCaseEntityName} == null) {
            return null;
        }
        ${pascalCaseEntityName}SimpleVo ${camelCaseEntityName}SimpleVo = new ${pascalCaseEntityName}SimpleVo();
        BeanUtil.copyProperties(${camelCaseEntityName}, ${camelCaseEntityName}SimpleVo);
        return ${camelCaseEntityName}SimpleVo;
    }

    /**
     * 获取${description}分页信息
     *
     * @param ${camelCaseEntityName}PageReqDTO ${description}分页请求DTO
     * @return 返回${description}分页结果
     */
    @Override
    public PageResult<${pascalCaseEntityName}Vo> get${pascalCaseEntityName}Page(${pascalCaseEntityName}PageReqDTO ${camelCaseEntityName}PageReqDTO) {
        PageResult<${pascalCaseEntityName}> pageResult = ${camelCaseEntityName}Mapper.selectPage(${camelCaseEntityName}PageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<${pascalCaseEntityName}Vo> ${camelCaseEntityName}Vos = pageResult.getList().stream().map(${camelCaseEntityName} -> {
            ${pascalCaseEntityName}Vo ${camelCaseEntityName}Vo = new ${pascalCaseEntityName}Vo();
            BeanUtil.copyProperties(${camelCaseEntityName}, ${camelCaseEntityName}Vo);
            return ${camelCaseEntityName}Vo;
        }).collect(Collectors.toList());
        return new PageResult<>(${camelCaseEntityName}Vos, pageResult.getTotal());
    }

    @Override
    public ${pascalCaseEntityName}Vo get${pascalCaseEntityName}VO(${pascalCaseEntityName} ${camelCaseEntityName}) {
        if (${camelCaseEntityName} == null) {
            return null;
        }
        ${pascalCaseEntityName}Vo ${camelCaseEntityName}Vo = new ${pascalCaseEntityName}Vo();
        BeanUtil.copyProperties(${camelCaseEntityName}, ${camelCaseEntityName}Vo);
        return ${camelCaseEntityName}Vo;
    }

}



