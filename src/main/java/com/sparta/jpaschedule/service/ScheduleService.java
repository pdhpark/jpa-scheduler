package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import com.sparta.jpaschedule.dto.ScheduleResponseDto;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    //Class에 Transactional이 걸려있기때문에 제거해도됐었음
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        //id에 해당하는 entity 찾기
        Schedule schedule = find(id);

        //PUT
        schedule.update(requestDto);

        //ResponseDto에 담아 반환
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    public Schedule find(Long id) {
        return scheduleRepository.findById(id).orElseThrow();
    }

    public List<ScheduleResponseDto> getScheduleList(Pageable pageable) {
        return scheduleRepository.findAllByOrderByModifiedAtDesc(pageable).stream().map(ScheduleResponseDto::new).toList();
    }

    public Long deleteSchedule(Long id) {
        Schedule schedule = find(id);
        scheduleRepository.delete(schedule);
        return id;
    }
}
