package com.ptit.grading.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioResultDTO {
    private UUID scenarioId;
    private String scenarioName;
    private boolean passed;
    private Integer actualStatus;
    private String actualBody;
    private String errorMessage;
    private Integer weight;
}
