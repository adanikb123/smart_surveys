package epolsoft.practice.smart_surveys.repository;

import epolsoft.practice.smart_surveys.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption,Long> {
    List<AnswerOption> findAllByPollId(Long id);
}
