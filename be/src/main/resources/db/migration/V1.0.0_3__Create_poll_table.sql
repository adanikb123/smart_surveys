-- Table: public.poll
CREATE TYPE public.poll_enum AS ENUM ('SINGLE', 'VARIABLE');

CREATE TABLE IF NOT EXISTS public."poll"
(
    id bigserial primary key,
    survey_id bigint REFERENCES public."survey" (id) ON DELETE CASCADE,
    poll_image bytea,
    question_text varchar(200) NOT NULL,
    poll_type poll_enum NOT NULL
);