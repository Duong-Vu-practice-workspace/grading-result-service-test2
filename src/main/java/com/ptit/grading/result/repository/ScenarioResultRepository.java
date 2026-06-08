package com.ptit.grading.result.repository;

import com.ptit.grading.result.model.ScenarioResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScenarioResultRepository extends JpaRepository<ScenarioResult, UUID> {
    List<ScenarioResult> findByGradingResultId(UUID gradingResultId);
}
