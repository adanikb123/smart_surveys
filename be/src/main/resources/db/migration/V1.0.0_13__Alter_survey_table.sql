CREATE TYPE public.time_enum AS ENUM ('SECOND', 'MINUTE', 'HOUR', 'DAY', 'WEEK', 'MONTH', 'YEAR');

ALTER TABLE IF EXISTS public."survey"
DROP COLUMN repeat_survey_interval CASCADE,
ADD time_amount integer NOT NULL DEFAULT 1,
ADD time_type public.time_enum NOT NULL DEFAULT 'WEEK';