package org.leocoder.codehub.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.admin.model.vo.UpdateAdminUserPasswordReqVO;
import org.leocoder.codehub.common.model.domain.User;
import org.leocoder.codehub.common.utils.Result;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-24 15:32
 * @description :
 */
public interface AdminUserService extends IService<User> {


    /**
     * 修改用户密码
     *
     * @param updateAdminUserPasswordReqVO 修改用户密码请求参数
     * @return Result
     */
    Result<String> updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);


}
