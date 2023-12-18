package com.pratap.controller;

import com.pratap.dto.UserDto;
import com.pratap.model.request.UserSignUpRequestModel;
import com.pratap.model.response.UserSignUpResponseModel;
import com.pratap.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserSignUpRequestModel userSignUpRequestModel){
        UserDto userDetailsDto = modelMapper.map(userSignUpRequestModel, UserDto.class);
        UserDto savedUserDto = userService.createUser(userDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedUserDto, UserSignUpResponseModel.class));

    }

    @GetMapping(value = "/{userId}",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String userId){

        UserDto userDto = userService.getUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(modelMapper.map(userDto, UserSignUpResponseModel.class));
    }
}
