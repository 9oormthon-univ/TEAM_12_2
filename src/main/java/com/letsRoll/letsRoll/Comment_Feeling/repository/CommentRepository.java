package com.letsRoll.letsRoll.Comment_Feeling.repository;

import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
