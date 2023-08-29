CREATE OR REPLACE FUNCTION updateVotedCount() RETURNS
    trigger AS '
        DECLARE count INTEGER;
        BEGIN
        SELECT COUNT(*)
        FROM user_vote
        WHERE user_vote.answer_option_id = NEW.answer_option_id
        INTO count;

        UPDATE answer_option
        SET voted_count = count
        WHERE id = NEW.answer_option_id;
        return NEW;
        END;
        ' LANGUAGE  plpgsql;

        CREATE TRIGGER execute_tranfer
        AFTER INSERT ON user_vote FOR EACH ROW
        EXECUTE PROCEDURE updateVotedCount();