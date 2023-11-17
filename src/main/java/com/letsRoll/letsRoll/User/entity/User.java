package com.letsRoll.letsRoll.User.entity;

import com.letsRoll.letsRoll.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
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

    @NonNull
    private String nickname;

    @Transient  // 세션 ID는 DB에 저장하지 않음
    private String sessionId;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }


    @Builder
    public User(@NonNull String userName, @NonNull String password, @NonNull String nickname, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }
}
