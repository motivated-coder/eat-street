package com.skd.service;

import com.skd.dto.UserDTO;
import com.skd.entity.UserInfo;
import com.skd.mapper.UserMapper;
import com.skd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO register(UserDTO userDTO) {
        UserInfo savedUser = userRepository.save(userMapper.toUserInfo(userDTO));
        return userMapper.toUserDTO(savedUser);
    }
}
