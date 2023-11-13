package com.letsRoll.letsRoll.Member.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberById(Long memberId);
}
