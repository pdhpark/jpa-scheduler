package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import com.sparta.jpaschedule.dto.ScheduleResponseDto;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 저장
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        //POST
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //ResponseDto에 담아 반환
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);
        return responseDto;
    }

    //일정 단건조회
    public ScheduleResponseDto getSchedule(Long id) {
        //id에 해당하는 entity 찾기
        Schedule schedule = find(id);

        //ResponseDto에 담아 반환(GET)
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        //id에 해당하는 entity 찾기
        Schedule schedule = find(id);

        //PUT
        schedule.update(requestDto);

        //ResponseDto에 담아 반환
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    private Schedule find(Long id) {
        return scheduleRepository.findById(id).orElseThrow();
    }
}
