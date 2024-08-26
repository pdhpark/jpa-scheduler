package com.sparta.jpaschedule.entity;

import com.sparta.jpaschedule.dto.ScheduleRequestDto;
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
@Table(name = "schedule")
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(ScheduleRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(ScheduleRequestDto requestDto) {
        //수정시 service에서 작성자 id가 같은지 확인하는 로직으로 인해 필요없는 코드소스 제거
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comments> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<Register> registerList = new ArrayList<>();
}
