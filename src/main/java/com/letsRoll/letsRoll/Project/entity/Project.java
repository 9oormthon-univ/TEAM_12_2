package com.letsRoll.letsRoll.Project.entity;

import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.global.enums.Mode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @Column(length = 100)
    private String description;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Mode mode;

    @NonNull
    private LocalDate startDate;

    @Setter
    private LocalDate finishDate;

    @NonNull
    private LocalDate endDate;

    @Builder
    public Project(@NonNull String title, String description, @NonNull String password, @NonNull Mode mode, @NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.password = password;
        this.mode = mode;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
