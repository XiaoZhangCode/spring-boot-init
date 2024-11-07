package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.UserRecordMapper;
import cn.xzhang.boot.model.dto.userrecord.UserRecordAddReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordPageReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserRecord;
import cn.xzhang.boot.model.vo.userrecord.UserRecordSimpleVo;
import cn.xzhang.boot.model.vo.userrecord.UserRecordVo;
import cn.xzhang.boot.service.UserRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【userrecord(提现记录表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class UserRecordServiceImpl extends ServiceImpl<UserRecordMapper, UserRecord> implements UserRecordService {

    @Resource
    private UserRecordMapper userrecordMapper;

    /**
     * 添加新提现记录
     *
     * @param userrecordReqDTO 提现记录信息请求DTO
     * @return 添加成功返回提现记录id
     */
    @Override
    public long addUserRecord(UserRecordAddReqDTO userrecordReqDTO) {
        UserRecord userrecord = new UserRecord();
        BeanUtil.copyProperties(userrecordReqDTO, userrecord);
        if (!this.save(userrecord)) {
            throw exception(ADD_FAIL);
        }
        return userrecord.getId();
    }

    /**
     * 更新提现记录信息
     *
     * @param userrecordReqDTO 提现记录信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateUserRecord(UserRecordUpdateReqDTO userrecordReqDTO) {
        if (userrecordReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        UserRecord userrecord = new UserRecord();
        BeanUtil.copyProperties(userrecordReqDTO, userrecord);
        boolean b = this.updateById(userrecord);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除提现记录
     *
     * @param id 提现记录id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteUserRecord(Long id) {
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
     * 将提现记录对象转换为提现记录VO对象
     *
     * @param userrecord 提现记录对象
     * @return 返回提现记录VO对象
     */
    @Override
    public UserRecordSimpleVo getSimpleUserRecordVO(UserRecord userrecord) {
        if (userrecord == null) {
            return null;
        }
        UserRecordSimpleVo userrecordSimpleVo = new UserRecordSimpleVo();
        BeanUtil.copyProperties(userrecord, userrecordSimpleVo);
        return userrecordSimpleVo;
    }

    /**
     * 获取提现记录分页信息
     *
     * @param userrecordPageReqDTO 提现记录分页请求DTO
     * @return 返回提现记录分页结果
     */
    @Override
    public PageResult<UserRecordVo> getUserRecordPage(UserRecordPageReqDTO userrecordPageReqDTO) {
        PageResult<UserRecord> pageResult = userrecordMapper.selectPage(userrecordPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<UserRecordVo> userrecordVos = pageResult.getList().stream().map(userrecord -> {
            UserRecordVo userrecordVo = new UserRecordVo();
            BeanUtil.copyProperties(userrecord, userrecordVo);
            return userrecordVo;
        }).collect(Collectors.toList());
        return new PageResult<>(userrecordVos, pageResult.getTotal());
    }

    @Override
    public UserRecordVo getUserRecordVO(UserRecord userrecord) {
        if (userrecord == null) {
            return null;
        }
        UserRecordVo userrecordVo = new UserRecordVo();
        BeanUtil.copyProperties(userrecord, userrecordVo);
        return userrecordVo;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateUserRecord(Long loginUserId, BigDecimal price) {
        BigDecimal totalPrice = userrecordMapper.getTotalPrice();
        BigDecimal subtract = totalPrice.subtract(price);
        UserRecordAddReqDTO addReqDTO = new UserRecordAddReqDTO();
        addReqDTO.setUserId(loginUserId);
        addReqDTO.setTotalPrice(price);
        addReqDTO.setBeforeDeduction(totalPrice);
        addReqDTO.setAfterDeduction(subtract);
        addReqDTO.setRemark("");
        userrecordMapper.updateTotalPrice(subtract);
        addUserRecord(addReqDTO);
    }

}



