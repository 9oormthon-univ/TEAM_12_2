package com.letsRoll.letsRoll.Goal.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.letsRoll.letsRoll.Todo.entity.Todo;
import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.Project.entity.Project;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal extends BaseEntity {
    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private LocalDate startDate;

    @Setter
    private LocalDate finishDate;

    @NonNull
    private LocalDate endDate;

    @NonNull
    @Column(columnDefinition = "Boolean default false")
    @Setter
    private Boolean isComplete = false;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GoalAgree> goalAgreeList;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Todo> todoList;

    @Builder
    public Goal(@NonNull Project project, @NonNull String title, @NonNull String content, @NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        this.project = project;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public float getProgress() {
        if (todoList == null || todoList.isEmpty()) {
            return 0.0f;
        }

        long completedGoals = todoList.stream()
                .filter(Todo::getIsComplete)
                .count();

        return (float) completedGoals / todoList.size() * 100;
    }

}
