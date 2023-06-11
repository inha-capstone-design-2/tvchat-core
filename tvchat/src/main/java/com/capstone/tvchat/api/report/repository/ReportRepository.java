package com.capstone.tvchat.api.report.repository;

import com.capstone.tvchat.api.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCreatedBy(Long memberId);
}
