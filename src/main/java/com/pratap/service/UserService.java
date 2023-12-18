package com.pratap.service;

import com.pratap.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDetailsDto);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
}
