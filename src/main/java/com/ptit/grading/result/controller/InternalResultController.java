package com.ptit.grading.result.controller;

import com.ptit.grading.result.dto.request.GradingResultRequest;
import com.ptit.grading.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/results")
@RequiredArgsConstructor
public class InternalResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<Void> saveResult(@RequestBody GradingResultRequest request) {
        resultService.saveResult(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
