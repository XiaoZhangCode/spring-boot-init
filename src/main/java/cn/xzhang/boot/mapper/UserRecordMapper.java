package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.userrecord.UserRecordPageReqDTO;
import cn.xzhang.boot.model.entity.UserRecord;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【userrecord(提现记录表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface UserRecordMapper extends BaseMapperPlus<UserRecord> {

    default PageResult<UserRecord> selectPage(UserRecordPageReqDTO userrecordPageReqDTO) {
        return selectPage(userrecordPageReqDTO, new LambdaQueryWrapper<UserRecord>()
                .eq(Objects.nonNull(userrecordPageReqDTO.getRemark()), UserRecord::getRemark, userrecordPageReqDTO.getRemark())
                .orderByDesc(UserRecord::getCreateTime)

        );
    }

    BigDecimal getTotalPrice();

    void updateTotalPrice(@Param("totalPrice") BigDecimal totalPrice);
}




