package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.dto.UserRequestDto;
import com.sparta.jpaschedule.dto.UserResponseDto;
import com.sparta.jpaschedule.entity.User;
import com.sparta.jpaschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        User saveUser = userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto(saveUser);
        return responseDto;
    }

    public UserResponseDto getUser(Long id) {
        User user = find(id);
        UserResponseDto responseDto = new UserResponseDto(user);
        return responseDto;
    }

        public List<UserResponseDto> getUserList() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = find(id);
        user.update(requestDto);

        UserResponseDto responseDto = new UserResponseDto(user);
        return responseDto;
    }

    public Long deleteUser(Long id) {
        User user = find(id);
        userRepository.delete(user);
        return id;
    }

    private User find(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
