package com.capstone.tvchat.api.member.domain.entity;

import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import com.capstone.tvchat.api.member.domain.entity.enumerate.Authority;
import com.capstone.tvchat.common.BaseEntity.BaseEntity;
import com.capstone.tvchat.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_nickname")
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "use_yn")
    private Yn useYn;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Member(Long id, String email, String password, String nickname, Authority authority, List<Bookmark> bookmarkList, Yn useYn) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.bookmarkList = bookmarkList;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Member create(String email, String password, String nickname){
        return Member.of()
                .email(email)
                .password(password)
                .nickname(nickname)
                .useYn(Yn.Y)
                .authority(Authority.ROLE_USER)
                .build();
    }


    public void changePassword(String password) {
        this.password = password;
    }
}
