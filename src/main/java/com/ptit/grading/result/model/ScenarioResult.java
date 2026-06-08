package com.ptit.grading.result.model;

import com.ptit.grading.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "scenario_results")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioResult extends BaseEntity {

    @Column(nullable = false)
    private UUID gradingResultId;

    @Column(nullable = false)
    private UUID scenarioId;

    private String scenarioName;

    @Column(nullable = false)
    private boolean passed;

    private Integer actualStatus;

    @Column(columnDefinition = "TEXT")
    private String actualBody;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private Integer weight;
}
