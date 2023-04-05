package com.capstone.tvchat.api.member.repository;

import com.capstone.tvchat.api.member.domain.entity.Member;
import io.lettuce.core.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
}
