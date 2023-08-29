package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.AnswerOptionResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyAnswerResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyRequestDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyAnswerOptionMapper extends GeneralMapper<SurveyAnswerResponseDto, Survey, SurveyRequestDto> {
    default List<AnswerOptionResponseDto> answerOptionListToAnswerOptionDtoList(List<AnswerOption> source) {
        return Mappers.getMapper(AnswerOptionMapper.class).toResponseDtos(source);
    }

    default AnswerOptionResponseDto answerOptionToAnswerOptionDto(AnswerOption answerOption, int totalVotes) {
        return Mappers.getMapper(AnswerOptionMapper.class).toResponseDto(answerOption);
    }
}
