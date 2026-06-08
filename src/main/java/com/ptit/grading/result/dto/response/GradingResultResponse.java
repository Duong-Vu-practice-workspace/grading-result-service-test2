package com.ptit.grading.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradingResultResponse {
    private UUID submissionId;
    private UUID assignmentId;
    private UUID studentId;
    private Double score;
    private Double maxScore;
    private String status;
    private String summaryLog;
    private List<ScenarioResultDTO> scenarioResults;
}
