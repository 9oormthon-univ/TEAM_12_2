package com.letsRoll.letsRoll.entity.Goal;


import com.letsRoll.letsRoll.entity.BaseEntity;
import com.letsRoll.letsRoll.entity.Project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
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
    public Goal(@NonNull Project project, @NonNull String content, @NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        this.project = project;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
