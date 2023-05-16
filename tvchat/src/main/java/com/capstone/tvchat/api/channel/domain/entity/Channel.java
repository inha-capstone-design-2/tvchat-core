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

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn")
    private UseYn useYn;

    @Column(name = "description")
    private String description;

    @Builder
    public Channel(Long id, String name, UseYn useYn, String description) {
        this.id = id;
        this.name = name;
        this.useYn = useYn;
        this.description = description;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Channel create(String name, String description) {
        return Channel.builder()
                .name(name)
                .description(description)
                .useYn(UseYn.Y)
                .build();
    }

    public void modifyChannel(ModifyChannelRequest modifyChannelRequest) {
        this.name = modifyChannelRequest.getChannelName();
    }

    public void delete() {
        this.useYn = UseYn.N;
    }
}
