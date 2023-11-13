package com.letsRoll.letsRoll.Comment_Feeling.entity;


import com.letsRoll.letsRoll.global.common.BaseEntity;
import com.letsRoll.letsRoll.Member.entity.Member;
import com.letsRoll.letsRoll.global.enums.Emoji;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feeling extends BaseEntity {
    @Id
    @Column(name = "feeling_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @NonNull
    @Setter
    private Emoji emoji;

    @Builder
    public Feeling(@NonNull Member member,@NonNull  Comment comment, @NonNull Emoji emoji) {
        this.member = member;
        this.comment = comment;
        this.emoji = emoji;
    }
}
