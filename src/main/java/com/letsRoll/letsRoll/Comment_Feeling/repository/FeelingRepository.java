package com.letsRoll.letsRoll.Comment_Feeling.repository;

import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Feeling;
import com.letsRoll.letsRoll.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeelingRepository extends JpaRepository<Feeling, Long> {
    List<Feeling> findAllByComment(Comment comment);
    Optional<Feeling> findFeelingByCommentAndMember(Comment comment, Member member);

}