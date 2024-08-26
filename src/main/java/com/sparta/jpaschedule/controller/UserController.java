package com.sparta.jpaschedule.controller;

import com.sparta.jpaschedule.dto.UserRequestDto;
import com.sparta.jpaschedule.dto.UserResponseDto;
import com.sparta.jpaschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //유저 등록
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto){
        return userService.createUser(requestDto);
    }

    //유저 단건조회
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //유저 다건조회
    @GetMapping
    public List<UserResponseDto> getUserList() {
        return userService.getUserList();
    }

    //유저 수정
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    //유저 삭제
    @DeleteMapping("/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
