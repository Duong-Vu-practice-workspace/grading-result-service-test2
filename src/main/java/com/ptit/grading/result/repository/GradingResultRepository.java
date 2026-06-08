package com.ptit.grading.result.repository;

import com.ptit.grading.result.model.GradingResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GradingResultRepository extends JpaRepository<GradingResult, UUID> {
    GradingResult findBySubmissionId(UUID submissionId);
    List<GradingResult> findByAssignmentIdOrderByCreatedAtDesc(UUID assignmentId);
    List<GradingResult> findByStudentIdOrderByCreatedAtDesc(UUID studentId);
}
