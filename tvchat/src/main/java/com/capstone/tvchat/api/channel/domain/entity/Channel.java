package com.capstone.tvchat.api.channel.domain.entity;

import com.capstone.tvchat.api.channel.domain.dto.request.ModifyChannelRequest;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Program> programList;

    @Builder
    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void modifyChannel(ModifyChannelRequest modifyChannelRequest) {
        this.name = modifyChannelRequest.getChannelName();
    }
}