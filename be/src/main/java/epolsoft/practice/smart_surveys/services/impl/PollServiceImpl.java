package epolsoft.practice.smart_surveys.services.impl;

import epolsoft.practice.smart_surveys.dto.AnswerOptionRequestDto;
import epolsoft.practice.smart_surveys.dto.PollRequestDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.entity.Poll;
import epolsoft.practice.smart_surveys.exceptions.NotFoundException;
import epolsoft.practice.smart_surveys.mapper.PollMapper;
import epolsoft.practice.smart_surveys.repository.PollRepository;
import epolsoft.practice.smart_surveys.services.AnswerOptionService;
import epolsoft.practice.smart_surveys.services.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {
    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollMapper pollMapper;

    @Autowired
    private AnswerOptionService answerOptionService;

    @Override
    @Transactional
    public Poll createPoll(PollRequestDto pollRequestDto) {

        Poll poll = pollMapper.toEntity(pollRequestDto);

        List<AnswerOptionRequestDto> answerOptionRequestDtoList = pollRequestDto.getAnswers();
        for (AnswerOptionRequestDto answerOptionRequestDto : answerOptionRequestDtoList) {
            AnswerOption answerOption = answerOptionService.createAnswerOption(answerOptionRequestDto);
            answerOption.setPoll(poll);
        }

        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> getPollsBySurveyId(Long id) {
        return pollRepository.findAllBySurveyId(id);
    }

    @Override
    public void checkById(Long id) throws NotFoundException {
        if (!pollRepository.existsById(id)) {
            throw new NotFoundException("Не найден вопрос с таким id в базе данных");
        }
    }
}