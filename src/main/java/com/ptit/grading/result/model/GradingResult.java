package com.ptit.grading.result.model;

import com.ptit.grading.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "grading_results")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GradingResult extends BaseEntity {

    @Column(nullable = false, unique = true)
    private UUID submissionId;

    @Column(nullable = false)
    private UUID assignmentId;

    @Column(nullable = false)
    private UUID studentId;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private Double maxScore = 10.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private com.ptit.grading.common.model.SubmissionStatus status;

    @Column(columnDefinition = "TEXT")
    private String summaryLog;
}
