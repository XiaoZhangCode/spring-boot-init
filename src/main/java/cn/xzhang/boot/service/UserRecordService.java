package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.userrecord.UserRecordAddReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordPageReqDTO;
import cn.xzhang.boot.model.dto.userrecord.UserRecordUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserRecord;
import cn.xzhang.boot.model.vo.userrecord.UserRecordSimpleVo;
import cn.xzhang.boot.model.vo.userrecord.UserRecordVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【userrecord(提现记录表)】的数据库操作Service
 */
public interface UserRecordService extends IService<UserRecord> {

    /**
     * 添加提现记录信息。
     *
     * @param userrecordReqDTO 提现记录添加请求数据传输对象，包含要添加的提现记录的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addUserRecord(UserRecordAddReqDTO userrecordReqDTO);

    /**
     * 更新提现记录信息。
     *
     * @param userrecordReqDTO 包含提现记录更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的提现记录属性。
     * @return boolean 返回true如果提现记录信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateUserRecord(UserRecordUpdateReqDTO userrecordReqDTO);

    /**
     * 删除提现记录
     *
     * @param id 提现记录的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteUserRecord(Long id);

    /**
     * 根据UserRecord对象获取对应的UserRecordVo对象。
     *
     * @param userrecord 一个包含提现记录信息的UserRecord对象。
     * @return 返回一个包含提现记录信息的UserRecordVo对象。
     */
    UserRecordSimpleVo getSimpleUserRecordVO(UserRecord userrecord);

    /**
     * 获取提现记录页面信息
     *
     * @param userrecordPageReqDTO 包含分页和筛选条件的提现记录请求数据传输对象
     * @return 返回提现记录页面的结果，包括提现记录列表和分页信息
     */
    PageResult<UserRecordVo> getUserRecordPage(UserRecordPageReqDTO userrecordPageReqDTO);

    /**
     * 根据UserRecord对象获取对应的UserRecordVo对象。
     *
     * @param userrecord 一个包含提现记录信息的UserRecord对象。
     * @return 返回一个包含提现记录信息的UserRecordVo对象。
     */
    UserRecordVo getUserRecordVO(UserRecord userrecord);


    void updateUserRecord(Long loginUserId, BigDecimal price);
}
