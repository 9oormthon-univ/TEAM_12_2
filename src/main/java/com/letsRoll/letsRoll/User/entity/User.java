package com.letsRoll.letsRoll.User.entity;

import com.letsRoll.letsRoll.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String userName;

    @NonNull
    private String password;

    @Builder
    public User(@NonNull String userName,@NonNull String password) {
        this.userName = userName;
        this.password = password;
    }
}
