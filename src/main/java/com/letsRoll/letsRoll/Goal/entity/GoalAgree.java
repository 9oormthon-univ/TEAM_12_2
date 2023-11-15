package com.letsRoll.letsRoll.Goal.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.Member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
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
    @JsonBackReference
    private Goal goal;

    @NonNull
    @Column(columnDefinition = "boolean default false")
    @Setter
    private Boolean memberCheck = false;

    @Builder
    public GoalAgree(@NonNull Goal goal, @NonNull Member member) {
        this.goal = goal;
        this.member = member;
    }

}
