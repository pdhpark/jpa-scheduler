package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.dto.CommentsRequestDto;
import com.sparta.jpaschedule.dto.CommentsResponseDto;
import com.sparta.jpaschedule.entity.Comments;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.repository.CommentsRepository;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentsResponseDto createComments(Long id, CommentsRequestDto requestDto) {
        //RequestDto -> Entity
        Comments comments = new Comments(requestDto);

        //등록할 Id값에 해당하는 Schedule Entity 불러오기
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();

        //연관관계 설정
        comments.setSchedule(schedule);

        //DB에 저장
        Comments saveComments = commentsRepository.save(comments);

        //ResponseDto에 반환
        CommentsResponseDto responseDto = response(saveComments);
        return responseDto;
    }

    public CommentsResponseDto getComments(Long id) {
        Comments comments = find(id);
        CommentsResponseDto responseDto = response(comments);
        return responseDto;
    }

    @Transactional
    public List<CommentsResponseDto> getCommentsList() {
        return commentsRepository.findAll().stream().map(CommentsResponseDto::new).toList();
    }

    @Transactional
    public CommentsResponseDto updateComments(Long id, CommentsRequestDto requestDto) {
        Comments comments = find(id);
        comments.update(requestDto);
        CommentsResponseDto responseDto = response(comments);
        return responseDto;
    }

    public Long deleteComments(Long id) {
        Comments comments = find(id);
        commentsRepository.delete(comments);
        return id;
    }

    private Comments find(Long id) {
        return commentsRepository.findById(id).orElseThrow();
    }

    private CommentsResponseDto response(Comments comments){
        return new CommentsResponseDto(comments);
    }
}
