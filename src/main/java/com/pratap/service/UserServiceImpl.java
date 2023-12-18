package com.pratap.service;

import com.pratap.dto.UserDto;
import com.pratap.entity.UserEntity;
import com.pratap.exception.UserAlreadyExistsException;
import com.pratap.exception.UserNotFoundException;
import com.pratap.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.pratap.util.UserServiceUtil.generateUserId;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDetailsDto) {
        log.info("Executing createUser() with payload = {}", userDetailsDto);
        //duplicate email check
        if (userRepository.findByEmail(userDetailsDto.getEmail()) != null)
            throw new UserAlreadyExistsException("Record already exist");

        UserEntity userDetailsEntity = modelMapper.map(userDetailsDto, UserEntity.class);
        userDetailsEntity.setUserId(generateUserId(30));
        userDetailsEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetailsDto.getPassword()));
        UserEntity savedEntity = userRepository.save(userDetailsEntity);
        return modelMapper.map(savedEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Executing loadUserByUsername() with username = {}", username);
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) throw new UserNotFoundException("No user registered with "+username);

        return new User(username, userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        log.info("Executing getUser() with email = {}", email);

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UserNotFoundException("No user registered with email:"+email);

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        log.info("Executing getUserByUserId() with userId = {}", userId);
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            throw new UserNotFoundException("No user registered with userId:"+userId);
        return modelMapper.map(userEntity, UserDto.class);
    }
}
