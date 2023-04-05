package com.skd.controller;

import com.skd.dto.AuthenticateRequest;
import com.skd.dto.UserDTO;
import com.skd.entity.UserInfo;
import com.skd.filter.RoleAllowed;
import com.skd.mapper.UserMapper;
import com.skd.service.JwtService;
import com.skd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.register(userDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.set("check your details","/users/"+registeredUser.getId());
        return new ResponseEntity<>(registeredUser, headers, HttpStatus.CREATED);
    }

    @GetMapping("/admin/fetchAll")
//    @RoleAllowed({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> userDTOS = userService.fetchAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/validateToken")
//    @RoleAllowed({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    public Boolean validateToken(String token, UserDetails userDetails) {
        return jwtService.validateToken(token,userDetails);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable Long user_id) {
        UserDTO userDTO = userService.fetchById(user_id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String authenticateRequest(@RequestBody AuthenticateRequest authenticateRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            UserInfo userInfo = userService.findByName(authenticateRequest.getUsername());
            return jwtService.generateToken(authenticateRequest.getUsername(), userInfo);
        }else
            throw new UsernameNotFoundException("User doesn't exist");
    }
}
