package com.letsRoll.letsRoll.entity.Todo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoManager extends BaseEntity {
    @Id
    @Column(name = "todoManager_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member  member;

    @Builder
    public TodoManager(@NonNull Member member) {
        this.member = member;
    }
}
