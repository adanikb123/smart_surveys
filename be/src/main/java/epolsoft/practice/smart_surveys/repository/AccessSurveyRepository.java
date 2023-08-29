package epolsoft.practice.smart_surveys.repository;

import epolsoft.practice.smart_surveys.entity.AccessSurvey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessSurveyRepository extends JpaRepository<AccessSurvey,Long> {
    Page<AccessSurvey> findAllByUserId(Long id, Pageable pageable);
}
