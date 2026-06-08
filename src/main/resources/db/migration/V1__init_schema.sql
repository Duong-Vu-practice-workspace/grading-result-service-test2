CREATE TABLE IF NOT EXISTS grading_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    submission_id UUID NOT NULL UNIQUE,
    assignment_id UUID NOT NULL,
    student_id UUID NOT NULL,
    score DOUBLE PRECISION NOT NULL,
    max_score DOUBLE PRECISION NOT NULL DEFAULT 10.0,
    status VARCHAR(20) NOT NULL,
    summary_log TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS scenario_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    grading_result_id UUID NOT NULL,
    scenario_id UUID NOT NULL,
    scenario_name TEXT,
    passed BOOLEAN NOT NULL,
    actual_status INT,
    actual_body TEXT,
    error_message TEXT,
    weight INT DEFAULT 1
);

CREATE INDEX IF NOT EXISTS idx_results_assignment ON grading_results(assignment_id);
CREATE INDEX IF NOT EXISTS idx_results_student ON grading_results(student_id);
CREATE INDEX IF NOT EXISTS idx_scenario_results_result ON scenario_results(grading_result_id);
