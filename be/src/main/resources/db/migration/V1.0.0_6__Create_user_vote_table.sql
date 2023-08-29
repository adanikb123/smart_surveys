-- Table: user_vote

CREATE TABLE IF NOT EXISTS public."user_vote"
(
    id bigserial primary key,
    answer_option_id bigint REFERENCES public."answer_option" (id) ON DELETE CASCADE,
    user_id bigint REFERENCES public."users" (id) ON DELETE CASCADE,
    text varchar(200)
);