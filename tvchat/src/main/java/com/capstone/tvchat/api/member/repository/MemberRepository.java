package com.capstone.tvchat.api.member.repository;

import com.capstone.tvchat.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
