package epolsoft.practice.smart_surveys.services.impl;

import epolsoft.practice.smart_surveys.dto.UserVoteRequestDto;
import epolsoft.practice.smart_surveys.dto.UserVoteResponseDto;
import epolsoft.practice.smart_surveys.dto.AnswerOptionRequestDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.exceptions.NotFoundException;
import epolsoft.practice.smart_surveys.mapper.AnswerOptionMapper;
import epolsoft.practice.smart_surveys.repository.AnswerOptionRepository;
import epolsoft.practice.smart_surveys.services.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private AnswerOptionMapper answerOptionMapper;

    @Override
    @Transactional
    public AnswerOption createAnswerOption(AnswerOptionRequestDto answerOptionRequestDto) {
        AnswerOption answerOption = answerOptionMapper.toEntity(answerOptionRequestDto);
        return answerOptionRepository.save(answerOption);
    }

    @Override
    public AnswerOption getAnswerOptionById(Long id) {
        checkById(id);
        return answerOptionRepository.findById(id).get();
    }

    @Override
    public List<AnswerOption> getAnswersOptionByPollId(Long id) {
        checkById(id);
        return answerOptionRepository.findAllByPollId(id);
    }

    @Override
    public void checkAll(List<UserVoteResponseDto> answerOptions) throws NotFoundException {
        for (UserVoteResponseDto answerOption : answerOptions) {
            answerOptionRepository.existsById(answerOption.getAnswerOptionId());
        }
    }

    @Override
    public void checkById(Long id) throws NotFoundException {
        if (!answerOptionRepository.existsById(id)) {
            throw new NotFoundException("Не найден вариант ответа с таким id в базе данных");
        }
    }
}
