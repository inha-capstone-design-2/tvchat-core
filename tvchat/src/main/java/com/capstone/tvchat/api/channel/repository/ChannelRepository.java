package com.capstone.tvchat.api.channel.repository;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
