package com.ptit.grading.result.service;

import com.ptit.grading.result.dto.request.GradingResultRequest;
import com.ptit.grading.result.dto.request.ScenarioResultRequest;
import com.ptit.grading.result.dto.response.GradingResultResponse;
import com.ptit.grading.result.dto.response.ScenarioResultDTO;
import com.ptit.grading.result.model.GradingResult;
import com.ptit.grading.result.model.ScenarioResult;
import com.ptit.grading.result.repository.GradingResultRepository;
import com.ptit.grading.result.repository.ScenarioResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultService {

    private final GradingResultRepository gradingResultRepository;
    private final ScenarioResultRepository scenarioResultRepository;

    @Transactional
    public void saveResult(GradingResultRequest request) {
        GradingResult result = GradingResult.builder()
                .submissionId(request.getSubmissionId())
                .assignmentId(request.getAssignmentId())
                .studentId(request.getStudentId())
                .score(request.getScore())
                .maxScore(request.getMaxScore() != null ? request.getMaxScore() : 10.0)
                .status(com.ptit.grading.common.model.SubmissionStatus.valueOf(request.getStatus()))
                .summaryLog(request.getSummaryLog())
                .build();
        result = gradingResultRepository.save(result);

        // Save scenario results
        if (request.getScenarioResults() != null) {
            for (ScenarioResultRequest srReq : request.getScenarioResults()) {
                scenarioResultRepository.save(
                    ScenarioResult.builder()
                        .gradingResultId(result.getId())
                        .scenarioId(srReq.getScenarioId())
                        .scenarioName(srReq.getScenarioName())
                        .passed(srReq.isPassed())
                        .actualStatus(srReq.getActualStatus())
                        .actualBody(srReq.getActualBody())
                        .errorMessage(srReq.getErrorMessage())
                        .weight(srReq.getWeight())
                        .build()
                );
            }
        }

        log.info("Result saved for submission {}: score={}/{}",
            request.getSubmissionId(), request.getScore(), request.getMaxScore());
    }

    @Transactional(readOnly = true)
    public GradingResultResponse getResult(UUID submissionId) {
        GradingResult result = gradingResultRepository.findBySubmissionId(submissionId);
        if (result == null) {
            throw new RuntimeException("Result not found for submission: " + submissionId);
        }
        return toResponse(result);
    }

    @Transactional(readOnly = true)
    public List<GradingResultResponse> getAssignmentResults(UUID assignmentId) {
        return gradingResultRepository.findByAssignmentIdOrderByCreatedAtDesc(assignmentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GradingResultResponse> getStudentResults(UUID studentId) {
        return gradingResultRepository.findByStudentIdOrderByCreatedAtDesc(studentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private GradingResultResponse toResponse(GradingResult result) {
        List<ScenarioResult> scenarioResults = scenarioResultRepository
                .findByGradingResultId(result.getId());

        List<ScenarioResultDTO> scenarioDTOs = scenarioResults.stream()
            .map(sr -> ScenarioResultDTO.builder()
                .scenarioId(sr.getScenarioId())
                .scenarioName(sr.getScenarioName())
                .passed(sr.isPassed())
                .actualStatus(sr.getActualStatus())
                .actualBody(sr.getActualBody())
                .errorMessage(sr.getErrorMessage())
                .weight(sr.getWeight())
                .build())
            .toList();

        return GradingResultResponse.builder()
                .submissionId(result.getSubmissionId())
                .assignmentId(result.getAssignmentId())
                .studentId(result.getStudentId())
                .score(result.getScore())
                .maxScore(result.getMaxScore())
                .status(result.getStatus().name())
                .summaryLog(result.getSummaryLog())
                .scenarioResults(scenarioDTOs)
                .build();
    }
}
