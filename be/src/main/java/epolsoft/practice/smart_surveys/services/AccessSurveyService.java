package epolsoft.practice.smart_surveys.services;

import epolsoft.practice.smart_surveys.dto.AccessSurveyRequestDto;
import epolsoft.practice.smart_surveys.entity.AccessSurvey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccessSurveyService {
    AccessSurvey createAccessSurvey(AccessSurveyRequestDto accessSurveyRequestDto);
    Page<AccessSurvey> getAccessSurveysByUser(Pageable pageable);
}
