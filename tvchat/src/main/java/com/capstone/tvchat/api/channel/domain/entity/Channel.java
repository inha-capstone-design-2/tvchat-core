package com.capstone.tvchat.api.channel.domain.entity;

import com.capstone.tvchat.common.BaseEntity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Channel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    @Column(name = "channel_name")
    private String name;

    @Builder
    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
