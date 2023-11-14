package com.letsRoll.letsRoll.Comment_Feeling.repository;

import com.letsRoll.letsRoll.Comment_Feeling.entity.Comment;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.global.enums.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByGoalAndTodoAndTypeOrderByCreatedDateAsc(Goal goal, Todo todo, CommentType commentType);

}
