package com.sparta.jpaschedule.entity;

import com.sparta.jpaschedule.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void update(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
    }

    @OneToMany(mappedBy = "user")
    private List<Register> registerList = new ArrayList<>();
}