package com.homework.pizza_app.service.impl;

import com.homework.pizza_app.io.entity.UserEntity;
import com.homework.pizza_app.repository.UserRepository;
import com.homework.pizza_app.service.UserService;
import com.homework.pizza_app.share.dto.UserDto;
import com.homework.pizza_app.share.dto.Utils;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author dritan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepository.findByUserName(userDto.getUserName()) != null) {
            throw new RuntimeException("User allrady exist!!");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(Utils.generateUserId(30));

        UserEntity saveUser = userRepository.save(userEntity);
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(saveUser, returnVal);

        return returnVal;
    }

    @Override
    public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUserName(str);
        if (userEntity == null) {
            throw new UsernameNotFoundException(str);
        }
        return new User(userEntity.getUserName(), userEntity.getEncryptPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userName);
        }
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);
        return returnVal;
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new UsernameNotFoundException(id);
        }
        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);
        return returnVal;
    }

    @Override
    public UserEntity findUserByUserName(String username) {
        UserEntity findByUserName = userRepository.findByUserName(username);

        return findByUserName;
    }

}
