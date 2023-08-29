package epolsoft.practice.smart_surveys.services;

import epolsoft.practice.smart_surveys.dto.SurveyRequestDto;
import epolsoft.practice.smart_surveys.entity.Survey;
import epolsoft.practice.smart_surveys.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyService {
    Survey createSurvey(SurveyRequestDto surveyRequestDto);

    Survey getSurveyById(Long surveyId);

    Page<Survey> getAllSurveysByUser(Pageable pageable);

    Survey getAllAnswersOptionById(Long id);

    void checkById(Long id) throws NotFoundException;

    void updateSurvey(Survey survey, Long surveyId);

    void deleteSurvey(Long surveyId);
}
