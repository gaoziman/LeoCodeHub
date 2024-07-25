package org.leocoder.codehub.common.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.codehub.common.mapper.UserRoleMapper;
import org.leocoder.codehub.common.model.domain.UserRole;
import org.leocoder.codehub.common.service.UserRoleService;
/**
 * @author : Leo
 * @date  2024-07-23 13:58
 * @version 1.0
 * @description :
 */

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

}
