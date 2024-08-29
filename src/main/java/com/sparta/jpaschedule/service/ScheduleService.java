package com.sparta.jpaschedule.service;

import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import com.sparta.jpaschedule.dto.ScheduleResponseDto;
import com.sparta.jpaschedule.entity.Register;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.repository.RegisterRepository;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import com.sparta.jpaschedule.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final RegisterRepository registerRepository;

    //일정 저장
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        //작성자id와 연간관계 설정
        Register register = new Register();
        register.setSchedule(schedule);
        register.setUser(userRepository.findById(schedule.getUser_id()).orElseThrow());
        registerRepository.save(register);

        //일정을 작성한 user가 추가로 일정 담당 유저들을 배치할 때 연간관계 설정
        for(Long a : requestDto.getOthers_id()) {
            Register register1 = new Register();
            register1.setSchedule(schedule);
            register1.setUser(userRepository.findById(a).orElseThrow());
            registerRepository.save(register1);
        }

        //POST
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //ResponseDto에 담아 반환
        ScheduleResponseDto responseDto = response(saveSchedule);
        return responseDto;
    }

    //일정 단건조회
    public ScheduleResponseDto getSchedule(Long id) {
        //id에 해당하는 entity 찾기
        Schedule schedule = find(id);

        List<Register> list = registerRepository.findAllBySchedule_Id(id);

        //ResponseDto에 담아 반환(GET)
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule, list);
        return responseDto;
    }

    //일정 수정
    //Class에 Transactional이 걸려있기때문에 제거해도됐었음
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        //id에 해당하는 entity 찾기
        Schedule schedule = find(id);

        //해당일정 작성자의 id와 받아온 dto에 있는 id가 일치한다면 PUT
        if(requestDto.getUser_id() == schedule.getUser_id()) {
            schedule.update(requestDto);
        }
        else {
            throw new RuntimeException("작성자 id와 일치하지않습니다.");
        }

        //ResponseDto에 담아 반환
        ScheduleResponseDto responseDto = response(schedule);
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

    private ScheduleResponseDto response(Schedule schedule){
        return new ScheduleResponseDto(schedule);
    }
}
