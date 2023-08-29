package epolsoft.practice.smart_surveys.services;

import epolsoft.practice.smart_surveys.dto.UserVoteRequestDto;
import epolsoft.practice.smart_surveys.dto.UserVoteResponseDto;
import epolsoft.practice.smart_surveys.dto.AnswerOptionRequestDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.exceptions.NotFoundException;

import java.util.List;

public interface AnswerOptionService {
    AnswerOption createAnswerOption(AnswerOptionRequestDto answerOptionRequestDto);
    AnswerOption getAnswerOptionById(Long id);
    List<AnswerOption> getAnswersOptionByPollId(Long id);
    void checkAll(List<UserVoteResponseDto> answerOptions) throws NotFoundException;
    void checkById(Long id) throws NotFoundException;
}
