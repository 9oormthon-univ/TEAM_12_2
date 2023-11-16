package com.letsRoll.letsRoll.Todo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.letsRoll.letsRoll.Goal.entity.Goal;
import com.letsRoll.letsRoll.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {
    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    @JsonBackReference
    private Goal goal;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoManager_id")
    private TodoManager todoManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoEndManager_id")
    @Setter
    private TodoEndManager todoEndManager;

    @NonNull
    private String content;

    @Setter
    private LocalDate startDate;

    @Setter
    private LocalDate finishDate;

    @NonNull //목표 날짜
    private LocalDate endDate;

    @NonNull
    @Column(columnDefinition = "boolean default false")
    @Setter
    private Boolean isComplete = false;

    @Builder
    public Todo(@NonNull Goal goal, @NonNull String content, @NonNull TodoManager todoManager, @NonNull LocalDate endDate) {
        this.goal = goal;
        this.content = content;
        this.todoManager = todoManager;
        this.endDate = endDate;
    }
}
