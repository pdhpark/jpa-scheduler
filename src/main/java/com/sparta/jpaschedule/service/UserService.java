package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.config.PasswordEncoder;
import com.sparta.jpaschedule.dto.UserRequestDto;
import com.sparta.jpaschedule.dto.UserResponseDto;
import com.sparta.jpaschedule.entity.User;
import com.sparta.jpaschedule.jwt.JwtUtil;
import com.sparta.jpaschedule.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final HttpServletResponse res;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        User user = new User(username, password, email);

        userRepository.save(user);
        UserResponseDto responseDto = response(user);

        String token = jwtUtil.createToken(email);
        jwtUtil.addJwtToCookie(token, res);
        return responseDto;
    }

    public UserResponseDto getUser(Long id) {
        User user = find(id);
        UserResponseDto responseDto = response(user);
        return responseDto;
    }

        public List<UserResponseDto> getUserList() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = find(id);
        user.update(requestDto);

        UserResponseDto responseDto = response(user);
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

    private UserResponseDto response(User user){
        return new UserResponseDto(user);
    }
}
