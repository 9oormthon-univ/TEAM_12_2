package com.letsRoll.letsRoll.entity.Goal;


import com.letsRoll.letsRoll.entity.BaseEntity;
import com.letsRoll.letsRoll.entity.Project.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalAgree extends BaseEntity {
    @Id
    @Column(name = "goalAgree_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @NonNull
    @Column(columnDefinition = "boolean default false")
    @Setter
    private Boolean memberCheck = false;

    @Builder
    public GoalAgree(@NonNull Goal goal) {
        this.goal = goal;
    }
}
