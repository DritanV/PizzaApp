package com.homework.pizza_app.service;

import com.homework.pizza_app.io.entity.UserEntity;
import com.homework.pizza_app.share.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author dritan
 */
public interface UserService extends UserDetailsService {

    public UserDto createUser(UserDto userDto);

    public UserDto getUser(String userName);

    public UserDto getUserByUserId(String id);

    public UserEntity findUserByUserName(String username);

}
