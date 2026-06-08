package com.ptit.grading.result.controller;

import com.ptit.grading.result.dto.response.GradingResultResponse;
import com.ptit.grading.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/{submissionId}")
    public ResponseEntity<GradingResultResponse> getResult(@PathVariable UUID submissionId) {
        return ResponseEntity.ok(resultService.getResult(submissionId));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<GradingResultResponse>> getAssignmentResults(
            @PathVariable UUID assignmentId) {
        return ResponseEntity.ok(resultService.getAssignmentResults(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradingResultResponse>> getStudentResults(
            @PathVariable UUID studentId) {
        return ResponseEntity.ok(resultService.getStudentResults(studentId));
    }
}
