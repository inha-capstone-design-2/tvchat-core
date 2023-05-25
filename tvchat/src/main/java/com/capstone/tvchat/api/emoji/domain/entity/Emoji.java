package com.capstone.tvchat.api.emoji.domain.entity;

import com.capstone.tvchat.api.emoji.domain.dto.request.CreateEmojiRequest;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "EMOJI")
public class Emoji extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emoji_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "emoji_url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Emoji(Long id, Program program, String url, UseYn useYn) {
        this.id = id;
        this.program = program;
        this.url = url;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Emoji create(CreateEmojiRequest createEmojiRequest) {
        return Emoji.builder()
                .program(createEmojiRequest.getProgram())
                .url(createEmojiRequest.getUrl())
                .useYn(UseYn.Y)
                .build();
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
