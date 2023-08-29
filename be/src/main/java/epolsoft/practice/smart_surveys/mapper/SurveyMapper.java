package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.SurveyRequestDto;
import epolsoft.practice.smart_surveys.dto.SurveyResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyWithPollsDto;
import epolsoft.practice.smart_surveys.entity.Survey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyMapper extends GeneralMapper<SurveyResponseDto, Survey, SurveyRequestDto>{
    SurveyWithPollsDto toSurveyWithPollsDto(Survey survey);
}
