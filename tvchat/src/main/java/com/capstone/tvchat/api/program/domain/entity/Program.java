package com.capstone.tvchat.api.program.domain.entity;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "PROGRAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    @Column(name = "program_title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Program(Long id, String title, Channel channel) {
        this.id = id;
        this.title = title;
        this.channel = channel;
    }

    public void modifyProgram(String title, Channel channel) {
        this.title = title;
        this.channel = channel;
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
