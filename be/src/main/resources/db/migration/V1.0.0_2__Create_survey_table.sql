-- Table: public.survey

CREATE TABLE IF NOT EXISTS public."survey"
(
    id bigserial PRIMARY KEY,
    survey_title varchar(50) NOT NULL,
    survey_description varchar(200) NOT NULL,
    survey_description_image bytea,
    anonymity boolean NOT NULL,
    author_id bigint NOT NULL REFERENCES public."users" (id) ON DELETE CASCADE,
    repeat_survey_interval interval NOT NULL,
    open_survey_date timestamp NOT NULL,
    close_survey_date timestamp NOT NULL,
    close_survey_iterable_date timestamp NOT NULL
);