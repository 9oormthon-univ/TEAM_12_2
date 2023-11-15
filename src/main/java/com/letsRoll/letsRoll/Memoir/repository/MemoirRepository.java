package com.letsRoll.letsRoll.Memoir.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Memoir.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoirRepository extends JpaRepository<Memoir, Long>{
    boolean existsByMember(Member member);
    List<Memoir> findByProjectId(Long projectId);


}
