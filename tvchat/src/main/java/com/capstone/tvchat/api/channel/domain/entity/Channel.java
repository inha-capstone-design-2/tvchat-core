package com.capstone.tvchat.api.channel.domain.entity;

import com.capstone.tvchat.api.channel.domain.dto.request.ModifyChannelRequest;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.common.domain.BaseEntity;
import com.capstone.tvchat.common.domain.enums.UseYn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CHANNEL")
public class Channel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    @Column(name = "channel_name")
    private String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Program> programList;

    @Column(name = "use_yn")
    private UseYn useYn;

    @Builder
    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Channel create(String name) {
        return Channel.builder()
                .name(name)
                .build();
    }

    public void modifyChannel(ModifyChannelRequest modifyChannelRequest) {
        this.name = modifyChannelRequest.getChannelName();
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
