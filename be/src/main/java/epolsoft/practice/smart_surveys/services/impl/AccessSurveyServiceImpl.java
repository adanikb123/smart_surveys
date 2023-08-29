package epolsoft.practice.smart_surveys.services.impl;

import epolsoft.practice.smart_surveys.dto.AccessSurveyRequestDto;
import epolsoft.practice.smart_surveys.entity.AccessSurvey;
import epolsoft.practice.smart_surveys.entity.Survey;
import epolsoft.practice.smart_surveys.entity.User;
import epolsoft.practice.smart_surveys.mapper.AccessSurveyMapper;
import epolsoft.practice.smart_surveys.repository.AccessSurveyRepository;
import epolsoft.practice.smart_surveys.services.AccessSurveyService;
import epolsoft.practice.smart_surveys.services.SurveyService;
import epolsoft.practice.smart_surveys.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessSurveyServiceImpl implements AccessSurveyService {

    @Autowired
    private AccessSurveyRepository accessSurveyRepository;

    @Autowired
    private AccessSurveyMapper accessSurveyMapper;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private SurveyService surveyService;

    @Override
    @Transactional
    public AccessSurvey createAccessSurvey(AccessSurveyRequestDto accessSurveyRequestDto) {
        AccessSurvey accessSurvey = accessSurveyMapper.toEntity(accessSurveyRequestDto);

        Long surveyId = accessSurveyRequestDto.getSurveyId();
        Survey survey = surveyService.getSurveyById(surveyId);
        accessSurvey.setSurvey(survey);

        Long userId = accessSurveyRequestDto.getUserId();
        User user = userService.getUserById(userId);
        accessSurvey.setUser(user);

        return accessSurveyRepository.save(accessSurvey);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccessSurvey> getAccessSurveysByUser(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((User)authentication.getPrincipal()).getId();

        return accessSurveyRepository.findAllByUserId(userId,pageable);
    }
}
