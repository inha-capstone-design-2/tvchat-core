package com.capstone.tvchat.api.program.domain.entity;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.common.domain.BaseEntity;
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

    @Builder
    public Program(Long id, String title, Channel channel) {
        this.id = id;
        this.title = title;
        this.channel = channel;
    }
}
