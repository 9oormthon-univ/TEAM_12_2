package com.letsRoll.letsRoll.Comment_Feeling.repository;

import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Comment_Feeling.entity.Feeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeelingRepository extends JpaRepository<Feeling, Long> {
    List<Feeling> findAllByComment(Comment comment);
}