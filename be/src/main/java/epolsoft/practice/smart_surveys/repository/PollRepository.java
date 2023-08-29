package epolsoft.practice.smart_surveys.repository;

import epolsoft.practice.smart_surveys.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findAllBySurveyId(Long id);
}
