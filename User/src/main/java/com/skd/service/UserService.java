package com.skd.service;

import com.skd.dto.UserDTO;
import com.skd.entity.Address;
import com.skd.entity.UserInfo;
import com.skd.exception.UserNotFoundException;
import com.skd.mapper.UserMapper;
import com.skd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDTO> fetchAllUsers() {
        List<UserInfo> users = userRepository.findAll();
        return users.stream().map(userInfo -> userMapper.toUserDTO(userInfo)).collect(Collectors.toList());
    }

    public UserDTO fetchById(Long user_id) {
        return userMapper.toUserDTO(userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("User doesn't exist")));
    }

    public UserInfo findByName(String username) {
        return userRepository.findByFirstName(username).orElseThrow(NullPointerException::new);
    }

    public List<Address> getAddressByUserId(Long userId) {
        UserInfo userInfo = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userInfo.getAddresses();
    }
}
