package com.letsRoll.letsRoll.Member.entity;

import com.letsRoll.letsRoll.Project.entity.Project;
import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.User.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @NonNull
    @Column(length = 20)
    private String nickname;

    @NonNull
    @Column(length = 10)
    private String role;

    @Builder
    public Member(@NonNull User user, @NonNull Project project, @NonNull String nickname, @NonNull String role) {
        this.user = user;
        this.project = project;
        this.nickname = nickname;
        this.role = role;
    }
}
