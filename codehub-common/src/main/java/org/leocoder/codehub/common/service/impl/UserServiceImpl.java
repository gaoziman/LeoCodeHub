package org.leocoder.codehub.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.codehub.common.model.domain.User;
import org.leocoder.codehub.common.mapper.UserMapper;
import org.leocoder.codehub.common.service.UserService;
import org.springframework.stereotype.Service;
/**
 * @author : Leo
 * @date  2024-07-20 12:15
 * @version 1.0
 * @description :
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
