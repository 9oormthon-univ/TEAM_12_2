package com.letsRoll.letsRoll.entity.Comment_Feeling;

import com.letsRoll.letsRoll.entity.BaseEntity;
import com.letsRoll.letsRoll.entity.Goal.Goal;
import com.letsRoll.letsRoll.entity.Project.Member;
import com.letsRoll.letsRoll.entity.Todo.Todo;
import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.comments.CommentType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @NonNull
    private CommentType type;

    @NonNull
    private String content;

    @Builder
    public Comment(@NonNull Member member, Goal goal, Todo todo, @NonNull CommentType type,@NonNull String content){
        this.member = member;
        this.goal = goal;
        this.todo = todo;
        this.type = type;
        this.content = content;
    }
}
