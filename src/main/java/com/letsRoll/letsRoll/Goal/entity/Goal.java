package com.letsRoll.letsRoll.Goal.entity;


import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.Project.entity.Project;
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
public class Goal extends BaseEntity {
    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
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

    @Builder
    public Goal(@NonNull Project project, @NonNull String title ,@NonNull String content, @NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        this.project = project;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
