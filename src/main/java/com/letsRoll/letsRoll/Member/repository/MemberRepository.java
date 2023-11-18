package com.letsRoll.letsRoll.Member.repository;

import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);
    List<Member> findByProject(Project project);
    List<Member> findMembersByUser(User user);
}
