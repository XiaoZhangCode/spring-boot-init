package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.user.UserAddReqDTO;
import cn.xzhang.boot.model.dto.user.UserPageReqDTO;
import cn.xzhang.boot.model.dto.user.UserProfileUpdateReqDTO;
import cn.xzhang.boot.model.dto.user.UserUpdateReqDTO;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.vo.user.LoginUserVO;
import cn.xzhang.boot.model.vo.user.UserSimpleVo;
import cn.xzhang.boot.model.vo.user.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【user(用户表)】的数据库操作Service
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册函数
     *
     * @param userAccount   用户账号，用于注册新用户时的账号标识。
     * @param userPassword  用户密码，用于注册新用户时设置的登录凭据。
     * @param checkPassword 确认密码，用于验证用户输入的密码是否一致。
     * @return 返回注册结果，成功返回用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录接口。
     *
     * @param userAccount  用户账号，用于标识用户的身份。
     * @param userPassword 用户密码，用于验证用户的身份。
     * @return LoginUserVO 返回一个登录用户的信息，如果登录成功，则包含用户的基本信息；如果登录失败，则可能为空。
     */
    LoginUserVO userLogin(String userAccount, String userPassword);

    /**
     * 获取当前登录的用户对象。
     *
     * @return User 返回当前登录的用户实例。
     */
    User getLoginUser();

    /**
     * 根据用户对象转换并获取登录用户的信息。
     *
     * @param user 用户实体对象，包含用户的基本信息。
     * @return LoginUserVO 登录用户信息的视图对象，封装了用户登录状态和信息。
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 添加用户信息。
     *
     * @param userReqDTO 用户添加请求数据传输对象，包含要添加的用户的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addUser(UserAddReqDTO userReqDTO);

    /**
     * 更新用户信息。
     *
     * @param userReqDTO 包含用户更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的用户属性。
     * @return boolean 返回true如果用户信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateUser(UserUpdateReqDTO userReqDTO);

    /**
     * 删除用户
     *
     * @param id 用户的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteUser(Long id);

    /**
     * 根据User对象获取对应的UserVo对象。
     *
     * @param user 一个包含用户信息的User对象。
     * @return 返回一个包含用户信息的UserVo对象。
     */
    UserSimpleVo getSimpleUserVO(User user);

    /**
     * 获取用户页面信息
     *
     * @param userPageReqDTO 包含分页和筛选条件的用户请求数据传输对象
     * @return 返回用户页面的结果，包括用户列表和分页信息
     */
    PageResult<UserVo> getUserPage(UserPageReqDTO userPageReqDTO);

    /**
     * 更新用户个人资料
     *
     * @param userProfileUpdateReqDTO 包含更新信息的用户个人资料更新请求数据传输对象
     * @return 若更新成功返回true，否则返回false
     */
    boolean updateUserProfile(UserProfileUpdateReqDTO userProfileUpdateReqDTO);

    /**
     * 根据User对象获取对应的UserVo对象。
     *
     * @param user 一个包含用户信息的User对象。
     * @return 返回一个包含用户信息的UserVo对象。
     */
    UserVo getUserVO(User user);
}
