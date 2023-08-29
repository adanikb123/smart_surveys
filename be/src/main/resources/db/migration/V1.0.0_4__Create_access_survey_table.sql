-- Table: access_survey

CREATE TABLE IF NOT EXISTS public."access_survey"
(
    id bigserial primary key,
    show_result boolean,
    survey_id bigint REFERENCES public."survey" (id) ON DELETE CASCADE,
    user_id bigint REFERENCES public."users" (id) ON DELETE CASCADE
);