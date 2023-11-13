package com.letsRoll.letsRoll.entity.Todo;


import com.letsRoll.letsRoll.entity.BaseEntity;
import com.letsRoll.letsRoll.entity.Project.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEndManager extends BaseEntity {
    @Id
    @Column(name = "todoManager_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TodoEndManager(@NonNull Member member) {
        this.member = member;
    }
}
