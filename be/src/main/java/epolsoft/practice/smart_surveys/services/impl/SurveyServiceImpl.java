package epolsoft.practice.smart_surveys.services.impl;

import epolsoft.practice.smart_surveys.dto.PollRequestDto;
import epolsoft.practice.smart_surveys.dto.SurveyRequestDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.entity.Poll;
import epolsoft.practice.smart_surveys.entity.Survey;
import epolsoft.practice.smart_surveys.entity.User;
import epolsoft.practice.smart_surveys.entity.UserVote;
import epolsoft.practice.smart_surveys.entity.enums.AnswerType;
import epolsoft.practice.smart_surveys.exceptions.NotFoundException;
import epolsoft.practice.smart_surveys.exceptions.ValidationException;
import epolsoft.practice.smart_surveys.mapper.SurveyMapper;
import epolsoft.practice.smart_surveys.repository.SurveyRepository;
import epolsoft.practice.smart_surveys.services.AccessSurveyService;
import epolsoft.practice.smart_surveys.services.AnswerOptionService;
import epolsoft.practice.smart_surveys.services.PollService;
import epolsoft.practice.smart_surveys.services.SurveyService;
import epolsoft.practice.smart_surveys.services.UserService;
import epolsoft.practice.smart_surveys.services.UserVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public Survey createSurvey(SurveyRequestDto surveyRequestDto) {

        Survey survey = surveyMapper.toEntity(surveyRequestDto);

        LocalDateTime openDate = survey.getOpenSurveyDate();
        LocalDateTime closeDate = survey.getCloseSurveyDate();
        LocalDateTime closeIterableDate = survey.getCloseSurveyIterableDate();
        if (!openDate.isBefore(closeDate)) {
            throw new ValidationException(
                    "Дата завершения опроса должна быть строго после даты начала"
            );
        } else if (closeIterableDate.isAfter(closeDate)) {
            throw new ValidationException(
                    "Дата завершения итерации опроса не должна быть после даты окончания самого опроса"
            );
        } else if (closeIterableDate.isBefore(openDate)) {
            throw new ValidationException(
                    "Дата завершения итерации опроса не должна быть до даты начала самого опроса"
            );
        }

        Integer timeAmount = survey.getTimeAmount();
        if (timeAmount == 0 && !closeIterableDate.isEqual(closeDate)) {
            throw new ValidationException(
                    "Если интервал равен 0, то дата завершения итерации опроса должна совпадать с датой окончания самого опроса"
            );
        }

        Long authorId = surveyRequestDto.getAuthorId();
        User author = userService.getUserById(authorId);
        survey.setAuthor(author);

        List<PollRequestDto> pollRequestDtoList = surveyRequestDto.getPolls();
        for (PollRequestDto pollRequestDto : pollRequestDtoList) {
            Poll poll = pollService.createPoll(pollRequestDto);
            poll.setSurvey(survey);
        }

        return surveyRepository.save(survey);
    }

    @Override
    @Transactional(readOnly = true)
    public Survey getSurveyById(Long id) {
        checkById(id);
        return surveyRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Survey> getAllSurveysByUser(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((User)authentication.getPrincipal()).getId();

        return surveyRepository.findAllByAuthorId(userId,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Survey getAllAnswersOptionById(Long id) {
        checkById(id);
        Survey survey = surveyRepository.findById(id).get();
        List<UserVote> userVotes = userVoteService.getAllVotes()
                .stream()
                .filter(userVote -> userVote.getText() != null)
                .collect(Collectors.toList());
        for (Poll poll : survey.getPolls()) {
            Set<AnswerOption> temp = new HashSet<>();
            for (AnswerOption answerOption : poll.getAnswers()) {
                if (answerOption.getAnswerType().equals(AnswerType.OPEN)) {
                    for (UserVote user : userVotes) {
                        AnswerOption userAnswer = new AnswerOption();
                        userAnswer.setAnswerType(AnswerType.OPEN);
                        userAnswer.setOption(user. getText());
                        userAnswer.setVotedCount((int) userVotes.stream()
                                .filter(userVote -> userVote.getText().equals(user.getText()))
                                .count());
                        userAnswer.setId(user.getAnswerOptionId());
                        userAnswer.setOptionImage(answerOption.getOptionImage());
                        userAnswer.setPoll(answerOption.getPoll());
                        temp.add(userAnswer);
                    }
                }
            }
            poll.getAnswers().removeIf(answerOption -> answerOption.getAnswerType().equals(AnswerType.OPEN));
            poll.getAnswers().addAll(temp);
        }
        return survey;
    }

    @Override
    public void updateSurvey(Survey survey, Long id) {
    }

    @Override
    public void deleteSurvey(Long id) {
    }

    @Override
    @Transactional(readOnly = true)
    public void checkById(Long id) throws NotFoundException {
        if (!surveyRepository.existsById(id)) {
            throw new NotFoundException("Не найден опрос с таким id в базе данных");
        }
    }
}
