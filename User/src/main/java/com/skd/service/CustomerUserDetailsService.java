package com.skd.service;

import com.skd.entity.UserInfo;
import com.skd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo =  userRepository.findByFirstName(username);
        return userInfo.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user exists with this firstName"));

    }
}
