package epolsoft.practice.smart_surveys.repository;

import epolsoft.practice.smart_surveys.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Page<Survey> findAllByAuthorId(Long id, Pageable pageable);
}

