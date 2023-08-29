package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.AccessSurveyRequestDto;
import epolsoft.practice.smart_surveys.dto.AccessSurveyResponseDto;
import epolsoft.practice.smart_surveys.entity.AccessSurvey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessSurveyMapper extends GeneralMapper<AccessSurveyResponseDto, AccessSurvey, AccessSurveyRequestDto> {
}

