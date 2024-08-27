package com.sparta.jpaschedule.repository;

import com.sparta.jpaschedule.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findAllBySchedule_Id(Long schedule_id);
}
