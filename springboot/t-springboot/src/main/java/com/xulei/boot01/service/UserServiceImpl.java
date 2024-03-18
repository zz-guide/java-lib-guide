package com.xulei.boot01.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xulei.boot01.mapper.UserMapper;
import com.xulei.boot01.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
