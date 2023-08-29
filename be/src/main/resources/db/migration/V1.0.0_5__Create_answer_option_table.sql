-- Table: answer_option

CREATE TYPE public.answer_type AS ENUM('OPEN', 'CLOSED');

CREATE TABLE IF NOT EXISTS public."answer_option"
(
    id bigserial primary key,
    poll_id bigint REFERENCES public."poll" (id) ON DELETE CASCADE,
    option_image bytea,
    option_text TEXT NOT NULL,
    voted_count integer NOT NULL,
    type public.answer_type NOT NULL
);
