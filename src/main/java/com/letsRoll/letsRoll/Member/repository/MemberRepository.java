package com.letsRoll.letsRoll.Member.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
